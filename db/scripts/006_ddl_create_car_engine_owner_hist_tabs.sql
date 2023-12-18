create table engine(
    id serial primary key
);

create table car(
    id serial primary key,
    engine_id int not null unique references engine(id)
);

create table owners(
    id serial primary key
);

create table history_owner(
    id serial primary key,
    owner_id int not null references owners(id),
    car_id int not null references car(id)
);

create table history(
    id serial primary key,
    startAI timestamp,
    endAt timestamp
);

ALTER TABLE auto_post
    ADD COLUMN IF NOT EXISTS
    car_id int REFERENCES car(id);