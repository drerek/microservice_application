/*Current sql-script creates sequences and triggers
for pre-auto-incrementing the field with PRIMARY KEY constraint
in each table.
 */

DROP SEQUENCE user_seq;
DROP TRIGGER user_tr;


CREATE SEQUENCE user_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
CREATE OR REPLACE TRIGGER user_tr
BEFORE INSERT ON uuser
FOR EACH ROW
BEGIN
  SELECT user_seq.NEXTVAL
  INTO   :new.user_id
  FROM   dual;
END;
/