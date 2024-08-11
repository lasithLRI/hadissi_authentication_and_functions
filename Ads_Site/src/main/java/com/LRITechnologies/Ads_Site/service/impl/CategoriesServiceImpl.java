package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedCategoryResponseDto;
import com.LRITechnologies.Ads_Site.entity.Category;
import com.LRITechnologies.Ads_Site.entity.SubCategory;
import com.LRITechnologies.Ads_Site.repository.AdvertisementHasCategoriesRepository;
import com.LRITechnologies.Ads_Site.repository.CategoryRepository;
import com.LRITechnologies.Ads_Site.repository.SubCategoryRepo;
import com.LRITechnologies.Ads_Site.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepo subCategoryRepo;


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

    @Override
    public ResponseCategoryDto findCategory(long id) {

        Optional<Category> optional = categoryRepository.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = optional.get();


        List<SubCategory> subCategories = subCategoryRepo.getSubCategoriesByCategoryId(category.getId());

        List<ResponseSubCategoryDto> subCategoryDtos = new ArrayList<>();

        subCategories.forEach(subCategory -> {
            subCategoryDtos.add(
                    new ResponseSubCategoryDto(
                            subCategory.getId(),subCategory.getName(),subCategory.getDescription()
                    )
            );

        });


        return new ResponseCategoryDto(
                category.getId(),
                category.getCategoryName(),
                category.getCategoryDescription(),
                subCategoryDtos
        );
    }

    @Override
    public void updateCategory(long id, RequestCategoryDto requestCategoryDto) {
        Optional<Category> optional=categoryRepository.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = optional.get();

        category.setCategoryName(requestCategoryDto.getCategoryName());
        category.setCategoryDescription(requestCategoryDto.getCategoryDescription());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        Optional<Category> optional=categoryRepository.findById(id);


        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        List<SubCategory> subCategories = subCategoryRepo.getSubCategoriesByCategoryId(optional.get().getId());

        List<Long> subCategoryIds = subCategories.stream()
                .map(SubCategory::getId)
                .collect(Collectors.toList());

        if (!subCategoryIds.isEmpty()) {
            subCategoryRepo.deleteAllById(subCategoryIds);
        }
        categoryRepository.delete(optional.get());
    }

    @Override
    public PaginatedCategoryResponseDto getCategories(String searchText, int page, int size) {

        searchText = "%"+searchText+"%";

        List<Category> categories = categoryRepository.searchCategory(searchText, PageRequest.of(page, size));

        long categoryCount = categoryRepository.countCategories(searchText);

        List<ResponseCategoryDto> responseCategories = new ArrayList<>();

        categories.forEach(category ->
                    responseCategories.add(

                            new ResponseCategoryDto(
                                    category.getId(),
                                    category.getCategoryName(),
                                    category.getCategoryDescription(),
                                    subCategoryRepo.getSubCategoriesByCategoryId(
                                            category.getId()).stream().map(subCategory ->
                                            new ResponseSubCategoryDto(
                                                    subCategory.getId(),
                                                    subCategory.getName(),
                                                    subCategory.getDescription()))
                                            .collect(Collectors.toList())
                            )
                    )
                );

        return new PaginatedCategoryResponseDto(categoryCount,responseCategories);
    }
}
