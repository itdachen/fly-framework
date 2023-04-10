package com.github.itdachen.framework.code.utils;

/**
 * Description:
 * Created by 王大宸 on 2022-08-14 15:58
 * Created with IntelliJ IDEA.
 */
public class GenConstants {


    /**
     * 树名称字段
     */
    public static final String TREE_NAME = "treeName" ;

    /**
     * 后端继承 biz
     */
    public static final String TPL_BIZ = "biz" ;

    /**
     * 上级菜单ID字段
     */
    public static final String PARENT_MENU_ID = "1539506450076930048" ;

    /**
     * 上级菜单名称字段
     */
    public static final String PARENT_MENU_NAME = "parentMenuName" ;

    /**
     * 默认包名
     */
    public static final String PACKAGE_NAME = "com.github.itdachen.admin" ;

    /**
     * 默认模块名称
     */
    public static final String MODULE_NAME = "admin" ;

    /**
     * 表单不展示字段
     */
    public static final String[] FIELD_NOT_FORM = {"id", "create_user", "create_user_id", "create_time",
            "update_time", "update_user", "update_user_id",
            "is_del", "del_flag", "tenant_id",
            "ID", "CREATE_USER", "CREATE_USER_ID", "CREATE_TIME", "UPDATE_USER", "UPDATE_USER_ID", "UPDATE_TIME",
            "DEL_FLAG", "REMARK", "REMARKS", "IS_DELETE", "IS_DEL", "TENANT_ID"};

    /**
     * Dto/Vo 排除字段
     */
    public static final String[] USER_FIELD = {"create_user", "create_user_id", "create_time",
            "update_time", "update_user", "update_user_id",
            "CREATE_USER", "CREATE_USER_ID", "CREATE_TIME", "UPDATE_USER", "UPDATE_USER_ID", "UPDATE_TIME",
            "DEL_FLAG", "REMARK", "REMARKS", "IS_DELETE", "IS_DEL", "TENANT_ID"};

    /**
     * 查询不需要的字段
     */
    public static final String[] COLUMNNAME_NOT_QUERY = {"id", "create_user", "create_user_id", "create_time",
            "update_time", "update_user", "update_user_id",
            "del_flag", "remark", "remarks", "is_del",
            "attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7", "attr8", "attr9", "attr10",
            "ID", "CREATE_USER", "CREATE_USER_ID", "CREATE_TIME", "UPDATE_USER", "UPDATE_USER_ID", "UPDATE_TIME",
            "DEL_FLAG", "REMARK", "REMARKS", "IS_DELETE", "IS_DEL", "TENANT_ID",
            "ATTR1", "ATTR2", "ATTR3", "ATTR4", "ATTR5", "ATTR6", "ATTR7", "ATTR8", "ATTR9", "ATTR10",
    };


    /**
     * 必填排除字典
     */
    public static final String[] COLUMN_NAME_NOT_REQUIRED = {"id", "create_user", "create_user_id", "create_time",
            "update_time", "update_user", "update_user_id",
            "remark", "remarks", "is_del", "del_flag", "descriptions", "description", "tenant_id",
            "attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7", "attr8", "attr9", "attr10",
            "ID", "CREATE_USER", "CREATE_USER_ID", "CREATE_TIME", "UPDATE_USER", "UPDATE_USER_ID", "UPDATE_TIME",
            "DEL_FLAG", "REMARK", "REMARKS", "IS_DELETE", "IS_DEL", "TENANT_ID", "DESCRIPTIONS", "DESCRIPTION",
            "ATTR1", "ATTR2", "ATTR3", "ATTR4", "ATTR5", "ATTR6", "ATTR7", "ATTR8", "ATTR9", "ATTR10",
    };

    /**
     * 页面表格分页不需要显示的列表字段
     */
    public static final String[] COLUMN_NAME_NOT_LIST = {"id", "create_user", "create_user_id", "create_time",
            "update_time", "update_user", "update_user_id",
            "remark", "remarks", "is_del", "del_flag", "tenant_id", "descriptions", "description",
            "attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7", "attr8", "attr9", "attr10",
            "ID", "CREATE_USER", "CREATE_USER_ID", "CREATE_TIME", "UPDATE_USER", "UPDATE_USER_ID", "UPDATE_TIME",
            "DEL_FLAG", "REMARK", "REMARKS", "IS_DELETE", "IS_DEL", "TENANT_ID",
            "ATTR1", "ATTR2", "ATTR3", "ATTR4", "ATTR5", "ATTR6", "ATTR7", "ATTR8", "ATTR9", "ATTR10",
    };

