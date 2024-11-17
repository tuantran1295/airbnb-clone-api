CREATE TABLE homestay
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    type INTEGER,
    host_id BIGINT,
    status INTEGER,
    phone_number TEXT,
    address TEXT,

    longitude  DOUBLE PRECISION,
    latitude DOUBLE PRECISION,
    geom TEXT,

    ward_id      INTEGER,
    district_id  INTEGER,
    province_id  INTEGER,

    images TEXT[],

    guests SMALLINT,
    bedrooms SMALLINT,
    bathrooms SMALLINT
);

CREATE TABLE "user"
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    fullname TEXT,
    type SMALLINT NOT NULL,
    status SMALLINT NOT NULL
);

CREATE TABLE profile
(
    user_id    BIGINT NOT NULL,
    avatar     TEXT,
    work       TEXT,
    about      TEXT,
    interests  TEXT[],
    status     SMALLINT
);

CREATE TABLE reserving
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    user_id BIGINT NOT NULL,
    homestay_id BIGINT NOT NULL,
    checkin_date DATE NOT NULL,
    checkout_date DATE NOT NULL,
    guests SMALLINT NOT NULL,
    status SMALLINT NOT NULL,

    subtotal numeric(12, 6),
    fee numeric(12, 6),
    discount numeric(12, 6),
    total_amount numeric NOT NULL,
    price_detail jsonb,
    currency TEXT NOT NULL,

    note TEXT,
    request_id TEXT NOT NULL

);

CREATE TABLE homestay_availability
(
    homestay_id BIGINT NOT NULL,
    date DATE NOT NULL,
    price numeric,
    status SMALLINT,
    primary key (homestay_id, date)
);

CREATE TABLE amenity
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL,
    icon TEXT NOT NULL
);

CREATE TABLE homestay_amenity
(
    homestay_id BIGINT NOT NULL CONSTRAINT homestay_amenity_homestay_id_fk REFERENCES homestay,
    amenity_id INTEGER NOT NULL CONSTRAINT homestay_amenity_amenity_id_fk REFERENCES amenity
);

CREATE TABLE ward
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    ward_name TEXT NOT NULL,
    place_id TEXT,
    district_id INTEGER
);

CREATE TABLE district
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    district_name TEXT NOT NULL,
    place_id TEXT,
    province_id INTEGER
);

CREATE TABLE province
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    province_name TEXT NOT NULL,
    place_id TEXT,
    country_id INTEGER
);
