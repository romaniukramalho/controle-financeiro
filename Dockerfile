FROM maven:3.9-eclipse-temurin-17-alpine
LABEL maintainer="Artur Ramalho <arturramalhocs@gmail.com>"

WORKDIR /app

COPY . .

RUN mvn package

ENV DB_URL=jdbc:postgresql://db:5432/sardinha
ENV DB_USER=arturramalho
ENV DB_PASSWORD=

EXPOSE 8080

CMD ["java", "-jar", "target/controle-financeiro-1.0.jar"]