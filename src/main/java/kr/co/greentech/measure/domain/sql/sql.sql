
-- DB 생성
create database measure character set utf8 collate utf8_general_ci;

-- measure
create table measure (
   measure_id bigint not null,
    create_time datetime(6),
    mode varchar(255),
    name varchar(255),
    status varchar(255),
    primary key (measure_id)
) engine=InnoDB;


-- measure_item
create table measure_item (
   measure_item_id bigint not null,
    count integer not null,
    data varchar(255),
    elapsed_time varchar(255),
    measure_count integer not null,
    step_p bigint,
    member_id bigint,
    primary key (measure_item_id)
) engine=InnoDB;

