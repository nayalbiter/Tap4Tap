drop schema if exists tap4tap;
CREATE SCHEMA tap4tap;
use tap4tap;

CREATE TABLE IF NOT EXISTS brewery
(
    brewery_id     UUID PRIMARY KEY,
    name           character varying(82) NOT NULL,
    brewery_type   character varying(10) NOT NULL,
    address_1      character varying(63),
    address_2      character varying(40),
    address_3      character varying(23),
    city           character varying(26) NOT NULL,
    state_province character varying(20) NOT NULL,
    postal_code    character varying(10) NOT NULL,
    country        character varying(14) NOT NULL,
    website_url    character varying(101),
    phone          character varying(17),
    longitude      numeric(18, 15),
    latitude       numeric(22, 15)
);
CREATE UNIQUE INDEX IF NOT EXISTS brewery_brewery_id_idx ON brewery (brewery_id);

create table if not exists user
(
    user_id serial primary key,
    username character varying(64) not null,
    hashed_password character varying(255),
    display_name character varying(36) not null
);


create table if not exists favorites_list
(
	list_id serial primary key,
	user_id bigint unsigned not null,
	constraint fk_favorites_list_user
		foreign key (user_id) references user (user_id)
		on delete cascade
);

create table if not exists favorite
(
	favorite_id serial primary key,
	brewery_id UUID not null,
	list_id bigint unsigned not null,
	constraint fk_favorite_brewery
		foreign key (brewery_id) references brewery (brewery_id)
		on delete cascade,
	constraint fk_favorite_favorites_list
		foreign key (list_id) references favorites_list (list_id)
		on delete cascade

);

# Data from Open Brewery DB
INSERT INTO
    tap4tap.brewery(brewery_id, name, brewery_type, address_1, address_2, address_3, city, state_province,
                    postal_code, country, phone, website_url, longitude, latitude)