/*Current sql-script creates database tables
with  PRIMARY KEY and UNIQUE constraints.
 */

DROP TABLE friend;
DROP TABLE uuser;

CREATE TABLE uuser (
  user_id NUMBER(11) PRIMARY KEY,
  login VARCHAR2(50) NOT NULL UNIQUE,
  password VARCHAR2(50) NULL,
  name VARCHAR2(254) NOT NULL,
  surname VARCHAR2(254) NOT NULL,
  email VARCHAR2(100) NOT NULL UNIQUE,
  timezone NUMBER(3),
  image_filepath VARCHAR2(200),
  bday DATE,
  phone VARCHAR2(25),
  periodical_email VARCHAR2(100),
  REGISTER_DATE timestamp
);

CREATE TABLE friend (
  sender_id number NOT NULL,
  receiver_id number NOT NULL,
  is_Confirmed number(1) NOT NULL,
  UNIQUE (sender_id, receiver_id)
);
