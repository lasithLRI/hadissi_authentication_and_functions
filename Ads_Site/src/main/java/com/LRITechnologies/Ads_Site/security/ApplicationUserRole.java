package com.LRITechnologies.Ads_Site.security;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRole {

    CUSTOMER(Set.of(ApplicationUserPermission.CUSTOMER_WRITE, ApplicationUserPermission.CUSTOMER_READ)),
    ADMIN(Set.of(ApplicationUserPermission.ADMIN_READ, ApplicationUserPermission.ADMIN_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }


    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions= getPermissions()
                .stream().map(permission->new
                        SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
