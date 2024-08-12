package com.LRITechnologies.Ads_Site.dto.response;

import com.LRITechnologies.Ads_Site.entity.Image;
import com.LRITechnologies.Ads_Site.entity.SubCategory;
import com.LRITechnologies.Ads_Site.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAdvertisementDto {
    private Long id;
    private String title;
    private String description;
    private String contact;
    private String location;
    private List<Image> imagesUrls;
    private User creator;
    private SubCategory subCategory;
    private double price;

}
