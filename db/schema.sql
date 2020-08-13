create table if not exists users(
    id serial primary key,
    username varchar(255),
    password varchar(255),
    email varchar(255)
);

create table if not exists themes(
    id serial primary key,
    name varchar(255),
    description varchar(1023),
    created timestamp,
    author_id int references users(id)
);

create table if not exists posts(
    id serial primary key,
    name varchar(255),
    description varchar(1023),
    created timestamp,
    author_id int references users(id),
    theme_id int references themes(id)
);

create table if not exists comments(
    id serial primary key,
    message varchar(10000),
    created timestamp,
    post_id int references posts(id),
    author_id int references users(id)
);