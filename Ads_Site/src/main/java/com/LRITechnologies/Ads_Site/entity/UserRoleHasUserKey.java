package com.LRITechnologies.Ads_Site.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserKey implements Serializable {
    @Column(name = "user_id")
    private long user;

    @Column(name = "role_id")
    private long userRole;
}
