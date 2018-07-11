-- apply changes
create table customer (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  registered                    timestamp,
  comments                      varchar(255),
  constraint pk_customer primary key (id)
);

