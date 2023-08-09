package ries.dan.services;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ries.dan.dto.ESPNItemURLReference;
import ries.dan.dto.ESPNPaginationResponse;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class RestService {

    private final HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(connection ->
                    connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

    public final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

    private ESPNPaginationResponse fetchPaginationResponse(String url) throws ExecutionException, InterruptedException {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ESPNPaginationResponse.class)
                .doOnNext(resp -> log.info("Received response {}", resp))
                .block();
    }

    public List<ESPNItemURLReference> fetchESPNItemsFromPagination(String url) throws ExecutionException, InterruptedException {
        var initResponse = fetchPaginationResponse(url);
        if (initResponse != null) {
            int pageCount = initResponse.getPageCount();
            int pageIndex = initResponse.getPageIndex();
            List<ESPNItemURLReference> refItems = new ArrayList<>(initResponse.getItems());
            while (pageIndex < pageCount) {
                String nextPageUrl = url + "?page=" + pageIndex;
                var nextPageResponse = fetchPaginationResponse(nextPageUrl);
                refItems.addAll(nextPageResponse.getItems());
                pageIndex++;
            }
            return refItems;
        }
        return null;
    }

}
