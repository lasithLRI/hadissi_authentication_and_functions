package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class AdvertisementHasCategoryKeys implements Serializable {

    @Column(name = "advertisement_id")
    private long advertisement;

    @Column(name = "category_id")
    private long category;
}
