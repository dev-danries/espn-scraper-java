package ries.dan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ries.dan.services.SportService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/fetch")
@Slf4j
public class JobController {

    private final SportService sportService;

    public JobController(final SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/sports")
    public void fetchSports() throws ExecutionException, InterruptedException {
        log.info("Fetching Sports Data");
        sportService.fetchData();
        log.info("Completed Fetching Sports Data");
    }

}
