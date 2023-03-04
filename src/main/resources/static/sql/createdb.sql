/* База данных 'Интернет-магазин электронных товаров' */
DROP DATABASE IF EXISTS `electromarketdb`;
CREATE DATABASE `electromarketdb` DEFAULT CHARACTER SET utf8;
USE electromarketdb;

/* Таблица 'Пользователи' : 1 - User, 2 - Admin */
CREATE TABLE users (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    login       VARCHAR(32) NOT NULL,
    password    VARCHAR(64) NOT NULL,
    mail        VARCHAR(32) NOT NULL,
    info        VARCHAR(64),
    type_id     INT,
    access_mode INT,
    PRIMARY KEY (id)
);

/* Таблица 'Категория товаров' */
CREATE TABLE category (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

/* Таблица 'Подкатегории товаров' */
CREATE TABLE subcategory (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    category_id BIGINT,
    name        VARCHAR(32) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

/* Таблица 'Товар' */
CREATE TABLE product (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(128) NOT NULL,
    subcategory_id  BIGINT,
    price           INT,
    remainder       INT,
    info            TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (subcategory_id) REFERENCES subcategory (id)
);

/* Таблица 'Заказ' */
CREATE TABLE orders (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    user_id         BIGINT,
    summ            DOUBLE,
    data            VARCHAR(64),
    status          INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

/* Таблица 'Заказ' */
CREATE TABLE order_list (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    order_id        BIGINT,
    product_id      BIGINT,
    amount          INT,
    price           DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

/* Таблица 'Корзина' */
CREATE TABLE basket (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    user_id         BIGINT,
    product_id      BIGINT,
    amount          INT,
    summ            DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

/* insert data */
-- users
insert into users values (1, 'user', 'user', 'user', '', '', 1, 0);
insert into users values (2, 'admin', 'admin', 'admin', '', '', 2, 0);

-- category
insert into category values (1, 'Аудиотехника');
insert into category values (2, 'Планшеты / эл.книги');
insert into category values (3, 'Телефония и связь');
insert into category values (4, 'ТВ- и видеотехника');
insert into category values (5, 'Фото- и видеокамеры');

-- subcategory
insert into subcategory values (1, 1, 'Музыкальные центры');
insert into subcategory values (2, 1, 'Виниловые проигрыватели');
insert into subcategory values (3, 1, 'Умные колонки');

insert into subcategory values (4, 2, 'Планшеты');
insert into subcategory values (5, 2, 'Электронные книги');
insert into subcategory values (6, 2, 'Аксессуары');

insert into subcategory values (7, 3, 'Мобильные телефоны');
insert into subcategory values (8, 3, 'Радиотелефоны');
insert into subcategory values (9, 3, 'Умные часы');

insert into subcategory values (10, 4, 'Телевизоры');
insert into subcategory values (11, 4, 'Проекторы');
insert into subcategory values (12, 4, 'Игровые приставки');

insert into subcategory values (13, 5, 'Фотоаппараты');
insert into subcategory values (14, 5, 'Экшн-камеры');
insert into subcategory values (15, 5, 'Палки для селфи');


/* Музыкальные центры */
insert into product values (1, 'Музыкальный центр Telefunken TF-PS1276B', 1, 142, 100, 'Код: 1841994,Страна производства: Китай,Прогрессивная развертка: нет,Выход на наушники: нет,Wi-Fi: нет,AirPlay: нет,Караоке: есть,Часы: нет,USB: есть');
insert into product values (2, 'Музыкальный центр Ginzzu GM-219', 1, 273, 100, 'Код: 2084251,Страна производства: Китай,Время работы от аккумулятора: 5 ч,Емкость аккумулятора: 4500 мАч,Тип: partybox,Тип аккумулятора: Li-ion,Цвет колонок: черный,Вход микрофонный: есть,Количество микрофонных входов: 1');
insert into product values (3, 'Музыкальный центр Ritmix SP-830B', 1, 164, 100, 'Код: 1943837,Страна производства: Китай,Выход на наушники: нет,Wi-Fi: нет,AirPlay: нет,Караоке: есть,Часы: нет,USB: есть,FM-радио: есть');

/* Виниловые проигрыватели */
insert into product values (4, 'Виниловый проигрыватель Audio-Technica AT-LP60XUSBGM', 2, 1100, 50, 'Код: 1565670,Страна производства: Китай,Тип привода: ременной,Переключение скоростей: электронное,Автомат: полный,Картридж в комплекте: есть,Тонарм в комплекте: есть,Встроенный фонокорректор: есть,Тип тонарма: поворотный');

/* Умные колонки */
insert into product values (5, 'Умная колонка Яндекс Станция Лайт (ультрафиолет)', 3, 205, 100, 'Код: 2048862,Страна производства: Китай,Тип: умная колонка,Питание: от сети,Цвет: фиолетовый,Суммарная мощность: 5 Вт,Количество динамиков: 1,Материал корпуса: ткань, пластик');
insert into product values (6, 'Умная колонка Яндекс Станция Макс (бордовый)', 3, 1295, 100, 'Код: 1970765,Страна производства: Китай,Тип: умная колонка,Питание: от сети,Спикерфон: есть,Стереодинамики: есть,Количество динамиков: 5,Количество пассивных динамиков: 1,Акустическое оформление: пассивный излучатель');
insert into product values (7, 'Умная колонка Яндекс Станция Мини 2 с часами (синий сапфир)', 3, 289, 100, 'Код: 2125063,Страна производства: Китай,Тип: умная колонка,Питание: от сети,Высота: 54 мм,Ширина: 97 мм,Глубина: 97 мм,Вес: 0.28 кг');


