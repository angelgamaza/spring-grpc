package com.gamaza.examples.grpc.server.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.gamaza.examples.grpc.server.constant.EntityConstants.*;
import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(
        schema = SCHEMA_PUBLIC_STRING,
        name = DIRECTOR_ENTITY_NAME
)
@NamedEntityGraph(
        name = DIRECTOR_ENTITY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode(value = FILMS_RELATIONSHIP_STRING)
        }
)
public class Director extends AbstractPersistable<UUID> {

    @Column(name = FIELD_FIRSTNAME_STRING, nullable = false)
    private String firstName;

    @Column(name = FIELD_LASTNAME_STRING, nullable = false)
    private String lastName;

    @OrderBy(value = "id DESC")
    @OneToMany(mappedBy = DIRECTOR_RELATIONSHIP_STRING, cascade = ALL, orphanRemoval = true, targetEntity = Film.class)
    private Set<Film> films = new HashSet<>();

    public Director() {
    }

    public Director(UUID id) {
        super.setId(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Director director)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(firstName, director.firstName) && Objects.equals(lastName, director.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName);
    }

}