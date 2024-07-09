/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100424
 Source Host           : localhost:3306
 Source Schema         : focus_app_db

 Target Server Type    : MySQL
 Target Server Version : 100424
 File Encoding         : 65001

 Date: 05/07/2024 09:21:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for achivements
-- ----------------------------
DROP TABLE IF EXISTS `achivements`;
CREATE TABLE `achivements`  (
  `date` date NULL DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total_time` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKex7obb7ejdjcetcqy15i3ut06`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKex7obb7ejdjcetcqy15i3ut06` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of achivements
-- ----------------------------
INSERT INTO `achivements` VALUES ('2024-07-04', 1, 1500000, 3);

-- ----------------------------
-- Table structure for course_group_courses
-- ----------------------------
DROP TABLE IF EXISTS `course_group_courses`;
CREATE TABLE `course_group_courses`  (
  `course_id` int NOT NULL,
  `course_group_id` bigint NOT NULL,
  PRIMARY KEY (`course_group_id`, `course_id`) USING BTREE,
  INDEX `FK2bdl8cuoglukrqn8g7s3eq8m1`(`course_id` ASC) USING BTREE,
  CONSTRAINT `FK2bdl8cuoglukrqn8g7s3eq8m1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8ytvldx15n8tvn7bogkdwjxi` FOREIGN KEY (`course_group_id`) REFERENCES `course_groups` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_group_courses
-- ----------------------------
INSERT INTO `course_group_courses` VALUES (200101, 1);
INSERT INTO `course_group_courses` VALUES (200102, 1);
INSERT INTO `course_group_courses` VALUES (200103, 1);
INSERT INTO `course_group_courses` VALUES (200105, 1);
INSERT INTO `course_group_courses` VALUES (200107, 1);
INSERT INTO `course_group_courses` VALUES (200201, 1);
INSERT INTO `course_group_courses` VALUES (200202, 1);
INSERT INTO `course_group_courses` VALUES (202108, 1);
INSERT INTO `course_group_courses` VALUES (202109, 1);
INSERT INTO `course_group_courses` VALUES (202110, 1);
INSERT INTO `course_group_courses` VALUES (202121, 1);
INSERT INTO `course_group_courses` VALUES (202206, 1);
INSERT INTO `course_group_courses` VALUES (202501, 1);
INSERT INTO `course_group_courses` VALUES (202502, 1);
INSERT INTO `course_group_courses` VALUES (202622, 1);
INSERT INTO `course_group_courses` VALUES (213603, 1);
INSERT INTO `course_group_courses` VALUES (213604, 1);
INSERT INTO `course_group_courses` VALUES (214201, 1);
INSERT INTO `course_group_courses` VALUES (214389, 1);
INSERT INTO `course_group_courses` VALUES (214231, 2);
INSERT INTO `course_group_courses` VALUES (214241, 2);
INSERT INTO `course_group_courses` VALUES (214242, 2);
INSERT INTO `course_group_courses` VALUES (214251, 2);
INSERT INTO `course_group_courses` VALUES (214252, 2);
INSERT INTO `course_group_courses` VALUES (214274, 2);
INSERT INTO `course_group_courses` VALUES (214321, 2);
INSERT INTO `course_group_courses` VALUES (214331, 2);
INSERT INTO `course_group_courses` VALUES (214352, 2);
INSERT INTO `course_group_courses` VALUES (214354, 2);
INSERT INTO `course_group_courses` VALUES (214362, 2);
INSERT INTO `course_group_courses` VALUES (214370, 2);
INSERT INTO `course_group_courses` VALUES (214441, 2);
INSERT INTO `course_group_courses` VALUES (214442, 2);
INSERT INTO `course_group_courses` VALUES (214461, 2);
INSERT INTO `course_group_courses` VALUES (214462, 2);
INSERT INTO `course_group_courses` VALUES (214463, 2);
INSERT INTO `course_group_courses` VALUES (202620, 3);
INSERT INTO `course_group_courses` VALUES (208453, 3);
INSERT INTO `course_group_courses` VALUES (214353, 4);
INSERT INTO `course_group_courses` VALUES (214451, 4);
INSERT INTO `course_group_courses` VALUES (214372, 5);
INSERT INTO `course_group_courses` VALUES (214386, 5);
INSERT INTO `course_group_courses` VALUES (214390, 5);
INSERT INTO `course_group_courses` VALUES (214282, 6);
INSERT INTO `course_group_courses` VALUES (214464, 6);
INSERT INTO `course_group_courses` VALUES (214465, 6);
INSERT INTO `course_group_courses` VALUES (214492, 6);
INSERT INTO `course_group_courses` VALUES (214493, 6);
INSERT INTO `course_group_courses` VALUES (214273, 7);
INSERT INTO `course_group_courses` VALUES (214291, 7);
INSERT INTO `course_group_courses` VALUES (214379, 7);
INSERT INTO `course_group_courses` VALUES (214388, 7);
INSERT INTO `course_group_courses` VALUES (214485, 7);
INSERT INTO `course_group_courses` VALUES (214490, 7);
INSERT INTO `course_group_courses` VALUES (214271, 8);
INSERT INTO `course_group_courses` VALUES (214292, 8);
INSERT INTO `course_group_courses` VALUES (214293, 8);
INSERT INTO `course_group_courses` VALUES (214383, 8);
INSERT INTO `course_group_courses` VALUES (214491, 8);
INSERT INTO `course_group_courses` VALUES (214290, 9);
INSERT INTO `course_group_courses` VALUES (214471, 9);
INSERT INTO `course_group_courses` VALUES (214483, 9);
INSERT INTO `course_group_courses` VALUES (214286, 10);
INSERT INTO `course_group_courses` VALUES (214374, 10);
INSERT INTO `course_group_courses` VALUES (214984, 10);
INSERT INTO `course_group_courses` VALUES (214987, 10);
INSERT INTO `course_group_courses` VALUES (214988, 10);
INSERT INTO `course_group_courses` VALUES (214989, 10);
INSERT INTO `course_group_courses` VALUES (200101, 11);
INSERT INTO `course_group_courses` VALUES (200102, 11);
INSERT INTO `course_group_courses` VALUES (200103, 11);
INSERT INTO `course_group_courses` VALUES (200105, 11);
INSERT INTO `course_group_courses` VALUES (200107, 11);
INSERT INTO `course_group_courses` VALUES (200201, 11);
INSERT INTO `course_group_courses` VALUES (200202, 11);
INSERT INTO `course_group_courses` VALUES (202108, 11);
INSERT INTO `course_group_courses` VALUES (202109, 11);
INSERT INTO `course_group_courses` VALUES (202110, 11);
INSERT INTO `course_group_courses` VALUES (202121, 11);
INSERT INTO `course_group_courses` VALUES (202206, 11);
INSERT INTO `course_group_courses` VALUES (202501, 11);
INSERT INTO `course_group_courses` VALUES (202502, 11);
INSERT INTO `course_group_courses` VALUES (202622, 11);
INSERT INTO `course_group_courses` VALUES (213603, 11);
INSERT INTO `course_group_courses` VALUES (213604, 11);
INSERT INTO `course_group_courses` VALUES (214201, 11);
INSERT INTO `course_group_courses` VALUES (214389, 11);
INSERT INTO `course_group_courses` VALUES (214231, 12);
INSERT INTO `course_group_courses` VALUES (214241, 12);
INSERT INTO `course_group_courses` VALUES (214242, 12);
INSERT INTO `course_group_courses` VALUES (214251, 12);
INSERT INTO `course_group_courses` VALUES (214252, 12);
INSERT INTO `course_group_courses` VALUES (214274, 12);
INSERT INTO `course_group_courses` VALUES (214321, 12);
INSERT INTO `course_group_courses` VALUES (214331, 12);
INSERT INTO `course_group_courses` VALUES (214351, 12);
INSERT INTO `course_group_courses` VALUES (214352, 12);
INSERT INTO `course_group_courses` VALUES (214361, 12);
INSERT INTO `course_group_courses` VALUES (214370, 12);
INSERT INTO `course_group_courses` VALUES (214441, 12);
INSERT INTO `course_group_courses` VALUES (214442, 12);
INSERT INTO `course_group_courses` VALUES (214461, 12);
INSERT INTO `course_group_courses` VALUES (214462, 12);
INSERT INTO `course_group_courses` VALUES (214463, 12);
INSERT INTO `course_group_courses` VALUES (202620, 13);
INSERT INTO `course_group_courses` VALUES (208407, 13);
INSERT INTO `course_group_courses` VALUES (208453, 13);
INSERT INTO `course_group_courses` VALUES (214271, 13);
INSERT INTO `course_group_courses` VALUES (214273, 13);
INSERT INTO `course_group_courses` VALUES (214282, 13);
INSERT INTO `course_group_courses` VALUES (214285, 13);
INSERT INTO `course_group_courses` VALUES (214290, 13);
INSERT INTO `course_group_courses` VALUES (214292, 13);
INSERT INTO `course_group_courses` VALUES (214293, 13);
INSERT INTO `course_group_courses` VALUES (214353, 13);
INSERT INTO `course_group_courses` VALUES (214372, 13);
INSERT INTO `course_group_courses` VALUES (214379, 13);
INSERT INTO `course_group_courses` VALUES (214383, 13);
INSERT INTO `course_group_courses` VALUES (214386, 13);
INSERT INTO `course_group_courses` VALUES (214387, 13);
INSERT INTO `course_group_courses` VALUES (214388, 13);
INSERT INTO `course_group_courses` VALUES (214451, 13);
INSERT INTO `course_group_courses` VALUES (214464, 13);
INSERT INTO `course_group_courses` VALUES (214465, 13);
INSERT INTO `course_group_courses` VALUES (214471, 13);
INSERT INTO `course_group_courses` VALUES (214483, 13);
INSERT INTO `course_group_courses` VALUES (214485, 13);
INSERT INTO `course_group_courses` VALUES (214491, 13);
INSERT INTO `course_group_courses` VALUES (214492, 13);
INSERT INTO `course_group_courses` VALUES (214493, 13);
INSERT INTO `course_group_courses` VALUES (214286, 14);
INSERT INTO `course_group_courses` VALUES (214291, 14);
INSERT INTO `course_group_courses` VALUES (214374, 14);
INSERT INTO `course_group_courses` VALUES (214490, 14);
INSERT INTO `course_group_courses` VALUES (214986, 14);
INSERT INTO `course_group_courses` VALUES (214987, 14);
INSERT INTO `course_group_courses` VALUES (214988, 14);

-- ----------------------------
-- Table structure for course_groups
-- ----------------------------
DROP TABLE IF EXISTS `course_groups`;
CREATE TABLE `course_groups`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `min_credits` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `training_program_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKl1c515kchdjefti1x6rim86tc`(`training_program_id` ASC) USING BTREE,
  CONSTRAINT `FKl1c515kchdjefti1x6rim86tc` FOREIGN KEY (`training_program_id`) REFERENCES `training_program` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_groups
-- ----------------------------
INSERT INTO `course_groups` VALUES (1, 48, 'Khối kiến thức cơ bản - Bắt buộc', 7);
INSERT INTO `course_groups` VALUES (2, 61, 'Khối kiến thức cơ sở ngành - Bắt buộc', 7);
INSERT INTO `course_groups` VALUES (3, 2, 'Nhóm học phần bắt buộc tự chọn 0301', 7);
INSERT INTO `course_groups` VALUES (4, 3, 'Nhóm học phần bắt buộc tự chọn 0302', 7);
INSERT INTO `course_groups` VALUES (5, 4, 'Nhóm học phần bắt buộc tự chọn 0303', 7);
INSERT INTO `course_groups` VALUES (6, 7, 'Nhóm học phần bắt buộc tự chọn 0304', 7);
INSERT INTO `course_groups` VALUES (7, 12, 'Nhóm học phần bắt buộc tự chọn 0305', 7);
INSERT INTO `course_groups` VALUES (8, 6, 'Nhóm học phần bắt buộc tự chọn 0306', 7);
INSERT INTO `course_groups` VALUES (9, 3, 'Nhóm học phần bắt buộc tự chọn 0307', 7);
INSERT INTO `course_groups` VALUES (10, 12, 'Nhóm học phần bắt buộc tự chọn 0308', 7);
INSERT INTO `course_groups` VALUES (11, 48, 'Khối kiến thức cơ bản - Bắt buộc', 5);
INSERT INTO `course_groups` VALUES (12, 61, 'Khối kiến thức cơ sở ngành - Bắt buộc', 5);
INSERT INTO `course_groups` VALUES (13, 37, 'Nhóm học phần bắt buộc tự chọn 0301', 5);
INSERT INTO `course_groups` VALUES (14, 12, 'Nhóm học phần bắt buộc tự chọn 0302', 5);

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `date_num_type` int NULL DEFAULT NULL,
  `study_slot` int NULL DEFAULT NULL,
  `num_of_lession` int NULL DEFAULT NULL,
  `is_practice` bit(1) NULL DEFAULT NULL,
  `week_id` bigint NULL DEFAULT NULL,
  `user_course_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqpatk8qmr3lc6frjlsgpf5bny`(`week_id` ASC) USING BTREE,
  INDEX `FK73ota4ncc7u6bhfh0dihg0b7j`(`user_course_id` ASC) USING BTREE,
  CONSTRAINT `FK73ota4ncc7u6bhfh0dihg0b7j` FOREIGN KEY (`user_course_id`) REFERENCES `user_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqpatk8qmr3lc6frjlsgpf5bny` FOREIGN KEY (`week_id`) REFERENCES `weeks` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 370 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_schedule
-- ----------------------------
INSERT INTO `course_schedule` VALUES (193, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 96, 11);
INSERT INTO `course_schedule` VALUES (194, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 96, 12);
INSERT INTO `course_schedule` VALUES (195, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 97, 11);
INSERT INTO `course_schedule` VALUES (196, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 97, 12);
INSERT INTO `course_schedule` VALUES (197, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 98, 11);
INSERT INTO `course_schedule` VALUES (198, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 98, 12);
INSERT INTO `course_schedule` VALUES (199, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 99, 11);
INSERT INTO `course_schedule` VALUES (200, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 99, 12);
INSERT INTO `course_schedule` VALUES (201, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 100, 11);
INSERT INTO `course_schedule` VALUES (202, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 100, 12);
INSERT INTO `course_schedule` VALUES (203, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 101, 11);
INSERT INTO `course_schedule` VALUES (204, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 101, 12);
INSERT INTO `course_schedule` VALUES (205, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 102, 11);
INSERT INTO `course_schedule` VALUES (206, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 102, 12);
INSERT INTO `course_schedule` VALUES (207, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 103, 11);
INSERT INTO `course_schedule` VALUES (208, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 103, 12);
INSERT INTO `course_schedule` VALUES (209, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 104, 11);
INSERT INTO `course_schedule` VALUES (210, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 104, 12);
INSERT INTO `course_schedule` VALUES (211, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 105, 11);
INSERT INTO `course_schedule` VALUES (212, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 105, 12);
INSERT INTO `course_schedule` VALUES (213, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 106, 11);
INSERT INTO `course_schedule` VALUES (214, 'HD205-Khu Hướng Dương', 6, 1, 6, b'0', 106, 12);
INSERT INTO `course_schedule` VALUES (215, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 107, 11);
INSERT INTO `course_schedule` VALUES (216, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 108, 11);
INSERT INTO `course_schedule` VALUES (217, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 109, 11);
INSERT INTO `course_schedule` VALUES (218, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 110, 11);
INSERT INTO `course_schedule` VALUES (219, 'RD104-Khu Rạng Đông', 5, 4, 3, b'0', 111, 11);
INSERT INTO `course_schedule` VALUES (220, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 96, 122);
INSERT INTO `course_schedule` VALUES (221, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 96, 125);
INSERT INTO `course_schedule` VALUES (222, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 96, 128);
INSERT INTO `course_schedule` VALUES (223, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 96, 126);
INSERT INTO `course_schedule` VALUES (224, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 96, 127);
INSERT INTO `course_schedule` VALUES (225, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 96, 124);
INSERT INTO `course_schedule` VALUES (226, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 97, 122);
INSERT INTO `course_schedule` VALUES (227, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 97, 127);
INSERT INTO `course_schedule` VALUES (228, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 97, 125);
INSERT INTO `course_schedule` VALUES (229, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 97, 128);
INSERT INTO `course_schedule` VALUES (230, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 97, 126);
INSERT INTO `course_schedule` VALUES (231, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 97, 124);
INSERT INTO `course_schedule` VALUES (232, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 98, 122);
INSERT INTO `course_schedule` VALUES (233, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 98, 127);
INSERT INTO `course_schedule` VALUES (234, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 98, 125);
INSERT INTO `course_schedule` VALUES (235, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 98, 128);
INSERT INTO `course_schedule` VALUES (236, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 98, 126);
INSERT INTO `course_schedule` VALUES (237, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 98, 124);
INSERT INTO `course_schedule` VALUES (238, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 98, 123);
INSERT INTO `course_schedule` VALUES (239, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 99, 122);
INSERT INTO `course_schedule` VALUES (240, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 99, 127);
INSERT INTO `course_schedule` VALUES (241, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 99, 125);
INSERT INTO `course_schedule` VALUES (242, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 99, 128);
INSERT INTO `course_schedule` VALUES (243, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 99, 126);
INSERT INTO `course_schedule` VALUES (244, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 99, 124);
INSERT INTO `course_schedule` VALUES (245, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 99, 123);
INSERT INTO `course_schedule` VALUES (246, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 100, 122);
INSERT INTO `course_schedule` VALUES (247, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 100, 127);
INSERT INTO `course_schedule` VALUES (248, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 100, 125);
INSERT INTO `course_schedule` VALUES (249, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 100, 128);
INSERT INTO `course_schedule` VALUES (250, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 100, 126);
INSERT INTO `course_schedule` VALUES (251, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 100, 124);
INSERT INTO `course_schedule` VALUES (252, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 100, 123);
INSERT INTO `course_schedule` VALUES (253, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 101, 122);
INSERT INTO `course_schedule` VALUES (254, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 101, 127);
INSERT INTO `course_schedule` VALUES (255, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 101, 125);
INSERT INTO `course_schedule` VALUES (256, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 101, 128);
INSERT INTO `course_schedule` VALUES (257, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 101, 126);
INSERT INTO `course_schedule` VALUES (258, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 101, 124);
INSERT INTO `course_schedule` VALUES (259, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 101, 123);
INSERT INTO `course_schedule` VALUES (260, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 102, 122);
INSERT INTO `course_schedule` VALUES (261, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 102, 127);
INSERT INTO `course_schedule` VALUES (262, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 102, 125);
INSERT INTO `course_schedule` VALUES (263, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 102, 128);
INSERT INTO `course_schedule` VALUES (264, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 102, 126);
INSERT INTO `course_schedule` VALUES (265, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 102, 124);
INSERT INTO `course_schedule` VALUES (266, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 102, 123);
INSERT INTO `course_schedule` VALUES (267, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 103, 122);
INSERT INTO `course_schedule` VALUES (268, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 103, 127);
INSERT INTO `course_schedule` VALUES (269, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 103, 125);
INSERT INTO `course_schedule` VALUES (270, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 103, 128);
INSERT INTO `course_schedule` VALUES (271, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 103, 126);
INSERT INTO `course_schedule` VALUES (272, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 103, 124);
INSERT INTO `course_schedule` VALUES (273, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 103, 123);
INSERT INTO `course_schedule` VALUES (274, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 104, 122);
INSERT INTO `course_schedule` VALUES (275, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 104, 127);
INSERT INTO `course_schedule` VALUES (276, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 104, 125);
INSERT INTO `course_schedule` VALUES (277, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 104, 128);
INSERT INTO `course_schedule` VALUES (278, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 104, 126);
INSERT INTO `course_schedule` VALUES (279, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 104, 124);
INSERT INTO `course_schedule` VALUES (280, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 104, 123);
INSERT INTO `course_schedule` VALUES (281, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 105, 122);
INSERT INTO `course_schedule` VALUES (282, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 105, 127);
INSERT INTO `course_schedule` VALUES (283, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 105, 125);
INSERT INTO `course_schedule` VALUES (284, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 105, 128);
INSERT INTO `course_schedule` VALUES (285, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 105, 126);
INSERT INTO `course_schedule` VALUES (286, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 105, 124);
INSERT INTO `course_schedule` VALUES (287, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 105, 123);
INSERT INTO `course_schedule` VALUES (288, 'RD100-Hội trường Rạng Đông', 2, 4, 3, b'0', 106, 122);
INSERT INTO `course_schedule` VALUES (289, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 106, 127);
INSERT INTO `course_schedule` VALUES (290, 'HD303-Khu Hướng Dương', 3, 1, 3, b'0', 106, 125);
INSERT INTO `course_schedule` VALUES (291, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 106, 128);
INSERT INTO `course_schedule` VALUES (292, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 106, 126);
INSERT INTO `course_schedule` VALUES (293, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 106, 124);
INSERT INTO `course_schedule` VALUES (294, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 106, 123);
INSERT INTO `course_schedule` VALUES (295, 'P3-Phòng máy K.CNTT', 2, 10, 3, b'1', 107, 127);
INSERT INTO `course_schedule` VALUES (296, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 107, 128);
INSERT INTO `course_schedule` VALUES (297, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 107, 126);
INSERT INTO `course_schedule` VALUES (298, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 107, 125);
INSERT INTO `course_schedule` VALUES (299, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 107, 124);
INSERT INTO `course_schedule` VALUES (300, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 107, 123);
INSERT INTO `course_schedule` VALUES (301, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 108, 128);
INSERT INTO `course_schedule` VALUES (302, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 108, 126);
INSERT INTO `course_schedule` VALUES (303, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 108, 124);
INSERT INTO `course_schedule` VALUES (304, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 108, 123);
INSERT INTO `course_schedule` VALUES (305, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 109, 128);
INSERT INTO `course_schedule` VALUES (306, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 109, 126);
INSERT INTO `course_schedule` VALUES (307, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 109, 124);
INSERT INTO `course_schedule` VALUES (308, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 109, 123);
INSERT INTO `course_schedule` VALUES (309, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 110, 128);
INSERT INTO `course_schedule` VALUES (310, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 110, 126);
INSERT INTO `course_schedule` VALUES (311, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 110, 124);
INSERT INTO `course_schedule` VALUES (312, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 110, 123);
INSERT INTO `course_schedule` VALUES (313, 'TV101-Khu Tường Vi', 3, 4, 3, b'0', 111, 128);
INSERT INTO `course_schedule` VALUES (314, 'RD206-Khu Rạng Đông', 4, 1, 3, b'0', 111, 126);
INSERT INTO `course_schedule` VALUES (315, 'CT102-Khu Cát Tường', 6, 1, 3, b'0', 111, 124);
INSERT INTO `course_schedule` VALUES (316, 'PV319-Khu Phượng Vỹ', 6, 7, 3, b'0', 111, 123);
INSERT INTO `course_schedule` VALUES (317, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 97, 11);
INSERT INTO `course_schedule` VALUES (318, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 98, 11);
INSERT INTO `course_schedule` VALUES (319, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 99, 11);
INSERT INTO `course_schedule` VALUES (320, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 100, 11);
INSERT INTO `course_schedule` VALUES (321, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 101, 11);
INSERT INTO `course_schedule` VALUES (322, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 102, 11);
INSERT INTO `course_schedule` VALUES (323, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 103, 11);
INSERT INTO `course_schedule` VALUES (324, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 104, 11);
INSERT INTO `course_schedule` VALUES (325, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 105, 11);
INSERT INTO `course_schedule` VALUES (326, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 106, 11);
INSERT INTO `course_schedule` VALUES (327, 'P3-Phòng máy K.CNTT', 5, 7, 3, b'1', 107, 11);
INSERT INTO `course_schedule` VALUES (328, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 97, 126);
INSERT INTO `course_schedule` VALUES (329, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 97, 128);
INSERT INTO `course_schedule` VALUES (330, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 97, 127);
INSERT INTO `course_schedule` VALUES (331, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 97, 125);
INSERT INTO `course_schedule` VALUES (332, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 98, 126);
INSERT INTO `course_schedule` VALUES (333, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 98, 128);
INSERT INTO `course_schedule` VALUES (334, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 98, 127);
INSERT INTO `course_schedule` VALUES (335, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 98, 125);
INSERT INTO `course_schedule` VALUES (336, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 99, 126);
INSERT INTO `course_schedule` VALUES (337, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 99, 128);
INSERT INTO `course_schedule` VALUES (338, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 99, 127);
INSERT INTO `course_schedule` VALUES (339, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 99, 125);
INSERT INTO `course_schedule` VALUES (340, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 100, 126);
INSERT INTO `course_schedule` VALUES (341, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 100, 128);
INSERT INTO `course_schedule` VALUES (342, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 100, 127);
INSERT INTO `course_schedule` VALUES (343, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 100, 125);
INSERT INTO `course_schedule` VALUES (344, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 101, 126);
INSERT INTO `course_schedule` VALUES (345, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 101, 128);
INSERT INTO `course_schedule` VALUES (346, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 101, 127);
INSERT INTO `course_schedule` VALUES (347, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 101, 125);
INSERT INTO `course_schedule` VALUES (348, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 102, 126);
INSERT INTO `course_schedule` VALUES (349, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 102, 128);
INSERT INTO `course_schedule` VALUES (350, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 102, 127);
INSERT INTO `course_schedule` VALUES (351, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 102, 125);
INSERT INTO `course_schedule` VALUES (352, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 103, 126);
INSERT INTO `course_schedule` VALUES (353, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 103, 128);
INSERT INTO `course_schedule` VALUES (354, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 103, 127);
INSERT INTO `course_schedule` VALUES (355, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 103, 125);
INSERT INTO `course_schedule` VALUES (356, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 104, 126);
INSERT INTO `course_schedule` VALUES (357, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 104, 128);
INSERT INTO `course_schedule` VALUES (358, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 104, 127);
INSERT INTO `course_schedule` VALUES (359, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 104, 125);
INSERT INTO `course_schedule` VALUES (360, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 105, 126);
INSERT INTO `course_schedule` VALUES (361, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 105, 128);
INSERT INTO `course_schedule` VALUES (362, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 105, 127);
INSERT INTO `course_schedule` VALUES (363, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 105, 125);
INSERT INTO `course_schedule` VALUES (364, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 106, 126);
INSERT INTO `course_schedule` VALUES (365, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 106, 128);
INSERT INTO `course_schedule` VALUES (366, 'RD203-Khu Rạng Đông', 5, 1, 3, b'0', 106, 127);
INSERT INTO `course_schedule` VALUES (367, 'P5-Phòng máy K.CNTT', 5, 4, 3, b'1', 106, 125);
INSERT INTO `course_schedule` VALUES (368, 'P5-Phòng máy K.CNTT', 4, 4, 3, b'1', 107, 126);
INSERT INTO `course_schedule` VALUES (369, 'P1-Phòng máy K.CNTT', 4, 7, 3, b'1', 107, 128);

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` int NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `credits` int NULL DEFAULT NULL,
  `is_mandatory` bit(1) NULL DEFAULT NULL,
  `practice_hours` int NULL DEFAULT NULL,
  `theory_hours` int NULL DEFAULT NULL,
  `department_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKly1t739uvqbroru9b7skxqvti`(`department_id` ASC) USING BTREE,
  CONSTRAINT `FKly1t739uvqbroru9b7skxqvti` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'Chuẩn đầu ra Tin học', 0, b'1', 0, 0, 52480201);
