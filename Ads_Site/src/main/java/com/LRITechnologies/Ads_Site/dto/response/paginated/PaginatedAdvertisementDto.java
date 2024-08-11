package com.LRITechnologies.Ads_Site.dto.response.paginated;

import com.LRITechnologies.Ads_Site.dto.response.ResponseAdvertisementDto;
import com.LRITechnologies.Ads_Site.dto.response.ResponseSubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedAdvertisementDto {
    private long totalItems;
    private List<ResponseAdvertisementDto> dataList;
}
