package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.Image;
import com.LRITechnologies.Ads_Site.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "SELECT * FROM image WHERE image_id = ?1 AND advertisement_id = ?2", nativeQuery = true)
    public Image getImageByImageIdAndAdvertisementId(String imageId, long advertisementId);

    @Query(value = "SELECT * FROM image WHERE  advertisement_id = ?1", nativeQuery = true)
    public List<Image> getImagesForAdvertisement(long advertisementId);

}
