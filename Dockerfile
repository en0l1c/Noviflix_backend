# for production
FROM openjdk:23-jdk-slim

# working directory inside container
WORKDIR /app

# set spring profile to "prod"
ENV SPRING_PROFILES_ACTIVE=prod

# copy executable JAR from local system to container
COPY target/Noviflix_backend-0.0.1-SNAPSHOT.jar app.jar

# Ορισμός της εντολής για την εκτέλεση της εφαρμογής
ENTRYPOINT ["java", "-jar", "app.jar"]




###### without the need of jar each time ######
#FROM openjdk:23-jdk-slim
#
## working directory inside container
#WORKDIR /app
#
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
## set spring profile to "prod"
#ENV SPRING_PROFILES_ACTIVE=prod
#
## adding environment variables for the database
##ENV DATABASE_HOST=${DATABASE_HOST}
##ENV DATABASE_SCHEMA=${DATABASE_SCHEMA}
##ENV DATABASE_USERNAME=${DATABASE_USERNAME}
##ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}


#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]