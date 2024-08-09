package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import com.LRITechnologies.Ads_Site.entity.Category;
import com.LRITechnologies.Ads_Site.entity.SubCategory;

import com.LRITechnologies.Ads_Site.repository.CategoryRepository;
import com.LRITechnologies.Ads_Site.repository.SubCategoryRepo;
import com.LRITechnologies.Ads_Site.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createSubCategory(RequestSubCategoryDto categoryDto) {

        Optional<Category> optionalMainCategory = categoryRepository.findById(categoryDto.getId());

        if (optionalMainCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        SubCategory subCategory = SubCategory
                .builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .category(optionalMainCategory.get())
                .build();

        subCategoryRepo.save(subCategory);

    }

    @Override
    public ResponseSubCategoryDto findSubCategoryUnitById(long id) {
        return null;
    }

    @Override
    public void deleteSubCategoryUnitById(long id) {

    }

    @Override
    public void updateSubCategoryUnit(long id, RequestSubCategoryDto categoryDto) {

    }

    @Override
    public List<ResponseSubCategoryDto> getSubCategoryUnits(String searchText, int page, int size) {
        return List.of();
    }
}
