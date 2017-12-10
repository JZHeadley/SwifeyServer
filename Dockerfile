FROM java:alpine
MAINTAINER Jonathon Headley <headleyjz@vcu.edu>

#ENTRYPOINT ["/usr/bin/java", "-jar", "/swifey.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/swifey/lib
# Add the service itself
#ARG JAR_FILE

COPY target/swifey-0.0.1-SNAPSHOT.jar /swifey.jar
#COPY target/${JAR_FILE} /swifey.jar

CMD ["/usr/bin/java", "-jar", "/swifey.jar"]