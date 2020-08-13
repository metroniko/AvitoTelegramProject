
CREATE TYPE public.botstate_type AS ENUM (
    'HELLO',
    'SUBSCRIBE_CAR',
    'UNSUBSCRIBE_THEME',
    'ASK_SOMETHING',
    'ASK_CARNAME',
    'ASK_MODEL',
    'ASK_PRICE',
    'PARSE_AUTO'
);


CREATE SEQUENCE public.car_carcase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE public.car_carcase (
    car_id bigint DEFAULT nextval('public.car_carcase_id_seq'::regclass) NOT NULL,
    car_name character varying NOT NULL,
    car_mark character varying NOT NULL,
    car_price bigint NOT NULL,
    user_id bigint
);

CREATE SEQUENCE public.car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.car_items (
    body_type character varying NOT NULL,
    brand character varying NOT NULL,
    color character varying NOT NULL,
    fuel_type character varying NOT NULL,
    image character varying NOT NULL,
    car_name character varying NOT NULL,
    price bigint NOT NULL,
    url_car character varying NOT NULL,
    user_id bigint,
    car_id bigint DEFAULT nextval('public.car_id_seq'::regclass) NOT NULL
);

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    bot_state integer
);


CREATE TABLE public.users_cars (
    user_id bigint NOT NULL,
    car_id bigint NOT NULL
);
