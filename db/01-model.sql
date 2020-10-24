BEGIN TRANSACTION;

-- 
-- Países
-- 
DROP TABLE IF EXISTS countries CASCADE;
CREATE TABLE countries (
    pk bigserial NOT NULL, -- Autoincremental
    name_es varchar(255) NOT NULL, -- Nombre en español
    name_en varchar(255) NOT NULL, -- Nombre en inglés
    name_fr varchar(255) NOT NULL, -- Nombre en Frances
    iso2 varchar(2) NOT NULL, -- Código ISO2
    iso3 varchar(3) NOT NULL, -- Código ISO3
    phone_code varchar(255) NOT NULL, -- Código teléfonico del país
    UNIQUE (iso2),
    UNIQUE (iso3),
    UNIQUE (name_es, name_en, name_fr),
    PRIMARY KEY (pk)
);
CREATE UNIQUE INDEX ON countries(UPPER(TRIM(both FROM iso2)));
CREATE UNIQUE INDEX ON countries(UPPER(TRIM(both FROM iso3)));



--
-- Estaciones meteorologicas
--
DROP TABLE IF EXISTS stations CASCADE;
CREATE TABLE stations (
    pk bigserial NOT NULL, -- Autoincremental
    country_fk bigint NOT NULL, -- País
    icao varchar(255) NOT NULL, -- Código ICAO
    name varchar(255) NOT NULL, -- Nombre de la estación
    latitude double precision NOT NULL, -- Latitud
    longitude double precision NOT NULL, -- Longitud
    elevation int NOT NULL, -- Elevación
    FOREIGN KEY (country_fk) REFERENCES countries(pk) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE (icao),
    PRIMARY KEY (pk)
);
CREATE UNIQUE INDEX ON stations(UPPER(TRIM(both FROM icao)));
CREATE INDEX ON stations(country_fk);


-- 
-- Tabla que almace la información del clima
--
DROP TABLE IF EXISTS climates CASCADE;
CREATE TABLE climates (
    pk bigserial NOT NULL, -- Autoincremental
    station_fk bigint NOT NULL, -- Referencia a la estación que ingresa los datos
    wind_direction varchar(255) NOT NULL, -- Texto, por ejemplo '210 (SSW)'
    wind_speed numeric NOT NULL DEFAULT '0' CHECK (wind_speed >= 0), -- Expresado en km/h
    visibility numeric NOT NULL DEFAULT '0', -- Escalar
    temperature numeric NOT NULL DEFAULT '0', -- Expresado en ºC
    dewpoint numeric NOT NULL DEFAULT '0', -- Expresado en ºC
    pressure numeric NOT NULL DEFAULT '0', -- Expresado en hPa
    created timestamp NOT NULL DEFAULT NOW(),
    FOREIGN KEY (station_fk) REFERENCES stations(pk) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (pk)
);
CREATE INDEX ON climates(station_fk);

COMMIT;

