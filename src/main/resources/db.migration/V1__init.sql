DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       username              VARCHAR(50) NOT NULL,
                       password              CHAR(80) NOT NULL,
                       first_name            VARCHAR(50) NOT NULL,
                       last_name             VARCHAR(50) NOT NULL,
                       email                 VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       name                  VARCHAR(50) DEFAULT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
                             user_id               INT(11) NOT NULL,
                             role_id               INT(11) NOT NULL,

                             PRIMARY KEY (user_id,role_id),

                             CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
                                 REFERENCES users (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                             CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
                                 REFERENCES roles (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
                            id	                INT(11) NOT NULL AUTO_INCREMENT,
                            title                 VARCHAR(255) NOT NULL,
                            description           VARCHAR(5000),
                            PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
                          id	                INT(11) NOT NULL AUTO_INCREMENT,
                          category_id           INT(11) NOT NULL,
                          title                 VARCHAR(255) NOT NULL,
                          price                 DECIMAL(8,2) NOT NULL,
                          create_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          update_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (id),
                          CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id)
                              REFERENCES categories (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders_statuses;

CREATE TABLE orders_statuses (
                                 id                    INT(11) NOT NULL AUTO_INCREMENT,
                                 title                 VARCHAR(50) DEFAULT NULL,
                                 PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS delivery_addresses;

CREATE TABLE delivery_addresses (
                                    id	                INT(11) NOT NULL AUTO_INCREMENT,
                                    user_id               INT(11) NOT NULL,
                                    address               VARCHAR(500) NOT NULL,
                                    PRIMARY KEY (id),
                                    CONSTRAINT FK_USER_ID_DEL_ADR FOREIGN KEY (user_id)
                                        REFERENCES users (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
                        id	                INT(11) NOT NULL AUTO_INCREMENT,
                        user_id               INT(11) NOT NULL,
                        price                 DECIMAL(8,2) NOT NULL,
                        delivery_price        DECIMAL(8,2) NOT NULL,
                        delivery_address_id   INT(11) NOT NULL,
                        status_id             INT(11) NOT NULL,
                        delivery_date         TIMESTAMP NOT NULL,
                        create_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        update_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (id),
                        CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
                            REFERENCES users (id),
                        CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)
                            REFERENCES orders_statuses (id),
                        CONSTRAINT FK_DELIVERY_ADDRESS_ID FOREIGN KEY (delivery_address_id)
                            REFERENCES delivery_addresses (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders_item;

CREATE TABLE orders_item (
                             id	                INT(11) NOT NULL AUTO_INCREMENT,
                             product_id            INT(11) NOT NULL,
                             order_id              INT(11) NOT NULL,
                             quantity              INT NOT NULL,
                             item_price            DECIMAL(8,2) NOT NULL,
                             total_price           DECIMAL(8,2) NOT NULL,
                             PRIMARY KEY (id),
                             CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
                                 REFERENCES orders (id),
                             CONSTRAINT FK_PRODUCT_ID_ORD_IT FOREIGN KEY (product_id)
                                 REFERENCES products (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
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



