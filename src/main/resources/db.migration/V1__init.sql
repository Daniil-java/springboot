create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

CREATE TABLE privileges
(
        id serial,
        name varchar(50) not null,
        primary key (id)
);

CREATE TABLE roles_privileges
    (
        role_id int    not null,
        privilege_id bigint not null,

        primary key (role_id, privilege_id),
        foreign key (role_id) references roles (id),
        foreign key (privilege_id) references users (id)
);

insert into privileges (name)
values ('READ_PRODUCTS'),
       ('READ_USERS');

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_USER2'),
       ('ROLE_USER3'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');


insert into users (username, password, email)
values ('user', '$2a$12$RclJYmx96aOSxIb8kr43O.943gavSnIZR/uytshcLF6kh5bwuJo0G', 'user@gmail.com'),
       ('user2', '$2a$12$K/KL7weWeb4mFAPjGGnwOOwAUFIPicfyJ8dr6Dvoka1ww5dMuM/8O', 'user2@gmail.com'),
       ('user3', '$2a$12$q4q8QVYxngKJ1cprIvVjqOtrfbFHuc/m7XeDOQ3w/Z2/SizHGOoaO', 'user3@gmail.com');

insert into roles_privileges (role_id, privilege_id)
values (2, 1), (3, 2);

insert into users_roles (user_id, role_id)
values (1, 4), (2, 2), (3, 3);



