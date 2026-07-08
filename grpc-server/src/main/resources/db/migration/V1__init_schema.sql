----------------------------------------------------
-- Extensions
----------------------------------------------------

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

----------------------------------------------------
-- Tables
----------------------------------------------------

-- Table: Director
CREATE TABLE director
(
    id        UUID DEFAULT uuid_generate_v4(),
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_director PRIMARY KEY (id)
);

-- Table: Film
CREATE TABLE film
(
    id       UUID DEFAULT uuid_generate_v4(),
    name     VARCHAR(255) NOT NULL,
    country  VARCHAR(255) NOT NULL,
    director UUID         NOT NULL,
    CONSTRAINT pk_film PRIMARY KEY (id),
    CONSTRAINT uk_film_name UNIQUE (name),
    CONSTRAINT fk_film_director FOREIGN KEY (director) REFERENCES director (id)
        MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Indexes: Film
CREATE UNIQUE INDEX ui_film_name ON film USING btree (name);