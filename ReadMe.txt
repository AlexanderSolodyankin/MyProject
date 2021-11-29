Проет расчитанный на автолюбителей и сбор всех сфер спроса по данной тематике 
а имеено покупка и продажа авто, сервисное обслуживоние, форум по разным тематикам, Список сервис центров,
 вакантные места и обьеявления исполнителей которые работают индвидуально, 


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



