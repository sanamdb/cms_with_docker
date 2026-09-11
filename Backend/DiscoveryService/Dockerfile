FROM openjdk:17.0.2-jdk-oraclelinux8
COPY target/DiscoveryService-1.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]
HEALTHCHECK --interval=10s --timeout=5s --start-period=30s --retries=10 \
  CMD curl -fsS http://localhost:8761/actuator/health || exit 1