create table cargos (
                        status boolean not null,
                        id uuid not null,
                        descricao varchar(255),
                        primary key (id)
);
create table funcionarios (
                              data_contratacao date,
                              salario float(53) not null,
                              status boolean not null,
                              cargo_id uuid not null,
                              id uuid not null,
                              cpf varchar(255),
                              nome varchar(255),
                              primary key (id)
);
create table funcionarios_unidades (
                                       fk_funcionario uuid not null,
                                       fk_unidade uuid not null
);
create table unidades (
                          status boolean not null,
                          id uuid not null,
                          descricao varchar(255),
                          endereco varchar(255),
                          primary key (id)
);
alter table if exists funcionarios
    add constraint FK94l75p7ixitcgh6l240unj9bm
        foreign key (cargo_id)
            references cargos;
alter table if exists funcionarios_unidades
    add constraint FKed7aa88icr1xdy2nr38twud4p
        foreign key (fk_unidade)
            references unidades;
alter table if exists funcionarios_unidades
    add constraint FKg6nq7h4hjl3njvdd4304jp2m0
        foreign key (fk_funcionario)
            references funcionarios;