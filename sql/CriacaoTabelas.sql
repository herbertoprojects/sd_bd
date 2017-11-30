DROP SEQUENCE seq_Eleicao;
DROP SEQUENCE seq_Pessoa;
DROP TABLE Candidatos CASCADE CONSTRAINTS;
DROP TABLE Departamento CASCADE CONSTRAINTS;
DROP TABLE DtemE CASCADE CONSTRAINTS;
DROP TABLE Eleicao CASCADE CONSTRAINTS;
DROP TABLE Faculdade CASCADE CONSTRAINTS;
DROP TABLE Individual CASCADE CONSTRAINTS;
DROP TABLE Lista CASCADE CONSTRAINTS;
DROP TABLE LtemP CASCADE CONSTRAINTS;
DROP TABLE MesaVoto CASCADE CONSTRAINTS;
DROP TABLE Pessoa CASCADE CONSTRAINTS;
DROP TABLE PvigiaT CASCADE CONSTRAINTS;
DROP TABLE PvotaE CASCADE CONSTRAINTS;
DROP TABLE TerminaisVoto CASCADE CONSTRAINTS;

CREATE SEQUENCE seq_Eleicao;
CREATE SEQUENCE seq_Pessoa;
CREATE TABLE Candidatos (
  ID        number(10) NOT NULL, 
  EleicaoID number(10) NOT NULL, 
  tipo      varchar2(20) NOT NULL, 
  nVotos    number(10), 
  PRIMARY KEY (ID, 
  EleicaoID));
CREATE TABLE Departamento (
  sigla varchar2(10) NOT NULL, 
  Fac   varchar2(10) NOT NULL, 
  Nome  varchar2(40) NOT NULL, 
  PRIMARY KEY (sigla, 
  Fac));
CREATE TABLE DtemE (
  EleicaoID                  number(10) NOT NULL, 
  Departamentosigla          varchar2(10) NOT NULL, 
  DepartamentoFaculdadesigla varchar2(10) NOT NULL, 
  PRIMARY KEY (EleicaoID, 
  Departamentosigla, 
  DepartamentoFaculdadesigla));
CREATE TABLE Eleicao (
  ID         number(10) NOT NULL, 
  tipo       varchar2(20) NOT NULL, 
  DataInicio timestamp(0), 
  DataFim    timestamp(0), 
  Titulo     varchar2(100), 
  Descricao  varchar2(255), 
  nVotoBNA   number(10), 
  PRIMARY KEY (ID));
CREATE TABLE Faculdade (
  sigla varchar2(10) NOT NULL, 
  Nome  varchar2(40) NOT NULL UNIQUE, 
  PRIMARY KEY (sigla));
CREATE TABLE Individual (
  PessoaID            number(10) NOT NULL, 
  CandidatosID        number(10) NOT NULL, 
  CandidatosEleicaoID number(10) NOT NULL, 
  PRIMARY KEY (PessoaID, 
  CandidatosID, 
  CandidatosEleicaoID));
CREATE TABLE Lista (
  nome        varchar2(20) NOT NULL, 
  Cand        number(10) NOT NULL, 
  CandEleicao number(10) NOT NULL, 
  PRIMARY KEY (nome, 
  Cand, 
  CandEleicao));
CREATE TABLE LtemP (
  Cargo            varchar2(20), 
  PessoaNCC        number(10) NOT NULL, 
  Listanome        varchar2(20) NOT NULL, 
  ListaCand        number(10) NOT NULL, 
  ListaCandEleicao number(10) NOT NULL, 
  PRIMARY KEY (PessoaNCC, 
  Listanome, 
  ListaCand, 
  ListaCandEleicao));
CREATE TABLE MesaVoto (
  EleicaoID number(10) NOT NULL, 
  Dep       varchar2(10) NOT NULL, 
  DepFac    varchar2(10) NOT NULL, 
  username  varchar2(20), 
  password  varchar2(20), 
  PRIMARY KEY (EleicaoID, 
  Dep, 
  DepFac));
CREATE TABLE Pessoa (
  NCC                        number(10) NOT NULL, 
  Faculdade                  varchar2(10), 
  DepartamentoNome           varchar2(10), 
  DepartamentoFaculdadesigla varchar2(10), 
  tipo                       varchar2(30) NOT NULL, 
  Nome                       varchar2(100) NOT NULL, 
  senha                      varchar2(100) NOT NULL, 
  telefone                   number(9), 
  morada                     varchar2(150), 
  PRIMARY KEY (NCC));
