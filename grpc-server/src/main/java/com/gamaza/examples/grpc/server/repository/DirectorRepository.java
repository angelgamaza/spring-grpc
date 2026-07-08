package com.gamaza.examples.grpc.server.repository;


import com.gamaza.examples.grpc.server.entity.Director;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.grpc.server.constant.EntityConstants.DIRECTOR_ENTITY_GRAPH;

@Repository
@NonNullApi
public interface DirectorRepository extends JpaRepository<Director, UUID> {

    /**
     * Find a {@link Director} by its id
     *
     * @param id The ID of the {@link Director}
     * @return An {@link Optional} of the {@link Director} found
     */
    @Override
    @EntityGraph(value = DIRECTOR_ENTITY_GRAPH)
    Optional<Director> findById(UUID id);

}
