package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.service.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<String> uploadImage(List<MultipartFile> files) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {

            try {

                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                imageUrls.add((String) uploadResult.get("secure_url"));

            }catch (IOException e){
                throw new RuntimeException("Failed to upload image to Cloudinary");
            }
        }

        return imageUrls;
    }

    @Override
    public void deleteImage(String imageId) {
        try {
            cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting image " + imageId, e);
        }
    }

    @Override
    public String uploadSingleImage(MultipartFile file) {
        String url;
        try {

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            url = (String) uploadResult.get("secure_url");


        }catch (IOException e){
            throw new RuntimeException("Image upload failed");
        }

        return url;
    }

    @Override
    public void deleteListOfImages(List<String> imageIds) {

        for (String imageId : imageIds) {
            deleteImage(imageId);
        }

    }
}
