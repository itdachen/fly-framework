CREATE TABLE "SYS_THIRD_PARTY_PLATFORMS" (
  "ID" VARCHAR2(36 BYTE)  NOT NULL,
  "THEME_TYPE" VARCHAR2(255 BYTE) ,
  "THEME_CODE" VARCHAR2(255 BYTE) ,
  "PLATFORM_TITLE" VARCHAR2(255 BYTE) ,
  "ASK_URI" VARCHAR2(255 BYTE) ,
  "ICON" NVARCHAR2(255) ,
  "REMARKS" NVARCHAR2(255) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER" NVARCHAR2(50) ,
  "CREATE_USER_ID" NVARCHAR2(36) ,
  "UPDATE_TIME" DATE ,
  "UPDATE_USER" NVARCHAR2(50) ,
  "UPDATE_USER_ID" NVARCHAR2(36) 
);
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."ID" IS '主键唯一标识';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."THEME_TYPE" IS '第三方平台类型';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."THEME_CODE" IS '第三方平台标识(例如: zhihu, 标识唯一)';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."PLATFORM_TITLE" IS '平台名称';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."ASK_URI" IS '请求地址';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."ICON" IS '图标';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."REMARKS" IS '备注';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."CREATE_USER_ID" IS '创建人ID';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."UPDATE_USER" IS '更新人';
COMMENT ON COLUMN "SYS_THIRD_PARTY_PLATFORMS"."UPDATE_USER_ID" IS '更新人ID';
COMMENT ON TABLE "SYS_THIRD_PARTY_PLATFORMS" IS '第三方平台信息';

-- ----------------------------
-- Records of SYS_THIRD_PARTY_PLATFORMS
-- ----------------------------
INSERT INTO "SYS_THIRD_PARTY_PLATFORMS" VALUES ('1643153887328342016', NULL, 'zhihu', '知乎', 'https://zhuanlan.zhihu.com', NULL, NULL, TO_DATE('2023-04-04 15:29:49', 'SYYYY-MM-DD HH24:MI:SS'), '王大宸', '1541230113952239617', TO_DATE('2023-04-04 15:29:49', 'SYYYY-MM-DD HH24:MI:SS'), '王大宸', '1541230113952239617');

-- ----------------------------
-- Primary Key structure for table SYS_THIRD_PARTY_PLATFORMS
-- ----------------------------
ALTER TABLE "SYS_THIRD_PARTY_PLATFORMS" ADD CONSTRAINT "SYS_C008775" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SYS_THIRD_PARTY_PLATFORMS
-- ----------------------------
ALTER TABLE "SYS_THIRD_PARTY_PLATFORMS" ADD CONSTRAINT "SYS_C008774" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
