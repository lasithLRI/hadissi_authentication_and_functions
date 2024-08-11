package com.LRITechnologies.Ads_Site.dto.response.paginated;

import com.LRITechnologies.Ads_Site.dto.response.ResponseCategoryDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedCategoryResponseDto {
    private long totalItems;
    private List<ResponseCategoryDto> dataList;
}
