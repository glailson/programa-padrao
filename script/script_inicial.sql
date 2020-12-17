CREATE SCHEMA aluguefacil
  AUTHORIZATION postgres;
  
  CREATE TABLE aluguefacil.tbcasa
(
  canumsequencial bigserial NOT NULL,
  carua varchar(100),
  cabairro varchar(100),
  cacidade varchar(100),
  caobservacao varchar(255),
  carsituacao varchar(30)
  canumero integer ,
  cadthrcadastro timestamp without time zone,
  CONSTRAINT tbcasa_pkey PRIMARY KEY (canumsequencial)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aluguefacil.tbcasa
  OWNER TO postgres;
  
CREATE TABLE aluguefacil.tbinquilino
(
  innumsequencial bigserial NOT NULL,
  innome varchar(100),
  innomeguerra varchar(100),
  incpf varchar(25),
  inobservacao varchar(255),
  intelefone varchar(25),
  inprofissao varchar(60),
  indthrcadastro timestamp without time zone,
  innomefiador varchar(100),
  intelefonefiador varchar(25),
  CONSTRAINT tbinquilino_pkey PRIMARY KEY (innumsequencial)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aluguefacil.tbinquilino
  OWNER TO postgres;