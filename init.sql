CREATE SCHEMA IF NOT EXISTS products;

CREATE TABLE products.category(
    id bigserial primary key,
    name varchar(100) not null
);

CREATE TABLE products.product(
    id bigserial primary key,
    product_identifier varchar not null,
    name varchar(100) not null,
    description varchar not null,
    price float not null,
    category_id bigint REFERENCES products.category(id)
);

INSERT INTO products.category(id, name) VALUES (1, 'Eletrônico');
INSERT INTO products.category(id, name) VALUES (2, 'Móveis');
INSERT INTO products.category(id, name) VALUES (3, 'Brinquedos');