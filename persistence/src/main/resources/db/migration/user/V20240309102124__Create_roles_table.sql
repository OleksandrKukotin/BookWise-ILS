create table if not exists roles(
    role_id serial,
    role_name varchar(255) not null unique,
    primary key (role_id)
)
