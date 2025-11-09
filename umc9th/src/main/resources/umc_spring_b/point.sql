create table point
(
    point_id   bigint      not null
        primary key,
    amount     bigint      null,
    created_at datetime(6) null,
    deleted_at datetime(6) null,
    member_id  bigint      null,
    constraint point_ibfk_1
        foreign key (member_id) references member (member_id)
);

create index member_id
    on point (member_id);

