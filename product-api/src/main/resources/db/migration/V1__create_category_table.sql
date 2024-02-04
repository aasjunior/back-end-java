create schema if not exists products;

create table if not exists products.category(
    id bigserial primary key,
    name varchar(100) not null
);
