FROM java:alpine
MAINTAINER Jonathon Headley <headleyjz@vcu.edu>

#ENTRYPOINT ["/usr/bin/java", "-jar", "/swifey.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/swifey/lib
# Add the service itself
ARG JAR_FILE

COPY target/${JAR_FILE} /swifey.jar

CMD ["/usr/bin/java", "-jar", "/swifey.jar"]