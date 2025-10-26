create table food
(
    id   bigint auto_increment
        primary key,
    name enum ('ASIAN', 'CHICKEN', 'CHINESE', 'DESSERT', 'FAST_FOOD', 'GRILL', 'JAPANESE', 'KOREAN', 'LATE_NIGHT', 'LUNCHBOX', 'SNACK', 'WESTERN') not null
);

