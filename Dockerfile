FROM gradle:6.7.0-jdk8 AS build-env
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . /home/gradle/src
RUN gradle clean build -x test --no-daemon --info

FROM openjdk:8-jre-slim
COPY --from=build-env /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
