CREATE TABLE temperature (
  timestamp TIMESTAMP NOT NULL,
  device_id BIGINT NOT NULL,
  temperature SMALLINT NOT NULL,
  PRIMARY KEY (timestamp, device_id));
