FROM tomcat:8.5.28-jre8
ENV DATAINGESTAPI_CONFIG=/usr/local/tomcat/conf/vfb_data_ingest_config.json
ADD dataingest-ui.war /usr/local/tomcat/webapps/
ADD dataingest-ui.war /usr/local/tomcat/webapps/
ADD vfb_data_ingest_config.json /usr/local/tomcat/conf/
#ADD server.xml /usr/local/tomcat/conf/
EXPOSE 8080
CMD chmod +x /usr/local/tomcat/bin/catalina.sh
CMD ["catalina.sh", "run"]