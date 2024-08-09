package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.UserHasAdvertisementKey;
import com.LRITechnologies.Ads_Site.entity.UserHasAdvertisements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasAdvertisementsRepository extends JpaRepository<UserHasAdvertisements, UserHasAdvertisementKey> {
}
