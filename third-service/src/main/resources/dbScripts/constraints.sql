/*This sql-script creates FOREIGN KEY constraints for the database*/

ALTER TABLE user_event DROP CONSTRAINT u_event_fk_user;
ALTER TABLE user_event DROP CONSTRAINT u_event_fk_event;
ALTER TABLE user_event DROP CONSTRAINT u_event_fk_role;
ALTER TABLE folder DROP CONSTRAINT folder_fk_user;
ALTER TABLE event DROP CONSTRAINT event_fk_period;
ALTER TABLE event DROP CONSTRAINT event_fk_e_type;
ALTER TABLE event DROP CONSTRAINT event_fk_folder;

ALTER TABLE user_event ADD CONSTRAINT u_event_fk_event FOREIGN KEY(event_id) REFERENCES event(event_id);
ALTER TABLE user_event ADD CONSTRAINT u_event_fk_role FOREIGN KEY(role_id) REFERENCES rrole(role_id);

ALTER TABLE event ADD CONSTRAINT event_fk_period FOREIGN KEY(periodicity_id) REFERENCES periodicity(periodicity_id);
ALTER TABLE event ADD CONSTRAINT event_fk_e_type FOREIGN KEY(event_type_id) REFERENCES event_type(event_type_id);
ALTER TABLE event ADD CONSTRAINT event_fk_folder FOREIGN KEY(folder_id) REFERENCES folder(folder_id);