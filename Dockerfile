FROM maven:3-jdk-11

WORKDIR /AppServer
COPY . .
ENV JAVA_OPTS="-Xms128m -XX:MaxMetaspaceSize=512m -XX:+UseG1GC"
RUN mvn install
COPY target/parlamentares-*.jar parlamentares.jar
EXPOSE 8080
CMD java -jar $JAVA_OPTS parlamentares.jar
