USE rfid;

CREATE TABLE rfid_event (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reader_name VARCHAR(20) NOT NULL,
	epc VARCHAR(30) NOT NULL,
	time_stamp datetime NOT NULL,
	peakrssi DOUBLE  NULL,
	read_count BIGINT NULL	
);
