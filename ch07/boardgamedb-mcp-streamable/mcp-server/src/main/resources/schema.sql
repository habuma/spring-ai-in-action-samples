drop table if exists game cascade;
create table game (
    id bigserial not null primary key,
    title varchar(255) not null,
    description varchar(1024),
    min_players int not null,
    max_players int not null,
    min_playing_time int not null,
    max_playing_time int not null
);