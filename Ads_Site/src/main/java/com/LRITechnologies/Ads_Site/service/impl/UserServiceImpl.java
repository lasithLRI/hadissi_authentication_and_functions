package com.LRITechnologies.Ads_Site.service.impl;

import com.LRITechnologies.Ads_Site.dto.request.RequestUserDto;
import com.LRITechnologies.Ads_Site.entity.User;
import com.LRITechnologies.Ads_Site.entity.UserRole;
import com.LRITechnologies.Ads_Site.entity.UserRoleHasUser;
import com.LRITechnologies.Ads_Site.jwt.JwtConfig;
import com.LRITechnologies.Ads_Site.repository.UserRepo;
import com.LRITechnologies.Ads_Site.repository.UserRoleHasUserRepo;
import com.LRITechnologies.Ads_Site.repository.UserRoleRepo;
import com.LRITechnologies.Ads_Site.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.HashSet;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserRoleHasUserRepo userRoleHasUserRepo,
                           PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }


    @Override
    public void signup(RequestUserDto userDto) {

        UserRole userRole;
        if (userDto.getId()==1){
            userRole = userRoleRepo.findUserRoleByName("ADMIN");
        }else{
            userRole =  userRoleRepo.findUserRoleByName("CUSTOMER");
        }

        if (userRole==null){
            throw new RuntimeException("User role not found");
        }

        User user = new User(
                userDto.getId(),
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                true,
                true,
                true,
                true,
                null,
                null
        );

        UserRoleHasUser userData = new UserRoleHasUser(user,userRole);
        userRepo.save(user);
        userRoleHasUserRepo.save(userData);


    }

    @Override
    public boolean verify(String type, String token) {

        String realToken =
                token.replace(jwtConfig.getTokenPrefix(),"");

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(realToken);
        String username = claimsJws.getBody().getSubject();
        User selectedUser = userRepo.findByUsername(username);
        if(null==selectedUser){
            throw new RuntimeException("Username not found!");
        }

        for(UserRoleHasUser roleUser:selectedUser.getUserRoleHasUsers()){


            if (roleUser.getUserRole().getRoleName().equals(type)){
                return true;
            }
        }
        return false;
    }
}
