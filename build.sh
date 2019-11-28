set -e
mvn clean vaadin:update-theme vaadin:update-widgetset install
cp target/dataingest-0.0.1-SNAPSHOT.war dataingest-ui.war
docker build -t vui .