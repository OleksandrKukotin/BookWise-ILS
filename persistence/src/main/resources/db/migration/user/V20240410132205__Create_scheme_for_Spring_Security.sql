create table if not exists profiles(
    profile_id serial,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    address varchar(255) not null,
    country_id int not null,
    city_name varchar(200),
    postcode varchar(10),
    phone_number varchar(20) not null unique,
    primary key (profile_id),
    foreign key (country_id) references countries(country_id)
);

create table users(
    username varchar(110) not null,
    password varchar(70) not null,
    profile_id int not null,
    enabled  boolean not null,
    primary key (username),
    foreign key (profile_id) references profiles(profile_id)
);

create table authorities(
    username  varchar(110) not null,
    authority varchar(70) not null,
    foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);
