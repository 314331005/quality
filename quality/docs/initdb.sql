/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2014-11-24 09:49:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_code`
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `ID` varchar(36) NOT NULL,
  `CODE` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `TYPE` varchar(50) NOT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `SEQNO` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_code
-- ----------------------------
INSERT INTO `SYS_CODE` VALUES ('cc18ca59-0204-486f-bd9b-0ff319a3c0c5', '1', '重要', 'IMPORTENT', '1', '1');
INSERT INTO `SYS_CODE` VALUES ('3c046a27-bc1b-4e93-a9ed-8942e5af5313', '2', '中等', 'IMPORTENT', '1', '2');
INSERT INTO `SYS_CODE` VALUES ('1f439a1e-0152-480f-ac78-393ce827e2c7', '3', '一般', 'IMPORTENT', '1', '3');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `ID` varchar(36) NOT NULL DEFAULT '' COMMENT '编号',
  `PID` varchar(36) DEFAULT '' COMMENT '上级部门ID',
  `DEPT_CODE` varchar(20) NOT NULL COMMENT '部门编码',
  `DEPT_NAME` varchar(50) NOT NULL COMMENT '部门名称',
  `DEPT_DESC` varchar(100) DEFAULT NULL COMMENT '部门描述',
  `LEVE` int(1) DEFAULT NULL COMMENT '层级（level为关键字）',
  `SEQNO` int(4) DEFAULT NULL COMMENT '排序号',
  `STATUS` int(1) DEFAULT NULL COMMENT '是否有效：1-有效， 0-无效',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` varchar(36) NOT NULL DEFAULT '' COMMENT '编号',
  `PID` varchar(36) DEFAULT NULL COMMENT '上级菜单ID',
  `MENU_CODE` varchar(20) NOT NULL COMMENT '菜单编码',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单名称',
  `MENU_DESC` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `MENU_LINK` varchar(100) DEFAULT NULL,
  `LEVE` int(1) DEFAULT NULL COMMENT '层级（level为关键字）',
  `SEQNO` int(4) DEFAULT NULL COMMENT '排序号',
  `STATUS` int(1) DEFAULT NULL COMMENT '是否有效：1-有效， 0-无效',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `SYS_MENU` VALUES ('eb755d9c-3430-44a1-b761-42347df98f96', '', '01', '系统管理', '系统管理', '', '1', '6', '1');
