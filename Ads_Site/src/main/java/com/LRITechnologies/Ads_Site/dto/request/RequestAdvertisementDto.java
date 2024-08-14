package com.LRITechnologies.Ads_Site.dto.request;

import com.LRITechnologies.Ads_Site.util.UploadedImageDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
    private Long subCategoryId;
    private BigDecimal price;
    private List<UploadedImageDetail> images;
}
