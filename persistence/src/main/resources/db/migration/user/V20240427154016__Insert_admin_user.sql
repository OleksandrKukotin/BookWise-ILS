insert into users (username, password, enabled)
values ('admin', '$2a$07$E4h78PxTkl0gbuHwDs2kG.XaiDS2ZP1f1IBYsfXfEUVuZuDvBXjoG', true);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
