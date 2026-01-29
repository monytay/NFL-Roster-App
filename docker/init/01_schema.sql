--
-- PostgreSQL database dump
--

\restrict y8oh6H284LkSdMBPJL6fOdcroaPOlhRyj8ZIGIkrX4cdFGRcKQK3GfY3reehnMG

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
-- Name: rosters_raw; Type: TABLE; Schema: public; Owner: antoniomajstorovic
--

CREATE TABLE public.rosters_raw (
    season text,
    team text,
    "position" text,
    depth_chart_position text,
    jersey_number text,
    status text,
    full_name text,
    first_name text,
    last_name text,
    birth_date text,
    height text,
    weight text,
    college text,
    gsis_id text,
    espn_id text,
    sportradar_id text,
    yahoo_id text,
    rotowire_id text,
    pff_id text,
    pfr_id text,
    fantasy_data_id text,
    sleeper_id text,
    years_exp text,
    headshot_url text,
    ngs_position text,
    week text,
    game_type text,
    status_description_abbr text,
    football_name text,
    esb_id text,
    gsis_it_id text,
    smart_id text,
    entry_year text,
    rookie_year text,
    draft_club text,
    draft_number text
);


ALTER TABLE public.rosters_raw OWNER TO antoniomajstorovic;

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

\unrestrict y8oh6H284LkSdMBPJL6fOdcroaPOlhRyj8ZIGIkrX4cdFGRcKQK3GfY3reehnMG

