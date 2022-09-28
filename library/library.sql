create table publisher (
    id serial primary key,
    name text,
    address text
);

create table book (
    ISBN text primary key,
    publisher_id int not null references publisher (id),
    name text,
    author text,
    year int,
    number_of_pages int
);

create table category (
    id serial primary key,
    parent_category_id int references category (id),
    name text
);

create table book_category (
    ISBN text not null references book (ISBN),
    category_id int not null references category (id),
    primary key (ISBN, category_id)
);

create table book_copy (
    ISBN text not null references book (ISBN),
    number int not null,
    primary key (ISBN, number),
    shelf_position text
);

create table reader (
    number int primary key,
    first_name text,
    last_name text,
    birthday date,
    address text
);

create table book_copy_taking (
    reader_number int not null references reader (number),
    ISBN text not null,
    book_copy_number int not null,
    foreign key (ISBN, book_copy_number) references book_copy (ISBN, number),
    return_date date
);
