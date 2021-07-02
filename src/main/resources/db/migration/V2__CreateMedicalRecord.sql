create table if not exists "medical_record"
(
    id          serial not null,
    patient_id  serial not null,
    added_at    date   not null,
    comment     text   not null,

    primary key (id),
    foreign key(patient_id) references "patient"(id)
);