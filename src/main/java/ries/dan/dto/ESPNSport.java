package ries.dan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ESPNSport {
    private String id;
    private String guid;
    private String uid;
    private String name;
    private String slug;
    private ESPNItemURLReference leagues;
}
