FROM openjdk:8
COPY JustClick.jar app.jar
RUN mkdir -p justclick/config
RUN mkdir -p /var/justclick/logs
RUN chmod 777 -R justclick
EXPOSE 8000
ENTRYPOINT ["java","-Duser.timezone=America/Guayaquil","-jar","/app.jar","--spring.config.location=file:/justclick/config/application.properties"]
