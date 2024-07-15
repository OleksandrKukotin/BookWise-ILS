alter table users
    drop column profile_id;
alter table profiles
    add column username varchar(110) unique,
    add foreign key (username) references users (username);
