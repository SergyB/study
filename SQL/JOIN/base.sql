-- http://www.skillz.ru/dev/php/article-Obyasnenie_SQL_obedinenii_JOIN_INNER_OUTER.html
use flowers;
drop database join_test;

create database join_test;
use join_test;

CREATE TABLE departments
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20)
);


CREATE TABLE users
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  d_id INT(11)
  -- CONSTRAINT department_ibfk_1 FOREIGN KEY (d_id) REFERENCES departments (id)
  -- FOREIGN KEY (d_id) REFERENCES departments (id)
);

insert departments (name) values ('Сейлз');
insert departments (name) values ('Поддержка');
insert departments (name) values ('Финансы');
insert departments (name) values ('Логистика');
-- insert departments (name) values ('test1');
-- insert departments (name) values ('test2');


insert users (name,d_id) values ('Владимир',1);
insert users (name,d_id) values ('Антон',2);
insert users (name,d_id) values ('Александр',6);
insert users (name,d_id) values ('Борис',2);
insert users (name,d_id) values ('Юрий',4);

-- delete from departments where id=6;

-- update users set d_id = 6 where name='Александр';
-- update users set d_id = 6 where name='Александр';

SELECT u.id, u.name, d.name AS d_name FROM users u INNER JOIN departments d ON u.d_id = d.id;

-- Ключевое слово OUTER можно опустить. Запись LEFT JOIN идентична LEFT OUTER JOIN.
SELECT u.id, u.name, d.name AS d_name FROM users u LEFT JOIN departments d ON u.d_id = d.id;
SELECT u.id, u.name, d.name AS d_name FROM users u LEFT JOIN departments d ON u.d_id = d.id WHERE d.id IS NULL;

-- RIGHT OUTER JOIN вернет полный список департаментов (правая таблица) и сопоставленных пользователей.
SELECT u.id, u.name, d.name AS d_name FROM users u RIGHT OUTER JOIN departments d ON u.d_id = d.id;

-- CROSS JOIN возвращает перекрестное (декартово) объединение двух таблиц. Результатом будет выборка всех записей первой таблицы объединенная с каждой строкой второй таблицы. 
-- Важным моментом является то, что для кросса не нужно указывать условие объединения.
SELECT u.id, u.name, d.name AS d_name FROM users u CROSS JOIN departments d;


-- Self Join
-- Напишите запрос, выбирающий уникальные пары `id` товаров с одинаковыми `name`, например:
-- (1,2), (4,1), (2,4), (6,3)...
-- При решении задачи необходимо учесть, что пары (x,y) и (y,x) — одинаковы.

