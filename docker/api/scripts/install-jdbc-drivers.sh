#! /bin/bash

# Postgresql
mkdir -p $JBOSS_HOME/modules/org/postgresql/main
wget "https://jdbc.postgresql.org/download/postgresql-$POSTGRESQL_JDBC_VERSION.jar" \
    -O "$JBOSS_HOME/modules/org/postgresql/main/postgresql-$POSTGRESQL_JDBC_VERSION.jar"

cat  <<EOL > $JBOSS_HOME/modules/org/postgresql/main/module.xml
<?xml version='1.0' encoding='UTF-8'?>

<module xmlns="urn:jboss:module:1.1" name="org.postgresql" slot="main">

    <resources>
        <resource-root path="postgresql-$POSTGRESQL_JDBC_VERSION.jar"/>
    </resources>

    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
EOL

# MySQL

mkdir -p $JBOSS_HOME/modules/com/mysql/main
wget "http://central.maven.org/maven2/mysql/mysql-connector-java/$MYSQL_JDBC_VERSION/mysql-connector-java-$MYSQL_JDBC_VERSION.jar" \
    -O "$JBOSS_HOME/modules/com/mysql/main/mysql-connector-java-$MYSQL_JDBC_VERSION.jar"

cat  <<EOL > $JBOSS_HOME/modules/com/mysql/main/module.xml
<?xml version='1.0' encoding='UTF-8'?>

<module xmlns="urn:jboss:module:1.1" name="com.mysql" slot="main">

    <resources>
        <resource-root path="mysql-connector-java-$MYSQL_JDBC_VERSION.jar"/>
    </resources>

    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
EOL
