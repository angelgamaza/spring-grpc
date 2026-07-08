package com.gamaza.examples.grpc.server.service;

import com.gamaza.examples.grpc.*;
import com.gamaza.examples.grpc.server.entity.Director;
import com.gamaza.examples.grpc.server.mapper.DirectorMapper;
import com.gamaza.examples.grpc.server.repository.DirectorRepository;
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

import static com.gamaza.examples.grpc.server.constant.EntityConstants.DIRECTOR_OBJECT_NAME;

@GrpcService
public class DirectorService extends DirectorServiceGrpc.DirectorServiceImplBase {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(DirectorService.class);

    // Injected variables
    private final DirectorRepository repository;
    private final DirectorMapper mapper;

    public DirectorService(DirectorRepository repository, DirectorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(DirectorPostDto postData, StreamObserver<DirectorDto> response) {
        try {
            // Map the post object to an entity
            Director mappedEntity = mapper.asEntity(postData);
            // Save in database
            Director savedEntity = repository.save(mappedEntity);
            // Send the response
            response.onNext(
                    mapper.asDto(savedEntity)
            );
            response.onCompleted();

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            logger.error(ExceptionUtils.getCauseOrDefaultMessage(e));
            Throwable throwable = ExceptionUtils.manageAlreadyExistsException(
                    DIRECTOR_OBJECT_NAME,
                    String.format("firstName=%s, lastName=%s", postData.getFirstName(), postData.getLastName())
            );
            response.onError(throwable);
        } catch (Exception e) {
            Throwable throwable = ExceptionUtils.manageGenericException(
                    ExceptionUtils.getCauseOrDefaultMessage(e)
            );
            response.onError(throwable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(StringValue id, StreamObserver<DirectorRelationsDto> response) {
        // Get received value and check if it is valid
        String decodedId = id.getValue();
        UUID uuid;
        try {
            // Build the UUID to look for
            uuid = UUID.fromString(decodedId);
            // Retrieve from database
            Optional<Director> retrievedEntity = repository.findById(uuid);
            if (retrievedEntity.isPresent()) {
                response.onNext(
                        mapper.asRelationsDto(
                                retrievedEntity.get()
                        )
                );
                response.onCompleted();
            } else {
                Throwable throwable = ExceptionUtils.manageNotFoundException(DIRECTOR_OBJECT_NAME, String.format("id=%s", decodedId));
                response.onError(throwable);
            }
        } catch (Exception e) {
            String errorDescription = ExceptionUtils.getCauseOrDefaultMessage(e);
            Throwable throwable = ExceptionUtils.manageIllegalArgumentException(errorDescription);
            response.onError(throwable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findAll(Empty empty, StreamObserver<DirectorsList> response) {
        // Retrieve from database
        DirectorsList result = DirectorsList.newBuilder()
                .addAllDirectors(
                        repository
                                .findAll()
                                .stream()
                                .map(mapper::asDto)
                                .toList()
                )
                .build();

        response.onNext(result);
        response.onCompleted();
    }

}
