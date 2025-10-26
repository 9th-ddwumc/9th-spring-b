create table customerservice
(
    customerservice_id bigint       not null
        primary key,
    title              varchar(20)  null,
    content            varchar(255) null,
    img                varchar(200) null,
    created_at         datetime(6)  null,
    deleted_at         datetime(6)  null,
    member_id          bigint       null,
    constraint customerservice_ibfk_1
        foreign key (member_id) references member (member_id)
);

create index member_id
    on customerservice (member_id);

