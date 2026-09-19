-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';
-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id varchar(255) NOT NULL,
	created_at timestamp(6) NOT NULL,
	email varchar(255) NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	"password" varchar(255) NULL,
	"role" varchar(255) NULL,
	update_at timestamp(6) NOT NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_created_at_not_null NOT NULL created_at,
	CONSTRAINT users_id_not_null NOT NULL id,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[]))),
	CONSTRAINT users_update_at_not_null NOT NULL update_at
);


-- public.activity definition

-- Drop table

-- DROP TABLE public.activity;

CREATE TABLE public.activity (
	id varchar(255) NOT NULL,
	additional_metrics json NULL,
	calories_burned int4 NULL,
	created_at timestamp(6) NOT NULL,
	duration int4 NULL,
	start_time timestamp(6) NULL,
	"type" varchar(255) NULL,
	update_at timestamp(6) NOT NULL,
	user_id varchar(255) NOT NULL,
	CONSTRAINT activity_created_at_not_null NOT NULL created_at,
	CONSTRAINT activity_id_not_null NOT NULL id,
	CONSTRAINT activity_pkey PRIMARY KEY (id),
	CONSTRAINT activity_type_check CHECK (((type)::text = ANY ((ARRAY['RUNNING'::character varying, 'WALKING'::character varying, 'CYCLING'::character varying, 'SWIMMING'::character varying, 'WEIGHT_TRAINING'::character varying, 'YOGA'::character varying, 'HIIT'::character varying, 'CARDIO'::character varying, 'STRETCHING'::character varying, 'OTHER'::character varying])::text[]))),
	CONSTRAINT activity_update_at_not_null NOT NULL update_at,
	CONSTRAINT activity_user_id_not_null NOT NULL user_id,
	CONSTRAINT fk_activity_user FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.recommendation definition

-- Drop table

-- DROP TABLE public.recommendation;

CREATE TABLE public.recommendation (
	id varchar(255) NOT NULL,
	created_at timestamp(6) NOT NULL,
	improvements json NULL,
	recommendation varchar(2000) NULL,
	safety json NULL,
	suggestions json NULL,
	"type" varchar(255) NULL,
	updated_at timestamp(6) NOT NULL,
	activity_id varchar(255) NOT NULL,
	user_id varchar(255) NOT NULL,
	CONSTRAINT recommendation_activity_id_not_null NOT NULL activity_id,
	CONSTRAINT recommendation_created_at_not_null NOT NULL created_at,
	CONSTRAINT recommendation_id_not_null NOT NULL id,
	CONSTRAINT recommendation_pkey PRIMARY KEY (id),
	CONSTRAINT recommendation_updated_at_not_null NOT NULL updated_at,
	CONSTRAINT recommendation_user_id_not_null NOT NULL user_id,
	CONSTRAINT fk_activity_activity FOREIGN KEY (activity_id) REFERENCES public.activity(id),
	CONSTRAINT fk_recommendation_user FOREIGN KEY (user_id) REFERENCES public.users(id)
);