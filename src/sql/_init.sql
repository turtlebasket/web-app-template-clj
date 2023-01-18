create table users (
    id serial,
    first_name text not null,
    last_name text not null,
    email text not null unique,
    pw_hash text not null,
    verified boolean not null
);
