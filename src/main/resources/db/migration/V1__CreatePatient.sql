create table if not exists "patient"
(
    id         serial       not null,
    full_name  varchar(255) not null,
    birthday   date         not null,
    sex        varchar(20)  not null,
    country    varchar(50)  not null,
    state      varchar(100) not null,
    address    varchar(255) not null,

    primary key (id)
);