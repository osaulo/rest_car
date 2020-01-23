FROM anapsix/alpine-java:8

EXPOSE 1111
VOLUME /logs

ARG JAR_FILE
ADD ${JAR_FILE} eureka-server.jar
RUN bash -c 'touch /eureka-server.jar'

CMD java -Djava.security.egd=file:/dev/./urandom -jar /eureka-server.jar 

