package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.entity.*;
import com.LRITechnologies.Ads_Site.repository.*;
import com.LRITechnologies.Ads_Site.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private AdvertisementHasCategoriesRepository advertisementHasCategoriesRepository;

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Override
    public void createAdvertisement(RequestAdvertisementDto advertisementDto) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Optional<SubCategory> subCategory = subCategoryRepo.findById(advertisementDto.getSubCategory());


        User user = userRepo.findByUsername(username);



        Advertisement advertisement = Advertisement.builder()
                .id(advertisementDto.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
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
}
