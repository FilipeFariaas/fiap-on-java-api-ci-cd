WORKDIR /opt/app
RUN mvn clean package
FROM eclipse-temurin:21-alpine-jre-alpine
RUN mkdir /opt/app
COPY --from=build /opt/app/target/app.jat /opt/app/app.jar
WORKDIR /opt/app
ENV PROFILE=dev
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}","-jar","app.jar"]