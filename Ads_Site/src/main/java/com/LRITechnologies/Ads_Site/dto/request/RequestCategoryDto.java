package com.LRITechnologies.Ads_Site.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCategoryDto {
    private Long id;
    private String categoryName;
    private String categoryDescription;
}
