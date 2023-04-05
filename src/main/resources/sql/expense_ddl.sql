create table if not exists expense (
    id varchar(255) primary key not null,
    amount int not null,
    category_id varchar(255) not null,
    date_added date not null,
    comment varchar(255),
    constraint expense_to_category_fk foreign key (category_id) references category (id)
    );