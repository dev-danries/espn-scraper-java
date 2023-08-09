package ries.dan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ESPNItemURLReference {
    @JsonProperty("$ref")
    private String reference;
}
