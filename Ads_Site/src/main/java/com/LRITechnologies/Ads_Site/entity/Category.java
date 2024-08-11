package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {

    @Id
    private long id;
    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private Set<AdvertisementHasCategories> advertisementHasCategories;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private Set<SubCategory> subCategoryHasCategory;
}
