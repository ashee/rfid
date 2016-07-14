USE rfid;

-- raw events from the reader
CREATE TABLE rfid_event (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reader_name VARCHAR(20) NOT NULL,
	epc VARCHAR(30) NOT NULL,
	time_stamp DATETIME NOT NULL,
	peakrssi DOUBLE  NULL,
	read_count BIGINT NULL	
) ENGINE=INNODB;

-- presence data
-- step-1: 
-- compute individual presence and its associated
-- interval. Please note that rfid_presence_interval has to be
-- populated first. This row is then referenced from rfid_presence as foreign key.
--
-- step-2:
-- scan overlapping intervals to identify epc tags 
-- that were together. Create a new row in
-- rfid_presence_interval and insert in 
-- rfid_presence table a set of rows one each 
-- for epc tags that were simulteneously found together

-- set of time windows - either for individual tag 
-- or a set of tags. presence_type of 'I' captures
-- presence of a single epc tag while 'C' captures
-- co-presence
CREATE TABLE rfid_presence_interval (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	presence_type CHAR(1) NOT NULL, -- 'I' = Individual, 'C' = Co-presence 
	start_time DATETIME NOT NULL,
	end_time DATETIME
) ENGINE=INNODB;

-- presence data - each row represents a tag in a
-- given interval. For copresence, there will be 
-- multiple rows referencing a single interval_id 
CREATE TABLE rfid_presence (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reader_name VARCHAR(20) NOT NULL,
	epc VARCHAR(30) NOT NULL,
	interval_id BIGINT NOT NULL
) ENGINE=INNODB;

-- the following section is for setting up the master
-- data and tag assignments

CREATE TABLE rfid_location (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	location_type VARCHAR(80) NOT NULL, -- TBD
	name VARCHAR(80) NOT NULL,
	description VARCHAR(512) NULL
) ENGINE=INNODB;

CREATE TABLE rfid_reader (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reader_type VARCHAR(80) NOT NULL,
	reader_name VARCHAR(80) NOT NULL,
	description VARCHAR(512) NULL
) ENGINE=INNODB;

CREATE TABLE rfid_reader_location (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	location_id BIGINT NOT NULL,
	reader_id BIGINT NOT NULL,
	status CHAR(1) NOT NULL, -- 'A' = Active, 'I' = Inactive
	start_date DATETIME NOT NULL,
	end_date DATETIME NULL
) ENGINE=INNODB;

CREATE TABLE rfid_subject (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	mrn VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE rfid_provider (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	provider_type VARCHAR(80) NOT NULL,
	institional_id VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE rfid_taggable (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	taggable_type CHAR(1) NOT NULL, -- 'P' = Provider, 'S' = Subject
	provider_id BIGINT NULL,
	subject_id BIGINT NULL
) ENGINE=INNODB;

CREATE TABLE rfid_tag_assignment (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	taggable_id BIGINT NOT NULL,
	status CHAR(1) NOT NULL, -- 'A' = Active, 'I' = Inactive
	start_date DATETIME NOT NULL,
	end_date DATETIME NULL
) ENGINE=INNODB;

-- foreign keys
ALTER TABLE rfid_presence 
	ADD CONSTRAINT fk_presence_interval
		FOREIGN KEY (interval_id)
		REFERENCES rfid_presence_interval(id);

ALTER TABLE rfid_reader_location
	ADD CONSTRAINT uk_reader_location
		UNIQUE KEY (location_id, reader_id, status);

ALTER TABLE rfid_reader_location 
	ADD CONSTRAINT fk_location
		FOREIGN KEY (location_id)
		REFERENCES rfid_location(id);

ALTER TABLE rfid_reader_location 
	ADD CONSTRAINT fk_reader
		FOREIGN KEY (reader_id)
		REFERENCES rfid_reader(id);

ALTER TABLE rfid_taggable
	ADD CONSTRAINT fk_taggable_provider
		FOREIGN KEY (provider_id)
		REFERENCES rfid_provider(id);

ALTER TABLE rfid_taggable
	ADD CONSTRAINT fk_taggable_subject
		FOREIGN KEY (provider_id)
		REFERENCES rfid_subject(id);

ALTER TABLE rfid_tag_assignment
	ADD CONSTRAINT uk_tag_assignment
		UNIQUE KEY (taggable_id, status);

ALTER TABLE rfid_tag_assignment
	ADD CONSTRAINT fk_taggable
		FOREIGN KEY (taggable_id)
		REFERENCES rfid_taggable(id);
