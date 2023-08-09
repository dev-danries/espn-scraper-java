package ries.dan.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("sports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sport {
    @Id
    private UUID id;
    private String espnUid;
    private String name;
    private String slug;
}
