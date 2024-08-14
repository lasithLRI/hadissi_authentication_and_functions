package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedSubcategoryResponseDto;
import com.LRITechnologies.Ads_Site.entity.Advertisement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertisementService {
    public void createAdvertisement(RequestAdvertisementDto advertisementDto);
    public ResponseAdvertisementDto findAdvertisementById(long id);
    public void updateAdvertisement(long id,RequestAdvertisementDto advertisementDto);
    public void deleteAdvertisement(long id);
    public PaginatedAdvertisementDto findAllAdvertisements(String searchText,int page,int size);
}
