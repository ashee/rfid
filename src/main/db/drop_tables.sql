ALTER TABLE rfid_presence 
	DROP foreign key fk_presence_interval;
    
ALTER TABLE rfid_reader_location
	DROP FOREIGN KEY fk_location;

ALTER TABLE rfid_reader_location
	DROP FOREIGN KEY fk_reader;

ALTER TABLE rfid_reader_location
	DROP INDEX uk_reader_location;

ALTER TABLE rfid_taggable
	DROP FOREIGN KEY fk_taggable_provider;

ALTER TABLE rfid_taggable
	DROP FOREIGN KEY fk_taggable_subject;

ALTER TABLE rfid_tag_assignment
	DROP FOREIGN KEY fk_taggable;

ALTER TABLE rfid_tag_assignment
	DROP INDEX uk_tag_assignment;
	
DROP TABLE rfid_event;
DROP TABLE rfid_presence_interval;
DROP TABLE rfid_presence;
DROP TABLE rfid_location;
DROP TABLE rfid_reader;
DROP TABLE rfid_reader_location;
DROP TABLE rfid_subject;
DROP TABLE rfid_provider;
DROP TABLE rfid_taggable;
DROP TABLE rfid_tag_assignment;