INSERT INTO `courses` VALUES (2, 'Chuẩn đầu ra B1', 0, b'1', 0, 0, 52480201);
INSERT INTO `courses` VALUES (200101, 'Triết học Mác Lênin', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (200102, 'Kinh tế chính trị Mác- Lênin', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (200103, 'Chủ nghĩa xã hội khoa học', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (200105, 'Lịch sử Đảng Cộng sản Việt Nam', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (200107, 'Tư tưởng Hồ Chí Minh', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (200201, 'Quân sự 1 (lý thuyết)*', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (200202, 'Quân sự 2 (thực hành)*', 3, b'1', 90, 0, 52480201);
INSERT INTO `courses` VALUES (202108, 'Toán cao cấp A1', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (202109, 'Toán cao cấp A2', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (202110, 'Toán cao cấp A3', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (202121, 'Xác suất thống kê', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (202206, 'Vật lý 2', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (202501, 'Giáo dục thể chất 1*', 1, b'1', 0, 0, 52480201);
INSERT INTO `courses` VALUES (202502, 'Giáo dục thể chất 2*', 1, b'1', 0, 0, 52480201);
INSERT INTO `courses` VALUES (202620, 'Kỹ năng giao tiếp', 2, b'0', 0, 30, 52480201);
INSERT INTO `courses` VALUES (202622, 'Pháp luật đại cương', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (208407, 'Khởi nghiệp', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (208453, 'Marketing căn bản', 2, b'0', 0, 30, 52480201);
INSERT INTO `courses` VALUES (213603, 'Anh văn 1*', 4, b'1', 0, 60, 52480201);
INSERT INTO `courses` VALUES (213604, 'Anh văn 2*', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (214201, 'Nhập môn tin học', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214231, 'Cấu trúc máy tính', 2, b'1', 0, 30, 52480201);
INSERT INTO `courses` VALUES (214241, 'Mạng máy tính cơ bản', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (214242, 'Nhập môn hệ điều hành', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214251, 'Hệ điều hành nâng cao', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214252, 'Lập trình mạng', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214271, 'Quản trị mạng', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214273, 'Lập trình mạng nâng cao', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214274, 'Lập trình trên thiết bị di động', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214282, 'Mạng máy tính nâng cao', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214285, 'Giải pháp mạng cho doanh nghiệp', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214286, 'Chuyên đề Java', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214290, 'IoT', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214291, 'Xử lý ảnh và thị giác máy tính', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214292, 'An ninh mạng', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214293, 'Thực tập lập trình trên thiết bị di động', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214321, 'Lập trình cơ bản', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214331, 'Lập trình nâng cao', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214351, 'Lý thuyết đồ thị', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214352, 'Thiết kế hướng đối tượng', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214353, 'Đồ họa máy tính', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214354, 'Lý thuyết đồ thị', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214361, 'Giao tiếp người _máy', 3, b'1', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214362, 'Giao tiếp người-máy', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214370, 'Nhập môn công nghệ phần mềm', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214372, 'Lập trình .NET', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214374, 'Chuyên đề WEB', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214379, 'Đảm bảo chất lượng và kiểm thử phần mềm', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214383, 'Quản lý dự án phần mềm', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214386, 'Lập trình PHP', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214387, 'Chuyên đề mã nguồn mở', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214388, 'Lập trình Front End', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214389, 'Toán rời rạc', 3, b'1', 0, 45, 52480201);
INSERT INTO `courses` VALUES (214390, 'Lập trình Python', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214441, 'Cấu trúc dữ liệu', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214442, 'Nhập môn cơ sở dữ liệu', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214451, 'Hệ quản trị cơ sở dữ liệu', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214461, 'Phân tích và thiết kế hệ thống thông tin', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214462, 'Lập trình Web', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214463, 'Nhập môn trí tuệ nhân tạo', 4, b'1', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214464, 'An toàn và bảo mật hệ thống thông tin', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214465, 'Hệ thống thông tin địa lý ứng dụng', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214471, 'Hệ thống thông tin quản lý', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214483, 'Thương mại điện tử', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214485, 'Data Mining', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214490, 'Phân tích dữ liệu lớn', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214491, 'Data Warehouse', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214492, 'Máy học', 4, b'0', 30, 45, 52480201);
INSERT INTO `courses` VALUES (214493, 'Thực tập lập trình Web', 3, b'0', 30, 30, 52480201);
INSERT INTO `courses` VALUES (214984, 'Đồ án chuyên ngành', 2, b'0', 0, 0, 52480201);
INSERT INTO `courses` VALUES (214986, 'Đồ án Công nghệ phần mềm', 2, b'0', 0, 0, 52480201);
INSERT INTO `courses` VALUES (214987, 'Khóa luận tốt nghiệp', 12, b'0', 0, 0, 52480201);
INSERT INTO `courses` VALUES (214988, 'Tiểu luận tốt nghiệp', 6, b'0', 0, 0, 52480201);
INSERT INTO `courses` VALUES (214989, 'Khởi nghiệp', 2, b'0', 0, 30, 52480201);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52480202 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (52480201, 'Công nghệ thông tin ');

-- ----------------------------
-- Table structure for link_to_dkmh
-- ----------------------------
DROP TABLE IF EXISTS `link_to_dkmh`;
CREATE TABLE `link_to_dkmh`  (
  `expires` datetime(6) NULL DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `issued` datetime(6) NULL DEFAULT NULL,
  `mssv` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `access_token` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `token_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_jeml6bxfwq20m2eylgvke8iff`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKqxtnimdjwfwprqflw12qou09w` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_to_dkmh
-- ----------------------------
INSERT INTO `link_to_dkmh` VALUES ('2024-07-04 01:53:30.000000', 3, '2024-07-04 01:23:30.000000', '20130337', 3, 'eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ii03MjIyNTM4NTU2MjAyOTM3ODM0IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IjIwMTMwMzM3IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9hY2Nlc3Njb250cm9sc2VydmljZS8yMDEwLzA3L2NsYWltcy9pZGVudGl0eXByb3ZpZGVyIjoiQVNQLk5FVCBJZGVudGl0eSIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiNDc1NTA4NzYzNTgxNDU4NTAxMyIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlNJTkhWSUVOIiwic2Vzc2lvbiI6Ii01MTE3MTc1MTAyMzc2MDQ3Mjc4IiwibmFtZSI6IlRyw6LMgG4gQnXMgGkgVHXDosyBbiBOZ2_Mo2MiLCJwYXNzdHlwZSI6IjAiLCJ1Y3YiOiIxODEzODI3ODIxIiwicHJpbmNpcGFsIjoiMjAxMzAzMzdAc3QuaGNtdWFmLmVkdS52biIsIndjZiI6IjAiLCJuYmYiOjE3MjAwNTYyMDksImV4cCI6MTcyMDA1ODAwOSwiaXNzIjoiZWR1c29mdCIsImF1ZCI6ImFsbCJ9.pWgxBjbGDKiDG4HfTLQjr-DYuYSHQrEJoNJoQ3pzEmk', '905AB38890AADF02CD26816DFED12BCE40C1DDD80BEBBD86718B11B6D0B6D46B3726B2FF99F5A3F5', 'bearer');
INSERT INTO `link_to_dkmh` VALUES ('2024-05-29 07:03:32.000000', 4, '2024-05-29 06:33:32.000000', '22130090', 4, 'eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ii02Mzg5ODY5NjQyMDY4OTUzMjg1IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IjIyMTMwMDkwIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9hY2Nlc3Njb250cm9sc2VydmljZS8yMDEwLzA3L2NsYWltcy9pZGVudGl0eXByb3ZpZGVyIjoiQVNQLk5FVCBJZGVudGl0eSIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiNzc4ODU3NDMyMjM5MTk5Nzc3MSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlNJTkhWSUVOIiwic2Vzc2lvbiI6Ii04NjU1MTQ5Nzg2ODc3ODI1Nzg2IiwibmFtZSI6IkjDgCBBTkggSOG7olAiLCJwYXNzdHlwZSI6IjAiLCJ1Y3YiOiI1MDczOTk2NDQiLCJwcmluY2lwYWwiOiIyMjEzMDA5MEBzdC5oY211YWYuZWR1LnZuIiwid2NmIjoiMCIsIm5iZiI6MTcxNjk2NDQxMCwiZXhwIjoxNzE2OTY2MjEwLCJpc3MiOiJlZHVzb2Z0IiwiYXVkIjoiYWxsIn0.d21vv3F88PBNngntOjKWmYTz_S8Gh1YipWE3v-Z9rVE', 'F74EAEED9E2579F5970C9218B8C12A5D913E444EA8B289638CCB770BB75DBC48DC79E0058A7FCA3C', 'bearer');
INSERT INTO `link_to_dkmh` VALUES ('2024-06-07 07:45:33.000000', 5, '2024-06-07 07:15:33.000000', '20130337', 5, 'eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ii03MjIyNTM4NTU2MjAyOTM3ODM0IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IjIwMTMwMzM3IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9hY2Nlc3Njb250cm9sc2VydmljZS8yMDEwLzA3L2NsYWltcy9pZGVudGl0eXByb3ZpZGVyIjoiQVNQLk5FVCBJZGVudGl0eSIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiNDc1NTA4NzYzNTgxNDU4NTAxMyIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlNJTkhWSUVOIiwic2Vzc2lvbiI6Ii01NDE2MDU5MDQwMDQ2NDI4MzM4IiwibmFtZSI6IlRyw6LMgG4gQnXMgGkgVHXDosyBbiBOZ2_Mo2MiLCJwYXNzdHlwZSI6IjAiLCJ1Y3YiOiIxMzg3OTkyNjk1IiwicHJpbmNpcGFsIjoiMjAxMzAzMzdAc3QuaGNtdWFmLmVkdS52biIsIndjZiI6IjAiLCJuYmYiOjE3MTc3NDQ1MzIsImV4cCI6MTcxNzc0NjMzMiwiaXNzIjoiZWR1c29mdCIsImF1ZCI6ImFsbCJ9.ujNYbNDnup7VvMqjxEsE7uwTw2ZaxiJzMVAd5oCfReQ', '9498BDF835DA4F79CF2989E4183A419DBF6DEB4E40AE300AE78DB42B13E12217BAB709CD98EE5CFB', 'bearer');

-- ----------------------------
-- Table structure for password_reset_token
-- ----------------------------
DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE `password_reset_token`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK83nsrttkwkb6ym0anu051mtxn`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK83nsrttkwkb6ym0anu051mtxn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of password_reset_token
-- ----------------------------
INSERT INTO `password_reset_token` VALUES (1, '2024-06-05 17:57:22.000000', 'Js7ipgysWr', 3);
INSERT INTO `password_reset_token` VALUES (2, '2024-06-05 19:32:14.000000', 'YLxQwMCVTX', 3);
INSERT INTO `password_reset_token` VALUES (3, '2024-06-05 18:18:25.000000', 'smQNxMmgIk', 3);

-- ----------------------------
-- Table structure for prerequisites
-- ----------------------------
DROP TABLE IF EXISTS `prerequisites`;
CREATE TABLE `prerequisites`  (
  `course_id` int NOT NULL,
  `prerequisite_id` int NOT NULL,
  INDEX `FK8yl9arstnhdw3iou5xwi0yrag`(`prerequisite_id` ASC) USING BTREE,
  INDEX `FK6c5rl8wmbsy9eknf0j4bgj5fr`(`course_id` ASC) USING BTREE,
  CONSTRAINT `FK6c5rl8wmbsy9eknf0j4bgj5fr` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8yl9arstnhdw3iou5xwi0yrag` FOREIGN KEY (`prerequisite_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prerequisites
-- ----------------------------
INSERT INTO `prerequisites` VALUES (200102, 200101);
INSERT INTO `prerequisites` VALUES (200103, 200102);
INSERT INTO `prerequisites` VALUES (202121, 202110);
INSERT INTO `prerequisites` VALUES (214389, 214321);
INSERT INTO `prerequisites` VALUES (214389, 202108);
INSERT INTO `prerequisites` VALUES (200107, 200103);
INSERT INTO `prerequisites` VALUES (200105, 200107);
INSERT INTO `prerequisites` VALUES (214331, 214321);
INSERT INTO `prerequisites` VALUES (214362, 214331);
INSERT INTO `prerequisites` VALUES (214441, 214331);
INSERT INTO `prerequisites` VALUES (214251, 214331);
INSERT INTO `prerequisites` VALUES (214251, 214242);
INSERT INTO `prerequisites` VALUES (214352, 214331);
INSERT INTO `prerequisites` VALUES (214354, 214331);
INSERT INTO `prerequisites` VALUES (214252, 214442);
INSERT INTO `prerequisites` VALUES (214252, 214331);
INSERT INTO `prerequisites` VALUES (214252, 214241);
INSERT INTO `prerequisites` VALUES (214462, 214241);
INSERT INTO `prerequisites` VALUES (214462, 214331);
INSERT INTO `prerequisites` VALUES (214463, 214331);
INSERT INTO `prerequisites` VALUES (214274, 214252);
INSERT INTO `prerequisites` VALUES (214370, 214352);
INSERT INTO `prerequisites` VALUES (214461, 214352);
INSERT INTO `prerequisites` VALUES (214461, 214442);
INSERT INTO `prerequisites` VALUES (214353, 214441);
INSERT INTO `prerequisites` VALUES (214451, 214442);
INSERT INTO `prerequisites` VALUES (214372, 214442);
INSERT INTO `prerequisites` VALUES (214372, 214331);
INSERT INTO `prerequisites` VALUES (214372, 214241);
INSERT INTO `prerequisites` VALUES (214386, 214331);
INSERT INTO `prerequisites` VALUES (214386, 214241);
INSERT INTO `prerequisites` VALUES (214390, 214331);
INSERT INTO `prerequisites` VALUES (214282, 214241);
INSERT INTO `prerequisites` VALUES (214464, 214462);
INSERT INTO `prerequisites` VALUES (214465, 214442);
INSERT INTO `prerequisites` VALUES (214492, 214463);
INSERT INTO `prerequisites` VALUES (214493, 214493);
INSERT INTO `prerequisites` VALUES (214273, 214252);
INSERT INTO `prerequisites` VALUES (214291, 214463);
INSERT INTO `prerequisites` VALUES (214379, 214370);
INSERT INTO `prerequisites` VALUES (214388, 214462);
INSERT INTO `prerequisites` VALUES (214485, 214442);
INSERT INTO `prerequisites` VALUES (214271, 214242);
INSERT INTO `prerequisites` VALUES (214271, 214241);
INSERT INTO `prerequisites` VALUES (214292, 214241);
INSERT INTO `prerequisites` VALUES (214293, 214274);
INSERT INTO `prerequisites` VALUES (214383, 214370);
INSERT INTO `prerequisites` VALUES (214491, 214442);
INSERT INTO `prerequisites` VALUES (214290, 214252);
INSERT INTO `prerequisites` VALUES (214471, 214442);
INSERT INTO `prerequisites` VALUES (214483, 214442);
INSERT INTO `prerequisites` VALUES (214483, 214462);
INSERT INTO `prerequisites` VALUES (214286, 214441);
INSERT INTO `prerequisites` VALUES (214374, 214462);
INSERT INTO `prerequisites` VALUES (200102, 200101);
INSERT INTO `prerequisites` VALUES (200103, 200102);
INSERT INTO `prerequisites` VALUES (202121, 202110);
INSERT INTO `prerequisites` VALUES (214389, 202108);
INSERT INTO `prerequisites` VALUES (214389, 214321);
INSERT INTO `prerequisites` VALUES (200107, 200103);
INSERT INTO `prerequisites` VALUES (200105, 200107);
INSERT INTO `prerequisites` VALUES (214331, 214321);
INSERT INTO `prerequisites` VALUES (214361, 214331);
INSERT INTO `prerequisites` VALUES (214441, 214331);
INSERT INTO `prerequisites` VALUES (214251, 214242);
INSERT INTO `prerequisites` VALUES (214251, 214331);
INSERT INTO `prerequisites` VALUES (214351, 214331);
INSERT INTO `prerequisites` VALUES (214352, 214331);
INSERT INTO `prerequisites` VALUES (214252, 214241);
INSERT INTO `prerequisites` VALUES (214252, 214442);
INSERT INTO `prerequisites` VALUES (214252, 214331);
INSERT INTO `prerequisites` VALUES (214462, 214241);
INSERT INTO `prerequisites` VALUES (214462, 214331);
INSERT INTO `prerequisites` VALUES (214463, 214331);
INSERT INTO `prerequisites` VALUES (214274, 214252);
INSERT INTO `prerequisites` VALUES (214370, 214352);
INSERT INTO `prerequisites` VALUES (214461, 214352);
INSERT INTO `prerequisites` VALUES (214461, 214442);
INSERT INTO `prerequisites` VALUES (214353, 214441);
INSERT INTO `prerequisites` VALUES (214282, 214241);
INSERT INTO `prerequisites` VALUES (214372, 214331);
INSERT INTO `prerequisites` VALUES (214372, 214442);
INSERT INTO `prerequisites` VALUES (214372, 214241);
INSERT INTO `prerequisites` VALUES (214386, 214241);
INSERT INTO `prerequisites` VALUES (214386, 214331);
INSERT INTO `prerequisites` VALUES (214451, 214442);
INSERT INTO `prerequisites` VALUES (214388, 214252);
INSERT INTO `prerequisites` VALUES (214388, 214462);
INSERT INTO `prerequisites` VALUES (214492, 214463);
INSERT INTO `prerequisites` VALUES (214493, 214462);
INSERT INTO `prerequisites` VALUES (214290, 214252);
INSERT INTO `prerequisites` VALUES (214292, 214241);
INSERT INTO `prerequisites` VALUES (214293, 214274);
INSERT INTO `prerequisites` VALUES (214379, 214370);
INSERT INTO `prerequisites` VALUES (214383, 214370);
INSERT INTO `prerequisites` VALUES (214464, 214462);
INSERT INTO `prerequisites` VALUES (214465, 214442);
INSERT INTO `prerequisites` VALUES (214485, 214442);
INSERT INTO `prerequisites` VALUES (214491, 214442);
INSERT INTO `prerequisites` VALUES (214271, 214242);
INSERT INTO `prerequisites` VALUES (214271, 214241);
INSERT INTO `prerequisites` VALUES (214285, 214241);
INSERT INTO `prerequisites` VALUES (214285, 214242);
INSERT INTO `prerequisites` VALUES (214387, 214370);
INSERT INTO `prerequisites` VALUES (214483, 214442);
INSERT INTO `prerequisites` VALUES (214483, 214462);
INSERT INTO `prerequisites` VALUES (214286, 214441);
INSERT INTO `prerequisites` VALUES (214291, 214463);
INSERT INTO `prerequisites` VALUES (214374, 214462);
INSERT INTO `prerequisites` VALUES (214490, 214463);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_permission` bit(1) NULL DEFAULT NULL,
  `user_permission` bit(1) NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, b'1', b'1', 'Admin', b'0');
INSERT INTO `roles` VALUES (2, b'0', b'0', 'User', b'0');

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `component_score` double NULL DEFAULT NULL,
  `final_score_10` double NULL DEFAULT NULL,
  `final_score_4` double NULL DEFAULT NULL,
  `final_score_char` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `result` bit(1) NULL DEFAULT NULL,
  `score` double NULL DEFAULT NULL,
  `user_course_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKlouwyevxgu37caa7wyxwia67i`(`user_course_id` ASC) USING BTREE,
  CONSTRAINT `FKlouwyevxgu37caa7wyxwia67i` FOREIGN KEY (`user_course_id`) REFERENCES `user_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 202 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES (11, 0, 0, 0, '', b'0', 0, 11);
INSERT INTO `scores` VALUES (12, 0, 0, 0, '', b'0', 0, 12);
INSERT INTO `scores` VALUES (13, 0, 0, 0, '', b'0', 0, 13);
INSERT INTO `scores` VALUES (14, 8.9, 8.9, 4, 'A', b'1', 8.9, 14);
INSERT INTO `scores` VALUES (15, 9.3, 8.8, 4, 'A', b'1', 8.5, 15);
INSERT INTO `scores` VALUES (16, 5, 4.6, 1, 'D', b'1', 4.3, 16);
INSERT INTO `scores` VALUES (17, 8, 8.3, 3.5, 'B+', b'1', 8.5, 17);
INSERT INTO `scores` VALUES (18, 7.9, 6.8, 2.5, 'C+', b'1', 5.7, 18);
INSERT INTO `scores` VALUES (19, 8.2, 8.6, 4, 'A', b'1', 9, 19);
INSERT INTO `scores` VALUES (20, 8, 7.6, 3, 'B', b'1', 7.2, 20);
INSERT INTO `scores` VALUES (21, 8.5, 7.9, 3, 'B', b'1', 7.5, 21);
INSERT INTO `scores` VALUES (22, 6.3, 7, 3, 'B', b'1', 7.5, 22);
INSERT INTO `scores` VALUES (23, 8.7, 7.7, 3, 'B', b'1', 7, 23);
INSERT INTO `scores` VALUES (24, 9, 6.7, 2.5, 'C+', b'1', 4.3, 24);
INSERT INTO `scores` VALUES (25, 7.2, 7.1, 3, 'B', b'1', 7, 25);
INSERT INTO `scores` VALUES (26, 0, 9.3, 4, 'A', b'1', 9.3, 26);
INSERT INTO `scores` VALUES (27, 0, 7.6, 3, 'B', b'1', 7.6, 27);
INSERT INTO `scores` VALUES (28, 0, 4.8, 1, 'D', b'1', 4.8, 28);
INSERT INTO `scores` VALUES (29, 0, 7.8, 3, 'B', b'1', 7.8, 29);
INSERT INTO `scores` VALUES (30, 0, 7.5, 3, 'B', b'1', 7.5, 30);
INSERT INTO `scores` VALUES (31, 0, 6.7, 2.5, 'C+', b'1', 6.7, 31);
INSERT INTO `scores` VALUES (32, 0, 8.5, 4, 'A', b'1', 8.5, 32);
INSERT INTO `scores` VALUES (33, 0, 9.3, 4, 'A', b'1', 9.3, 33);
INSERT INTO `scores` VALUES (34, 0, 5.8, 2, 'C', b'1', 5.8, 34);
INSERT INTO `scores` VALUES (35, 0, 9, 4, 'A', b'1', 9, 35);
INSERT INTO `scores` VALUES (36, 0, 4.8, 1, 'D', b'1', 4.8, 36);
INSERT INTO `scores` VALUES (37, 0, 7.1, 3, 'B', b'1', 7.1, 37);
INSERT INTO `scores` VALUES (38, 0, 4.5, 1, 'D', b'1', 4.5, 38);
INSERT INTO `scores` VALUES (39, 0, 7.4, 3, 'B', b'1', 7.4, 39);
INSERT INTO `scores` VALUES (40, 0, 3.7, 0, 'F', b'0', 3.7, 40);
INSERT INTO `scores` VALUES (41, 0, 6.3, 2, 'C', b'1', 6.3, 41);
INSERT INTO `scores` VALUES (42, 0, 7.8, 3, 'B', b'1', 7.8, 42);
INSERT INTO `scores` VALUES (43, 0, 8.1, 3.5, 'B+', b'1', 8.1, 43);
INSERT INTO `scores` VALUES (44, 0, 8.8, 4, 'A', b'1', 8.8, 44);
INSERT INTO `scores` VALUES (45, 0, 7.3, 3, 'B', b'1', 7.3, 45);
INSERT INTO `scores` VALUES (46, 0, 8.3, 3.5, 'B+', b'1', 8.3, 46);
INSERT INTO `scores` VALUES (47, 0, 7.7, 3, 'B', b'1', 7.7, 47);
INSERT INTO `scores` VALUES (48, 0, 5.8, 2, 'C', b'1', 5.8, 48);
INSERT INTO `scores` VALUES (49, 0, 4, 1, 'D', b'1', 4, 49);
INSERT INTO `scores` VALUES (50, 0, 7.9, 3, 'B', b'1', 7.9, 50);
INSERT INTO `scores` VALUES (51, 0, 8.2, 3.5, 'B+', b'1', 8.2, 51);
INSERT INTO `scores` VALUES (52, 0, 6.4, 2.5, 'C+', b'1', 6.4, 52);
INSERT INTO `scores` VALUES (53, 0, 6.6, 2.5, 'C+', b'1', 6.6, 53);
INSERT INTO `scores` VALUES (54, 0, 6.2, 2.5, 'C+', b'1', 6.2, 54);
INSERT INTO `scores` VALUES (55, 0, 7, 3, 'B', b'1', 7, 55);
INSERT INTO `scores` VALUES (56, 0, 6.9, 2.5, 'C+', b'1', 6.9, 56);
INSERT INTO `scores` VALUES (57, 0, 6.6, 2.5, 'C+', b'1', 6.6, 57);
INSERT INTO `scores` VALUES (58, 0, 5.4, 2, 'C', b'1', 5.4, 58);
INSERT INTO `scores` VALUES (59, 0, 5, 2, 'C', b'1', 5, 59);
INSERT INTO `scores` VALUES (60, 0, 7.8, 3, 'B', b'1', 7.8, 60);
INSERT INTO `scores` VALUES (61, 0, 7.1, 3, 'B', b'1', 7.1, 61);
INSERT INTO `scores` VALUES (62, 0, 5.2, 2, 'C', b'1', 5.2, 62);
INSERT INTO `scores` VALUES (120, 0, 0, 0, '', b'0', 0, 120);
INSERT INTO `scores` VALUES (121, 0, 0, 0, '', b'0', 0, 121);
INSERT INTO `scores` VALUES (122, 0, 0, 0, '', b'0', 0, 122);
INSERT INTO `scores` VALUES (123, 0, 0, 0, '', b'0', 0, 123);
INSERT INTO `scores` VALUES (124, 0, 0, 0, '', b'0', 0, 124);
INSERT INTO `scores` VALUES (125, 0, 0, 0, '', b'0', 0, 125);
INSERT INTO `scores` VALUES (126, 0, 0, 0, '', b'0', 0, 126);
INSERT INTO `scores` VALUES (127, 0, 0, 0, '', b'0', 0, 127);
INSERT INTO `scores` VALUES (128, 0, 0, 0, '', b'0', 0, 128);
INSERT INTO `scores` VALUES (129, 9, 6.2, 2, 'C', b'1', 5, 129);
INSERT INTO `scores` VALUES (130, 8, 5.9, 2, 'C', b'1', 4.5, 130);
INSERT INTO `scores` VALUES (131, 6.7, 5.5, 2, 'C', b'1', 4.7, 131);
INSERT INTO `scores` VALUES (132, 8.5, 7, 3, 'B', b'1', 5.5, 132);
INSERT INTO `scores` VALUES (133, 6, 4.4, 1, 'D', b'1', 3.3, 133);
INSERT INTO `scores` VALUES (134, 0, 8.8, 4, 'P', b'1', 8.8, 134);
INSERT INTO `scores` VALUES (135, 0, 7.8, 3, 'P', b'1', 7.8, 135);
INSERT INTO `scores` VALUES (136, 9, 4.8, 1, 'D', b'1', 3, 136);
INSERT INTO `scores` VALUES (137, 10, 7.2, 3, 'B', b'1', 5.4, 137);
INSERT INTO `scores` VALUES (138, 0, 7, 3, 'P', b'1', 7, 138);
INSERT INTO `scores` VALUES (139, 9.1, 8.5, 4, 'A', b'1', 8.1, 139);
INSERT INTO `scores` VALUES (140, 6.8, 6.4, 2, 'C', b'1', 6, 140);
INSERT INTO `scores` VALUES (141, 6.3, 4.7, 1, 'D', b'1', 3, 141);
INSERT INTO `scores` VALUES (142, 0, 8.4, 3.5, 'B+', b'1', 8.4, 142);
INSERT INTO `scores` VALUES (143, 0, 6.6, 2.5, 'C+', b'1', 6.6, 143);
INSERT INTO `scores` VALUES (144, 0, 8, 3.5, 'B+', b'1', 8, 144);
INSERT INTO `scores` VALUES (145, 0, 5.1, 1.5, 'D+', b'1', 5.1, 145);
INSERT INTO `scores` VALUES (146, 0, 9, 4, 'P', b'1', 9, 146);
INSERT INTO `scores` VALUES (147, 0, 8.8, 4, 'A', b'1', 8.8, 147);
INSERT INTO `scores` VALUES (148, 0, 4.5, 1, 'D', b'1', 4.5, 148);
INSERT INTO `scores` VALUES (149, 0, 0, 0, 'M', b'1', 0, 149);
INSERT INTO `scores` VALUES (150, 0, 0, 0, '', b'0', 0, 150);
INSERT INTO `scores` VALUES (151, 0, 0, 0, '', b'0', 0, 151);
INSERT INTO `scores` VALUES (152, 0, 0, 0, '', b'0', 0, 152);
INSERT INTO `scores` VALUES (153, 8.9, 8.9, 4, 'A', b'1', 8.9, 153);
INSERT INTO `scores` VALUES (154, 9.3, 8.8, 4, 'A', b'1', 8.5, 154);
INSERT INTO `scores` VALUES (155, 5, 4.6, 1, 'D', b'1', 4.3, 155);
INSERT INTO `scores` VALUES (156, 8, 8.3, 3.5, 'B+', b'1', 8.5, 156);
INSERT INTO `scores` VALUES (157, 7.9, 6.8, 2.5, 'C+', b'1', 5.7, 157);
INSERT INTO `scores` VALUES (158, 8.2, 8.6, 4, 'A', b'1', 9, 158);
INSERT INTO `scores` VALUES (159, 8, 7.6, 3, 'B', b'1', 7.2, 159);
INSERT INTO `scores` VALUES (160, 8.5, 7.9, 3, 'B', b'1', 7.5, 160);
INSERT INTO `scores` VALUES (161, 6.3, 7, 3, 'B', b'1', 7.5, 161);
INSERT INTO `scores` VALUES (162, 8.7, 7.7, 3, 'B', b'1', 7, 162);
INSERT INTO `scores` VALUES (163, 9, 6.7, 2.5, 'C+', b'1', 4.3, 163);
INSERT INTO `scores` VALUES (164, 7.2, 7.1, 3, 'B', b'1', 7, 164);
INSERT INTO `scores` VALUES (165, 0, 9.3, 4, 'A', b'1', 9.3, 165);
INSERT INTO `scores` VALUES (166, 0, 7.6, 3, 'B', b'1', 7.6, 166);
INSERT INTO `scores` VALUES (167, 0, 4.8, 1, 'D', b'1', 4.8, 167);
INSERT INTO `scores` VALUES (168, 0, 7.8, 3, 'B', b'1', 7.8, 168);
INSERT INTO `scores` VALUES (169, 0, 7.5, 3, 'B', b'1', 7.5, 169);
INSERT INTO `scores` VALUES (170, 0, 6.7, 2.5, 'C+', b'1', 6.7, 170);
INSERT INTO `scores` VALUES (171, 0, 8.5, 4, 'A', b'1', 8.5, 171);
INSERT INTO `scores` VALUES (172, 0, 9.3, 4, 'A', b'1', 9.3, 172);
INSERT INTO `scores` VALUES (173, 0, 5.8, 2, 'C', b'1', 5.8, 173);
INSERT INTO `scores` VALUES (174, 0, 9, 4, 'A', b'1', 9, 174);
INSERT INTO `scores` VALUES (175, 0, 4.8, 1, 'D', b'1', 4.8, 175);
INSERT INTO `scores` VALUES (176, 0, 7.1, 3, 'B', b'1', 7.1, 176);
INSERT INTO `scores` VALUES (177, 0, 4.5, 1, 'D', b'1', 4.5, 177);
INSERT INTO `scores` VALUES (178, 0, 7.4, 3, 'B', b'1', 7.4, 178);
INSERT INTO `scores` VALUES (179, 0, 3.7, 0, 'F', b'0', 3.7, 179);
INSERT INTO `scores` VALUES (180, 0, 6.3, 2, 'C', b'1', 6.3, 180);
INSERT INTO `scores` VALUES (181, 0, 7.8, 3, 'B', b'1', 7.8, 181);
INSERT INTO `scores` VALUES (182, 0, 8.1, 3.5, 'B+', b'1', 8.1, 182);
INSERT INTO `scores` VALUES (183, 0, 8.8, 4, 'A', b'1', 8.8, 183);
INSERT INTO `scores` VALUES (184, 0, 7.3, 3, 'B', b'1', 7.3, 184);
INSERT INTO `scores` VALUES (185, 0, 8.3, 3.5, 'B+', b'1', 8.3, 185);
INSERT INTO `scores` VALUES (186, 0, 7.7, 3, 'B', b'1', 7.7, 186);
INSERT INTO `scores` VALUES (187, 0, 5.8, 2, 'C', b'1', 5.8, 187);
INSERT INTO `scores` VALUES (188, 0, 4, 1, 'D', b'1', 4, 188);
INSERT INTO `scores` VALUES (189, 0, 7.9, 3, 'B', b'1', 7.9, 189);
INSERT INTO `scores` VALUES (190, 0, 8.2, 3.5, 'B+', b'1', 8.2, 190);
INSERT INTO `scores` VALUES (191, 0, 6.4, 2.5, 'C+', b'1', 6.4, 191);
INSERT INTO `scores` VALUES (192, 0, 6.6, 2.5, 'C+', b'1', 6.6, 192);
INSERT INTO `scores` VALUES (193, 0, 6.2, 2.5, 'C+', b'1', 6.2, 193);
INSERT INTO `scores` VALUES (194, 0, 7, 3, 'B', b'1', 7, 194);
INSERT INTO `scores` VALUES (195, 0, 6.9, 2.5, 'C+', b'1', 6.9, 195);
INSERT INTO `scores` VALUES (196, 0, 6.6, 2.5, 'C+', b'1', 6.6, 196);
INSERT INTO `scores` VALUES (197, 0, 5.4, 2, 'C', b'1', 5.4, 197);
INSERT INTO `scores` VALUES (198, 0, 5, 2, 'C', b'1', 5, 198);
INSERT INTO `scores` VALUES (199, 0, 7.8, 3, 'B', b'1', 7.8, 199);
INSERT INTO `scores` VALUES (200, 0, 7.1, 3, 'B', b'1', 7.1, 200);
INSERT INTO `scores` VALUES (201, 0, 5.2, 2, 'C', b'1', 5.2, 201);

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `semester_id` int NOT NULL,
  `semester_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `end_date` date NULL DEFAULT NULL,
  `start_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`semester_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES (0, 'Bảo lưu', NULL, NULL);
INSERT INTO `semester` VALUES (20201, 'Học kỳ 1 Năm học 2020-2021', '2021-02-01', '2020-09-14');
INSERT INTO `semester` VALUES (20202, 'Học kỳ 2 Năm học 2020-2021', '2021-07-12', '2021-02-22');
INSERT INTO `semester` VALUES (20203, 'Học kỳ 3 Năm học 2020-2021', '2021-08-30', '2021-07-12');
INSERT INTO `semester` VALUES (20211, 'Học kỳ 1 Năm học 2021-2022', '2022-01-24', '2021-09-06');
INSERT INTO `semester` VALUES (20212, 'Học kỳ 2 Năm học 2021-2022', '2022-07-11', '2022-02-14');
INSERT INTO `semester` VALUES (20213, 'Học kỳ 3 Năm học 2021-2022', '2022-08-29', '2022-07-11');
INSERT INTO `semester` VALUES (20221, 'Học kỳ 1 Năm học 2022-2023', '2023-01-16', '2022-08-29');
INSERT INTO `semester` VALUES (20222, 'Học kỳ 2 Năm học 2022-2023', '2023-06-26', '2023-01-30');
INSERT INTO `semester` VALUES (20223, 'Học kỳ 3 Năm học 2022-2023', '2023-08-07', '2023-06-26');
INSERT INTO `semester` VALUES (20231, 'Học kỳ 1 Năm học 2023-2024', '2024-01-29', '2023-09-04');
INSERT INTO `semester` VALUES (20232, 'Học kỳ 2 Năm học 2023-2024', '2024-07-08', '2024-02-19');
INSERT INTO `semester` VALUES (20233, 'Học kỳ 3 Năm học 2023-2024', '2024-08-26', '2024-07-08');
INSERT INTO `semester` VALUES (20241, 'Học kỳ 1 Năm học 2024-2025', '2025-01-20', '2024-09-09');
INSERT INTO `semester` VALUES (20242, 'Học kỳ 2 Năm học 2024-2025', NULL, NULL);
INSERT INTO `semester` VALUES (20251, 'Học kỳ 1 Năm học 2025-2026', NULL, NULL);
INSERT INTO `semester` VALUES (20252, 'Học kỳ 2 Năm học 2025-2026', NULL, NULL);

-- ----------------------------
-- Table structure for semester_course
-- ----------------------------
DROP TABLE IF EXISTS `semester_course`;
CREATE TABLE `semester_course`  (
  `course_id` int NOT NULL,
  `semester_id` int NOT NULL,
  PRIMARY KEY (`course_id`, `semester_id`) USING BTREE,
  INDEX `FKop689lbxwyresgnke5h4s9327`(`semester_id` ASC) USING BTREE,
  CONSTRAINT `FKm0gsjgqrgmnm590y2w01c32w9` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKop689lbxwyresgnke5h4s9327` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of semester_course
-- ----------------------------
INSERT INTO `semester_course` VALUES (1, 20201);
INSERT INTO `semester_course` VALUES (2, 20201);
INSERT INTO `semester_course` VALUES (2, 20252);
INSERT INTO `semester_course` VALUES (200101, 20201);
INSERT INTO `semester_course` VALUES (200101, 20221);
INSERT INTO `semester_course` VALUES (200102, 20202);
INSERT INTO `semester_course` VALUES (200102, 20222);
INSERT INTO `semester_course` VALUES (200103, 20211);
INSERT INTO `semester_course` VALUES (200103, 20231);
INSERT INTO `semester_course` VALUES (200105, 20221);
INSERT INTO `semester_course` VALUES (200105, 20241);
INSERT INTO `semester_course` VALUES (200107, 20212);
INSERT INTO `semester_course` VALUES (200107, 20232);
INSERT INTO `semester_course` VALUES (200201, 20202);
INSERT INTO `semester_course` VALUES (200201, 20222);
INSERT INTO `semester_course` VALUES (200202, 20202);
INSERT INTO `semester_course` VALUES (200202, 20222);
INSERT INTO `semester_course` VALUES (202108, 20201);
INSERT INTO `semester_course` VALUES (202108, 20221);
INSERT INTO `semester_course` VALUES (202109, 20201);
INSERT INTO `semester_course` VALUES (202109, 20221);
INSERT INTO `semester_course` VALUES (202110, 20202);
INSERT INTO `semester_course` VALUES (202110, 20222);
INSERT INTO `semester_course` VALUES (202121, 20211);
INSERT INTO `semester_course` VALUES (202121, 20231);
INSERT INTO `semester_course` VALUES (202206, 20201);
INSERT INTO `semester_course` VALUES (202206, 20221);
INSERT INTO `semester_course` VALUES (202501, 20201);
INSERT INTO `semester_course` VALUES (202501, 20221);
INSERT INTO `semester_course` VALUES (202502, 20202);
INSERT INTO `semester_course` VALUES (202502, 20222);
INSERT INTO `semester_course` VALUES (202620, 20211);
INSERT INTO `semester_course` VALUES (202620, 20231);
INSERT INTO `semester_course` VALUES (202622, 20211);
INSERT INTO `semester_course` VALUES (202622, 20231);
INSERT INTO `semester_course` VALUES (208407, 20231);
INSERT INTO `semester_course` VALUES (208453, 20211);
INSERT INTO `semester_course` VALUES (208453, 20231);
INSERT INTO `semester_course` VALUES (213603, 20201);
INSERT INTO `semester_course` VALUES (213603, 20221);
INSERT INTO `semester_course` VALUES (213604, 20202);
INSERT INTO `semester_course` VALUES (213604, 20222);
INSERT INTO `semester_course` VALUES (214201, 20201);
INSERT INTO `semester_course` VALUES (214201, 20221);
INSERT INTO `semester_course` VALUES (214231, 20202);
INSERT INTO `semester_course` VALUES (214231, 20222);
INSERT INTO `semester_course` VALUES (214241, 20211);
INSERT INTO `semester_course` VALUES (214241, 20232);
INSERT INTO `semester_course` VALUES (214242, 20202);
INSERT INTO `semester_course` VALUES (214242, 20222);
INSERT INTO `semester_course` VALUES (214251, 20212);
INSERT INTO `semester_course` VALUES (214251, 20232);
INSERT INTO `semester_course` VALUES (214252, 20221);
INSERT INTO `semester_course` VALUES (214252, 20241);
INSERT INTO `semester_course` VALUES (214271, 20232);
INSERT INTO `semester_course` VALUES (214271, 20251);
INSERT INTO `semester_course` VALUES (214273, 20222);
INSERT INTO `semester_course` VALUES (214273, 20251);
INSERT INTO `semester_course` VALUES (214274, 20222);
INSERT INTO `semester_course` VALUES (214274, 20242);
INSERT INTO `semester_course` VALUES (214282, 20221);
INSERT INTO `semester_course` VALUES (214282, 20242);
INSERT INTO `semester_course` VALUES (214285, 20232);
INSERT INTO `semester_course` VALUES (214286, 20232);
INSERT INTO `semester_course` VALUES (214286, 20252);
INSERT INTO `semester_course` VALUES (214290, 20231);
INSERT INTO `semester_course` VALUES (214290, 20252);
INSERT INTO `semester_course` VALUES (214291, 20232);
INSERT INTO `semester_course` VALUES (214291, 20251);
INSERT INTO `semester_course` VALUES (214292, 20231);
INSERT INTO `semester_course` VALUES (214292, 20251);
INSERT INTO `semester_course` VALUES (214293, 20231);
INSERT INTO `semester_course` VALUES (214293, 20251);
INSERT INTO `semester_course` VALUES (214321, 20201);
INSERT INTO `semester_course` VALUES (214321, 20221);
INSERT INTO `semester_course` VALUES (214331, 20202);
INSERT INTO `semester_course` VALUES (214331, 20222);
INSERT INTO `semester_course` VALUES (214351, 20212);
INSERT INTO `semester_course` VALUES (214352, 20212);
INSERT INTO `semester_course` VALUES (214352, 20232);
INSERT INTO `semester_course` VALUES (214353, 20212);
INSERT INTO `semester_course` VALUES (214353, 20241);
INSERT INTO `semester_course` VALUES (214354, 20232);
INSERT INTO `semester_course` VALUES (214361, 20211);
INSERT INTO `semester_course` VALUES (214362, 20231);
INSERT INTO `semester_course` VALUES (214370, 20222);
INSERT INTO `semester_course` VALUES (214370, 20242);
INSERT INTO `semester_course` VALUES (214372, 20221);
INSERT INTO `semester_course` VALUES (214372, 20241);
INSERT INTO `semester_course` VALUES (214374, 20232);
INSERT INTO `semester_course` VALUES (214374, 20252);
INSERT INTO `semester_course` VALUES (214379, 20231);
INSERT INTO `semester_course` VALUES (214379, 20251);
INSERT INTO `semester_course` VALUES (214383, 20231);
INSERT INTO `semester_course` VALUES (214383, 20251);
INSERT INTO `semester_course` VALUES (214386, 20221);
INSERT INTO `semester_course` VALUES (214386, 20241);
INSERT INTO `semester_course` VALUES (214387, 20232);
INSERT INTO `semester_course` VALUES (214388, 20222);
INSERT INTO `semester_course` VALUES (214388, 20251);
INSERT INTO `semester_course` VALUES (214389, 20211);
INSERT INTO `semester_course` VALUES (214389, 20231);
INSERT INTO `semester_course` VALUES (214390, 20241);
INSERT INTO `semester_course` VALUES (214441, 20211);
INSERT INTO `semester_course` VALUES (214441, 20231);
INSERT INTO `semester_course` VALUES (214442, 20212);
INSERT INTO `semester_course` VALUES (214442, 20232);
INSERT INTO `semester_course` VALUES (214451, 20221);
INSERT INTO `semester_course` VALUES (214451, 20241);
INSERT INTO `semester_course` VALUES (214461, 20222);
INSERT INTO `semester_course` VALUES (214461, 20242);
INSERT INTO `semester_course` VALUES (214462, 20221);
INSERT INTO `semester_course` VALUES (214462, 20241);
INSERT INTO `semester_course` VALUES (214463, 20221);
INSERT INTO `semester_course` VALUES (214463, 20241);
INSERT INTO `semester_course` VALUES (214464, 20231);
INSERT INTO `semester_course` VALUES (214464, 20242);
INSERT INTO `semester_course` VALUES (214465, 20231);
INSERT INTO `semester_course` VALUES (214465, 20242);
INSERT INTO `semester_course` VALUES (214471, 20222);
INSERT INTO `semester_course` VALUES (214471, 20252);
INSERT INTO `semester_course` VALUES (214483, 20232);
INSERT INTO `semester_course` VALUES (214483, 20252);
INSERT INTO `semester_course` VALUES (214485, 20231);
INSERT INTO `semester_course` VALUES (214485, 20251);
INSERT INTO `semester_course` VALUES (214490, 20232);
INSERT INTO `semester_course` VALUES (214490, 20251);
INSERT INTO `semester_course` VALUES (214491, 20231);
INSERT INTO `semester_course` VALUES (214491, 20251);
INSERT INTO `semester_course` VALUES (214492, 20222);
INSERT INTO `semester_course` VALUES (214492, 20242);
INSERT INTO `semester_course` VALUES (214493, 20222);
INSERT INTO `semester_course` VALUES (214493, 20242);
INSERT INTO `semester_course` VALUES (214984, 20252);
INSERT INTO `semester_course` VALUES (214986, 20232);
INSERT INTO `semester_course` VALUES (214987, 20232);
INSERT INTO `semester_course` VALUES (214987, 20252);
INSERT INTO `semester_course` VALUES (214988, 20232);
INSERT INTO `semester_course` VALUES (214988, 20252);
INSERT INTO `semester_course` VALUES (214989, 20252);

-- ----------------------------
-- Table structure for test_schedule
-- ----------------------------
DROP TABLE IF EXISTS `test_schedule`;
CREATE TABLE `test_schedule`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `test_room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `test_time` time(6) NULL DEFAULT NULL,
  `start_slot` int NULL DEFAULT NULL,
  `test_date` date NULL DEFAULT NULL,
  `user_course_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK43n8kng2wc2bi77suy2e0ldsf`(`user_course_id` ASC) USING BTREE,
  CONSTRAINT `FK43n8kng2wc2bi77suy2e0ldsf` FOREIGN KEY (`user_course_id`) REFERENCES `user_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_schedule
-- ----------------------------
INSERT INTO `test_schedule` VALUES (5, 'P1', '07:30:00.000000', 1, '2024-06-24', 11);
INSERT INTO `test_schedule` VALUES (6, 'RD206', '14:45:00.000000', 10, '2024-06-11', 125);
INSERT INTO `test_schedule` VALUES (7, 'RD404', '07:30:00.000000', 1, '2024-06-12', 127);
INSERT INTO `test_schedule` VALUES (8, 'P4', '12:15:00.000000', 7, '2024-06-13', 126);
INSERT INTO `test_schedule` VALUES (9, 'RD101', '09:45:00.000000', 4, '2024-06-17', 124);
INSERT INTO `test_schedule` VALUES (10, 'RD405', '07:30:00.000000', 1, '2024-06-21', 122);
INSERT INTO `test_schedule` VALUES (11, 'RD405', '09:45:00.000000', 4, '2024-06-24', 128);
INSERT INTO `test_schedule` VALUES (12, 'RD204', '14:45:00.000000', 10, '2024-06-25', 123);

-- ----------------------------
-- Table structure for todo
-- ----------------------------
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo`  (
  `deadline` date NULL DEFAULT NULL,
  `is_complete` bit(1) NULL DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKdcopxq1yu1u8ijb7rjexhsr6v`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKdcopxq1yu1u8ijb7rjexhsr6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo
-- ----------------------------
INSERT INTO `todo` VALUES ('2024-07-04', b'1', 1, 3, 'Xong chức năng to-do và trang chủ', 'Làm xong phần UI cho user');
INSERT INTO `todo` VALUES ('2024-07-04', b'1', 2, 3, 'Xong chức năng to-do và trang chủ', 'Làm xong phần UI cho user');
INSERT INTO `todo` VALUES ('2024-07-09', b'0', 3, 3, 'Xong chức năng to-do và trang chủ', 'Làm xong phần UI cho user');
INSERT INTO `todo` VALUES ('2024-07-17', b'0', 4, 3, 'Xong chức năng to-do và trang chủ', 'Làm xong phần UI cho user');
INSERT INTO `todo` VALUES ('2024-08-02', b'0', 5, 3, 'Xong chức năng to-do và trang chủ', 'Làm xong phần UI cho user');
INSERT INTO `todo` VALUES ('2024-08-03', b'0', 6, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-12', b'0', 7, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-13', b'0', 8, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-16', b'0', 9, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-15', b'0', 10, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-19', b'0', 11, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-04', b'1', 12, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-04', b'1', 13, 3, '123', 'Công việc a');
INSERT INTO `todo` VALUES ('2024-07-04', b'1', 14, 3, '123', 'Công việc a');

-- ----------------------------
-- Table structure for training_program
-- ----------------------------
DROP TABLE IF EXISTS `training_program`;
CREATE TABLE `training_program`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `year` int NULL DEFAULT NULL,
  `department_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1ey5ykgblkulmtiiixdpwvwqo`(`department_id` ASC) USING BTREE,
  CONSTRAINT `FK1ey5ykgblkulmtiiixdpwvwqo` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of training_program
-- ----------------------------
INSERT INTO `training_program` VALUES (5, 2020, 52480201);
INSERT INTO `training_program` VALUES (7, 2022, 52480201);

-- ----------------------------
-- Table structure for training_program_semesters
-- ----------------------------
DROP TABLE IF EXISTS `training_program_semesters`;
CREATE TABLE `training_program_semesters`  (
  `semester_id` int NOT NULL,
  `training_program_id` bigint NOT NULL,
  PRIMARY KEY (`semester_id`, `training_program_id`) USING BTREE,
  INDEX `FKtl73idm9deqob9ibu968lfi6`(`training_program_id` ASC) USING BTREE,
  CONSTRAINT `FKm6h2csjwoyvkcnhegdv79p2rq` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtl73idm9deqob9ibu968lfi6` FOREIGN KEY (`training_program_id`) REFERENCES `training_program` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of training_program_semesters
-- ----------------------------
INSERT INTO `training_program_semesters` VALUES (20201, 5);
INSERT INTO `training_program_semesters` VALUES (20202, 5);
INSERT INTO `training_program_semesters` VALUES (20211, 5);
INSERT INTO `training_program_semesters` VALUES (20212, 5);
INSERT INTO `training_program_semesters` VALUES (20221, 5);
INSERT INTO `training_program_semesters` VALUES (20221, 7);
INSERT INTO `training_program_semesters` VALUES (20222, 5);
INSERT INTO `training_program_semesters` VALUES (20222, 7);
INSERT INTO `training_program_semesters` VALUES (20231, 5);
INSERT INTO `training_program_semesters` VALUES (20231, 7);
INSERT INTO `training_program_semesters` VALUES (20232, 5);
INSERT INTO `training_program_semesters` VALUES (20232, 7);
INSERT INTO `training_program_semesters` VALUES (20241, 7);
INSERT INTO `training_program_semesters` VALUES (20242, 7);
INSERT INTO `training_program_semesters` VALUES (20251, 7);
INSERT INTO `training_program_semesters` VALUES (20252, 7);

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` int NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `user_semester_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKf4qni5wnlq0x70fm39w9tv7x4`(`course_id` ASC) USING BTREE,
  INDEX `FKoc4yl478i6o8hf240vumhjgrb`(`user_id` ASC) USING BTREE,
  INDEX `FK3yhgppabh7coic7u414tqyi45`(`user_semester_id` ASC) USING BTREE,
  CONSTRAINT `FK3yhgppabh7coic7u414tqyi45` FOREIGN KEY (`user_semester_id`) REFERENCES `user_semesters` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKf4qni5wnlq0x70fm39w9tv7x4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKoc4yl478i6o8hf240vumhjgrb` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 202 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES (11, 214374, 3, 5);
INSERT INTO `user_course` VALUES (12, 214986, 3, 5);
INSERT INTO `user_course` VALUES (13, 214988, 3, 5);
INSERT INTO `user_course` VALUES (14, 208407, 3, 6);
INSERT INTO `user_course` VALUES (15, 208453, 3, 6);
INSERT INTO `user_course` VALUES (16, 214282, 3, 6);
INSERT INTO `user_course` VALUES (17, 214293, 3, 6);
INSERT INTO `user_course` VALUES (18, 214464, 3, 6);
INSERT INTO `user_course` VALUES (19, 214485, 3, 6);
INSERT INTO `user_course` VALUES (20, 214491, 3, 6);
INSERT INTO `user_course` VALUES (21, 214274, 3, 7);
INSERT INTO `user_course` VALUES (22, 214370, 3, 7);
INSERT INTO `user_course` VALUES (23, 214461, 3, 7);
INSERT INTO `user_course` VALUES (24, 214492, 3, 7);
INSERT INTO `user_course` VALUES (25, 214493, 3, 7);
INSERT INTO `user_course` VALUES (26, 200105, 3, 8);
INSERT INTO `user_course` VALUES (27, 202622, 3, 8);
INSERT INTO `user_course` VALUES (28, 214252, 3, 8);
INSERT INTO `user_course` VALUES (29, 214372, 3, 8);
INSERT INTO `user_course` VALUES (30, 214451, 3, 8);
INSERT INTO `user_course` VALUES (31, 214462, 3, 8);
INSERT INTO `user_course` VALUES (32, 214463, 3, 8);
INSERT INTO `user_course` VALUES (33, 200107, 3, 9);
INSERT INTO `user_course` VALUES (34, 214251, 3, 9);
INSERT INTO `user_course` VALUES (35, 214351, 3, 9);
INSERT INTO `user_course` VALUES (36, 214352, 3, 9);
INSERT INTO `user_course` VALUES (37, 214442, 3, 9);
INSERT INTO `user_course` VALUES (38, 202121, 3, 10);
INSERT INTO `user_course` VALUES (39, 202620, 3, 10);
INSERT INTO `user_course` VALUES (40, 202622, 3, 10);
INSERT INTO `user_course` VALUES (41, 214241, 3, 10);
INSERT INTO `user_course` VALUES (42, 214361, 3, 10);
INSERT INTO `user_course` VALUES (43, 214389, 3, 10);
INSERT INTO `user_course` VALUES (44, 214441, 3, 10);
INSERT INTO `user_course` VALUES (45, 200201, 3, 11);
INSERT INTO `user_course` VALUES (46, 200202, 3, 11);
INSERT INTO `user_course` VALUES (47, 200103, 3, 12);
INSERT INTO `user_course` VALUES (48, 202110, 3, 12);
INSERT INTO `user_course` VALUES (49, 202502, 3, 12);
INSERT INTO `user_course` VALUES (50, 213604, 3, 12);
INSERT INTO `user_course` VALUES (51, 214231, 3, 12);
INSERT INTO `user_course` VALUES (52, 214242, 3, 12);
INSERT INTO `user_course` VALUES (53, 214331, 3, 12);
INSERT INTO `user_course` VALUES (54, 200101, 3, 13);
INSERT INTO `user_course` VALUES (55, 200102, 3, 13);
INSERT INTO `user_course` VALUES (56, 202108, 3, 13);
INSERT INTO `user_course` VALUES (57, 202109, 3, 13);
INSERT INTO `user_course` VALUES (58, 202206, 3, 13);
INSERT INTO `user_course` VALUES (59, 202501, 3, 13);
INSERT INTO `user_course` VALUES (60, 213603, 3, 13);
INSERT INTO `user_course` VALUES (61, 214201, 3, 13);
INSERT INTO `user_course` VALUES (62, 214321, 3, 13);
INSERT INTO `user_course` VALUES (120, 202622, 4, 28);
INSERT INTO `user_course` VALUES (121, 214331, 4, 28);
INSERT INTO `user_course` VALUES (122, 200107, 4, 29);
INSERT INTO `user_course` VALUES (123, 213604, 4, 29);
INSERT INTO `user_course` VALUES (124, 214241, 4, 29);
INSERT INTO `user_course` VALUES (125, 214251, 4, 29);
INSERT INTO `user_course` VALUES (126, 214352, 4, 29);
INSERT INTO `user_course` VALUES (127, 214354, 4, 29);
INSERT INTO `user_course` VALUES (128, 214442, 4, 29);
INSERT INTO `user_course` VALUES (129, 200103, 4, 30);
INSERT INTO `user_course` VALUES (130, 202121, 4, 30);
INSERT INTO `user_course` VALUES (131, 214362, 4, 30);
INSERT INTO `user_course` VALUES (132, 214389, 4, 30);
INSERT INTO `user_course` VALUES (133, 214441, 4, 30);
INSERT INTO `user_course` VALUES (134, 200201, 4, 31);
INSERT INTO `user_course` VALUES (135, 200202, 4, 31);
INSERT INTO `user_course` VALUES (136, 200102, 4, 32);
INSERT INTO `user_course` VALUES (137, 202110, 4, 32);
INSERT INTO `user_course` VALUES (138, 202502, 4, 32);
INSERT INTO `user_course` VALUES (139, 214231, 4, 32);
INSERT INTO `user_course` VALUES (140, 214242, 4, 32);
INSERT INTO `user_course` VALUES (141, 214331, 4, 32);
INSERT INTO `user_course` VALUES (142, 200101, 4, 33);
INSERT INTO `user_course` VALUES (143, 202108, 4, 33);
INSERT INTO `user_course` VALUES (144, 202109, 4, 33);
INSERT INTO `user_course` VALUES (145, 202206, 4, 33);
INSERT INTO `user_course` VALUES (146, 202501, 4, 33);
INSERT INTO `user_course` VALUES (147, 214201, 4, 33);
INSERT INTO `user_course` VALUES (148, 214321, 4, 33);
INSERT INTO `user_course` VALUES (149, 213603, 4, 34);
INSERT INTO `user_course` VALUES (150, 214374, 5, 35);
INSERT INTO `user_course` VALUES (151, 214986, 5, 35);
INSERT INTO `user_course` VALUES (152, 214988, 5, 35);
INSERT INTO `user_course` VALUES (153, 208407, 5, 36);
INSERT INTO `user_course` VALUES (154, 208453, 5, 36);
INSERT INTO `user_course` VALUES (155, 214282, 5, 36);
INSERT INTO `user_course` VALUES (156, 214293, 5, 36);
INSERT INTO `user_course` VALUES (157, 214464, 5, 36);
INSERT INTO `user_course` VALUES (158, 214485, 5, 36);
INSERT INTO `user_course` VALUES (159, 214491, 5, 36);
INSERT INTO `user_course` VALUES (160, 214274, 5, 37);
INSERT INTO `user_course` VALUES (161, 214370, 5, 37);
INSERT INTO `user_course` VALUES (162, 214461, 5, 37);
INSERT INTO `user_course` VALUES (163, 214492, 5, 37);
INSERT INTO `user_course` VALUES (164, 214493, 5, 37);
INSERT INTO `user_course` VALUES (165, 200105, 5, 38);
INSERT INTO `user_course` VALUES (166, 202622, 5, 38);
INSERT INTO `user_course` VALUES (167, 214252, 5, 38);
INSERT INTO `user_course` VALUES (168, 214372, 5, 38);
INSERT INTO `user_course` VALUES (169, 214451, 5, 38);
INSERT INTO `user_course` VALUES (170, 214462, 5, 38);
INSERT INTO `user_course` VALUES (171, 214463, 5, 38);
INSERT INTO `user_course` VALUES (172, 200107, 5, 39);
INSERT INTO `user_course` VALUES (173, 214251, 5, 39);
INSERT INTO `user_course` VALUES (174, 214351, 5, 39);
INSERT INTO `user_course` VALUES (175, 214352, 5, 39);
INSERT INTO `user_course` VALUES (176, 214442, 5, 39);
INSERT INTO `user_course` VALUES (177, 202121, 5, 40);
INSERT INTO `user_course` VALUES (178, 202620, 5, 40);
INSERT INTO `user_course` VALUES (179, 202622, 5, 40);
INSERT INTO `user_course` VALUES (180, 214241, 5, 40);
INSERT INTO `user_course` VALUES (181, 214361, 5, 40);
INSERT INTO `user_course` VALUES (182, 214389, 5, 40);
INSERT INTO `user_course` VALUES (183, 214441, 5, 40);
INSERT INTO `user_course` VALUES (184, 200201, 5, 41);
INSERT INTO `user_course` VALUES (185, 200202, 5, 41);
INSERT INTO `user_course` VALUES (186, 200103, 5, 42);
INSERT INTO `user_course` VALUES (187, 202110, 5, 42);
INSERT INTO `user_course` VALUES (188, 202502, 5, 42);
INSERT INTO `user_course` VALUES (189, 213604, 5, 42);
INSERT INTO `user_course` VALUES (190, 214231, 5, 42);
INSERT INTO `user_course` VALUES (191, 214242, 5, 42);
INSERT INTO `user_course` VALUES (192, 214331, 5, 42);
INSERT INTO `user_course` VALUES (193, 200101, 5, 43);
INSERT INTO `user_course` VALUES (194, 200102, 5, 43);
INSERT INTO `user_course` VALUES (195, 202108, 5, 43);
INSERT INTO `user_course` VALUES (196, 202109, 5, 43);
INSERT INTO `user_course` VALUES (197, 202206, 5, 43);
INSERT INTO `user_course` VALUES (198, 202501, 5, 43);
INSERT INTO `user_course` VALUES (199, 213603, 5, 43);
INSERT INTO `user_course` VALUES (200, 214201, 5, 43);
INSERT INTO `user_course` VALUES (201, 214321, 5, 43);

-- ----------------------------
-- Table structure for user_semesters
-- ----------------------------
DROP TABLE IF EXISTS `user_semesters`;
CREATE TABLE `user_semesters`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credit_hours` int NULL DEFAULT NULL,
  `cumulative_credit` int NULL DEFAULT NULL,
  `cumulative_gpa_10` double NULL DEFAULT NULL,
  `cumulative_gpa_4` double NULL DEFAULT NULL,
  `gpa_10` double NULL DEFAULT NULL,
  `gpa_4` double NULL DEFAULT NULL,
  `semester_id` int NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3ky19us6brlx4fo7u9rknplro`(`semester_id` ASC) USING BTREE,
  INDEX `FKd15tm2fjbjfb8i8tb06sy7r1w`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK3ky19us6brlx4fo7u9rknplro` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKd15tm2fjbjfb8i8tb06sy7r1w` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_semesters
-- ----------------------------
INSERT INTO `user_semesters` VALUES (5, 0, 147, 7.07, 2.78, 0, 0, 20232, 3);
INSERT INTO `user_semesters` VALUES (6, 22, 147, 7.07, 2.78, 7.51, 3.05, 20231, 3);
INSERT INTO `user_semesters` VALUES (7, 18, 125, 6.99, 2.72, 7.26, 2.89, 20222, 3);
INSERT INTO `user_semesters` VALUES (8, 23, 107, 6.93, 2.69, 7.28, 2.83, 20221, 3);
INSERT INTO `user_semesters` VALUES (9, 17, 84, 6.82, 2.64, 7.04, 2.71, 20212, 3);
INSERT INTO `user_semesters` VALUES (10, 18, 67, 6.75, 2.63, 6.88, 2.53, 20211, 3);
INSERT INTO `user_semesters` VALUES (11, 0, 0, 0, 0, 0, 0, 20203, 3);
INSERT INTO `user_semesters` VALUES (12, 18, 43, 6.49, 2.53, 6.77, 2.61, 20202, 3);
INSERT INTO `user_semesters` VALUES (13, 25, 25, 6.3, 2.48, 6.3, 2.48, 20201, 3);
INSERT INTO `user_semesters` VALUES (28, 0, 0, 0, 0, 0, 0, 20233, 4);
INSERT INTO `user_semesters` VALUES (29, 0, 60, 6.26, 2.24, 0, 0, 20232, 4);
INSERT INTO `user_semesters` VALUES (30, 16, 60, 6.26, 2.24, 5.67, 1.94, 20231, 4);
INSERT INTO `user_semesters` VALUES (31, 0, 0, 0, 0, 0, 0, 20223, 4);
INSERT INTO `user_semesters` VALUES (32, 15, 38, 6.56, 2.39, 6.16, 2.07, 20222, 4);
INSERT INTO `user_semesters` VALUES (33, 19, 23, 6.87, 2.64, 6.87, 2.64, 20221, 4);
INSERT INTO `user_semesters` VALUES (34, 0, 0, 0, 0, 0, 0, 0, 4);
INSERT INTO `user_semesters` VALUES (35, 0, 147, 7.07, 2.78, 0, 0, 20232, 5);
INSERT INTO `user_semesters` VALUES (36, 22, 147, 7.07, 2.78, 7.51, 3.05, 20231, 5);
INSERT INTO `user_semesters` VALUES (37, 18, 125, 6.99, 2.72, 7.26, 2.89, 20222, 5);
INSERT INTO `user_semesters` VALUES (38, 23, 107, 6.93, 2.69, 7.28, 2.83, 20221, 5);
INSERT INTO `user_semesters` VALUES (39, 17, 84, 6.82, 2.64, 7.04, 2.71, 20212, 5);
INSERT INTO `user_semesters` VALUES (40, 18, 67, 6.75, 2.63, 6.88, 2.53, 20211, 5);
INSERT INTO `user_semesters` VALUES (41, 0, 0, 0, 0, 0, 0, 20203, 5);
INSERT INTO `user_semesters` VALUES (42, 18, 43, 6.49, 2.53, 6.77, 2.61, 20202, 5);
INSERT INTO `user_semesters` VALUES (43, 25, 25, 6.3, 2.48, 6.3, 2.48, 20201, 5);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `desired_score` double NULL DEFAULT NULL,
  `is_verify` bit(1) NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_valid` datetime(6) NULL DEFAULT NULL,
  `verification_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `department_id` bigint NULL DEFAULT NULL,
  `training_program_id` bigint NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKp56c1712k691lhsyewcssf40f`(`role_id` ASC) USING BTREE,
  INDEX `FKfi832e3qv89fq376fuh8920y4`(`department_id` ASC) USING BTREE,
  INDEX `FK33djlgr47t4j9js706ue2rujl`(`training_program_id` ASC) USING BTREE,
  CONSTRAINT `FK33djlgr47t4j9js706ue2rujl` FOREIGN KEY (`training_program_id`) REFERENCES `training_program` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfi832e3qv89fq376fuh8920y4` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (3.63, b'1', 1, b'1', 2, '2024-06-07 09:34:50.000000', 'dBHM8ScM06', '$2a$10$B.5LGFP5LqvQlH4IoenevuLbGbie6DMkyNRvkf5vnNjV4.aYBJ2Ba', 'tranbuituanngoc@gmail.com', 'Trần Bùi Tuấn Ngọc', NULL, NULL, 52480201, NULL, b'0');
INSERT INTO `users` VALUES (2.85, b'1', 2, b'1', 3, '2024-06-04 23:38:23.000000', 'qeMK9R2VC3', '$2a$10$JMqt7HjAqpTnI5oCGGkdFeqtCfVm4xE.ytwZYuRLBJQNajrr/dC9G', '20130337@st.hcmuaf.edu.vn', 'Trần Bùi Tuấn Ngọc', NULL, NULL, 52480201, 5, b'0');
INSERT INTO `users` VALUES (3.25, b'1', 2, b'1', 4, '2024-07-04 08:19:32.000000', 'RVBg7ZNuky', '$2a$10$B.5LGFP5LqvQlH4IoenevuLbGbie6DMkyNRvkf5vnNjV4.aYBJ2Ba', '22130090@st.hcmuaf.edu.vn', 'Hà Anh Hợp', NULL, NULL, 52480201, 7, b'0');
INSERT INTO `users` VALUES (3.2, b'1', 2, b'1', 5, NULL, NULL, NULL, '20130333@st.hcmuaf.edu.vn', 'Trần Bùi Tuấn Ngọc', 'google', '118283470581908776608', 52480201, NULL, b'0');
INSERT INTO `users` VALUES (3.6, b'1', 2, b'1', 8, NULL, NULL, NULL, '20130332@st.hcmuaf.edu.vn', 'Trần Bùi Tuấn Ngọc', 'google', '118283470581908776608', 52480201, NULL, b'0');

-- ----------------------------
-- Table structure for weeks
-- ----------------------------
DROP TABLE IF EXISTS `weeks`;
CREATE TABLE `weeks`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date NULL DEFAULT NULL,
  `semester_week` int NULL DEFAULT NULL,
  `start_date` date NULL DEFAULT NULL,
  `semester_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKrwtln1vn658oogstugx88k95i`(`semester_id` ASC) USING BTREE,
  CONSTRAINT `FKrwtln1vn658oogstugx88k95i` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weeks
-- ----------------------------
INSERT INTO `weeks` VALUES (96, '2024-02-25', 25, '2024-02-19', 20232);
INSERT INTO `weeks` VALUES (97, '2024-03-03', 26, '2024-02-26', 20232);
INSERT INTO `weeks` VALUES (98, '2024-03-10', 27, '2024-03-04', 20232);
INSERT INTO `weeks` VALUES (99, '2024-03-17', 28, '2024-03-11', 20232);
INSERT INTO `weeks` VALUES (100, '2024-03-24', 29, '2024-03-18', 20232);
INSERT INTO `weeks` VALUES (101, '2024-03-31', 30, '2024-03-25', 20232);
INSERT INTO `weeks` VALUES (102, '2024-04-07', 31, '2024-04-01', 20232);
INSERT INTO `weeks` VALUES (103, '2024-04-14', 32, '2024-04-08', 20232);
INSERT INTO `weeks` VALUES (104, '2024-04-21', 33, '2024-04-15', 20232);
INSERT INTO `weeks` VALUES (105, '2024-04-28', 34, '2024-04-22', 20232);
INSERT INTO `weeks` VALUES (106, '2024-05-05', 35, '2024-04-29', 20232);
INSERT INTO `weeks` VALUES (107, '2024-05-12', 36, '2024-05-06', 20232);
INSERT INTO `weeks` VALUES (108, '2024-05-19', 37, '2024-05-13', 20232);
INSERT INTO `weeks` VALUES (109, '2024-05-26', 38, '2024-05-20', 20232);
INSERT INTO `weeks` VALUES (110, '2024-06-02', 39, '2024-05-27', 20232);
INSERT INTO `weeks` VALUES (111, '2024-06-09', 40, '2024-06-03', 20232);
INSERT INTO `weeks` VALUES (112, '2024-06-16', 41, '2024-06-10', 20232);
INSERT INTO `weeks` VALUES (113, '2024-06-23', 42, '2024-06-17', 20232);
INSERT INTO `weeks` VALUES (114, '2024-06-30', 43, '2024-06-24', 20232);
INSERT INTO `weeks` VALUES (115, '2024-07-14', 45, '2024-07-08', 20233);
INSERT INTO `weeks` VALUES (116, '2024-07-21', 46, '2024-07-15', 20233);
INSERT INTO `weeks` VALUES (117, '2024-07-28', 47, '2024-07-22', 20233);
INSERT INTO `weeks` VALUES (118, '2024-08-04', 48, '2024-07-29', 20233);
INSERT INTO `weeks` VALUES (119, '2024-08-11', 49, '2024-08-05', 20233);
INSERT INTO `weeks` VALUES (120, '2024-08-18', 50, '2024-08-12', 20233);
INSERT INTO `weeks` VALUES (121, '2024-08-25', 51, '2024-08-19', 20233);
INSERT INTO `weeks` VALUES (122, '2024-07-07', 44, '2024-07-01', 20232);

SET FOREIGN_KEY_CHECKS = 1;
