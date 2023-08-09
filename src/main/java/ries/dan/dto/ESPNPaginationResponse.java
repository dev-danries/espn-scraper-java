package ries.dan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ESPNPaginationResponse {
    private int count;
    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private List<ESPNItemURLReference> items;
}
