insert into basket(id, quantity)
VALUES (1, 8);
insert into basket(id, quantity)
VALUES (2, 4);
insert into basket(id, quantity)
VALUES (3, 5);
insert into basket(id, quantity)
VALUES (4, 7);
insert into basket(id, quantity)
VALUES (5, 1);
insert into basket(id, quantity)
VALUES (6, 9);

insert into "user"(email, name, surname, basket_id)
VALUES ('fff@gmail.com', 'max', 'prachyk', 1);
insert into "user"(email, name, surname, basket_id)
VALUES ('asda@gmail.com', 'qwe', 'asd', 2);
insert into "user"(email, name, surname, basket_id)
VALUES ('xxx@gmail.com', 'sada', 'cvcv', 6);
insert into "user"(email, name, surname, basket_id)
VALUES ('fgfg@gmail.com', 'nbnv', 'prkggf', 5);
insert into "user"(email, name, surname, basket_id)
VALUES ('hgf@gmail.com', 'hfgjf', 'prykjgh', 4);
insert into "user"(email, name, surname, basket_id)
VALUES ('fff@gmail.com', 'jkjh', 'yopjuytmj', 3);


insert into product (id, category, name, price, basket_id)
VALUES (1, 'LAPTOP', 'Lenovo turbo', 1600, 1);
insert into product (id, category, name, price, basket_id)
VALUES (2, 'MONITOR', 'Lenovo', 233, 2);
insert into product (id, category, name, price, basket_id)
VALUES (3, 'LAPTOP', 'Asus turf', 500, 3);
insert into product (id, category, name, price, basket_id)
VALUES (4, 'PHONE', 'Samsung M 32', 345, 4);
insert into product (id, category, name, price, basket_id)
VALUES (5, 'CHARGER', 'Gelius l-12', 125, 5);


select * from "order";
insert into "order"(order_status, product_id_id, user_id) VALUES ('IN_PROGRESS',1,1);
insert into "order"(order_status, product_id_id, user_id) VALUES ('ORDER_ACCEPTED',2,2);
insert into "order"(order_status, product_id_id, user_id) VALUES ('DELIVERING',3,6);
insert into "order"(order_status, product_id_id, user_id) VALUES ('DELIVERED',4,5);
insert into "order"(order_status, product_id_id, user_id) VALUES ('IN_PROGRESS',5,4);
