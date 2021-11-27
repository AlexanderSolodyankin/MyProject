Идея проекта это создание онлайн игрушки РПГ направление двухмерная. Цель повторить уже существующею игру «Космические Рейджеры2: Доминаторы». Поиграть по локальной-сети в игру так же возможность потренироваться в разных методиках программирования.

Игра по сути будет в себя включать экономическую составляющие где люди и НПС-персы смогут вести динамическую торговлю по вселенной. Развитие технологий (пока еще под вопросом как это сделать лучше) отпираясь по игровому времени. Реализация торговой системы и площадки, самостоятельное развитие НПС персонажей, Квесты с логической подоплекой, Аукцион , производство, усовершенствования, поиск сокровищ, исследования, Захват Систем и планет на вкус игрока, Динамичное расширение вселенной в зависимости от количества пользователей.


Первый этап:
Создать БД для регистрации пользователя-игрока, реализовать создание персонажа и заход в игру (по Базе данных).

Второй этап:
Реализация одной карты с полноценным функционалом не игровых персов и путешествия игрока а так же взаимодействия его с объектами окружающего мира.

Третий этап: 
Реализация независимого функционала неигровых персов по созданной карте и самостоятельному развитию. 

Примерная БД для игры:
create table users(
id serial primary key
login varchar unique not null
password varchar not null,
create_data timestamp
);

create table roles(
id serial primary key,
role_name varchar not null unique,
user_id int references users(id)
);

create table person(
id serial primary key,
persone_name varchar not null,
credit int,
point_skills int,
point_progres int,
role_id int references roles(id)
);

create table skills(
id serial primary key,
person_id int references person(id),
tehnic int,
accuracy int,
mobility int,
traid int,
sharm int,
leadership int
);

create table race(
id serial primary key,
person_id int references person(id)
);

