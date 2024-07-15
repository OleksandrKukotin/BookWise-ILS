create table if not exists users(
    user_id serial,
    nickname varchar(255) not null,
    email text not null unique,
    password varchar(60) not null,
    primary key (user_id)
)
