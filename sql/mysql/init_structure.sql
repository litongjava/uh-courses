
-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS course;
CREATE TABLE course  (
  id bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  institution varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'institution',
  term varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'term',
  subject_abbr varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'subject_abbr',
  subject_name varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'subject_name',
  focus_on varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GenEd/Focus/Special Des',
  crn varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'crn',
  course varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'course',
  section varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'section',
  title varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'title',
  credits varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'credits',
  instructor varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'instructor',
  curr_enrolled varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'curr_enrolled',
  seats_avail varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'seats_avail',
  curr_waitlisted varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'curr_waitlisted',
  wait_avail varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'wait_avail',
  days varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'days',
  time varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'time',
  room varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'room',
  dates varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'dates',
  details_url varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'details_url',
  sources_url varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sources_url',
  creator varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  updater varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  deleted tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  tenant_id bigint(20) NULL DEFAULT 1 COMMENT 'tenant_id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_remark
-- ----------------------------
DROP TABLE IF EXISTS course_remark;
CREATE TABLE course_remark  (
  id bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  course_id bigint(20) NULL DEFAULT NULL COMMENT 'course_id',
  remark text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'remark',
  creator varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  updater varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  deleted tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  tenant_id bigint(20) NULL DEFAULT 1 COMMENT 'tenant_id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'course_remark' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for institution
-- ----------------------------
DROP TABLE IF EXISTS institution;
CREATE TABLE institution  (
  id bigint(20) NOT NULL COMMENT 'id',
  abbr_name varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'abb_name',
  name varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'name',
  creator varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  updater varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  deleted tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  tenant_id bigint(20) NULL DEFAULT 1 COMMENT 'tenant_id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'institution' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS semester;
CREATE TABLE semester  (
  id bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  institution_id bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT 'Institution_id',
  name varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'name',
  t int(10) NULL DEFAULT NULL COMMENT 't',
  creator varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  updater varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  deleted tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  tenant_id bigint(20) NULL DEFAULT 1 COMMENT 'tenant_id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'semester' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS subject;
CREATE TABLE subject  (
  id bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  semester_id bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT 'semester_id',
  name varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'name',
  s varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 's',
  creator varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  updater varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  deleted tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  tenant_id bigint(20) NULL DEFAULT 1 COMMENT 'tenant_id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'subject' ROW_FORMAT = Dynamic;
