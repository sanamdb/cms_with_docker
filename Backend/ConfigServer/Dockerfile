FROM openjdk:17.0.2-jdk-oraclelinux8
COPY target/ConfigServer-1.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]
HEALTHCHECK --interval=10s --timeout=5s --start-period=30s --retries=10 \
  CMD curl -fsS http://localhost:8888/actuator/health || exit 1