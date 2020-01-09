CREATE TABLE entity_a (
  uuid						VARCHAR(128) PRIMARY KEY,
  status					VARCHAR(64) NOT NULL,
  created_date				TIMESTAMP NOT NULL,
  detail					CLOB NOT NULL,
  updated_date              TIMESTAMP NOT NULL
);
