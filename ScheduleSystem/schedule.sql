show databases;
show variables like '%character%';
show variables like '%collation%';
create database if not exists schedule_system;
show create database schedule_system;
-- =====
set names utf8mb4;
set foreign_key_checks = 0;
-- =====
use schedule_system;
create table if not exists sys_schedule(
                                           sid int not null auto_increment,
                                           uid int null default null,
                                           title varchar(20) character set utf8mb4 collate utf8mb4_0900_ai_ci null default null,
    completed int(1) null default null,
    primary key (sid) using btree
    ) engine = InnoDB auto_increment = 1 character set = utf8mb4 collate = utf8mb4_0900_ai_ci row_format = dynamic;
-- ====
create table if not exists sys_user(
                                       uid int not null auto_increment,
                                       username varchar(10) character set utf8mb4 collate utf8mb4_0900_ai_ci null default null,
    user_pwd varchar(100) character set utf8mb4 collate utf8mb4_0900_ai_ci null default null,
    primary key (uid) using btree,
    unique index (username) using btree
    ) engine = InnoDB auto_increment = 1 character set = utf8mb4 collate = utf8mb4_0900_ai_ci row_format = dynamic;
insert into sys_user values
                         (1, 'ZhangSan', 'aff4584e06e60bd733b52e3e6133210e'),
                         (2, 'lisi', '79e38485feb879b31686d3a07357e35d');
select * from sys_user;
-- ==
set foreign_key_checks = 1;
show tables ;
