create table if not exists countries(
    country_id serial,
    country_name varchar(255) not null unique,
    primary key (country_id)
)
