create table member
(
    member_id      bigint      not null
        primary key,
    name           varchar(6)  null,
    nickname       varchar(7)  null,
    gender         tinyint     null,
    phone          varchar(13) null,
    birth          varchar(10) null,
    address        varchar(30) null,
    social_type    varchar(3)  null,
    created_at     datetime(6) null,
    deleted_at     datetime(6) null,
    status         tinyint     null,
    inactive_date  varchar(10) null,
    detail_address varchar(30) null,
    social_uid     varchar(20) null,
    email          varchar(20) null,
    phone_number   varchar(20) null,
    point          bigint      null,
    socail_type    tinyint     null,
    socail_uid     varchar(20) null,
    check (`socail_type` between 0 and 4)
);

