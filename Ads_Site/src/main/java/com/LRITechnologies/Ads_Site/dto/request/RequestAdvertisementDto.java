package com.LRITechnologies.Ads_Site.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAdvertisementDto {
    private Long id;
    private String title;
    private String description;
    private String contact;
    private String location;
    private long subCategoryId;
    private List<MultipartFile> images;
}
