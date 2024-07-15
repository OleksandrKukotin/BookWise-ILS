create table if not exists categories(
    category_id serial,
    category_name varchar(255) not null unique,
    primary key (category_id)
);

alter table documents
    add column category_id int,
    add constraint documents_category_id_fkey foreign key (category_id) references categories(category_id);
