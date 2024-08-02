package com.LRITechnologies.Ads_Site.jwt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsernamePasswordAuthenticationRequest {

    private String username;
    private String password;
}