INSERT INTO `SYS_MENU` VALUES ('6d0ff035-d4f8-47ae-8ec0-bde93e2ff673', 'eb755d9c-3430-44a1-b761-42347df98f96', '0101', '菜单管理', '菜单管理', '/system/menu/list', '2', '1', '1');
INSERT INTO `SYS_MENU` VALUES ('a737a3ff-2d91-408e-b586-d88f4ef479de', 'eb755d9c-3430-44a1-b761-42347df98f96', '0102', '部门管理', '部门管理', '/system/dept/list', '2', '2', '1'); 
INSERT INTO `SYS_MENU` VALUES ('c6e4f14c-8b02-4738-a64f-df0228268634', 'eb755d9c-3430-44a1-b761-42347df98f96', '0103', '用户管理', '用户管理', '/system/user/list', '2', '3', '1');
INSERT INTO `SYS_MENU` VALUES ('2b97fba6-76a8-4526-a8a1-957f92127aef', 'eb755d9c-3430-44a1-b761-42347df98f96', '0104', '编码管理', '编码管理', '/system/code/list', '2', '4', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` varchar(36) NOT NULL DEFAULT '' COMMENT '编号',
  `ROLE_CODE` varchar(20) NOT NULL COMMENT '角色编码',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `STATUS` int(1) DEFAULT NULL COMMENT '是否有效：1-有效， 0-无效',
  `DEPT_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `rid` varchar(36) NOT NULL,
  `mid` varchar(36) NOT NULL,
  UNIQUE KEY `PK_SYS_ROLE_MENU` (`rid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` varchar(36) NOT NULL DEFAULT '' COMMENT '编号',
  `LOGIN_NAME` varchar(16) NOT NULL COMMENT '登录名',
  `LOGIN_PASS` varchar(32) NOT NULL COMMENT '登录密码',
  `USER_NAME` varchar(50) NOT NULL COMMENT '真实姓名',
  `IDCARD` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `TYPE` int(1) DEFAULT NULL COMMENT '用户类型（1：系统管理员，2：部门管理员，3：普通用户）',
  `MOBILE_PHONE` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态：是否激活(1：激活，0：无效)',
  `DEPT_ID` varchar(36) NOT NULL,
  `INDEX_OPER_PRIV` int(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4a03b08b-b532-4a81-8441-1a9be50420c5', 'admin', 'a66abb5684c45962d887564f08346e8d', '系统管理员', '', '1', '', '', '1', 'dea4cb8d-3e11-11e4-b1dc-7824af411e81', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `UID` varchar(36) NOT NULL,
  `RID` varchar(36) NOT NULL,
  UNIQUE KEY `pk_sys_user_role` (`UID`,`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for `view_sys_role`
-- ----------------------------
CREATE OR REPLACE VIEW view_sys_role AS 
select `r`.`ID` AS `id`,`r`.`ROLE_CODE` AS `role_code`,`r`.`ROLE_NAME` AS `role_name`,`r`.`ROLE_DESC` AS `role_desc`,`r`.`STATUS` AS `status`,`r`.`DEPT_ID` AS `dept_id`,`d`.`DEPT_CODE` AS `dept_code`,`d`.`DEPT_NAME` AS `dept_name` 
from (`sys_role` `r` join `sys_dept` `d` on((`r`.`DEPT_ID` = `d`.`ID`)));

-- ----------------------------
-- View structure for `view_sys_user`
-- ----------------------------
CREATE OR REPLACE VIEW view_sys_user AS 
select  u.ID AS id,u.LOGIN_NAME AS login_name,u.LOGIN_PASS AS login_pass,
	u.USER_NAME AS user_name,u.IDCARD AS idcard,u.MOBILE_PHONE AS mobile_phone,
	u.EMAIL AS email,u.TYPE AS type,u.STATUS AS status,u.INDEX_OPER_PRIV AS index_oper_priv,
	u.DEPT_ID AS dept_id,d.DEPT_CODE AS dept_code,d.DEPT_NAME AS dept_name,
	d.STATUS AS dept_status from (sys_user u join sys_dept d on((u.DEPT_ID = d.ID and u.type<>1)))
union all
select  u.ID AS id,u.LOGIN_NAME AS login_name,u.LOGIN_PASS AS login_pass,
	u.USER_NAME AS user_name,u.IDCARD AS idcard,u.MOBILE_PHONE AS mobile_phone,
	u.EMAIL AS email,u.TYPE AS type,u.STATUS AS status,u.INDEX_OPER_PRIV AS index_oper_priv,
	'0' AS dept_id,'0' AS dept_code,'XTGL' AS dept_name, 1 AS dept_status from sys_user u 
	where u.type=1
-- ----------------------------
-- View structure for `view_sys_menu`
-- ----------------------------	
create or replace view view_sys_menu as 
select id as id, pid as pid, menu_code as menu_code, menu_name as menu_name, menu_desc as menu_desc, menu_link as menu_link, leve as leve, seqno as seqno, status as status, 1 as flag from sys_menu 
union all
select code as id, '' as pid, code as menu_code, name as  menu_name, name as menu_desc, '' as menu_link,1 as leve, seqno as seqno, status as status,0 as flag from sys_code where (sys_code.type = 'SYS_APP')

-- ----------------------------
-- View structure for `view_sys_dept`
-- ----------------------------	
create or replace view view_sys_dept as 
select id as id, pid as pid, dept_code as dept_code, dept_name as dept_name, dept_desc as dept_desc, leve as leve, seqno as seqno, status as status, 1 as flag from sys_dept
union all
select code as id, '' as pid, code as dept_code, name as  dept_name, name as dept_desc, 1 as leve, seqno as seqno, status as status, 0 as flag from sys_code where (sys_code.type = 'SYS_APP')
