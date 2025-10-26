create table market
(
    market_id      bigint        not null
        primary key,
    name           varchar(100)  not null,
    category       varchar(20)   null,
    address        varchar(255)  not null,
    phone          varchar(13)   null,
    description    varchar(100)  null,
    created_at     datetime(6)   null,
    deleted_at     datetime(6)   null,
    latitude       decimal(9, 6) null,
    longitude      decimal(9, 6) null,
    location_id    bigint        null,
    detail_address varchar(255)  not null,
    manager_number bigint        null,
    region_id      bigint        not null,
    constraint market_ibfk_1
        foreign key (location_id) references location (location_id)
);

create index location_id
    on market (location_id);

