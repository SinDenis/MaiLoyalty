FROM eclipse-temurin:21-jre

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /app.jar

CMD java ${DEV_DEBUG_JAVA_OPTS} ${DEV_JMX_JAVA_OPTS} ${JAVA_OPTS} -jar app.jar
