create table auto_post(
    id   serial primary key,
    description varchar not null,
    created date not null,
    auto_user_id int
);