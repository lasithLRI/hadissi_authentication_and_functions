package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.service.CategoriesService;
import com.LRITechnologies.Ads_Site.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CatController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> createCategory(@RequestBody RequestCategoryDto requestCategoryDto) {

        // Consider adding validation for requestCategoryDto here

        categoriesService.createCategory(requestCategoryDto);

        return new ResponseEntity<>(
                new StandardResponse(200, "Category created", requestCategoryDto.getCategoryName()),
                HttpStatus.CREATED
        );
    }
}
