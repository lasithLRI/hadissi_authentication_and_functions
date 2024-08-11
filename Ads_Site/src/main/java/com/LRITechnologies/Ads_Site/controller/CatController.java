package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestCategoryDto;
import com.LRITechnologies.Ads_Site.service.CategoriesService;
import com.LRITechnologies.Ads_Site.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CatController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> createCategory(@RequestBody RequestCategoryDto requestCategoryDto) {

        categoriesService.createCategory(requestCategoryDto);

        return new ResponseEntity<>(
                new StandardResponse(200, "Category created", requestCategoryDto.getCategoryName()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> findCategoryById(@PathVariable long id) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Category found",categoriesService.findCategory(id)),
                HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> updateCategoryById(
            @RequestParam long id,
            @RequestBody RequestCategoryDto requestCategoryDto
    ) {

        categoriesService.updateCategory(id, requestCategoryDto);
        return new ResponseEntity<>(
                new StandardResponse(200,"Category updated",requestCategoryDto.getCategoryName()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> deleteCategoryById(@PathVariable long id) {

        categoriesService.deleteCategory(id);

        return new ResponseEntity<>(
                new StandardResponse(200,"Category deleted",id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(value = "/list", params = {"searchText","page","size"})
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> findAllCategories(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return new ResponseEntity<>(
                new StandardResponse(200,"Category list received", categoriesService.getCategories(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
