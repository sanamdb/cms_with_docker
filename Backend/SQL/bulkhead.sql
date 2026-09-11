-- Allow maximum 5 concurrent executions for fetchExamPaper
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.bulkhead.instances.examBulkhead.max-concurrent-calls', '5');

-- How long a thread will wait for a free slot before throwing BulkheadFullException (0ms = reject immediately)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'resilience4j.bulkhead.instances.examBulkhead.max-wait-duration', '0ms');

COMMIT;