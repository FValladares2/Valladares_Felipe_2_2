#basado en el docker que utiliza mi grupo para el proyecto, con un poco
#   del docker init para manejar usuario
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /muebleria

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY build/libs/*.jar ingSoftEval2.jar

EXPOSE 1984

ENTRYPOINT [ "java", "-jar", "ingSoftEval2.jar" ]
