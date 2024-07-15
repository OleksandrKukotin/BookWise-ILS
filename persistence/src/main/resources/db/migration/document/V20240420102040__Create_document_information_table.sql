create table if not exists document_information (
    information_id serial not null unique,
    document_type int not null,
    description text,
    publishing_year int,
    author_id int,
    category_id int,
    primary key (information_id),
    foreign key (document_type) references document_types(type_id),
    foreign key (author_id) references authors(author_id),
    foreign key (category_id) references categories(category_id)
);

alter table documents
    add column information_id int,
    add foreign key (information_id) references document_information(information_id),
    drop column document_type,
    drop column description,
    drop column publishing_year,
    drop column author_id,
    drop column category_id;
