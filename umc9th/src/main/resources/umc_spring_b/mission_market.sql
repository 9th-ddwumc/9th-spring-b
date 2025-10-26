create table mission_market
(
    id          bigint auto_increment
        primary key,
    is_complete bit    null,
    market_id   bigint null,
    mission_id  bigint null
);

