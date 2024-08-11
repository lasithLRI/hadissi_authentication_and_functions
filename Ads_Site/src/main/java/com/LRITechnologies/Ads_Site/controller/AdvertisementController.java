package com.LRITechnologies.Ads_Site.controller;

import com.LRITechnologies.Ads_Site.dto.request.RequestAdvertisementDto;
import com.LRITechnologies.Ads_Site.service.AdvertisementService;
import com.LRITechnologies.Ads_Site.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;


    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> createAdvertisement(@RequestBody RequestAdvertisementDto advertisementDto) {

        advertisementService.createAdvertisement(advertisementDto);

        return new ResponseEntity<>(
                new StandardResponse(200,"Advertisement created",advertisementDto.getTitle()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> findAdvertisementById(@PathVariable long id) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Advertisement found",advertisementService.findAdvertisementById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> updateAdvertisement(
            @RequestParam long id,
            @RequestBody RequestAdvertisementDto advertisementDto
    ) {
        advertisementService.updateAdvertisement(id, advertisementDto);
        return new ResponseEntity<>(
                new StandardResponse(200,"Advertisement updated",advertisementDto.getTitle()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> deleteAdvertisement(@PathVariable long id) {

        advertisementService.deleteAdvertisement(id);

        return new ResponseEntity<>(
                new StandardResponse(200,"Advertisement deleted","deleted"),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(value = "/list", params = {"searchText","page","size"})
    @PreAuthorize("hasAuthority('customer:write')")
    public ResponseEntity<StandardResponse> findAllAdvertisements(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Advertisement updated",advertisementService.findAllAdvertisements(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
