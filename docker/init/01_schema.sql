--
-- PostgreSQL database dump
--

\restrict EhXnaYYl5Yl0xxNnU4Tz2eeJiR4GCR1gLXn9bBw5Ew3I6Sq75kdyUTnddjWQEPk

-- Dumped from database version 18.1 (Postgres.app)
-- Dumped by pg_dump version 18.1 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: player_data; Type: TABLE; Schema: public; Owner: antoniomajstorovic
--

CREATE TABLE public.player_data (
    team character varying(50),
    "position" character varying(10),
    jersey_number integer,
    status character varying(20),
    first_name character varying(50),
    last_name character varying(50),
    birth_date date,
    height integer,
    weight integer,
    college character varying(255),
    years_exp integer,
    rookie_year integer,
    draft_club character varying(50),
    id bigint NOT NULL
);


ALTER TABLE public.player_data OWNER TO antoniomajstorovic;

--
-- Name: player_data_id_seq; Type: SEQUENCE; Schema: public; Owner: antoniomajstorovic
--

CREATE SEQUENCE public.player_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.player_data_id_seq OWNER TO antoniomajstorovic;

--
-- Name: player_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: antoniomajstorovic
--

ALTER SEQUENCE public.player_data_id_seq OWNED BY public.player_data.id;


--
-- Name: player_data id; Type: DEFAULT; Schema: public; Owner: antoniomajstorovic
--

ALTER TABLE ONLY public.player_data ALTER COLUMN id SET DEFAULT nextval('public.player_data_id_seq'::regclass);


--
-- Name: player_data player_data_pkey; Type: CONSTRAINT; Schema: public; Owner: antoniomajstorovic
--

ALTER TABLE ONLY public.player_data
    ADD CONSTRAINT player_data_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--



