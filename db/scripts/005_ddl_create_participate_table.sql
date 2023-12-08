create table participate(
    id   serial primary key,
    auto_user_id int REFERENCES auto_user(id)    NOT NULL,
    auto_post_id int REFERENCES auto_post(id)    NOT NULL,
    unique (auto_user_id, auto_post_id)
);