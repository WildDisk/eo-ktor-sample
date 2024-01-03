create table if not exists users
(
    id       serial             not null,
    username varchar(50)        not null,
    password varchar(50)        not null,
    email    varchar(100)       not null,
    primary key (id)
);