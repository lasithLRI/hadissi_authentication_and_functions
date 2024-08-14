package com.LRITechnologies.Ads_Site.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("${application.cloudinary.cloudName}")
    private String cloudName;

    @Value("${application.cloudinary.apiKey}")
    private String apiKey;

    @Value("${application.cloudinary.apiSecret}")

    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret

        )
        );
    }
}
