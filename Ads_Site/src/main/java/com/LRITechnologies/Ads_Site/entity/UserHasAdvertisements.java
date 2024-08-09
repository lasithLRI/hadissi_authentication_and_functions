package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserHasAdvertisements {

    @EmbeddedId
    private UserHasAdvertisementKey id = new UserHasAdvertisementKey();

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @ManyToOne
    @MapsId("advertisement")
    @JoinColumn(name = "advertisement_id",nullable = false)
    private Advertisement advertisement;

    public UserHasAdvertisements(User user, Advertisement advertisement) {
        this.user = user;
        this.advertisement = advertisement;
    }
}
