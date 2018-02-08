## Status
[![Build Status](https://travis-ci.org/famaridon/redmine-ng-api.svg?branch=develop)](https://travis-ci.org/famaridon/redmine-ng-api)


## Configuration

### create data-source

1. install the mysql jdbc driver
 1. download the latest [MySQL driver](https://dev.mysql.com/downloads/connector/j/)
 1. run jboss-cli
 1. add mysql module
```bash
module add --name=org.mysql --resources=mysql-connector-java-5.1.45.jar --dependencies=javax.api,javax.transaction.api
 ```
 1. add jdbc-driver to Wildfly
```bash
/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=org.mysql,driver-name=mysql,driver-class-name=com.mysql.jdbc.Driver,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)
``` 
1. add the datasource
```bash
data-source add --jndi-name=java:jboss/datasources/redmine-ng --name=redmine-ng --connection-url=jdbc:mysql://localhost:3306/redmine-ng?autoReconnect=true&useSSL=false --driver-name=mysql --user-name=root --password=manager --jta=false
```

### create cache-container


