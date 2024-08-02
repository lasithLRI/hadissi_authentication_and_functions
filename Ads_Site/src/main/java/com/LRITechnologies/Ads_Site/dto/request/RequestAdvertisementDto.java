package com.LRITechnologies.Ads_Site.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAdvertisementDto {
    private Long id;
    private String title;
    private String description;
}
