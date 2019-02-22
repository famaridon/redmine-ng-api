# Use latest jboss/base-jdk:8 image as the base
FROM jboss/base-jdk:11

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 15.0.1.Final
ENV WILDFLY_SHA1 23d6a5889b76702fc518600fc5b2d80d6b3b7bb1
ENV JBOSS_HOME /opt/wildfly
ENV RUN_CONF $JBOSS_HOME/standalone-custom.conf
ENV DOCKER_COMPOSE_WAIT_VERSION 2.4.0
ENV JPROFILER false
ENV JPROFILER_PORT 8860
ENV JACOCO false
ENV JACOCO_PORT 6300
ENV JACOCO_VERSION 0.8.3
ENV JACOCO_MD5 d4f07cb98bbf2305dbd16ee3012d7321
ENV POSTGRESQL_JDBC_VERSION 42.2.5
ENV MYSQL_JDBC_VERSION 5.1.47

USER root

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && curl -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

## Add the docker compose waiting tool for database
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$DOCKER_COMPOSE_WAIT_VERSION/wait $JBOSS_HOME/wait

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN chmod +x $JBOSS_HOME/wait

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER jboss

WORKDIR $JBOSS_HOME

COPY scripts $JBOSS_HOME/

# configure
RUN ${JBOSS_HOME}/bin/add-user.sh sysadmin manager \
    && ${JBOSS_HOME}/install-jdbc-drivers.sh \
    && ${JBOSS_HOME}/install-jacoco.sh \
    && ${JBOSS_HOME}/install-jprofiler.sh

# Expose the ports we're interested in
EXPOSE 8080
EXPOSE 9990
EXPOSE 9999
EXPOSE 5000
EXPOSE $JPROFILER_PORT
EXPOSE $JACOCO_PORT

VOLUME $JBOSS_HOME/configuration/

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/wildfly/start.sh"]