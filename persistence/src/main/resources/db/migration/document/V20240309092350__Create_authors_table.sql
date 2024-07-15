create table if not exists authors(
    author_id      serial,
    author_name    varchar(255) not null,
    author_surname varchar(255) not null,
    primary key (author_id)
)
