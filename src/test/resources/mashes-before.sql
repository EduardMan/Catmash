DELETE FROM voted_user_targets;
DELETE FROM target;
DELETE FROM mash;

INSERT INTO mash(id, message, name) VALUES
(1, 'Кто из них милее?', 'Мимимиметр'),
(2, 'Кто из них красивее?', 'Собакамимимиметр'),
(3, 'Кто из них круче?', 'Автомобили'),
(4, 'Какой дом лучше?', 'Выбор дома');

INSERT INTO target(id, file_name, name, rating, mash_id) VALUES
(1, 'Барсик.jpg', 'Барсик', 0, 1),
(2, 'Мурзик.jpg', 'Мурзик', 0, 1),
(3, 'Матроскин.jpg', 'Матроскин', 0, 1),
(4, 'Барсик.jpg', 'Барсик', 0, 1),
(5, 'Рыжик.jpg', 'Рыжик', 0, 1),
(6, 'Алтай.jpg', 'Алтай', 0, 1),
(7, 'Бац.jpg', 'Бац', 0, 1),
(8, 'Борис.jpg', 'Борис', 0, 1),
(9, 'Барбос.jpg', 'Барбос', 0, 1),
(10, 'Голиаф.jpg', 'Голиаф', 0, 1),
(11, 'Грот.jpg', 'Грот', 0, 1),
(12, 'Дартаньян.jpg', 'Вольт', 0, 1),
(13, 'Вандер.jpg', 'Вандер', 0, 2),
(14, 'Шарик.jpg', 'Шарик', 0, 2),
(15, 'Рекс.jpg', 'Рекс', 0, 2),
(16, 'Партос.jpg', 'Партос', 0, 2),
(17, 'Данка.jpg', 'Данка', 0, 2),
(18, 'Есения.jpg', 'Есения', 0, 2),
(19, 'Ельма.jpg', 'Ельма', 0, 2),
(20, 'Оманда.jpg', 'Оманда', 0, 2),
(21, 'Пэтти.jpg', 'Пэтти', 0, 2),
(22, 'Тинки.jpg', 'Тинки', 0, 2),
(23, 'Audi A6.jpg', 'Audi A6', 0, 3),
(24, 'Audi RS4.jpg', 'Audi RS4', 0, 3),
(25, 'BMW 8.jpg', 'BMW 8', 0, 3),
(26, 'BMW M5.jpg', 'BMW M5', 0, 3),
(27, 'Toyota LC200.jpg', 'Toyota LC200', 0, 3),
(28, 'Toyota Highlander.jpg', 'Toyota Highlander', 0, 3),
(29, 'Toyota Camry.jpg', 'Toyota Camry', 0, 3),
(30, 'Mercedes C.jpg', 'Mercedes C', 0, 3),
(31, 'Mercedes E.jpg', 'Mercedes E', 0, 3),
(32, 'Mercedes AMG', 'Mercedes AMG', 0, 3),
(33, 'Volkswagen Passat.jpg', 'Volkswagen Passat', 0, 3),
(34, 'Volkswagen Toureg.jpg', 'Volkswagen Toureg', 0, 3),
(35, 'Volkswagen Tiguan.jpg', 'Volkswagen Tiguan', 0, 3),
(36, 'BMW X3', 'BMW X3', 0, 3),
(37, 'BMW X3', 'Большой дом', 0, 4),
(38, 'BMW X3', 'Красивый дом', 0, 4),
(39, 'BMW X3', 'Дом у реки', 0, 4),
(40, 'BMW X3', 'Дом у моря', 0, 4),
(41, 'BMW X3', 'Дом с бассейном', 0, 4),
(42, 'BMW X3', 'Дом на дереве', 0, 4),
(43, 'BMW X3', 'Замок', 0, 4),
(44, 'BMW X3', 'Дом на колесах', 0, 4),
(45, 'BMW X3', 'Домик в деревне', 0, 4),
(46, 'BMW X3', 'Умный дом', 0, 4);

ALTER SEQUENCE hibernate_sequence restart with 50;