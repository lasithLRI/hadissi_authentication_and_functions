package com.LRITechnologies.Ads_Site.service;

import com.LRITechnologies.Ads_Site.dto.request.RequestUserDto;

public interface UserService {
    public void signup(RequestUserDto userDto);
    public boolean verify(String type, String token);
}
