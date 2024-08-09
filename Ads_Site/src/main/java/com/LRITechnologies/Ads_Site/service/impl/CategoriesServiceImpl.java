package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.entity.Category;
import com.LRITechnologies.Ads_Site.repository.AdvertisementHasCategoriesRepository;
import com.LRITechnologies.Ads_Site.repository.CategoryRepository;
import com.LRITechnologies.Ads_Site.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void createCategory(RequestCategoryDto requestCategoryDto) {
        Category category = Category
                .builder()
                .id(requestCategoryDto.getId())
                .categoryName(requestCategoryDto.getCategoryName())
                .categoryDescription(requestCategoryDto.getCategoryDescription())
                .build();



        categoryRepository.save(category);
    }
}
