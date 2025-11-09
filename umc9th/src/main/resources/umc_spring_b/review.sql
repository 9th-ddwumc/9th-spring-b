create table review
(
    review_id   bigint       not null
        primary key,
    rating      int          null,
    img         varchar(200) null,
    description varchar(255) null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    member_id   bigint       null,
    market_id2  bigint       null,
    content     varchar(255) null,
    star        int          null,
    market_id   bigint       not null,
    constraint review_ibfk_1
        foreign key (member_id) references member (member_id),
    constraint review_ibfk_2
        foreign key (market_id2) references market (market_id)
);

create index market_id2
    on review (market_id2);

create index member_id
    on review (member_id);

