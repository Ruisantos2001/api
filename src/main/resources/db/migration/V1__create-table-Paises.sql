create table paises(
    id bigint not null auto_increment PRIMARY KEY,
    nome varchar(20) not null,
    capital varchar(20) not null,
    regiao varchar(20) not null,
    subregiao varchar(20) not null,
    area varchar(15) not null
);