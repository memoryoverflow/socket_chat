/*
 Navicat Premium Data Transfer

 Source Server         : www.memoryoverflow.cn
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : www.memoryoverflow.cn:3306
 Source Schema         : sockect_chat

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 04/01/2020 20:02:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `me_id` varchar(64) NOT NULL,
  `talk_user_id` varchar(64) NOT NULL,
  `msg` text NOT NULL,
  `type` int(2) NOT NULL DEFAULT '0',
  `parent_id` int(11) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
BEGIN;
INSERT INTO `tb_message` VALUES (1, '1', '2', 'hello', 0, 0, '2019-12-26 17:12:32', 1);
INSERT INTO `tb_message` VALUES (2, '2', '1', 'hello too !', 0, 1, '2019-12-26 17:13:08', 1);
INSERT INTO `tb_message` VALUES (3, '1', '2', 'what is your name ?', 0, 2, '2019-12-26 17:13:34', 1);
INSERT INTO `tb_message` VALUES (4, '2', '1', '张三。you?', 0, 3, '2019-12-26 17:13:59', 1);
INSERT INTO `tb_message` VALUES (5, '1', '2', '李四', 0, 4, '2019-12-26 17:14:12', 1);
INSERT INTO `tb_message` VALUES (6, '3', '1', '你好，张三；我是王五', 0, 0, '2019-12-29 14:32:19', 1);
INSERT INTO `tb_message` VALUES (7, '3', '1', '在吗？ 张三', 0, 0, '2019-12-29 14:32:29', 1);
INSERT INTO `tb_message` VALUES (8, '2', '1', 'sdf', 0, 0, '2020-01-03 14:57:49', 1);
INSERT INTO `tb_message` VALUES (9, '2', '1', 'sdf', 0, 0, '2020-01-03 15:09:10', 1);
INSERT INTO `tb_message` VALUES (10, '2', '1', 'sdf1111', 0, 0, '2020-01-03 15:09:27', 1);
INSERT INTO `tb_message` VALUES (11, '2', '1', 'sxcs', 0, 0, '2020-01-03 15:09:40', 1);
INSERT INTO `tb_message` VALUES (12, '2', '1', 'vvvvv', 0, 0, '2020-01-03 15:10:01', 1);
INSERT INTO `tb_message` VALUES (13, '2', '1', 'vvvvv3333', 0, 0, '2020-01-03 15:10:22', 1);
INSERT INTO `tb_message` VALUES (14, '1', '2', '干嘛\n', 0, 0, '2020-01-03 15:10:50', 1);
INSERT INTO `tb_message` VALUES (15, '1', '2', '李四你好啊', 0, 0, '2020-01-03 15:17:27', 1);
INSERT INTO `tb_message` VALUES (16, '1', '2', '李四你好啊', 0, 0, '2020-01-03 15:18:06', 1);
INSERT INTO `tb_message` VALUES (17, '1', '2', '李四你好啊', 0, 0, '2020-01-03 15:19:52', 1);
INSERT INTO `tb_message` VALUES (22, '1', '2', 'asda', 0, 0, '2020-01-03 15:26:09', 1);
INSERT INTO `tb_message` VALUES (23, '1', '2', 'asdc', 0, 0, '2020-01-03 15:29:16', 1);
INSERT INTO `tb_message` VALUES (24, '1', '2', 'asdc', 0, 0, '2020-01-03 15:29:44', 1);
INSERT INTO `tb_message` VALUES (25, '1', '2', 'asdc', 0, 0, '2020-01-03 15:29:49', 1);
INSERT INTO `tb_message` VALUES (26, '1', '2', 'asdc', 0, 0, '2020-01-03 15:30:06', 1);
INSERT INTO `tb_message` VALUES (27, '1', '2', 'sa', 0, 0, '2020-01-03 15:32:42', 1);
INSERT INTO `tb_message` VALUES (28, '1', '2', 's', 0, 0, '2020-01-03 15:32:58', 1);
INSERT INTO `tb_message` VALUES (29, '1', '2', '你好', 0, 0, '2020-01-04 15:07:58', 1);
INSERT INTO `tb_message` VALUES (30, '1', '2', '你好', 0, 0, '2020-01-04 15:08:12', 1);
INSERT INTO `tb_message` VALUES (31, '1', '2', '你\n', 0, 0, '2020-01-04 15:09:56', 1);
INSERT INTO `tb_message` VALUES (32, '1', '2', '你', 0, 0, '2020-01-04 15:10:00', 1);
INSERT INTO `tb_message` VALUES (33, '1', '2', '你', 0, 0, '2020-01-04 15:10:28', 1);
INSERT INTO `tb_message` VALUES (34, '1', '2', '你', 0, 0, '2020-01-04 15:11:22', 1);
INSERT INTO `tb_message` VALUES (35, '1', '2', '你好', 0, 0, '2020-01-04 15:11:44', 1);
INSERT INTO `tb_message` VALUES (36, '2', '1', 'hello', 0, 0, '2020-01-04 15:15:44', 1);
INSERT INTO `tb_message` VALUES (37, '2', '1', 'hello', 0, 0, '2020-01-04 15:15:46', 1);
INSERT INTO `tb_message` VALUES (38, '2', '1', '你是不是张三', 0, 0, '2020-01-04 15:27:43', 1);
INSERT INTO `tb_message` VALUES (39, '1', '2', '我是张三啊', 0, 0, '2020-01-04 15:28:29', 1);
INSERT INTO `tb_message` VALUES (40, '2', '1', '你有什么事情吗\n', 0, 0, '2020-01-04 15:28:36', 1);
INSERT INTO `tb_message` VALUES (41, '2', '1', '大傻子\n', 0, 0, '2020-01-04 15:28:40', 1);
INSERT INTO `tb_message` VALUES (42, '1', '2', '你才是大傻子', 0, 0, '2020-01-04 15:28:49', 1);
INSERT INTO `tb_message` VALUES (43, 'bcc781a0aa941dcbf6db8ad21f831f3f', '1', '你好', 0, 0, '2020-01-04 16:16:12', 1);
INSERT INTO `tb_message` VALUES (44, '1', 'bcc781a0aa941dcbf6db8ad21f831f3f', '我是张三', 0, 0, '2020-01-04 18:59:58', 1);
INSERT INTO `tb_message` VALUES (45, 'bcc781a0aa941dcbf6db8ad21f831f3f', '1', '我是李三角', 0, 0, '2020-01-04 19:00:09', 1);
INSERT INTO `tb_message` VALUES (46, '2', '1', '？？\n', 0, 0, '2020-01-04 19:04:56', 1);
INSERT INTO `tb_message` VALUES (47, '3', '1', '1', 0, 0, '2020-01-04 19:05:09', 1);
INSERT INTO `tb_message` VALUES (48, '1', '1213369926949703682', '你', 0, 0, '2020-01-04 19:49:51', 1);
INSERT INTO `tb_message` VALUES (49, '1', '1213369926949703682', 'w', 0, 0, '2020-01-04 19:49:54', 1);
INSERT INTO `tb_message` VALUES (50, '1', '1213369926949703682', '你好\n', 0, 0, '2020-01-04 19:49:57', 1);
INSERT INTO `tb_message` VALUES (51, 'bcc781a0aa941dcbf6db8ad21f831f3f', '1', '落班饮啤酒了喂\n', 0, 0, '2020-01-04 19:54:06', 1);
INSERT INTO `tb_message` VALUES (52, '1213369041322467329', '1', '今晚打老虎\n', 0, 0, '2020-01-04 19:55:18', 0);
INSERT INTO `tb_message` VALUES (53, '1213369041322467329', '1', '黄飞鸿\n', 0, 0, '2020-01-04 19:56:03', 0);
INSERT INTO `tb_message` VALUES (54, '1213369926949703682', '1', '我是李三脚\n', 0, 0, '2020-01-04 19:56:43', 0);
INSERT INTO `tb_message` VALUES (55, '1213369926949703682', '1', '脚气的那个\n', 0, 0, '2020-01-04 19:56:49', 0);
INSERT INTO `tb_message` VALUES (56, '2', '1', '今晚disco  Catwalk 酒吧  \n', 0, 0, '2020-01-04 19:58:23', 1);
INSERT INTO `tb_message` VALUES (57, '1', '2', '有妹子得没\n', 0, 0, '2020-01-04 19:59:26', 1);
INSERT INTO `tb_message` VALUES (58, '2', '1', '多得很啊。个个都是靓女来个喂\n', 0, 0, '2020-01-04 19:59:47', 1);
INSERT INTO `tb_message` VALUES (59, '1', '2', '等住，今晚我埋单\n', 0, 0, '2020-01-04 20:00:02', 1);
INSERT INTO `tb_message` VALUES (60, '2', '1', '👌\n', 0, 0, '2020-01-04 20:00:11', 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `login_key` varchar(64) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `online` tinyint(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', '张三', '123456', '张三', '127.0.0.1', 1, '广东广州');
INSERT INTO `tb_user` VALUES ('1213368731724111874', '永健', NULL, NULL, '127.0.0.1', NULL, 'XX XX 内网IP');
INSERT INTO `tb_user` VALUES ('1213369041322467329', '黄飞鸿', NULL, NULL, '127.0.0.1', 0, 'XX XX 内网IP');
INSERT INTO `tb_user` VALUES ('1213369245102727169', '李小龙', NULL, NULL, '127.0.0.1', NULL, 'XX XX 内网IP');
INSERT INTO `tb_user` VALUES ('1213369596606320641', '史泰龙', NULL, NULL, '127.0.0.1', NULL, 'XX XX 内网IP');
INSERT INTO `tb_user` VALUES ('1213369926949703682', '李三脚', NULL, NULL, '127.0.0.1', 0, 'XX XX 内网IP');
INSERT INTO `tb_user` VALUES ('2', '李四', '123456', '李四', '127.0.0.1', 0, '湖北武汉');
INSERT INTO `tb_user` VALUES ('3', '王五', '123456', '王五', '127.0.0.1', 0, '山西河北');
INSERT INTO `tb_user` VALUES ('4', '赵六', '123456', '赵六', '127.0.0.1', 0, '广州深圳');
INSERT INTO `tb_user` VALUES ('bcc781a0aa941dcbf6db8ad21f831f3f', '李三角', NULL, NULL, '127.0.0.1', 0, 'XX XX 内网IP');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_friend
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_friend`;
CREATE TABLE `tb_user_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `me_id` varchar(64) NOT NULL,
  `friend_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user_friend
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_friend` VALUES (1, '1', '2');
INSERT INTO `tb_user_friend` VALUES (2, '1', '3');
INSERT INTO `tb_user_friend` VALUES (3, '2', '1');
INSERT INTO `tb_user_friend` VALUES (4, '3', '1');
INSERT INTO `tb_user_friend` VALUES (5, '1', '4');
INSERT INTO `tb_user_friend` VALUES (7, 'bcc781a0aa941dcbf6db8ad21f831f3f', '1');
INSERT INTO `tb_user_friend` VALUES (8, '1', '1213369926949703682');
INSERT INTO `tb_user_friend` VALUES (9, '1', 'bcc781a0aa941dcbf6db8ad21f831f3f');
INSERT INTO `tb_user_friend` VALUES (10, '1213369041322467329', '1');
INSERT INTO `tb_user_friend` VALUES (11, '1', '1213369041322467329');
INSERT INTO `tb_user_friend` VALUES (12, '1', '1213369926949703682');
INSERT INTO `tb_user_friend` VALUES (13, '1213369926949703682', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
