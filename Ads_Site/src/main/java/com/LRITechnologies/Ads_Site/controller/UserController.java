package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestUserDto;
import com.LRITechnologies.Ads_Site.service.UserService;
import com.LRITechnologies.Ads_Site.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> createAdvertisement(@RequestBody RequestUserDto requestUserDto) {
        userService.signup(requestUserDto);

        return new ResponseEntity<>(
                new StandardResponse(200, "User registered", requestUserDto.getUsername()),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/verify", params = {"type"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<StandardResponse> verifyUser(
            @RequestParam String type,
            @RequestHeader("Authorization") String token
    ){


        return new ResponseEntity<>(
                new StandardResponse(200, "User state", userService.verify(type, token)),
                HttpStatus.OK
        );
    }
}
