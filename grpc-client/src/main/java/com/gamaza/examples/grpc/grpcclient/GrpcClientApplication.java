package com.gamaza.examples.grpc.grpcclient;

import com.gamaza.examples.grpc.FilmRelationsDto;
import com.gamaza.examples.grpc.grpcclient.service.DirectorService;
import com.gamaza.examples.grpc.grpcclient.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication implements CommandLineRunner {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(GrpcClientApplication.class);

    // Injected variables
    private final DirectorService directorService;
    private final FilmService filmService;

    public GrpcClientApplication(DirectorService directorService, FilmService filmService) {
        this.directorService = directorService;
        this.filmService = filmService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            logger.info("---------------- GRPC Client ----------------");
            logger.info("Getting all Directors...");
            directorService
                    .findAll()
                    .forEach(current -> logger.info("[{}] {} {}", current.getId(), current.getFirstName(), current.getLastName()));

            logger.info("Getting all USA Films...");
            filmService
                    .findAll()
                    .forEach(current -> logger.info("[{}] {} [{}]", current.getId(), current.getName(), current.getCountry()));

            logger.info("Getting Film with name: Pulp Fiction...");
            FilmRelationsDto pulpFiction = filmService.findByName("Pulp Fiction");
            logger.info(
                    "[{}] {} [{}] from [{} {}]",
                    pulpFiction.getId(),
                    pulpFiction.getName(),
                    pulpFiction.getCountry(),
                    pulpFiction.getDirector().getFirstName(),
                    pulpFiction.getDirector().getLastName()
            );
            logger.info("---------------------------------------------");
            System.exit(0);

        } catch (Exception e) {
            logger.error("An error occurred during gRPC communications", e);
            System.exit(1);
        }
    }
}
