package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@Embeddable
@EqualsAndHashCode
public class SubCategoryHasCategoryKey implements Serializable {
    @Column(name = "category_id")
    private long category;

    @Column(name = "subcategory_id")
    private long subCategory;
}
