-- add index to read faster
ALTER TABLE `ChartTimedValueEntity`
  ADD INDEX `IDX_READ_TIMED_DATA` (`date` ASC, `id` ASC);

-- add planned development cost
ALTER TABLE `IterationEntity`
  ADD COLUMN `plannedDevelopmentCost` DECIMAL(19,2) NOT NULL DEFAULT 0;
