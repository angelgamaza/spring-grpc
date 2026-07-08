package com.gamaza.examples.grpc.grpcclient.config;

import com.gamaza.examples.grpc.DirectorServiceGrpc;
import com.gamaza.examples.grpc.FilmServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcConfig {

    @Bean
    FilmServiceGrpc.FilmServiceBlockingStub filmServiceBlockingStub(GrpcChannelFactory channelFactory) {
        return FilmServiceGrpc.newBlockingStub(channelFactory.createChannel("local"));
    }

    @Bean
    DirectorServiceGrpc.DirectorServiceBlockingStub directorServiceBlockingStub(GrpcChannelFactory channelFactory) {
        return DirectorServiceGrpc.newBlockingStub(channelFactory.createChannel("local"));
    }

}
