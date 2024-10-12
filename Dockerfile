FROM jboss/wildfly:latest
EXPOSE 8080
COPY /out/c9_whRestJakarta.war /opt/jboss/wildfly/standalone/deployments/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
