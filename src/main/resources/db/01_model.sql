
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
    `project_id` BIGINT not null auto_increment primary key,
    `name` VARCHAR(20) not null
) ENGINE=InnoDB AUTO_INCREMENT=10000000;

ALTER TABLE `project`
ADD UNIQUE `unq_project_name` (`name`);
