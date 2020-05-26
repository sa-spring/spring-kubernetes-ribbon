FROM fabric8/java-centos-openjdk8-jdk:1.5
ENV JAVA_APP_DIR=/deployments
LABEL org.label-schema.description="A simple example that demonstrates how to build and test a basic spring\
		cloud kubernetes app" org.label-schema.version=1.0 org.label-schema.schema-version=1.0 org.label-schema.build-date=2020-05-26T15:15:23.020960 org.label-schema.name="Spring Cloud Kubernetes :: Examples :: Hello World" org.label-schema.url=https://spring.io/projects/spring-boot/k8s-robbinclient org.label-schema.vendor="Pivotal Software, Inc."
EXPOSE 8080 8778 9779
COPY maven /deployments/
