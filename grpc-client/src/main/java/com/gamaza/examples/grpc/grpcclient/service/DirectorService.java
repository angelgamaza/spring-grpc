package com.gamaza.examples.grpc.grpcclient.service;

import com.gamaza.examples.grpc.DirectorDto;
import com.gamaza.examples.grpc.DirectorPostDto;
import com.gamaza.examples.grpc.DirectorRelationsDto;

import java.util.List;

/**
 * Director Service interface for GRPC
 */
public interface DirectorService {

    /**
     * Save a Director
     *
     * @param postData The Director data
     * @return The Director saved
     */
    DirectorDto save(DirectorPostDto postData);

    /**
     * Find all Directors
     *
     * @return The found Directors list
     */
    List<DirectorDto> findAll();

    /**
     * Find a Director by ID
     *
     * @param id The Director ID to look for
     * @return The found Director
     */
    DirectorRelationsDto findById(String id);

}
