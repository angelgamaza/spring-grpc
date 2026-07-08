package com.gamaza.examples.grpc.server.mapper;

import com.gamaza.examples.grpc.FilmDto;
import com.gamaza.examples.grpc.FilmPostDto;
import com.gamaza.examples.grpc.FilmRelationsDto;
import com.gamaza.examples.grpc.server.entity.Director;
import com.gamaza.examples.grpc.server.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Film
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface FilmMapper {

    @Mapping(source = "directorId", target = "director", qualifiedByName = "directorMapping")
    Film asEntity(FilmPostDto source);

    FilmDto asDto(Film source);

    FilmRelationsDto asRelationsDto(Film source);

    @Named("directorMapping")
    default Director directorMapping(UUID directorId) {
        return new Director(directorId);
    }

}
