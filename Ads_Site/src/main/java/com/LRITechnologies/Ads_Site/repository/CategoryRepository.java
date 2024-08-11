package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.Category;
import com.LRITechnologies.Ads_Site.entity.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "SELECT * FROM category WHERE category_name LIKE ?1 OR category_description LIKE ?1",nativeQuery = true)
    public List<Category> searchCategory(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM category WHERE category_name LIKE ?1 OR category_description LIKE ?1",nativeQuery = true)
    public Long countCategories(String searchText);

}
