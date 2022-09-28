create table city (
    name text not null,
    region text not null,
    primary key (name, region)
);

create table station (
    name text primary key,
    tracks int,
    city_name text not null,
    city_region text not null,
    foreign key (city_name, city_region) references city (name, region)
);

create table train (
    train_nr int primary key,
    length int,
    start_station_name text not null references station (name),
    end_station_name text not null references station (name)
);

create table connected (
    from_station_name text not null references station (name),
    to_station_name text not null references station (name),
    train_nr int not null references train (train_nr),
    primary key (from_station_name, to_station_name, train_nr),
    arrival timestamp not null,
    departure timestamp not null
);
