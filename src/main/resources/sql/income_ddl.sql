create table if not exists income (
    id varchar(255) primary key not null,
    amount int not null,
    date_added date not null,
    comment varchar(255)
    );