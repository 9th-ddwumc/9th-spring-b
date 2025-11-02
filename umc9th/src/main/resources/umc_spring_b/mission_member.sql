create table mission_member
(
    missionmember_id bigint not null
        primary key,
    complete         bit    null,
    member_id        bigint null,
    mission_id       bigint null,
    constraint mission_member_ibfk_1
        foreign key (member_id) references member (member_id),
    constraint mission_member_ibfk_2
        foreign key (mission_id) references mission (missionmember_id)
);

create index member_id
    on mission_member (member_id);

create index mission_id
    on mission_member (mission_id);

