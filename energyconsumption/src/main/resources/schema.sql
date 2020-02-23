CREATE TABLE counter(
  C_ID LONG AUTO_INCREMENT PRIMARY KEY,
  counter_id LONG NOT NULL,
  consumption DOUBLE(2),
  consumption_time timestamp without time zone DEFAULT NOW()
);

CREATE TABLE village (
  V_ID LONG AUTO_INCREMENT PRIMARY KEY,
  village_id LONG NOT NULL,
  village_name VARCHAR(255) NOT NULL,
  counter_id LONG NOT NULL
);
