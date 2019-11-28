FROM tomcat:8.5.28-jre8
ENV dataingest_url=http://localhost:8182
ENV dataingest_endpoint_dataset=dataset
ENV dataingest_endpoint_datasets=datasets
ENV dataingest_endpoint_project=project
ENV dataingest_endpoint_projects=projects
ENV dataingest_endpoint_neuron=neuron
ENV dataingest_endpoint_neurons=neurons
ENV dataingest_endpoint_login=login
ENV login_redirect_uri=http://localhost:8080/dataingest-ui/login
ENV client_id=APP-ENQTIY7Z904S6O1W
ENV app_title="VFB Data Ingest"
ENV authorise_url=https://orcid.org/oauth/authorize?client_id=%s&response_type=code&scope=/authenticate&redirect_uri=%s

ADD dataingest-ui.war /usr/local/tomcat/webapps/
ADD dataingest-ui.war /usr/local/tomcat/webapps/
#ADD server.xml /usr/local/tomcat/conf/
EXPOSE 8080
CMD chmod +x /usr/local/tomcat/bin/catalina.sh
CMD ["catalina.sh", "run"]