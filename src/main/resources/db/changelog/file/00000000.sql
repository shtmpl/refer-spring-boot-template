--liquibase formatted sql

--changeset ibardych:0000000000042-1
CREATE SEQUENCE currency_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE currency
(
  id   BIGINT PRIMARY KEY DEFAULT nextval('currency_id_seq'),
  code VARCHAR(3),
  name TEXT
);

ALTER TABLE currency
  ADD CONSTRAINT currency_code_key UNIQUE (code);

