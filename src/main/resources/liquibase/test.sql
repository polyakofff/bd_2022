-- liquibase formatted sql

-- changeset liquibase:1
drop table if exists Results;
drop table if exists Players;
drop table if exists Events;
drop table if exists Olympics;
drop table if exists Countries;

create table Countries (
                           name char(40),
                           country_id char(3) unique,
                           area_sqkm integer,
                           population integer
);

create table Olympics (
                          olympic_id char(7) unique,
                          country_id char(3),
                          city char(50),
                          year integer,
                          startdate date,
                          enddate date,
                          foreign key (country_id) references Countries(country_id)
);

create table Players (
                         name char(40),
                         player_id char(10) unique,
                         country_id char(3),
                         birthdate date,
                         foreign key (country_id) references Countries(country_id)
);

create table Events (
                        event_id char(7) unique,
                        name char(40),
                        eventtype char(20),
                        olympic_id char(7),
                        is_team_event integer check (is_team_event in (0, 1)),
                        num_players_in_team integer,
                        result_noted_in char(100),
                        foreign key (olympic_id) references Olympics(olympic_id)
);

create table Results (
                         event_id char(7),
                         player_id char(10),
                         medal char(7),
                         result float,
                         foreign key (event_id) references Events(event_id),
                         foreign key (player_id) references players(player_id)
);

insert into Countries values('Algeria', 'ALG', 2381741, 32853800);
