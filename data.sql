insert into basket(id, basket_data)
VALUES (1, '[{  "product_id": 547816,  "quantity": 1}, {  "product_id": 1487431,  "quantity": 1}, {  "product_id": 81647256,   "quantity": 5}]');

insert into "user"(id, email, name, surname, basket_id)
VALUES (2, 'fff@gmail.com', 'max', 'prachyk', 1);

insert into product (id, category, name, price)
VALUES (547816, 'LAPTOP', 'Lenovo turbo', 1600);
insert into product (id, category, name, price)
VALUES (1487431, 'MONITOR', 'Lenovo', 233);
insert into product (id, category, name, price)
VALUES (81647256, 'LAPTOP', 'Asus turf', 500);

-- TODO: add orderData JSON column to class
insert into "order"(id, order_status, user_id) VALUES (1, 'IN_PROGRESS',2);
