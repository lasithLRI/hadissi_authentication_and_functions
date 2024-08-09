package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserHasAdvertisementKey implements Serializable {
    @Column(name = "user_id")
    private long user;

    @Column(name = "advertisement_id")
    private long advertisement;
}
