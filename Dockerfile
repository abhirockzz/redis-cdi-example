FROM airhacks/tomee
ENV WAR redis-cdi.war
COPY target/${WAR} ${DEPLOYMENT_DIR}