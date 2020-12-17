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