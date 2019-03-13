## Status
[![Build Status](https://travis-ci.org/famaridon/redmine-ng-api.svg?branch=develop)](https://travis-ci.org/famaridon/redmine-ng-api)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.famaridon%3Aredmine-ng-api&metric=coverage)](https://sonarcloud.io/dashboard?id=com.famaridon%3Aredmine-ng-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.famaridon%3Aredmine-ng-api&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.famaridon%3Aredmine-ng-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.famaridon%3Aredmine-ng-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.famaridon%3Aredmine-ng-api)

## Configuration

You can use the prebuild standalone.xml file in ```docker/api/configuration```.

This file contains :

1. MySQL driver configuration
```
<driver name="mysql" module="com.mysql">
	<!-- for xa datasource -->
	<xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
	<!-- for non-xa datasource -->
	<driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```

2. MySQL datasource configuration

The datasource is set to use 

```
<datasource jndi-name="java:jboss/datasources/redmine-ng" pool-name="redmine-ng">
	<connection-url>jdbc:mysql://database:3306/redminengapi</connection-url>
	<driver>mysql</driver>
	<security>
		<user-name>redmine-ng</user-name>
		<password>secret</password>
	</security>
	<pool>
		<min-pool-size>2</min-pool-size>
		<max-pool-size>50</max-pool-size>
		<prefill>true</prefill>
	</pool>
	<validation>
		<background-validation>true</background-validation>
		<background-validation-millis>10000</background-validation-millis>
		<valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
		<exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
	</validation>
</datasource>
```

3. Infinispan cache

```
<cache-container name="redmine-ng-api">
</cache-container>
```

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


