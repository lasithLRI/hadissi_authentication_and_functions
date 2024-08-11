package com.LRITechnologies.Ads_Site.dto.response;

import com.LRITechnologies.Ads_Site.entity.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCategoryDto {
    private long id;
    private String categoryName;
    private String categoryDescription;
    private List<ResponseSubCategoryDto> subCategoryHasCategory;
}
