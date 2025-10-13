create table memberform
(
    user_form_id bigint not null
        primary key,
    member_id    bigint null,
    form_id      bigint null,
    constraint memberform_ibfk_1
        foreign key (member_id) references member (member_id),
    constraint memberform_ibfk_2
        foreign key (form_id) references form (form_id)
);

create index form_id
    on memberform (form_id);

create index member_id
    on memberform (member_id);