CREATE TABLE `ya_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into ya_goods values (1, 'яблоки'), (2, 'яблоки') ,(3, 'груши'), (4,'яблоки'), (5, 'апельсины'), (6, 'груши');
-- `id`    `name`
-- 1       Яблоки
-- 2       Яблоки
-- 3       Груши
-- 4       Яблоки
-- 5       Апельсины
-- 6       Груши

SELECT g1.id id1, g2.id id2
-- CONCAT('(', LEAST(g1.id, g2.id), ',', GREATEST(g1.id, g2.id), ')') row
FROM ya_goods g1 
INNER JOIN ya_goods g2 ON g1.name = g2.name
WHERE g1.id <> g2.id
GROUP BY LEAST(g1.id, g2.id), GREATEST(g1.id, g2.id);
-- http://www.mysql.ru/docs/man/Mathematical_functions.html
-- LEAST(X,Y,...) Если задано два или более аргументов, возвращает наименьший (с минимальным значением) аргумент
-- GREATEST(X,Y,...) Возвращает наибольший (с максимальным значением) аргумент. Сравнение аргументов происходит по тем же правилам, что и для LEAST

SELECT g1.id id1, g2.id id2
-- CONCAT('(', LEAST(g1.id, g2.id), ',', GREATEST(g1.id, g2.id), ')') row
FROM ya_goods g1 
INNER JOIN ya_goods g2 ON g1.name = g2.name 
WHERE g1.id <> g2.id
GROUP BY LEAST(g1.id, g2.id), GREATEST(g1.id, g2.id)
ORDER BY g1.id;

-- Множественное объединение multi join
CREATE TABLE  `products` (
  `id` int(11),
  `title` varchar(255),
  `created_at` datetime
);
 
CREATE TABLE `product_options` (
  `id` int(11),
  `name` varchar(255)
);
 
CREATE TABLE `product2options` (
  `product_id` int(11),
  `option_id` int(11),
  `value` int(11)
);

INSERT INTO `products` (`id`, `title`, `created_at`) VALUES
        (1, 'Кружка', '2009-01-17 20:00:00'),
        (2, 'Ложка', '2009-01-18 20:00:00'),
        (3, 'Тарелка', '2009-01-19 20:00:00');
 
INSERT INTO `product_options` (`id`, `name`) VALUES
        (11, 'Вес'),
        (12, 'Объем');
 
INSERT INTO `product2options` (`product_id`, `option_id`, `value`) VALUES
        (1, 11, 200),
        (1, 12, 250),
        (2, 11, 35),
        (2, 12, 15),
        (3, 11, 310),
        (3, 12, 300),
        (2, 11, 45),
        (2, 12, 25);


-- Пример: выбрать товары, 
-- добавленные после 17/01/2009 в следующих вариантах:
-- вес=310, объем=300
-- вес=35, объем=15
-- вес=45, объем=25
-- вес=200, объем=250

-- Просто перечислить условия вариантов в подзапросе/джоине через OR/AND не сработает, 
-- необходимо осуществить объединение таблиц вариантов равное количеству этих самых вариантов (у нас - 2: объем и вес)
SELECT p.*, po1.name 'P1', p2o1.value, po2.name 'P2', p2o2.value     
 
FROM products p       
 
INNER JOIN product2options p2o1 ON p.id = p2o1.product_id
INNER JOIN product_options po1  ON po1.id = p2o1.option_id
 
INNER JOIN product2options p2o2 ON p.id = p2o2.product_id
INNER JOIN product_options po2  ON po2.id = p2o2.option_id
 
WHERE p.created_at > '2009-01-17 21:00' 
  AND ( -- тарелка#3 
  p2o1.option_id = 11 AND p2o1.value = 310 AND p2o2.option_id = 12 AND p2o2.value = 300
  OR  -- ложка#2
  p2o1.option_id = 11 AND p2o1.value = 35 AND p2o2.option_id = 12 AND p2o2.value = 15           
  OR  -- ложка#2
  p2o1.option_id = 11 AND p2o1.value = 45 AND p2o2.option_id = 12 AND p2o2.value = 25   
  OR  -- кружка#1 не попадает по дате
  p2o1.option_id = 12 AND p2o1.value = 250 AND p2o2.option_id = 11 AND p2o2.value = 200
  )  
;

-- DELETE и JOIN
-- Рассмотрим пример с удалением дубликатов. Есть таблица tableWithDups (id, email). Нужно удалить строки с одинаковыми email:
CREATE TABLE `tableWithDups` (
  `id` int(11),
  `email` varchar(255)
);

INSERT INTO `tableWithDups` (`id`, `email`) VALUES
        (1, 'email-1@mail.ru'),
        (2,  'email-2@mail.ru'),
        (3,  'email-3@mail.ru'),
        (4,  'email-4@mail.ru'),
        (5,  'email-1@mail.ru'),
        (6,  'email-1@mail.ru'),
        (7,  'email-2@mail.ru'),
        (8,  'email-2@mail.ru');
        
SELECT * FROM tableWithDups;

SELECT MAX(id) AS lastId, email
        FROM tableWithDups
        GROUP BY email
        HAVING COUNT(*) > 1;


DELETE tableWithDups
FROM tableWithDups
INNER JOIN (
        SELECT MAX(id) AS lastId, email
        FROM tableWithDups
        GROUP BY email
        HAVING COUNT(*) > 1
        ) dups ON dups.email = tableWithDups.email
WHERE tableWithDups.id < dups.lastId;
        

