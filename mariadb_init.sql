-- --------------------------------------------------------
-- 主机:                           203.195.186.200
-- 服务器版本:                        5.5.5-10.0.13-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 startup 的数据库结构
CREATE DATABASE IF NOT EXISTS `startup` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `startup`;


-- 导出  表 startup.login_record 结构
CREATE TABLE IF NOT EXISTS `login_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(32) NOT NULL COMMENT '登录名',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `ip` char(15) DEFAULT NULL COMMENT '登录ip ',
  `result` char(1) NOT NULL DEFAULT '0' COMMENT '登录结果:0:失败 1:成功',
  `description` varchar(128) NOT NULL DEFAULT '0' COMMENT '描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录表';

-- 数据导出被取消选择。


-- 导出  表 startup.menu 结构
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0' COMMENT '菜单名',
  `url` varchar(32) NOT NULL DEFAULT '0' COMMENT '资源路径',
  `parent` int(11) NOT NULL DEFAULT '-1',
  `privilegeId` varchar(32) NOT NULL DEFAULT '-1',
  `priority` int(11) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(32) NOT NULL DEFAULT 'system',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 startup.privilege 结构
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '0' COMMENT '权限名',
  `parent` varchar(32) NOT NULL DEFAULT '-1' COMMENT '父id，-1表示无',
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(32) DEFAULT 'system',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限';

-- 数据导出被取消选择。


-- 导出  表 startup.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` varchar(32) NOT NULL DEFAULT '0',
  `name` varchar(32) NOT NULL DEFAULT '0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(50) DEFAULT 'system',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- 数据导出被取消选择。


-- 导出  表 startup.role_privilege 结构
CREATE TABLE IF NOT EXISTS `role_privilege` (
  `roleId` varchar(32) NOT NULL,
  `privilegeId` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 startup.sms_record 结构
CREATE TABLE IF NOT EXISTS `sms_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `code` char(6) NOT NULL COMMENT '验证码',
  `type` char(1) DEFAULT '0' COMMENT '0:注册验证码 1:密码找回验证码2:修改个人信息',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `expireTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送记录表';

-- 数据导出被取消选择。


-- 导出  表 startup.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `uid` char(32) NOT NULL COMMENT '用户id',
  `loginName` varchar(16) DEFAULT NULL,
  `password` char(32) NOT NULL,
  `nickName` varchar(32) DEFAULT '' COMMENT '昵称',
  `phone` char(11) NOT NULL,
  `avatar` varchar(128) DEFAULT '' COMMENT '头像url',
  `email` varchar(64) DEFAULT '',
  `isEmailChecked` char(1) DEFAULT '0' COMMENT '邮箱是否被验证过 0:否 1:是',
  `type` char(1) DEFAULT NULL COMMENT '用户类型 ',
  `status` char(1) DEFAULT '1' COMMENT '用户状态 0:未激活 1:激活',
  `lastLoginTime` timestamp NULL DEFAULT NULL COMMENT '上次登录时间',
  `lastLoginIp` varchar(15) DEFAULT '' COMMENT '上次登录IP地址',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- 数据导出被取消选择。


-- 导出  表 startup.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `userId` int(11) unsigned NOT NULL,
  `roleId` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
