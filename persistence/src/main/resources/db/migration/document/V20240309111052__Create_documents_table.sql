create table if not exists documents(
    document_id serial,
    document_type int,
    document_title varchar(400),
    description text unique,
    publishing_year int not null,
    author_id int,
    country_id int,
    available_copies int not null,
    last_update_time timestamp not null default CURRENT_TIMESTAMP,
    updated_by int,
    primary key (document_id),
    foreign key (document_type) references document_types(type_id),
    foreign key (author_id) references authors(author_id),
    foreign key (country_id) references countries(country_id),
    foreign key (updated_by) references users(user_id)
)
