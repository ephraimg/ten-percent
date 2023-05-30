-- Spring will use this file to create a schema
create table todo
(
    id varchar(36),
    user_id varchar(20),
    title varchar(50),
    completed boolean,
    primary key(id)
);
