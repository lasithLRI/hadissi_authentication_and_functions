package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE username=?1",nativeQuery = true)
    User findByUsername(String username);
}
