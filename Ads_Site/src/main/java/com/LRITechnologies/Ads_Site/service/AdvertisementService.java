package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.entity.Advertisement;

public interface AdvertisementService {
    public void createAdvertisement(RequestAdvertisementDto advertisementDto);
}
