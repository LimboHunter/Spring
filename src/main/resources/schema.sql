create table if not exists Pizza_Order
(
    id              int generated always as identity,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(10) not null,
    delivery_Zip    varchar(10) not null,
    "user"          varchar(50) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null
);
create table if not exists Pizza
(
    id              int generated always as identity,
    name            varchar(50) not null,
    pizza_order     bigint      not null,
    pizza_order_key bigint      not null,
    created_at      timestamp   not null
);
create table if not exists Ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(10) not null
);
alter table Pizza
    add foreign key (pizza_order) references pizza_order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
alter table Pizza_Order
    add foreign key (user) references user(id);