package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedCategoryResponseDto;

import java.util.List;

public interface CategoriesService {

    public void createCategory(RequestCategoryDto requestCategoryDto);
    public ResponseCategoryDto findCategory(long id);
    public void updateCategory(long id,RequestCategoryDto requestCategoryDto);
    public void deleteCategory(long id);
    public PaginatedCategoryResponseDto getCategories(String searchText, int page, int pageSize);
}
