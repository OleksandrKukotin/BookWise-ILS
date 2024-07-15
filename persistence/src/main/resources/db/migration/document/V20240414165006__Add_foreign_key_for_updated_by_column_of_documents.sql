alter table documents
    alter column updated_by type varchar(110),
    add foreign key (updated_by) references users(username);
