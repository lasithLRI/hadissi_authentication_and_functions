package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.controller.AdvertisementController;
import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedSubcategoryResponseDto;
import com.LRITechnologies.Ads_Site.entity.*;
import com.LRITechnologies.Ads_Site.repository.*;
import com.LRITechnologies.Ads_Site.service.AdvertisementService;
import com.LRITechnologies.Ads_Site.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepo advertisementRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserHasAdvertisementsRepository userHasAdvertisementsRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AdvertisementHasCategoriesRepository advertisementHasCategoriesRepository;

    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private AdvertisementController advertisementController;

    @Override
    public void createAdvertisement(RequestAdvertisementDto advertisementDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Optional<SubCategory> subCategory = subCategoryRepo.findById(advertisementDto.getSubCategoryId());

        User user = userRepo.findByUsername(username);

        List<Image> images = new ArrayList<>();

        List<String> imageUrls = imageService.uploadImage(advertisementDto.getImages());

        for (String imageUrl : imageUrls) {
            images.add(
                    new Image(imageUrl)
            );
        }

        Advertisement advertisement = Advertisement.builder()
                .id(advertisementDto.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .contact(advertisementDto.getContact())
                .location(advertisementDto.getLocation())
                .images(images)
                .creator(user)
                .category(subCategory.get().getCategory())
                .subCategory(subCategory.get())
                .build();

        advertisementRepo.save(advertisement);

        UserHasAdvertisements userHasAdvertisements = new UserHasAdvertisements(user,advertisement);

        AdvertisementHasCategories advertisementHasCategories = new AdvertisementHasCategories(advertisement,subCategory.get().getCategory());

        userHasAdvertisementsRepository.save(userHasAdvertisements);

        advertisementHasCategoriesRepository.save(advertisementHasCategories);
    }

    @Override
    public ResponseAdvertisementDto findAdvertisementById(long id) {
        Optional<Advertisement> optional=advertisementRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Advertisement not found");
        }

        Advertisement advertisement = optional.get();

//        List<String> images = new ArrayList<>();
//
//        for (Image image: advertisement.getImages()) {
//            images.add(
//                    image.getImageUrl()
//            );
//        }

        return new ResponseAdvertisementDto(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getDescription(),
                advertisement.getContact(),
                advertisement.getLocation(),
                advertisement.getImages(),
                advertisement.getCreator(),
                advertisement.getSubCategory(),
                advertisement.getPrice()
        );
    }


    @Override
    public void updateAdvertisement(long id, RequestAdvertisementDto advertisementDto) {
        Optional<Advertisement> optional=advertisementRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Advertisement advertisement = optional.get();

        

        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setDescription(advertisementDto.getDescription());
        advertisement.setContact(advertisementDto.getContact());
        advertisement.setLocation(advertisementDto.getLocation());

        advertisementRepo.save(advertisement);
    }

    @Override
    public void deleteAdvertisement(long id) {

        Optional<Advertisement> optional=advertisementRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Advertisement advertisement = optional.get();

        advertisementRepo.delete(advertisement);

    }

    @Override
    public PaginatedAdvertisementDto findAllAdvertisements(String searchText, int page, int size) {

        searchText = "%"+searchText+"%";

        List<Advertisement> advertisements = advertisementRepo.searchAdvertisement(searchText, PageRequest.of(page, size));

        long advertisementCount = advertisementRepo.countAdvertisement(searchText);

        List<ResponseAdvertisementDto> responseAdvertisementDtos = new ArrayList<>();

        List<String> images = new ArrayList<>();

        advertisements.forEach(advertisement ->

                    responseAdvertisementDtos.add(
                            new ResponseAdvertisementDto(
                                    advertisement.getId(),
                                    advertisement.getTitle(),
                                    advertisement.getDescription(),
                                    advertisement.getContact(),
                                    advertisement.getLocation(),
                                    advertisement.getImages(),
                                    advertisement.getCreator(),
                                    advertisement.getSubCategory(),
                                    advertisement.getPrice()
                            )
                    )
                );

        return new PaginatedAdvertisementDto(
                advertisementCount,responseAdvertisementDtos
        );
    }
}
