CREATE TABLE `project`  (
                            `id` bigint NOT NULL COMMENT '主键',
                            `standard_id` bigint NULL COMMENT '标准id',
                            `start_time` datetime NULL COMMENT '项目开始时间',
                            `end_time` datetime NULL COMMENT '项目结束时间',
                            `cost` decimal(30, 10) NULL COMMENT '集团费用',
                            `extra_cost` decimal(30, 10) NULL COMMENT '其他费用',
                            `project_type` tinyint NULL COMMENT '项目类型，如复审、新增、废止等',
                            `plan_type` tinyint NULL COMMENT '计划类型。1：计划内；2：计划外',
                            `status` tinyint NULL COMMENT '状态',
                            `created_time` datetime(0) NULL COMMENT '创建时间',
                            `created_by` bigint NULL COMMENT '创建人',
                            `created_org` bigint(10) ZEROFILL NULL COMMENT '创建单位',
                            `updated_time` datetime(0) NULL COMMENT '修改时间',
                            `updated_by` bigint NULL COMMENT '修改人',
                            `is_deleted` tinyint NULL COMMENT '是否删除。1：删除；0：未删除',
                            PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '项目';