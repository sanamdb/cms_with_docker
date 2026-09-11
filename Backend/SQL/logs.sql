-- Tell ALL microservices to save their logs to your specific workspace logs folder
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('application', 'default', 'latest', 'logging.file.name', 'D:/Workspace/Eclipse/RevisionStartFrom23062026/SpringBoot/RestApplication/logs/${spring.application.name}.log');

-- Set max file size to 10MB to prevent giant, unreadable files
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('application', 'default', 'latest', 'logging.logback.rollingpolicy.max-file-size', '10MB');

-- Keep only the last 7 days of logs to save disk space
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('application', 'default', 'latest', 'logging.logback.rollingpolicy.max-history', '7');