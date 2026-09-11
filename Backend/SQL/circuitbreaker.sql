-- Define sliding window size (last 10 requests) and failure threshold percentage (50%)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.circuitbreaker.instances.questionServiceCB.sliding-window-size', '10');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.circuitbreaker.instances.questionServiceCB.failure-rate-threshold', '50');

-- Wait time in OPEN state before trying HALF-OPEN state (10 seconds)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.circuitbreaker.instances.questionServiceCB.wait-duration-in-open-state', '10000ms');

COMMIT;