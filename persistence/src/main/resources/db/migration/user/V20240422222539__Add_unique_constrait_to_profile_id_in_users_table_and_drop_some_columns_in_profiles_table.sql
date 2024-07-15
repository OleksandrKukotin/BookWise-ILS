alter table users add unique (profile_id);
alter table profiles
    drop column city_name,
    drop column address,
    drop column phone_number,
    drop column postcode,
    add column email varchar(255) unique;
