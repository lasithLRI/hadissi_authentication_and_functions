package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.util.UploadedImageDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public List<UploadedImageDetail> uploadImage(List<MultipartFile> files);
    public void deleteImage(String imageId,long adId);
    public String uploadSingleImage(MultipartFile file);
    public void deleteListOfImages(List<String> imageIds,long adId);

}
