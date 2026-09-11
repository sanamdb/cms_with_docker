-- 1. Direct microservices to push spans to your Zipkin server
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'management.zipkin.tracing.endpoint', 'http://localhost:9411/api/v2/spans');

-- 2. Trace 100% of requests (1.0 = 100% sampling rate for DEV testing)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'management.tracing.sampling.probability', '1.0');

-- 3. Update console logs to print [AppName, TraceID, SpanID]
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'logging.pattern.level', '%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]');

-- 1. Direct microservices to push spans to your Zipkin server
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Gateway-Service', 'default', 'latest', 'management.zipkin.tracing.endpoint', 'http://localhost:9411/api/v2/spans');

-- 2. Trace 100% of requests (1.0 = 100% sampling rate for DEV testing)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Gateway-Service', 'default', 'latest', 'management.tracing.sampling.probability', '1.0');

-- 3. Update console logs to print [AppName, TraceID, SpanID]
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Gateway-Service', 'default', 'latest', 'logging.pattern.level', '%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]');


-- 1. Direct microservices to push spans to your Zipkin server
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Question-Service', 'default', 'latest', 'management.zipkin.tracing.endpoint', 'http://localhost:9411/api/v2/spans');

-- 2. Trace 100% of requests (1.0 = 100% sampling rate for DEV testing)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Question-Service', 'default', 'latest', 'management.tracing.sampling.probability', '1.0');

-- 3. Update console logs to print [AppName, TraceID, SpanID]
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Question-Service', 'default', 'latest', 'logging.pattern.level', '%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]');

COMMIT;


INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Exam-Service', 'default', 'latest', 'management.tracing.enabled', 'true');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Gateway-Service', 'default', 'latest', 'management.tracing.enabled', 'true');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Question-Service', 'default', 'latest', 'management.tracing.enabled', 'true');

COMMIT;