package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedSubcategoryResponseDto;
import com.LRITechnologies.Ads_Site.entity.Category;
import com.LRITechnologies.Ads_Site.entity.SubCategory;

import com.LRITechnologies.Ads_Site.repository.CategoryRepository;
import com.LRITechnologies.Ads_Site.repository.SubCategoryRepo;
import com.LRITechnologies.Ads_Site.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        Optional<Category> optionalMainCategory = categoryRepository.findById(categoryDto.getCategoryId());

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

        Optional<SubCategory> optional=subCategoryRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("SubCategory not found");
        }

        SubCategory subCategory = optional.get();

        return new ResponseSubCategoryDto(
                subCategory.getId(),subCategory.getName(),subCategory.getDescription()
        );
    }

    @Override
    public void deleteSubCategoryUnitById(long id) {
        Optional<SubCategory> optional=subCategoryRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("SubCategory not found");
        }

        subCategoryRepo.deleteById(optional.get().getId());
    }

    @Override
    public void updateSubCategoryUnit(long id, RequestSubCategoryDto categoryDto) {
        Optional<SubCategory> optional=subCategoryRepo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("SubCategory not found");
        }

        SubCategory subCategory = optional.get();
        subCategory.setName(categoryDto.getName());
        subCategory.setDescription(categoryDto.getDescription());

        subCategoryRepo.save(subCategory);
    }

    @Override
    public PaginatedSubcategoryResponseDto getSubCategoryUnits(String searchText, int page, int size) {

        searchText = "%"+searchText+"%";

        List<SubCategory> subCategories = subCategoryRepo.searchSubCategories(searchText, PageRequest.of(page, size));

        long subCategoryCount = subCategoryRepo.countSubCategories(searchText);

        List<ResponseSubCategoryDto> subCategoryDtos = new ArrayList<>();

        subCategories.forEach(
                subCategory -> {
                    subCategoryDtos.add(
                            new ResponseSubCategoryDto(
                                    subCategory.getId(),subCategory.getName(),subCategory.getDescription()
                            )
                    );
                }
        );

        return new PaginatedSubcategoryResponseDto(
            subCategoryCount,subCategoryDtos
        );
    }
}
