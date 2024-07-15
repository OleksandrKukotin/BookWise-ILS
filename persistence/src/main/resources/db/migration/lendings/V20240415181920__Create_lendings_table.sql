create table if not exists lendings(
    lending_id serial not null,
    document_id int not null,
    username varchar(110) not null,
    checkout_date timestamp not null,
    due_date timestamp not null,
    return_date timestamp,
    status varchar(50),
    primary key (lending_id),
    foreign key (document_id) references documents(document_id),
    foreign key (username) references users(username)
)
