package com.gamaza.examples.grpc.server.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;
import java.util.UUID;

import static com.gamaza.examples.grpc.server.constant.EntityConstants.*;
import static jakarta.persistence.ConstraintMode.CONSTRAINT;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(
        schema = SCHEMA_PUBLIC_STRING,
        name = FILM_ENTITY_NAME,
        indexes = {
                @Index(name = UINDEX_FILM_NAME_STRING, columnList = FIELD_NAME_STRING, unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = UK_FILM_NAME_STRING, columnNames = FIELD_NAME_STRING)
        }
)
@NamedEntityGraph(
        name = FILM_ENTITY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode(value = DIRECTOR_RELATIONSHIP_STRING)
        }
)
public class Film extends AbstractPersistable<UUID> {

    @Column(name = FIELD_NAME_STRING, nullable = false, unique = true)
    private String name;

    @Column(name = FIELD_COUNTRY_STRING, nullable = false)
    private String country;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(
            name = DIRECTOR_RELATIONSHIP_STRING,
            referencedColumnName = FIELD_ID_STRING,
            foreignKey = @ForeignKey(name = FK_FILM_DIRECTOR, value = CONSTRAINT)
    )
    private Director director;

    public Film() {
    }

    public Film(UUID id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Film film)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(name, film.name) && Objects.equals(country, film.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, country);
    }

}