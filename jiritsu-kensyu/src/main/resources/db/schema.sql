--管理者アカウントテーブル--
create table admin_account(
    id serial primary key,
    name varchar(100) not null,
    password varchar(100) not null
);
insert into admin_account values (nextval('admin_account_id_seq'),'admin','admin');