package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.entity.UserRole;
import com.LRITechnologies.Ads_Site.repository.UserRoleRepo;
import com.LRITechnologies.Ads_Site.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    @Override
    public void initializeRoles() {
        if (userRoleRepo.count()==0){
            UserRole admin = new UserRole(1,"ADMIN","admin",null);
            UserRole cus = new UserRole(2,"CUSTOMER","customer",null);
            userRoleRepo.saveAll(List.of(admin,cus));
        }
    }
}
