package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.Advertisement;
import com.LRITechnologies.Ads_Site.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AdvertisementRepo extends JpaRepository<Advertisement, Long> {

    @Query(value = "SELECT * FROM advertisement WHERE title LIKE ?1 OR description LIKE ?1",nativeQuery = true)
    public List<Advertisement> searchAdvertisement(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM advertisement WHERE title LIKE ?1 OR description LIKE ?1",nativeQuery = true)
    public Long countAdvertisement(String searchText);
}
