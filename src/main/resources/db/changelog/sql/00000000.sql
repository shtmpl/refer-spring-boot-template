--liquibase formatted sql

--changeset ibardych:0000000000042-1

CREATE SEQUENCE runtime_configuration_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE runtime_configuration
(
  id          BIGINT PRIMARY KEY DEFAULT nextval('runtime_configuration_id_seq'),
  param       VARCHAR(255),
  value       TEXT,
  description TEXT
);

ALTER TABLE runtime_configuration
  ADD CONSTRAINT runtime_configuration_param_key UNIQUE (param);

INSERT INTO runtime_configuration (id, param, value, description)
VALUES (nextval('runtime_configuration_id_seq'),
        'application.name', 'Template', 'Application name');
