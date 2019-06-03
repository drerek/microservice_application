/*This sql-script creates FOREIGN KEY constraints for the database*/

ALTER TABLE friend DROP CONSTRAINT friend_fk_user_s;
ALTER TABLE friend DROP CONSTRAINT friend_fk_user_r;

ALTER TABLE friend ADD CONSTRAINT friend_fk_user_s FOREIGN KEY(sender_id) REFERENCES uuser(user_id);
ALTER TABLE friend ADD CONSTRAINT friend_fk_user_r FOREIGN KEY(receiver_id) REFERENCES uuser(user_id);