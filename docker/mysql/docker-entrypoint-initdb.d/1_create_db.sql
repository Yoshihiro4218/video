CREATE DATABASE IF NOT EXISTS video;

create table actor(
  id int(11) PRIMARY KEY auto_increment,
  name varchar(255) NOT NULL,
  sex varchar(10),
  birthday int(8),
  height int(3),
  citizenship varchar(20),
  dead_flg tinyint(1) NOT NULL default false
);

create table movie(
  id int(11) PRIMARY KEY auto_increment,
  title varchar(255) NOT NULL,
  search_title varchar(255) NOT NULL,
  release_year int(4) NOT NULL,
  original_language varchar(20) NOT NULL,
  show_times int(11) NOT NULL,
  starring_num1 int(11),
  starring_num2 int(11),
  starring_num3 int(11),
  starring_num4 int(11),
  watched_flg tinyint(1) NOT NULL default false,
  FOREIGN KEY(starring_num1) REFERENCES actor(id),
  FOREIGN KEY(starring_num2) REFERENCES actor(id),
  FOREIGN KEY(starring_num3) REFERENCES actor(id),
  FOREIGN KEY(starring_num4) REFERENCES actor(id)
);

create table language_code(
  id int(11) PRIMARY KEY auto_increment,
  value varchar(255) NOT NULL UNIQUE
);

create table citizenship_code(
  id int(11) PRIMARY KEY auto_increment,
  value varchar(255) NOT NULL UNIQUE
);