package ries.dan.services;

import org.springframework.stereotype.Service;
import ries.dan.config.EspnApiConfigurationProperties;
import ries.dan.dao.Sport;
import ries.dan.dto.ESPNItemURLReference;
import ries.dan.dto.ESPNSport;
import ries.dan.repository.SportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SportService extends RestService {
    private final SportRepository sportRepository;
    private final EspnApiConfigurationProperties properties;
    private List<ESPNItemURLReference> itemRefs = new ArrayList<>();

    public SportService(final SportRepository sportRepository,
                        final EspnApiConfigurationProperties properties) {
        this.sportRepository = sportRepository;
        this.properties = properties;
    }

    public void fetchData() throws ExecutionException, InterruptedException {
        String baseUrl = properties.getBaseApi() + properties.getSportsPath();
        itemRefs = fetchESPNItemsFromPagination(baseUrl);
        itemRefs.parallelStream().forEach(itemRefs -> {
            var sport = webClient.get().uri(itemRefs.getReference())
                    .retrieve().bodyToMono(ESPNSport.class)
                    .block();
            System.out.println(sport);
        });
    }

}
