package ries.dan.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "espn")
public class EspnApiConfigurationProperties {
    private String baseApi;
    private String sportsPath;
}
