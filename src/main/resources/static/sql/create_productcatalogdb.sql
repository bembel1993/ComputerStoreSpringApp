DROP DATABASE IF EXISTS `productcatalogdb`;
CREATE DATABASE `productcatalogdb` DEFAULT CHARACTER SET utf8;
USE productcatalogdb;

/** Таблица "Категория товаров" */
CREATE TABLE category (
    id           BIGINT NOT NULL AUTO_INCREMENT,
    name         VARCHAR(64) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

/** Таблица "Товары" */
CREATE TABLE product (
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    category_id  BIGINT NOT NULL,
    name         VARCHAR(64) NULL DEFAULT NULL,
    info         VARCHAR(64) NULL DEFAULT NULL,
    price        VARCHAR(64) NULL DEFAULT NULL,
    count        INT NOT NULL,
    manufacturer VARCHAR(64) NULL DEFAULT NULL,
    releaseDate  VARCHAR(64) NULL DEFAULT NULL,
    link         VARCHAR(64) NULL DEFAULT NULL,
    photo        BLOB NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

/** Таблица "Тип пользователей" */
CREATE TABLE user_type (
    id           BIGINT NOT NULL AUTO_INCREMENT,
    name         VARCHAR(64) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

/** Таблица "Пользователи" */
CREATE TABLE user (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    type_id     BIGINT NOT NULL,
    name        VARCHAR(64) NOT NULL DEFAULT '0',
    login       VARCHAR(64) NOT NULL DEFAULT '0',
    password    VARCHAR(64) NOT NULL DEFAULT '0',
    email       VARCHAR(64) NOT NULL DEFAULT '0',
    contact     VARCHAR(64) NOT NULL DEFAULT '0',
    access_mode INT,
    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES user_type (id)
);

/** Таблица "Сообщения" */
CREATE TABLE message (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    question    VARCHAR(64) NULL DEFAULT NULL,
    message     VARCHAR(64) NULL DEFAULT NULL,
    answer      VARCHAR(64) NULL DEFAULT NULL,
    status      VARCHAR(64) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_type (id)
);

-- category
insert into category values (1, 'Computers');
insert into category values (2, 'Electronics');
insert into category values (3, 'Accessories');

-- products
insert into product values (1, 1, 'Computer1', 'HP', '2500', 100, 'HP', '01.01.2021', '', null);
insert into product values (2, 2, 'Phone', 'Samsung', '1700', 100, 'Samsung', '01.01.2021', '', null);
insert into product values (3, 3, 'PowerBank', 'Xiaomi', '50', 100, 'Xiaomi', '01.01.2021', '', null);

-- user_type
insert into user_type values (1, 'User');
insert into user_type values (2, 'Admin');

-- users
insert into user values (1, 1, 'user', 'user', 'user', '', '', 0);
insert into user values (2, 2, 'admin', 'admin', 'admin', '', '', 0);
