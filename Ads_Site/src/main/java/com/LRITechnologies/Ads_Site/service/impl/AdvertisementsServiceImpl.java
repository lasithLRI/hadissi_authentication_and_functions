package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.controller.AdvertisementController;
import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedAdvertisementDto;
import com.LRITechnologies.Ads_Site.entity.*;
import com.LRITechnologies.Ads_Site.repository.*;
import com.LRITechnologies.Ads_Site.service.AdvertisementService;
import com.LRITechnologies.Ads_Site.service.ImageService;
import com.LRITechnologies.Ads_Site.util.UploadedImageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementsServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepo advertisementRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserHasAdvertisementsRepository userHasAdvertisementsRepository;

    @Autowired
    private ImageService imageService;


    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public void createAdvertisement(RequestAdvertisementDto advertisementDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();



        Optional<SubCategory> subCategory = subCategoryRepo.findById(advertisementDto.getSubCategoryId());

        User user = userRepo.findByUsername(username);

        List<Image> imageArrayList = new ArrayList<>();

        List<UploadedImageDetail> uploadedImageDetailList = advertisementDto.getImages();

        Advertisement advertisement = Advertisement.builder()
                .id(advertisementDto.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .contact(advertisementDto.getContact())
                .location(advertisementDto.getLocation())
                .images(imageArrayList)
                .price(advertisementDto.getPrice())
                .creator(user)
                .subCategory(subCategory.get())
                .build();

        advertisementRepo.save(advertisement);

        for (UploadedImageDetail imageDetail : uploadedImageDetailList) {
            Image image = new Image(imageDetail.getUrl(),imageDetail.getId(),advertisement);
            imageArrayList.add(image);
        }

        imageRepository.saveAll(imageArrayList);

        UserHasAdvertisements userHasAdvertisements = new UserHasAdvertisements(user,advertisement);

        userHasAdvertisementsRepository.save(userHasAdvertisements);


    }

    @Override
    public ResponseAdvertisementDto findAdvertisementById(long id) {
        Optional<Advertisement> optional=advertisementRepo.findById(id);



        if (optional.isEmpty()) {
            throw new RuntimeException("Advertisement not found");
        }

        Advertisement advertisement = optional.get();

        List<UploadedImageDetail> images = new ArrayList<>();

        for (Image image: advertisement.getImages()) {
            images.add(

                    new UploadedImageDetail(image.getImageUrl(),image.getImageId())

            );
        }



        return new ResponseAdvertisementDto(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getDescription(),
                advertisement.getContact(),
                advertisement.getLocation(),
                images,
                advertisement.getCreator().getFullName(),
                advertisement.getSubCategory().getName(),
                advertisement.getPrice()
        );
    }


    @Override
    public void updateAdvertisement(long id, RequestAdvertisementDto advertisementDto) {

        Optional<Advertisement> advertisement =advertisementRepo.findById(id);

        if (advertisement.isEmpty()) {
            throw new RuntimeException("Advertisement not found");
        }

        Advertisement existingAdvertisement = advertisement.get();




        List<Image> existingAdvertisementImagesImages = imageRepository.getImagesForAdvertisement(id);

        List<Image>  newAdvertisementImagesImages = new ArrayList<>();




        for (UploadedImageDetail imageDetail : advertisementDto.getImages()) {
            newAdvertisementImagesImages.add(
                    new Image(imageDetail.getUrl(),imageDetail.getId(),existingAdvertisement)
            );
        }

        for (Image image: existingAdvertisementImagesImages) {
            newAdvertisementImagesImages.removeIf(image1 -> image1.getImageId().equals(image.getImageId()));
        }
        existingAdvertisementImagesImages.addAll(newAdvertisementImagesImages);

        existingAdvertisement.setTitle(advertisementDto.getTitle());
        existingAdvertisement.setDescription(advertisementDto.getDescription());
        existingAdvertisement.setContact(advertisementDto.getContact());
        existingAdvertisement.setLocation(advertisementDto.getLocation());
        existingAdvertisement.setPrice(advertisementDto.getPrice());
        existingAdvertisement.setImages(existingAdvertisementImagesImages);
        existingAdvertisement.setSubCategory(subCategoryRepo.findById(advertisementDto.getSubCategoryId()).get());

        advertisementRepo.save(existingAdvertisement);

        imageRepository.saveAll(newAdvertisementImagesImages);


    }

    @Override
    public void deleteAdvertisement(long id) {

        Optional<Advertisement> optional=advertisementRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Advertisement advertisement = optional.get();

        List<Image> images = advertisement.getImages();
        List<String> deletedImages = new ArrayList<>();

        for (Image image : images) {
            deletedImages.add(image.getImageUrl());
        }

        imageService.deleteListOfImages(deletedImages,id);

        advertisementRepo.delete(advertisement);

    }

    @Override
    public PaginatedAdvertisementDto findAllAdvertisements(String searchText, int page, int size) {

        searchText = "%"+searchText+"%";

        List<Advertisement> advertisements = advertisementRepo.searchAdvertisement(searchText, PageRequest.of(page, size));

        for (Advertisement advertisement : advertisements) {
            System.out.println(advertisement.getTitle());
        };

        long advertisementCount = advertisementRepo.countAdvertisement(searchText);

        List<ResponseAdvertisementDto> responseAdvertisementDtos = new ArrayList<>();


        for (Advertisement advertisement : advertisements) {

            List<UploadedImageDetail> images = new ArrayList<>();

            for (Image image: advertisement.getImages()) {
                images.add(

                        new UploadedImageDetail(image.getImageUrl(),image.getImageId())

                );
            }

            responseAdvertisementDtos.add(
                    new ResponseAdvertisementDto(
                            advertisement.getId(),
                            advertisement.getTitle(),
                            advertisement.getDescription(),
                            advertisement.getContact(),
                            advertisement.getLocation(),
                            images,
                            advertisement.getCreator().getFullName(),
                            advertisement.getSubCategory().getName(),
                            advertisement.getPrice()
                    )
            );
        }

        return new PaginatedAdvertisementDto(
                advertisementCount,responseAdvertisementDtos
        );
    }
}
