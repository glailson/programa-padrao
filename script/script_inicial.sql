CREATE SCHEMA aluguefacil
  AUTHORIZATION postgres;
  
  CREATE TABLE aluguefacil.tbcasa
(
  canumsequencial bigserial NOT NULL,
  carua varchar(100),
  cabairro varchar(100),
  cacidade varchar(100),
  caobservacao varchar(255),
  carsituacao varchar(30),
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
  
 CREATE TABLE aluguefacil.tbaluguel
(
  alnumsequencial bigserial NOT NULL,
  alstatus varchar(50),
  alinquilino bigint,
  alcasa bigint,
  aldthraluguel timestamp without time zone,
  alvencimento integer,
  alobservacao varchar(255),
  CONSTRAINT tbaluguel_pkey PRIMARY KEY (alnumsequencial),
  CONSTRAINT tbinquilino_alinquilino_fkey FOREIGN KEY (alinquilino)
      REFERENCES aluguefacil.tbinquilino (innumsequencial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tbcasa_alcasa_fkey FOREIGN KEY (alcasa)
      REFERENCES aluguefacil.tbcasa (canumsequencial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aluguefacil.tbaluguel
  OWNER TO postgres;
  
  
ALTER TABLE aluguefacil.tbaluguel ADD COLUMN  alvalor numeric(5,2);
ALTER TABLE aluguefacil.tbaluguel ADD COLUMN  alsituacao varchar(50);

  
CREATE TABLE aluguefacil.tbpagamentoaluguel
(
  panumsequencial bigserial NOT NULL,
  paaluguel bigint,
  padthrpagamento timestamp without time zone,
  CONSTRAINT tbpagamentoaluguel_pkey PRIMARY KEY (panumsequencial),
  CONSTRAINT tbpagamentoaluguel_paaluguel_fkey FOREIGN KEY (paaluguel)
      REFERENCES aluguefacil.tbaluguel (alnumsequencial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aluguefacil.tbpagamentoaluguel
  OWNER TO postgres;
  
ALTER TABLE aluguefacil.tbpagamentoaluguel ADD COLUMN padthrreferenciapagamento timestamp without time zone;
ALTER TABLE aluguefacil.tbpagamentoaluguel ADD COLUMN pausuariorecebedor varchar(60);