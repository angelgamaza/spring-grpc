package com.gamaza.examples.grpc.server.repository;


import com.gamaza.examples.grpc.server.entity.Director;
import com.gamaza.examples.grpc.server.entity.Film;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.grpc.server.constant.EntityConstants.FILM_ENTITY_GRAPH;

@Repository
@NonNullApi
public interface FilmRepository extends JpaRepository<Film, UUID> {

    /**
     * Find a {@link Film} by its id
     *
     * @param id The ID of the {@link Film}
     * @return An {@link Optional} of the {@link Film} found
     */
    @Override
    @EntityGraph(value = FILM_ENTITY_GRAPH)
    Optional<Film> findById(UUID id);

    /**
     * Find a {@link Film} by its name
     *
     * @param name The name of the {@link Film} to find
     * @return An {@link Optional} of the {@link Film} found
     */
    @EntityGraph(value = FILM_ENTITY_GRAPH)
    Optional<Film> findByName(String name);

    /**
     * Find all {@link Film} by its country
     *
     * @param country The country of the {@link Film}
     * @return A {@link List} of the {@link Film} found
     */
    List<Film> findAllByCountry(String country);

    /**
     * Find all {@link Film} by its director id
     *
     * @param directorId The ID of the {@link Director}
     * @return A {@link List} of the {@link Film} found
     */
    @SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
    List<Film> findAllByDirectorId(UUID directorId);

}
