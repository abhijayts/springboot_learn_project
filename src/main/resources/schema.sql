drop table if exists "books";
drop table if exists "authors";

create table authors(
    id serial primary key,
    name text,
    age int
);

create table books(
    isbn text,
    title text,
    author_id int,
    constraint "fk_author_id" foreign key(author_id) references authors(id)
);