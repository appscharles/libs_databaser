
    create table customer (
       id bigint generated by default as identity,
        createdAt timestamp,
        updatedAt timestamp,
        comments varchar(255),
        name varchar(255),
        registered timestamp,
        primary key (id)
    );

    create table customer2 (
       id bigint generated by default as identity,
        createdAt timestamp,
        updatedAt timestamp,
        comments varchar(255),
        name varchar(255),
        registered timestamp,
        primary key (id)
    );