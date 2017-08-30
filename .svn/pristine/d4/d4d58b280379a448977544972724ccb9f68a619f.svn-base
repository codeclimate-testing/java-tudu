# ************************************************************
# Tudu Lists database creation.
#
# This script creates the database schema, and adds some
# important default values.
# ************************************************************

# Dump of table property
# ------------------------------------------------------------

CREATE TABLE `property` (
  `pkey` varchar(100) NOT NULL,
  `value` varchar(200) NULL,
  PRIMARY KEY  (`pkey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.host","localhost");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.port","25");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.user","");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.password","");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.from","Tudu Lists");


# Dump of table role
# ------------------------------------------------------------

CREATE TABLE `role` (
  `role` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `role` (`role`) VALUES ("ROLE_ADMIN");
INSERT INTO `role` (`role`) VALUES ("ROLE_USER");


# Dump of table todo
# ------------------------------------------------------------

CREATE TABLE `todo` (
  `id` varchar(32) NOT NULL default '',
  `creation_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `description` varchar(255) NOT NULL default '',
  `priority` int(4) NOT NULL default '0',
  `completed` tinyint(1) NOT NULL default '0',
  `completion_date` datetime default NULL,
  `todo_list_id` varchar(32) NOT NULL default '',
  `due_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `todo_list_id_index` (`todo_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table todo_list
# ------------------------------------------------------------

CREATE TABLE `todo_list` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(100) NOT NULL default '',
  `rss_allowed` tinyint(1) NOT NULL default '0',
  `last_update` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

CREATE TABLE `user` (
  `login` varchar(50) NOT NULL default '',
  `password` varchar(50) NOT NULL default '',
  `enabled` tinyint(1) NOT NULL default '0',
  `first_name` varchar(100) default '',
  `last_name` varchar(100) default '',
  `email` varchar(150) default '',
  `creation_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `last_access_date` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`login`,`password`,`enabled`,`first_name`,`last_name`,`email`,`creation_date`,`last_access_date`) VALUES ("admin","admin","1","","","","2000-01-01 00:00:00","2000-01-01 00:00:00");


# Dump of table user_role
# ------------------------------------------------------------

CREATE TABLE `user_role` (
  `login` varchar(50) NOT NULL default '',
  `role` varchar(50) NOT NULL default '',
  KEY `login_role_index` (`login`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_role` (`login`,`role`) VALUES ("admin","ROLE_ADMIN");
INSERT INTO `user_role` (`login`,`role`) VALUES ("admin","ROLE_USER");


# Dump of table user_todo_list
# ------------------------------------------------------------

CREATE TABLE `user_todo_list` (
  `login` varchar(50) NOT NULL default '',
  `listId` varchar(32) NOT NULL default '',
  KEY `login_role_index` (`login`,`listId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



