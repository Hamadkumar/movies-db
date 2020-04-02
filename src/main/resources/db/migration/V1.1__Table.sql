CREATE TABLE movies.movies
(
    u_id bigint NOT NULL,
    adult boolean,
    backdrop_path character varying(255) COLLATE pg_catalog."default",
    id integer,
    original_language character varying(255) COLLATE pg_catalog."default",
    original_title character varying(255) COLLATE pg_catalog."default",
    overview text COLLATE pg_catalog."default",
    popularity double precision,
    poster_path character varying(255) COLLATE pg_catalog."default",
    release_date character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    video boolean,
    vote_average integer,
    vote_count integer,
    CONSTRAINT movies_pkey PRIMARY KEY (u_id),
    CONSTRAINT uk_7ia0wlb9huk8qap7q5otg60hm UNIQUE (id)
);