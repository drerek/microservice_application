/*Current sql-script creates database tables
with  PRIMARY KEY and UNIQUE constraints.
 */

DROP TABLE user_event;
DROP TABLE rrole;
DROP TABLE periodicity;
DROP TABLE event;
DROP TABLE folder;
DROP TABLE event_type;


CREATE TABLE user_event (
  user_id varchar2(50) NOT NULL,
  event_id number NOT NULL,
  role_id number NOT NULL,
  UNIQUE (user_id, event_id)
);

CREATE TABLE rrole (
  role_id number,
  name varchar2(50) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE folder (
  folder_id number,
  name varchar2(100) NOT NULL,
  user_id varchar2(50) NOT NULL,
  PRIMARY KEY (folder_id)
);

CREATE TABLE event (
  event_id number,
  name varchar2(50) NOT NULL,
  event_date timestamp,
  description varchar2(1023),
  periodicity_id number,
  place varchar2(100),
  event_type_id number NOT NULL,
  is_draft number(1) NOT NULL,
  folder_id number NOT NULL,
  image_filepath varchar2(255) NOT NULL,
  PRIMARY KEY (event_id)
);


CREATE TABLE periodicity (
  periodicity_id number,
  periodicity_name varchar2(20) NOT NULL,
  PRIMARY KEY (periodicity_id)
);

CREATE TABLE event_type (
  event_type_id number,
  type varchar(50) NOT NULL,
  PRIMARY KEY (event_type_id)
);