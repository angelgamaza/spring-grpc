package com.gamaza.examples.grpc.server.service;

import com.gamaza.examples.grpc.*;
import com.gamaza.examples.grpc.server.entity.Film;
import com.gamaza.examples.grpc.server.mapper.FilmMapper;
import com.gamaza.examples.grpc.server.repository.FilmRepository;
import com.gamaza.examples.grpc.server.util.ExceptionUtils;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.grpc.server.constant.EntityConstants.FILM_OBJECT_NAME;

@GrpcService
public class FilmService extends FilmServiceGrpc.FilmServiceImplBase {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(FilmService.class);

    // Injected variables
    private final FilmRepository repository;
    private final FilmMapper mapper;

    /**
     * Constructor injection
     */
    public FilmService(FilmRepository repository, FilmMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(FilmPostDto postData, StreamObserver<FilmDto> response) {
        try {
            // Map the post object to an entity
            Film mappedEntity = mapper.asEntity(postData);
            // Save in database
            Film savedEntity = repository.save(mappedEntity);
            // Send the response
            response.onNext(
                    mapper.asDto(savedEntity)
            );
            // Complete the request
            response.onCompleted();

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            // Logs
            logger.error(ExceptionUtils.getCauseOrDefaultMessage(e));
            // Manage the exception
            Throwable throwable = ExceptionUtils.manageAlreadyExistsException(
                    FILM_OBJECT_NAME,
                    String.format("name=%s", postData.getName())
            );
            // Send the response
            response.onError(throwable);
        } catch (Exception e) {
            // Manage the exception
            Throwable throwable = ExceptionUtils.manageGenericException(
                    ExceptionUtils.getCauseOrDefaultMessage(e)
            );
            // Send the response
            response.onError(throwable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(StringValue id, StreamObserver<FilmRelationsDto> response) {
        // Get received value and check if it is valid
        String decodedId = id.getValue();
        UUID uuid;
        try {
            // Build the UUID to look for
            uuid = UUID.fromString(decodedId);
            // Retrieve from database
            Optional<Film> retrievedEntity = repository.findById(uuid);
            if (retrievedEntity.isPresent()) {
                // Send the response
                response.onNext(
                        mapper.asRelationsDto(
                                retrievedEntity.get()
                        )
                );
                // Complete the request
                response.onCompleted();
            } else {
                // Manage the exception
                Throwable throwable = ExceptionUtils.manageNotFoundException(FILM_OBJECT_NAME, String.format("id=%s", decodedId));
                // Send the response
                response.onError(throwable);
            }
        } catch (Exception e) {
            // Error messages
            String errorDescription = ExceptionUtils.getCauseOrDefaultMessage(e);
            // Manage the exception
            Throwable throwable = ExceptionUtils.manageIllegalArgumentException(errorDescription);
            // Send the response
            response.onError(throwable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findByName(StringValue name, StreamObserver<FilmRelationsDto> response) {
        // Get received value
        String decodedName = name.getValue();
        // Retrieve from database
        Optional<Film> retrievedEntity = repository.findByName(decodedName);
        if (retrievedEntity.isPresent()) {
            // Send the response
            response.onNext(
                    mapper.asRelationsDto(
                            retrievedEntity.get()
                    )
            );
            // Complete the request
            response.onCompleted();
        } else {
            // Manage the exception
            Throwable throwable = ExceptionUtils.manageNotFoundException(FILM_OBJECT_NAME, String.format("name=%s", decodedName));
            // Send the response
            response.onError(throwable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findAll(Empty empty, StreamObserver<FilmsList> response) {
        // Retrieve from database
        FilmsList result = FilmsList.newBuilder()
                .addAllFilms(
                        repository
                                .findAll()
                                .stream()
                                .map(mapper::asDto)
                                .toList()
                )
                .build();

        // Send the response
        response.onNext(result);
        // Complete the request
        response.onCompleted();
    }

    @Override
    @Transactional(readOnly = true)
    public void findAllByCountry(StringValue country, StreamObserver<FilmsList> response) {
        // Retrieve from database
        FilmsList result = FilmsList
                .newBuilder()
                .addAllFilms(
                        repository
                                .findAllByCountry(country.getValue())
                                .stream()
                                .map(mapper::asDto)
                                .toList()
                )
                .build();

        // Send the response
        response.onNext(result);
        // Complete the request
        response.onCompleted();
    }

    @Override
    @Transactional(readOnly = true)
    public void findAllByDirectorId(StringValue directorId, StreamObserver<FilmsList> response) {
        // Get received value and check if it is valid
        UUID uuid;
        try {
            // Build the UUID to look for
            uuid = UUID.fromString(directorId.getValue());
            // Retrieve from database
            FilmsList result = FilmsList
                    .newBuilder()
                    .addAllFilms(
                            repository
                                    .findAllByDirectorId(uuid)
                                    .stream()
                                    .map(mapper::asDto)
                                    .toList()
                    )
                    .build();

            // Send the response
            response.onNext(result);
            // Complete the request
            response.onCompleted();

        } catch (Exception e) {
            // Error messages
            String errorDescription = ExceptionUtils.getCauseOrDefaultMessage(e);
            // Manage the exception
            Throwable throwable = ExceptionUtils.manageIllegalArgumentException(errorDescription);
            // Send the response
            response.onError(throwable);
        }
    }

}
