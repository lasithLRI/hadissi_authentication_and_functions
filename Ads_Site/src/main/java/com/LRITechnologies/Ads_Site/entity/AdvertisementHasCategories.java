package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity

@RequiredArgsConstructor
@Getter
@Setter
public class AdvertisementHasCategories {

    @EmbeddedId
    private AdvertisementHasCategoryKeys id = new AdvertisementHasCategoryKeys();

    @ManyToOne
    @MapsId("advertisement")
    @JoinColumn(name = "advertisement_id", nullable = false) // Assuming it should not be nullable
    private Advertisement advertisement;

    @ManyToOne
    @MapsId("category")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public AdvertisementHasCategories(Advertisement advertisement, Category category) {
        this.advertisement = advertisement;
        this.category = category;
    }
}