FROM openjdk:11.0.7-jre
RUN mkdir /opt/app
WORKDIR /opt/app
COPY build/libs/*.jar /opt/app/web.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/web.jar