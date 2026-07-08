package com.gamaza.examples.grpc.grpcclient.service.impl;

import com.gamaza.examples.grpc.FilmDto;
import com.gamaza.examples.grpc.FilmPostDto;
import com.gamaza.examples.grpc.FilmRelationsDto;
import com.gamaza.examples.grpc.FilmServiceGrpc;
import com.gamaza.examples.grpc.grpcclient.service.FilmService;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    // Injected variables
    private final FilmServiceGrpc.FilmServiceBlockingStub stub;

    public FilmServiceImpl(FilmServiceGrpc.FilmServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public FilmDto save(FilmPostDto postData) {
        return stub.save(postData);
    }

    @Override
    public List<FilmDto> findAll() {
        return stub
                .findAll(Empty.getDefaultInstance())
                .getFilmsList();
    }

    @Override
    public FilmRelationsDto findById(String id) {
        return stub.findById(StringValue.of(id));
    }

    @Override
    public FilmRelationsDto findByName(String name) {
        return stub.findByName(StringValue.of(name));
    }

    @Override
    public List<FilmDto> findAllByCountry(String country) {
        return stub
                .findAllByCountry(StringValue.of(country))
                .getFilmsList();
    }

    @Override
    public List<FilmDto> findAllByDirectorId(String directorId) {
        return stub
                .findAllByDirectorId(StringValue.of(directorId))
                .getFilmsList();
    }

}
