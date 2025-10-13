create table reviewreply
(
    replay_id bigint       not null
        primary key,
    content   varchar(255) null,
    review_id bigint       null,
    constraint reviewreply_ibfk_1
        foreign key (review_id) references review (review_id)
);

create index review_id
    on reviewreply (review_id);

