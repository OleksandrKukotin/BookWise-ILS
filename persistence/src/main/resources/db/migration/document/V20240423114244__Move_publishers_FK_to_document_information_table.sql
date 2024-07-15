alter table documents drop column publisher_id;
alter table document_information
    add column publisher_id int,
    add foreign key (publisher_id) references publishers(publisher_id);
