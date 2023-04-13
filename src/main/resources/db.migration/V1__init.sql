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

insert into categories (title) values ('Еда');

insert into products (title, price, category_id)
values ('Молоко', 100.20, 1),
       ('Хлеб', 80.20, 1),
       ('Сыр', 90.20, 1),
       ('Масло', 320.00, 1);

create table categories
(
    id              long primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table products
(
    id              long primary key,
    title           varchar(255),
    price           numeric(8, 2) not null,
    category_id     bigint references categories (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Еда');

insert into products (title, price, category_id)
values ('Молоко', 100.20, 1),
       ('Хлеб', 80.20, 1),
       ('Сыр', 90.20, 1),
       ('Масло', 320.00, 1);

create table orders
(
    id              long primary key,
    username        varchar(255),
    total_price     numeric(8, 2),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      long primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);




