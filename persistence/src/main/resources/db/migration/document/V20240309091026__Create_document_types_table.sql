create table if not exists document_types(
    type_id serial,
    type_name varchar(255) not null unique,
    primary key (type_id)
)
