
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE TABLE `clean_tuser_todo_list` (
  `login` varchar(50) NOT NULL default '',
  `listId` varchar(32) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO clean_tuser_todo_list SELECT DISTINCT login, listId FROM user_todo_list t;

DROP TABLE user_todo_list;
RENAME TABLE clean_tuser_todo_list to tuser_todo_list;

RENAME TABLE user to tuser;
RENAME TABLE user_role to tuser_role;

ALTER TABLE todo ADD assignedUser_login varchar(50) NULL;
ALTER TABLE todo ADD completion int NOT NULL default '0';
ALTER TABLE todo ADD hasNotes tinyint(1) NOT NULL default '0';
ALTER TABLE todo ADD notes varchar(10000) NULL default '';
ALTER TABLE todo CHANGE creation_date creationDate datetime NOT NULL default '0000-00-00 00:00:00';
ALTER TABLE todo CHANGE completion_date completionDate datetime default NULL;
ALTER TABLE todo CHANGE due_date dueDate datetime default NULL;
ALTER TABLE todo CHANGE todo_list_id todoList_id varchar(32) NOT NULL default '';

ALTER TABLE todo_list CHANGE last_update lastUpdate datetime default NULL;
ALTER TABLE todo_list CHANGE rss_allowed rssAllowed tinyint(1) NOT NULL default '0';

ALTER TABLE tuser ADD dateFormat varchar(10) NULL;
ALTER TABLE tuser CHANGE first_name firstName varchar(100);
ALTER TABLE tuser CHANGE last_name lastName varchar(100);
ALTER TABLE tuser CHANGE creation_date creationDate datetime NOT NULL default '0000-00-00 00:00:00';
ALTER TABLE tuser CHANGE last_access_date lastAccessDate datetime NOT NULL default '0000-00-00 00:00:00';
UPDATE tuser SET dateFormat='MM/dd/yyyy';

ALTER TABLE tuser_todo_list CHANGE login users_login varchar(50) NOT NULL default '';
ALTER TABLE tuser_todo_list CHANGE listId todoLists_id varchar(32) NOT NULL default '';   
ALTER TABLE tuser_todo_list ADD PRIMARY KEY (`users_login`,`todoLists_id`);

ALTER TABLE tuser_role CHANGE login tuser_login varchar(50) NOT NULL default '';
ALTER TABLE tuser_role CHANGE role roles_role varchar(50) NOT NULL default '';
ALTER TABLE tuser_role DROP KEY login_role_index;
ALTER TABLE tuser_role ADD PRIMARY KEY (`tuser_login`,`roles_role`);

ALTER TABLE role ENGINE = InnoDB;


ALTER TABLE todo ADD CONSTRAINT `FK_t_assigned_user` FOREIGN KEY `FK_t_assigned_user` (`assignedUser_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE todo ADD CONSTRAINT `FK_t_todo_list` FOREIGN KEY `FK_t_todo_list` (`todoList_id`)
    REFERENCES `todo_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE tuser_role ADD CONSTRAINT `FK_tr_tuser` FOREIGN KEY `FK_tr_tuser` (`tuser_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
ALTER TABLE tuser_role ADD INDEX `FK_tr_tuser` (`tuser_login`);

ALTER TABLE tuser_role ADD CONSTRAINT `FK_tr_role` FOREIGN KEY `FK_tr_role` (`roles_role`)
    REFERENCES `role` (`role`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE tuser_todo_list ADD CONSTRAINT `FK_ttl_users` FOREIGN KEY `FK_ttl_users` (`users_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
ALTER TABLE tuser_todo_list ADD INDEX `FK_ttl_users` (`users_login`);

ALTER TABLE tuser_todo_list ADD CONSTRAINT `FK_ttl_todo_list` FOREIGN KEY `FK_ttl_todo_list` (`todoLists_id`)
    REFERENCES `todo_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


