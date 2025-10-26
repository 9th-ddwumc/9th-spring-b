create table mission
(
    missionmember_id bigint      not null
        primary key,
    deadline         datetime(6) null,
    conditional      varchar(50) null,
    point            int         null,
    created_at       datetime(6) null,
    deleted_at       datetime(6) null,
    market_id        bigint      null,
    deal_line        datetime(6) null,
    constraint mission_ibfk_1
        foreign key (market_id) references market (market_id)
);

create index market_id
    on mission (market_id);

