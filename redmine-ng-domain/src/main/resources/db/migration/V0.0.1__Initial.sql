CREATE TABLE IF NOT EXISTS `IterationEntity`
(
  `id`     bigint(20) NOT NULL AUTO_INCREMENT,
  `name`   varchar(255) DEFAULT NULL,
  `end`    date       NOT NULL,
  `number` bigint(20) NOT NULL,
  `start`  date       NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_IterationEntity_number` (`number`),
  UNIQUE KEY `UK_IterationEntity_name` (`name`)
);

CREATE TABLE IF NOT EXISTS `BurndownChartEntity`
(
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `name`         varchar(255) DEFAULT NULL,
  `iteration_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_BurndownChartEntity_name` (`name`),
  KEY `FK_IterationEntity_iteration_id` (`iteration_id`),
  CONSTRAINT `FK_IterationEntity_iteration_id` FOREIGN KEY (`iteration_id`) REFERENCES `IterationEntity` (`id`)
);

CREATE TABLE IF NOT EXISTS `ChartTimedValueEntity`
(
  `id`    bigint(20)     NOT NULL AUTO_INCREMENT,
  `name`  varchar(255) DEFAULT NULL,
  `date`  datetime(6)    NOT NULL,
  `value` decimal(19, 2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `BurndownChartEntity_ChartTimedValueEntity`
(
  `BurndownChartEntity_id` bigint(20) NOT NULL,
  `values_id`              bigint(20) NOT NULL,
  UNIQUE KEY `UK_BurndownChartEntity_ChartTimedValueEntity_values_id` (`values_id`),
  KEY `FK_BurndownChartEntity_BurndownChartEntity_id` (`BurndownChartEntity_id`),
  CONSTRAINT `FK_ChartTimedValueEntity_values_id` FOREIGN KEY (`values_id`) REFERENCES `ChartTimedValueEntity` (`id`),
  CONSTRAINT `FK_BurndownChartEntity_BurndownChartEntity_id` FOREIGN KEY (`BurndownChartEntity_id`) REFERENCES `BurndownChartEntity` (`id`)
);


CREATE TABLE IF NOT EXISTS `ObjectiveEntity`
(
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `name`         varchar(255) DEFAULT NULL,
  `description`  longtext,
  `iteration_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ObjectiveEntity_name` (`name`),
  KEY `FK_ObjectiveEntity_iteration_id` (`iteration_id`),
  CONSTRAINT `FK_ObjectiveEntity_iteration_id` FOREIGN KEY (`iteration_id`) REFERENCES `IterationEntity` (`id`)
);
