FROM openjdk:11-slim

# Wildlfy
ENV JBOSS_HOME /opt/wildfly
ENV WILDFLY_USER wildfly
ENV WILDFLY_GROUP wildfly
ENV WILDFLY_VERSION 16.0.0.Final
ENV WILDFLY_SHA1 287c21b069ec6ecd80472afec01384093ed8eb7d
ENV RUN_CONF $JBOSS_HOME/standalone-custom.conf

# Sync dockers
ENV DOCKER_COMPOSE_WAIT_VERSION 2.4.0

# JProfiler
ENV JPROFILER false
ENV JPROFILER_PORT 8860

#Jacoco
ENV JACOCO false
ENV JACOCO_PORT 6300
ENV JACOCO_VERSION 0.8.3
ENV JACOCO_MD5 d4f07cb98bbf2305dbd16ee3012d7321

#Debug
ENV DEBUG false
ENV DEBUG_PORT 5005

# JDBC Drivers
ENV POSTGRESQL_JDBC_VERSION 42.2.5
ENV MYSQL_JDBC_VERSION 5.1.47

USER root

# Add JBoss User
RUN apt-get update \
	&& apt-get install wget -y  && apt-get clean \
    && groupadd -r $WILDFLY_GROUP -g 1000 \
    && useradd -u 1000 -r -g $WILDFLY_GROUP -m -d $JBOSS_HOME -s /sbin/nologin -c "JBoss user" $WILDFLY_USER \
    && chmod 755 $JBOSS_HOME


WORKDIR $JBOSS_HOME

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $JBOSS_HOME \
    && wget -nv -O wildfly-$WILDFLY_VERSION.tar.gz "https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz" \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar --extract --file wildfly-$WILDFLY_VERSION.tar.gz --directory $JBOSS_HOME --strip-components 1 \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R $WILDFLY_USER:$WILDFLY_GROUP $JBOSS_HOME \
    && chmod -R g+rw $JBOSS_HOME

## Add the docker compose waiting tool for database
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$DOCKER_COMPOSE_WAIT_VERSION/wait $JBOSS_HOME/wait

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN chmod +x $JBOSS_HOME/wait

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

RUN chown -R $WILDFLY_USER:$WILDFLY_GROUP $JAVA_HOME

USER $WILDFLY_USER

COPY scripts $JBOSS_HOME/

# configure
RUN $JBOSS_HOME/bin/add-user.sh sysadmin manager \
    && $JBOSS_HOME/install-jdbc-drivers.sh \
    && $JBOSS_HOME/install-jacoco.sh \
    && $JBOSS_HOME/install-jprofiler.sh

# Expose the ports we're interested in
EXPOSE 8080
EXPOSE 9990
EXPOSE 9999
EXPOSE 5000
EXPOSE $JPROFILER_PORT
EXPOSE $JACOCO_PORT
EXPOSE $DEBUG_PORT

VOLUME $JBOSS_HOME/configuration/

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/wildfly/start.sh"]
