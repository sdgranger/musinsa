insert into `category` (`name`)
values ('상의'),
       ('아우터'),
       ('바지'),
       ('스니커즈'),
       ('가방'),
       ('모자'),
       ('양말'),
       ('액세서리');


insert into `brand` (`name`)
values ('A'),
       ('B'),
       ('C'),
       ('D'),
       ('E'),
       ('F'),
       ('G'),
       ('H'),
       ('I');

insert into `item` (`category_id`, `brand_id`, `price`)
values (1, 1, 11200),
       (1, 2, 10500),
       (1, 3, 10000),
       (1, 4, 10100),
       (1, 5, 10700),
       (1, 6, 11200),
       (1, 7, 10500),
       (1, 8, 10800),
       (1, 9, 11400),
       (2, 1, 5500),
       (2, 2, 5900),
       (2, 3, 6200),
       (2, 4, 5100),
       (2, 5, 5000),
       (2, 6, 7200),
       (2, 7, 5800),
       (2, 8, 6300),
       (2, 9, 6700),
       (3, 1, 4200),
       (3, 2, 3800),
       (3, 3, 3300),
       (3, 4, 3000),
       (3, 5, 3800),
       (3, 6, 4000),
       (3, 7, 3900),
       (3, 8, 3100),
       (3, 9, 3200),
       (4, 1, 9000),
       (4, 2, 9100),
       (4, 3, 9200),
       (4, 4, 9500),
       (4, 5, 9900),
       (4, 6, 9300),
       (4, 7, 9000),
       (4, 8, 9700),
       (4, 9, 9500),
       (5, 1, 2000),
       (5, 2, 2100),
       (5, 3, 2200),
       (5, 4, 2500),
       (5, 5, 2300),
       (5, 6, 2100),
       (5, 7, 2200),
       (5, 8, 2100),
       (5, 9, 2400),
       (6, 1, 1700),
       (6, 2, 2000),
       (6, 3, 1900),
       (6, 4, 1500),
       (6, 5, 1800),
       (6, 6, 1600),
       (6, 7, 1700),
       (6, 8, 1600),
       (6, 9, 1700),
       (7, 1, 1800),
       (7, 2, 2000),
       (7, 3, 2200),
       (7, 4, 2400),
       (7, 5, 2100),
       (7, 6, 2300),
       (7, 7, 2100),
       (7, 8, 2000),
       (7, 9, 1700),
       (8, 1, 2300),
       (8, 2, 2200),
       (8, 3, 2100),
       (8, 4, 2000),
       (8, 5, 2100),
       (8, 6, 1900),
       (8, 7, 2000),
       (8, 8, 2000),
       (8, 9, 2400);