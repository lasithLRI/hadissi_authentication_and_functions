package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestSubCategoryDto;
import com.LRITechnologies.Ads_Site.service.SubCategoryService;
import com.LRITechnologies.Ads_Site.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> createSubcategoryUnit(@RequestBody RequestSubCategoryDto categoryDto){

        subCategoryService.createSubCategory(categoryDto);

        return new ResponseEntity<>(
                new StandardResponse(200,"Category created", categoryDto.getName()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public String findSubCategoryUnitById(@PathVariable String id){

    return "findSubCategoryUnitById";
    }

    @PutMapping(params = "id")
    public String updateSubcategoryUnit(
            @PathVariable String id,
            @RequestBody RequestSubCategoryDto categoryDto

    ){

        return categoryDto.getName();
    }

    @DeleteMapping("/{id}")
    public String deleteSubcategoryUnit(@PathVariable String id){
        return "deleteSubcategoryUnit";
    };

    @GetMapping(path = "/list", params = {"searchText", "page", "size"})
    public String findAllSubCategories(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return "findAllSubCategories";
    }

}
