package com.LRITechnologies.Ads_Site.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAdvertisementDto {
    private Long id;
    private String title;
    private String description;
}