CREATE TABLE PvigiaT (
  PessoaNCC        number(10) NOT NULL, 
  TermVotoid       number(10) NOT NULL, 
  TermVotoMesaid   number(10) NOT NULL, 
  TermVMesaEleicao number(10) NOT NULL, 
  TermVMesaDep     varchar2(10) NOT NULL, 
  TermVMesaDepFac  varchar2(10) NOT NULL, 
  PRIMARY KEY (PessoaNCC, 
  TermVotoid, 
  TermVotoMesaid, 
  TermVMesaEleicao, 
  TermVMesaDep, 
  TermVMesaDepFac));
CREATE TABLE PvotaE (
  PessoaNCC   number(10) NOT NULL, 
  MesaEleicao number(10) NOT NULL, 
  MesaDep     varchar2(10) NOT NULL, 
  MesaDepFac  varchar2(10) NOT NULL, 
  PRIMARY KEY (PessoaNCC, 
  MesaEleicao, 
  MesaDep, 
  MesaDepFac));
CREATE TABLE TerminaisVoto (
  id          number(10) NOT NULL, 
  Mesaid      number(10) NOT NULL, 
  MesaEleicao number(10) NOT NULL, 
  MesaDep     varchar2(10) NOT NULL, 
  MesaDepFac  varchar2(10) NOT NULL, 
  PRIMARY KEY (id, 
  Mesaid, 
  MesaEleicao, 
  MesaDep, 
  MesaDepFac));
ALTER TABLE LtemP ADD CONSTRAINT FKLtemP45843 FOREIGN KEY (Listanome, ListaCand, ListaCandEleicao) REFERENCES Lista (nome, Cand, CandEleicao);
ALTER TABLE LtemP ADD CONSTRAINT FKLtemP257266 FOREIGN KEY (PessoaNCC) REFERENCES Pessoa (NCC);
ALTER TABLE PvigiaT ADD CONSTRAINT FKPvigiaT660881 FOREIGN KEY (TermVotoid, TermVotoMesaid, TermVMesaEleicao, TermVMesaDep, TermVMesaDepFac) REFERENCES TerminaisVoto (id, Mesaid, MesaEleicao, MesaDep, MesaDepFac);
ALTER TABLE TerminaisVoto ADD CONSTRAINT FKTerminaisV229312 FOREIGN KEY (MesaEleicao, MesaDep, MesaDepFac) REFERENCES MesaVoto (EleicaoID, Dep, DepFac);
ALTER TABLE MesaVoto ADD CONSTRAINT FKMesaVoto713575 FOREIGN KEY (Dep, DepFac) REFERENCES Departamento (sigla, Fac);
ALTER TABLE PvotaE ADD CONSTRAINT FKPvotaE813167 FOREIGN KEY (MesaEleicao, MesaDep, MesaDepFac) REFERENCES MesaVoto (EleicaoID, Dep, DepFac);
ALTER TABLE PvotaE ADD CONSTRAINT FKPvotaE726956 FOREIGN KEY (PessoaNCC) REFERENCES Pessoa (NCC);
ALTER TABLE DtemE ADD CONSTRAINT FKDtemE62934 FOREIGN KEY (Departamentosigla, DepartamentoFaculdadesigla) REFERENCES Departamento (sigla, Fac);
ALTER TABLE Departamento ADD CONSTRAINT FKDepartamen333212 FOREIGN KEY (Fac) REFERENCES Faculdade (sigla);
ALTER TABLE Candidatos ADD CONSTRAINT FKCandidatos422330 FOREIGN KEY (EleicaoID) REFERENCES Eleicao (ID);
ALTER TABLE DtemE ADD CONSTRAINT FKDtemE750031 FOREIGN KEY (EleicaoID) REFERENCES Eleicao (ID);
ALTER TABLE Lista ADD CONSTRAINT FKLista319911 FOREIGN KEY (Cand, CandEleicao) REFERENCES Candidatos (ID, EleicaoID);
ALTER TABLE Individual ADD CONSTRAINT FKIndividual40223 FOREIGN KEY (CandidatosID, CandidatosEleicaoID) REFERENCES Candidatos (ID, EleicaoID);
ALTER TABLE Individual ADD CONSTRAINT e FOREIGN KEY (PessoaID) REFERENCES Pessoa (NCC);
ALTER TABLE MesaVoto ADD CONSTRAINT pertence FOREIGN KEY (EleicaoID) REFERENCES Eleicao (ID);
ALTER TABLE Pessoa ADD CONSTRAINT PpertenceD FOREIGN KEY (DepartamentoNome, DepartamentoFaculdadesigla) REFERENCES Departamento (sigla, Fac);
ALTER TABLE Pessoa ADD CONSTRAINT PpertenceF FOREIGN KEY (Faculdade) REFERENCES Faculdade (sigla);
ALTER TABLE PvigiaT ADD CONSTRAINT vigia FOREIGN KEY (PessoaNCC) REFERENCES Pessoa (NCC);
