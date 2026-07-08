package com.gamaza.examples.grpc.grpcclient.service.impl;

import com.gamaza.examples.grpc.DirectorDto;
import com.gamaza.examples.grpc.DirectorPostDto;
import com.gamaza.examples.grpc.DirectorRelationsDto;
import com.gamaza.examples.grpc.DirectorServiceGrpc;
import com.gamaza.examples.grpc.grpcclient.service.DirectorService;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    // Injected variables
    private final DirectorServiceGrpc.DirectorServiceBlockingStub stub;

    public DirectorServiceImpl(DirectorServiceGrpc.DirectorServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public DirectorDto save(DirectorPostDto postData) {
        return stub.save(postData);
    }

    @Override
    public List<DirectorDto> findAll() {
        return stub
                .findAll(Empty.getDefaultInstance())
                .getDirectorsList();
    }

    @Override
    public DirectorRelationsDto findById(String id) {
        return stub.findById(StringValue.of(id));
    }

}
