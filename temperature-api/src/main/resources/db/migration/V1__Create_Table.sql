CREATE TABLE temperature (
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  device_id INT UNSIGNED NOT NULL,
  temperature TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (timestamp, device_id));
