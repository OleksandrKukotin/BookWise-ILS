create table if not exists reservations(
    reservation_id serial,
    document_id int not null,
    username varchar(110) not null,
    reservation_date timestamp not null,
    expiration_date timestamp not null,
    status varchar(50),
    primary key (reservation_id),
    foreign key (document_id) references documents(document_id),
    foreign key (username) references users(username)
)
