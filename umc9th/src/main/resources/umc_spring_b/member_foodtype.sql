create table member_foodtype
(
    member_foodtype_id bigint not null
        primary key,
    member_id          bigint null,
    foodtype_id        bigint null,
    constraint member_foodtype_ibfk_1
        foreign key (member_id) references member (member_id),
    constraint member_foodtype_ibfk_2
        foreign key (foodtype_id) references foodtype (foodtype_id)
);

create index foodtype_id
    on member_foodtype (foodtype_id);

create index member_id
    on member_foodtype (member_id);

