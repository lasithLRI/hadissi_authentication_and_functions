package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.dto.request.RequestSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import com.LRITechnologies.Ads_Site.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    public void createSubCategory(RequestSubCategoryDto categoryDto);
    public ResponseSubCategoryDto findSubCategoryUnitById(long id);
    public void deleteSubCategoryUnitById(long id);
    public void updateSubCategoryUnit(long id,RequestSubCategoryDto categoryDto);
    public List<ResponseSubCategoryDto> getSubCategoryUnits(String searchText, int page, int size);
}
