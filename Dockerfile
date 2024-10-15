FROM quay.io/wildfly/wildfly:latest
EXPOSE 8080
ADD c9_whRestJakarta-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/whrest.war
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
