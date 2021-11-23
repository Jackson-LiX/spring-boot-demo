create table if not exists demo.user_access
(
    user_id   bigserial
        constraint user_access_pk
            primary key,
    user_name text not null,
    password  text not null
);

create unique index if not exists user_access_user_id_uindex
    on demo.user_access (user_id);

