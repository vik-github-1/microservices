#Start with a base image containing java runtime
FROM eclipse-temurin:21-jdk

# Maintainer instruction is deprecated in favour of using label
# Maintainer mygithub.com
# Information around who maintains the image
LABEL "org.opencontainers.image.authors"="mygithub.com"

#Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]

