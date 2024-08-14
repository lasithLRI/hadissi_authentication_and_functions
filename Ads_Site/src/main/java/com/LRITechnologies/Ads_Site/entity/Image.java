package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    public Image(String url, String id, Advertisement advertisement) {
        this.imageUrl = url;
        this.imageId = id;
        this.advertisement = advertisement;
    }
}