    /**
     * 描述字段, 默认使用文本域
     */
    public static final String[] REMARKS_FILED = {"remarks", "remark", "describe", "des", "desc",
            "describes", "REMARKS", "REMARK", "descriptions", "description", "DESCRIPTIONS", "DESCRIPTION"};

    /**
     * 数据库数字类型
     */
    public static final String[] COLUMN_TYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bigint", "float", "double", "decimal"};


    /**
     * 数据库 Integer 类型
     */
    public static final String[] COLUMN_TYPE_INTEGER = {"tinyint", "smallint", "mediumint", "int", "integer", "number", "NUMBER"};

    /**
     * 数据库 Long 类型
     */
    public static final String[] COLUMN_TYPE_LONG = {"bigint"};

    /**
     * 数据库 Long 类型
     */
    public static final String[] COLUMN_TYPE_DECIMAL = {"decimal"};

    /**
     * 数据库 Double 类型
     */
    public static final String[] COLUMN_TYPE_DOUBLE = {"double"};

    /**
     * 数据库 Float 类型
     */
    public static final String[] COLUMN_TYPE_FLOAT = {"float"};

    /**
     * 数据库 bit 类型
     */
    public static final String[] COLUMN_TYPE_BIT = {"bit"};

    /**
     * 数据库字符串类型
     */
    public static final String[] COLUMNTYPE_STR = {"char", "blob", "longtext", "mediumtext",
            "varchar", "nvarchar", "varchar2", "text"};

    /**
     * 数据库文本类型
     */
    public static final String[] COLUMNTYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     * 数据库时间类型
     */
    public static final String[] COLUMNTYPE_TIME = {"datetime", "time", "date", "DATE", "timestamp"};

    /**
     * 文本域
     */
    public static final String HTML_TEXTAREA = "textarea" ;

    /**
     * 文本框
     */
    public static final String HTML_INPUT = "input" ;

    /**
     * 下拉框
     */
    public static final String HTML_SELECT = "select" ;

    /**
     * 单选框
     */
    public static final String HTML_RADIO = "radio" ;

    /**
     * 复选框
     */
    public static final String HTML_CHECKBOX = "checkbox" ;

    /**
     * 图片上传控件
     */
    public static final String HTML_IMAGE_UPLOAD = "imageUpload" ;

    /**
     * 文件上传控件
     */
    public static final String HTML_FILE_UPLOAD = "fileUpload" ;

    /**
     * 富文本控件
     */
    public static final String HTML_EDITOR = "editor" ;

    /**
     * 字符串类型
     */
    public static final String TYPE_STRING = "String" ;

    /**
     * 日期控件
     */
    public static final String HTML_DATETIME = "datetime" ;

    /**
     * 时间类型
     */
    public static final String TYPE_DATE = "LocalDateTime" ;

    /**
     * 整型
     */
    public static final String TYPE_INTEGER = "Integer" ;

    /**
     * 长整型
     */
    public static final String TYPE_LONG = "Long" ;

    /**
     * 浮点型
     */
    public static final String TYPE_DOUBLE = "Double" ;

    /**
     * 浮点型
     */
    public static final String TYPE_FLOAT = "Float" ;

    /**
     * 高精度计算类型
     */
    public static final String TYPE_BIGDECIMAL = "BigDecimal" ;

    /**
     * 布尔类型
     */
    public static final String TYPE_BOOLEAN = "Boolean" ;

    /**
     * 需要
     */
    public static final String REQUIRE = "1" ;

    /**
     * 不需要
     */
    public static final String NOT_REQUIRE = "0" ;

    /**
     * 相等查询
     */
    public static final String QUERY_EQ = "EQ" ;

    /**
     * 模糊查询
     */
    public static final String QUERY_LIKE = "LIKE" ;

}
