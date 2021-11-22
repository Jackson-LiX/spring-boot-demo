create table if not exists user_access.user_access
(
    user_id   bigserial
        constraint user_access_pk
            primary key,
    user_name text not null,
    password  text not null
);

create unique index user_access_user_id_uindex
    on user_access.user_access (user_id);

