package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.auth.ApplicationUser;
import com.LRITechnologies.Ads_Site.entity.User;
import com.LRITechnologies.Ads_Site.entity.UserRoleHasUser;
import com.LRITechnologies.Ads_Site.repository.UserRepo;
import com.LRITechnologies.Ads_Site.repository.UserRoleHasUserRepo;
import com.LRITechnologies.Ads_Site.security.ApplicationUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.LRITechnologies.Ads_Site.security.ApplicationUserRole.ADMIN;
import static com.LRITechnologies.Ads_Site.security.ApplicationUserRole.CUSTOMER;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;

    public ApplicationUserServiceImpl(UserRepo userRepo, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User selectedUser = userRepo.findByUsername(username);

        if (selectedUser==null){
            throw new UsernameNotFoundException(String.format("username %s not found",username));
        }

        List<UserRoleHasUser> userRoles = userRoleHasUserRepo.findByUserId(selectedUser.getId());

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

//        for (UserRoleHasUser userRole:userRoles){
//            if (userRole.getUserRole().getRoleName().equals("ADMIN")){
//                grantedAuthorities.addAll(ADMIN.getSimpleGrantedAuthorities());
//            }
//            if (userRole.getUserRole().getRoleName().equals("CUSTOMER")){
//                grantedAuthorities.addAll(CUSTOMER.getSimpleGrantedAuthorities());
//            }
//        }

        for (UserRoleHasUser userRole : userRoles) {
            String roleName = userRole.getUserRole().getRoleName();
            ApplicationUserRole applicationUserRole = ApplicationUserRole.valueOf(roleName);
            grantedAuthorities.addAll(applicationUserRole.getSimpleGrantedAuthorities());
        }

        return new ApplicationUser(
                grantedAuthorities,
                selectedUser.getPassword(),
                selectedUser.getUsername(),
                selectedUser.isAccountNonExpired(),
                selectedUser.isAccountNonLocked(),
                selectedUser.isCredentialsNonExpired(),
                selectedUser.isEnabled()
        );
    }
}
