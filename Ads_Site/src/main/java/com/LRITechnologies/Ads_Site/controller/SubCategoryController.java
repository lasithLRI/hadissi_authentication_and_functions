package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestSubCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.paginated.PaginatedSubcategoryResponseDto;
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
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> subCategoryUnitById(@PathVariable long id){

    return new ResponseEntity<>(
            new StandardResponse(200,"Category found",subCategoryService.findSubCategoryUnitById(id)),
            HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> updateSubcategoryUnit(
            @PathVariable long id,
            @RequestBody RequestSubCategoryDto categoryDto

    ){

        subCategoryService.updateSubCategoryUnit(id, categoryDto);
        return new ResponseEntity<>(
                new StandardResponse(200,"Sub-category updated",categoryDto.getName()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> deleteSubcategoryUnit(@PathVariable long id){

        subCategoryService.deleteSubCategoryUnitById(id);

        return new ResponseEntity<>(
                new StandardResponse(204,"Sub-category deleted",id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/list", params = {"searchText", "page", "size"})
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> findAllSubCategories(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"subcategory search",subCategoryService.getSubCategoryUnits(searchText,page,size)),
                HttpStatus.OK
        );
    }

}
