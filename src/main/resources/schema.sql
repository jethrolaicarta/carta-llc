DROP TABLE IF EXISTS entitlement;

CREATE TABLE entitlement (
  id						VARCHAR(128) PRIMARY KEY,
  created					TIMESTAMP NOT NULL,
  updated					TIMESTAMP NOT NULL,
  deleted					TIMESTAMP NOT NULL,
  holder_id     VARCHAR(128),
  quantity                  DOUBLE NOT NULL,
  company_id                VARCHAR(128)
);

CREATE TABLE user (
  id						VARCHAR(128) PRIMARY KEY,
  created					TIMESTAMP NOT NULL
);

CREATE TABLE entitlement_holder (
  id						VARCHAR(128) PRIMARY KEY,
  created					TIMESTAMP NOT NULL,
  user_id                   VARCHAR(128),
  company_id                VARCHAR(128)
);

CREATE TABLE company (
  id						VARCHAR(128) PRIMARY KEY,
  created					TIMESTAMP NOT NULL
);