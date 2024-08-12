package com.LRITechnologies.Ads_Site.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public List<String> uploadImage(List<MultipartFile> files);
    public void deleteImage(String imageId);
    public String uploadSingleImage(MultipartFile file);
    public void deleteListOfImages(List<String> imageIds);

}
