/*Current sql-script creates sequences and triggers
for pre-auto-incrementing the field with PRIMARY KEY constraint
in each table.
 */

DROP SEQUENCE role_seq;
DROP TRIGGER role_tr;

DROP SEQUENCE folder_seq;
DROP TRIGGER folder_tr;

DROP SEQUENCE event_seq;
DROP TRIGGER event_tr;

DROP SEQUENCE periodicity_seq;
DROP TRIGGER periodicity_tr;

DROP SEQUENCE e_type_seq;
DROP TRIGGER e_type_tr;

CREATE SEQUENCE role_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER role_tr
BEFORE INSERT ON rrole
FOR EACH ROW
BEGIN
  SELECT role_seq.NEXTVAL
  INTO   :new.role_id
  FROM   dual;
END;
/

CREATE SEQUENCE folder_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER folder_tr
BEFORE INSERT ON folder
FOR EACH ROW
BEGIN
  SELECT folder_seq.NEXTVAL
  INTO   :new.folder_id
  FROM   dual;
END;
/

CREATE SEQUENCE event_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER event_tr
BEFORE INSERT ON event
FOR EACH ROW
BEGIN
  SELECT event_seq.NEXTVAL
  INTO   :new.event_id
  FROM   dual;
END;
/

CREATE SEQUENCE periodicity_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER periodicity_tr
BEFORE INSERT ON periodicity
FOR EACH ROW
BEGIN
  SELECT periodicity_seq.NEXTVAL
  INTO   :new.periodicity_id
  FROM   dual;
END;
/

CREATE SEQUENCE e_type_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER e_type_tr
BEFORE INSERT ON event_type
FOR EACH ROW
BEGIN
  SELECT e_type_seq.NEXTVAL
  INTO   :new.event_type_id
  FROM   dual;
END;
/
