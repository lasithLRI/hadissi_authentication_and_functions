package com.LRITechnologies.Ads_Site.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSubCategoryDto {
    private long id;
    private String name;
    private String description;
    private long categoryId;
}
