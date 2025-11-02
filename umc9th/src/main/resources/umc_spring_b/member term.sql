create table `member term`
(
    id        bigint auto_increment
        primary key,
    member_id bigint not null,
    term_id   bigint not null,
    constraint FK6grout39uiaxvij9edcss6602
        foreign key (term_id) references term (id)
);

