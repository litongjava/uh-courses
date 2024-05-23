/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.9-5432
 Source Server Type    : PostgreSQL
 Source Server Version : 160003
 Source Host           : 192.168.3.9:5432
 Source Catalog        : uh_courses
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003
 File Encoding         : 65001

 Date: 22/05/2024 20:55:30
*/


-- ----------------------------
-- Table structure for institution
-- ----------------------------
DROP TABLE IF EXISTS "public"."institution";
CREATE TABLE "public"."institution" (
  "id" "pg_catalog"."int8" NOT NULL,
  "abbr_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "creator" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "create_time" "pg_catalog"."timestamp",
  "updater" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "update_time" "pg_catalog"."timestamp",
  "deleted" "pg_catalog"."int2" DEFAULT 0,
  "tenant_id" "pg_catalog"."int8" DEFAULT 1
)
;
COMMENT ON COLUMN "public"."institution"."id" IS 'id';
COMMENT ON COLUMN "public"."institution"."abbr_name" IS 'abbr_name';
COMMENT ON COLUMN "public"."institution"."name" IS 'name';
COMMENT ON COLUMN "public"."institution"."creator" IS '创建者';
COMMENT ON COLUMN "public"."institution"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."institution"."updater" IS '更新者';
COMMENT ON COLUMN "public"."institution"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."institution"."deleted" IS '是否删除';
COMMENT ON COLUMN "public"."institution"."tenant_id" IS 'tenant_id';
COMMENT ON TABLE "public"."institution" IS 'institution';

-- ----------------------------
-- Records of institution
-- ----------------------------
INSERT INTO "public"."institution" VALUES (260626905647022080, 'HAW', 'Hawaii Community College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905697353728, 'HON', 'Honolulu Community College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905730908160, 'KAP', 'Kapi''olani Community College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905764462592, 'KAU', 'Kauai Community College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905789628416, 'LEE', 'Leeward Community College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905814794240, 'MAU', 'University of Hawaii Maui College', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905835765760, 'WOA', 'University of Hawaii West Oahu', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905860931584, 'HIL', 'University of Hawaii at Hilo', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905886097408, 'MAN', 'University of Hawaii at Manoa', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO "public"."institution" VALUES (260626905915457536, 'WIN', 'Windward Community College', NULL, NULL, NULL, NULL, 0, 1);

-- ----------------------------
-- Primary Key structure for table institution
-- ----------------------------
ALTER TABLE "public"."institution" ADD CONSTRAINT "institution_pkey" PRIMARY KEY ("id");
