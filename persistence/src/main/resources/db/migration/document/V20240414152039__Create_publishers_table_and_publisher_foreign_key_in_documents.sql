create table if not exists publishers(
    publisher_id serial,
    publisher_name varchar(255),
    country_id int,
    primary key (publisher_id),
    foreign key (country_id) references countries(country_id)
);

alter table documents
    add column publisher_id int,
    add foreign key (publisher_id) references publishers(publisher_id);
