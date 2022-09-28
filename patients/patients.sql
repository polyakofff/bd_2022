create table station (
    stat_nr int primary key,
    name text
);

create table room (
    room_nr int primary key,
    beds int,
    stat_nr int not null references station (stat_nr)
);

create table station_personnel (
    pers_nr int primary key,
    name text,
    stat_nr int not null references station (stat_nr)
);

create table caregiver (
    pers_nr int primary key references station_personnel (pers_nr),
    qualification text
);

create table doctor (
    pers_nr int primary key references station_personnel (pers_nr),
    area text,
    rank text
);

create table patient (
    patient_nr int primary key,
    name text,
    disease text,
    admission_room_nr int references room (room_nr),
    admission_from timestamp,
    admission_to timestamp,
    doctor_pers_nr int references doctor (pers_nr)
);
