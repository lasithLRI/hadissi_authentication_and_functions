package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Advertisement advertisement;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
