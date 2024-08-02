package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.entity.Advertisement;
import com.LRITechnologies.Ads_Site.repository.AdvertisementRepo;
import com.LRITechnologies.Ads_Site.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepo advertisementRepo;

    @Override
    public void createAdvertisement(RequestAdvertisementDto advertisementDto) {
        Advertisement advertisement = Advertisement.builder()
                .id(advertisementDto.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .build();

        advertisementRepo.save(advertisement);
    }
}
