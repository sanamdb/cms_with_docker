-- ################# Producer
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'dev', 'latest', 'spring.kafka.producer.bootstrap-servers', 'localhost:9092,localhost:9093');

-- Set Key Serializer to String
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'dev', 'latest', 'spring.kafka.producer.key-serializer', 'org.apache.kafka.common.serialization.StringSerializer');

-- Set Value Serializer to JSON (For your POJO)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'dev', 'latest', 'spring.kafka.producer.value-serializer', 'org.springframework.kafka.support.serializer.JsonSerializer');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'prod', 'latest', 'spring.kafka.producer.bootstrap-servers', 'localhost:9092,localhost:9093');

-- Set Key Serializer to String
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'prod', 'latest', 'spring.kafka.producer.key-serializer', 'org.apache.kafka.common.serialization.StringSerializer');

-- Set Value Serializer to JSON (For your POJO)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('College-Management', 'prod', 'latest', 'spring.kafka.producer.value-serializer', 'org.springframework.kafka.support.serializer.JsonSerializer');

------ #############Consumer
-- Set the Kafka Brokers
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.bootstrap-servers', 'localhost:9092,localhost:9093');

-- Set the Consumer Group ID
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.group-id', 'notification-group');

-- Set Key Deserializer to String
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.key-deserializer', 'org.apache.kafka.common.serialization.StringDeserializer');

-- Set Value Deserializer to JSON
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.value-deserializer', 'org.springframework.kafka.support.serializer.JsonDeserializer');

-- Trust all packages (Crucial for deserializing POJOs safely)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.properties.spring.json.trusted.packages', '*');

-- Ignore the type headers sent by the Producer
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.properties.spring.json.use.type.headers', 'false');

-- Force it to map to the Consumer's exact Notification class path
-- (REPLACE 'com.yourpackage.Notification' WITH your actual package path in Notification-Service)
INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE) 
VALUES ('Notification-Service', 'default', 'latest', 'spring.kafka.consumer.properties.spring.json.value.default.type', 'com.learning.college.NotificationService.dto.Notification');