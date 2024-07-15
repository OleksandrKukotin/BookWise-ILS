create table if not exists user_roles(
    entity_id serial,
    user_id int,
    role_id int,
    primary key (entity_id),
    foreign key (user_id) references users(user_id),
    foreign key (role_id) references roles(role_id)
)