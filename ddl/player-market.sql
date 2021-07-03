create table player
(
	player_id int auto_increment
		primary key,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	age int not null,
	created_date timestamp default current_timestamp() null,
	last_modified_date timestamp default current_timestamp() null,
	last_modified_by varchar(64) default 'system' null,
	record_status int default 1 null
);

create table team
(
	team_id int auto_increment
		primary key,
	name varchar(128) not null,
	created_date timestamp default current_timestamp() null,
	last_modified_date timestamp default current_timestamp() null,
	last_modified_by varchar(64) default 'system' null,
	record_status int default 1 null
);

create table contract
(
	contract_id int auto_increment
		primary key,
	start_date date not null,
	end_date date null,
	team_fk int not null,
	player_fk int not null,
	created_date timestamp default current_timestamp() null,
	last_modified_date timestamp default current_timestamp() null,
	last_modified_by varchar(64) default 'system' null,
	record_status int default 1 null,
	constraint contract_player_fk
		foreign key (player_fk) references player (player_id),
	constraint contract_team_fk
		foreign key (team_fk) references team (team_id)
);

create table transfer
(
	transfer_id int auto_increment
		primary key,
	from_team_id int not null,
	to_team_id int not null,
	player_id int not null,
	transfer_fee float null,
	commission float not null,
	created_date timestamp default current_timestamp() null,
	last_modified_date timestamp default current_timestamp() null,
	last_modified_by varchar(64) default 'system' null,
	record_status int default 1 null
);

