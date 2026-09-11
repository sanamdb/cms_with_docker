-- Allow max 10 requests inside a 1-second period
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.ratelimiter.instances.examRateLimiter.limit-for-period', '10');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.ratelimiter.instances.examRateLimiter.limit-refresh-period', '300s');

-- Max time a request will wait in line if limit is hit before being rejected (0ms = reject immediately)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.ratelimiter.instances.examRateLimiter.timeout-duration', '0ms');

COMMIT;