# The first line is always FROM and defines a base image
FROM tomcat:8.0-jre8

# Adding info about who created this image
LABEL maintainer="Leo Juarez & Gavin Schindler"

# Move the .war file to the webapps directory
# The webapps directory contains the app that TOMCAT serves
ADD target/project1-team01.war /usr/local/tomcat/webapps

# Expose port 8080
EXPOSE 8080

# CMD instruction specifies what to run when the container is run
# In our case the TOMCAT server is started by running a shell script
CMD ["catalina.sh", "run"]