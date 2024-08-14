package com.LRITechnologies.Ads_Site.repository;

import com.LRITechnologies.Ads_Site.entity.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

    @Query(value = "SELECT * FROM sub_category WHERE name LIKE ?1 OR description LIKE ?1",nativeQuery = true)
    public List<SubCategory> searchSubCategories(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM sub_category WHERE name LIKE ?1 OR description LIKE ?1",nativeQuery = true)
    public Long countSubCategories(String searchText);

    @Query(value = "SELECT * FROM sub_category WHERE  LIKE ?1 ",nativeQuery = true)
    public List<SubCategory> getCategoriesBySubCategoryId(long id);
}
