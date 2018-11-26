#! /bin/bash

mkdir -p $JBOSS_HOME/modules/org/postgresql/main
curl -L "https://jdbc.postgresql.org/download/postgresql-$POSTGRESQL_JDBC_VERSION.jar" \
    --output "$JBOSS_HOME/modules/org/postgresql/main/postgresql-$POSTGRESQL_JDBC_VERSION.jar"

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