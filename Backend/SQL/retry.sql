-- Maximum total attempts (1 initial call + 2 retries)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('UsersManagementSystem', 'default', 'latest', 'resilience4j.retry.instances.notificationServiceRetry.max-attempts', '3');

-- Wait duration between retries (2 seconds)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('UsersManagementSystem', 'default', 'latest', 'resilience4j.retry.instances.notificationServiceRetry.wait-duration', '2000ms');

COMMIT;