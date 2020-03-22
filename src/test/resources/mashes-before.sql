DELETE FROM mash;

INSERT INTO mash(id, message, name) values
(1, 'Кто из них милее?', 'Мимимиметр'),
(2, 'Кто из них красивее?', 'Собавкамимимиметр'),
(3, 'Кто из них круче?', 'Автомобили'),
(4, 'Какой дом лучше?', 'Выбор дома');

alter sequence hibernate_sequence restart with 10;