package com.gamaza.examples.grpc.server.mapper;

import com.gamaza.examples.grpc.DirectorDto;
import com.gamaza.examples.grpc.DirectorPostDto;
import com.gamaza.examples.grpc.DirectorRelationsDto;
import com.gamaza.examples.grpc.server.entity.Director;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Director
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface DirectorMapper {

    Director asEntity(DirectorPostDto source);

    DirectorDto asDto(Director source);

    DirectorRelationsDto asRelationsDto(Director source);

}
