package com.gamaza.examples.grpc.server.constant;

/**
 * Constants for Entity classes
 */
public final class EntityConstants {

    /*
     * Generic Entity constants
     */
    public static final String SCHEMA_PUBLIC_STRING = "public";

    /*
     * Entity names
     */
    public static final String DIRECTOR_ENTITY_NAME = "director";
    public static final String FILM_ENTITY_NAME = "film";

    /*
     * Entity fields
     */
    public static final String FIELD_ID_STRING = "id";
    public static final String FIELD_NAME_STRING = "name";
    public static final String FIELD_COUNTRY_STRING = "country";
    public static final String FIELD_FIRSTNAME_STRING = "firstname";
    public static final String FIELD_LASTNAME_STRING = "lastname";

    /*
     * Relationship fields
     */
    public static final String DIRECTOR_RELATIONSHIP_STRING = "director";
    public static final String FILMS_RELATIONSHIP_STRING = "films";

    /*
     * Unique constraints
     */
    public static final String UK_FILM_NAME_STRING = "uk_film_name";

    /*
     * Unique indexes
     */
    public static final String UINDEX_FILM_NAME_STRING = "ui_film_name";

    /*
     * Foreign keys
     */
    public static final String FK_FILM_DIRECTOR = "fk_film_director";

    /*
     * Object names
     */
    public static final String DIRECTOR_OBJECT_NAME = "Director";
    public static final String FILM_OBJECT_NAME = "Film";

    /*
     * Entity graphs
     */

    // Graphs
    public static final String DIRECTOR_ENTITY_GRAPH = "graph.director";
    public static final String FILM_ENTITY_GRAPH = "graph.film";

    /**
     * Private constructor
     */
    private EntityConstants() {
        throw new IllegalStateException("Could not instantiate EntityConstants class!");
    }

}
