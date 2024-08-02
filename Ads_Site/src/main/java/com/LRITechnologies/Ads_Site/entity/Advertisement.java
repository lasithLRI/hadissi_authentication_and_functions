package com.LRITechnologies.Ads_Site.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Advertisement {
    @Id
    private Long id;
    private String title;
    private String description;
}
