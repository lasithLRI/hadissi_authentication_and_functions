package com.LRITechnologies.Ads_Site.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Advertisement {
    @Id
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "advertisement")
    private Set<UserHasAdvertisements> userHasAdvertisements ;

    @OneToMany(mappedBy = "advertisement")
    private Set<AdvertisementHasCategories> advertisementHasCategories;
}
