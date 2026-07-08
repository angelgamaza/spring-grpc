package com.gamaza.examples.grpc.grpcclient.service;

import com.gamaza.examples.grpc.FilmDto;
import com.gamaza.examples.grpc.FilmPostDto;
import com.gamaza.examples.grpc.FilmRelationsDto;

import java.util.List;

/**
 * Film Service interface for GRPC
 */
public interface FilmService {

    /**
     * Save a Film
     *
     * @param postData The Film data
     * @return The Film saved
     */
    FilmDto save(FilmPostDto postData);

    /**
     * Find all Films
     *
     * @return The found Films list
     */
    List<FilmDto> findAll();

    /**
     * Find a Film by ID
     *
     * @param id The Film ID to look for
     * @return The found Film
     */
    FilmRelationsDto findById(String id);

    /**
     * Find a Film by name
     *
     * @param name The Film name to look for
     * @return The found Film
     */
    FilmRelationsDto findByName(String name);

    /**
     * Find all Films by its Country
     *
     * @param country The Country name
     * @return The found Films list
     */
    List<FilmDto> findAllByCountry(String country);

    /**
     * Find all Films by its Director ID
     *
     * @param directorId The Director's ID
     * @return The found Films list
     */
    List<FilmDto> findAllByDirectorId(String directorId);

}
