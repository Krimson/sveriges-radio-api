FROM 259233288424.dkr.ecr.eu-north-1.amazonaws.com/jre-alpine:latest
COPY target/docker-spring-boot.jar deployments/docker-spring-boot.jar
ENTRYPOINT ["/deployments/run-java.sh"]