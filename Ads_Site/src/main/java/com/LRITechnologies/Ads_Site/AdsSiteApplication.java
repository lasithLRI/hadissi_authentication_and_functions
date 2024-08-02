package com.LRITechnologies.Ads_Site;

import com.LRITechnologies.Ads_Site.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdsSiteApplication implements CommandLineRunner {

	@Autowired
	private UserRoleService userRoleService;

	public static void main(String[] args) {
		SpringApplication.run(AdsSiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRoleService.initializeRoles();//----
	}
}
