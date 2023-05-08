package com.github.itdachen.framework.code.utils;

import com.github.itdachen.framework.boot.runner.handler.ContextPathHandler;
import com.github.itdachen.framework.code.entity.ProtoColumns;
import com.github.itdachen.framework.code.entity.ProtoTable;
import com.github.itdachen.framework.code.entity.ProtoTableInfo;
import com.github.itdachen.framework.code.entity.TableInfo;
import com.github.itdachen.framework.code.entity.TableColumn;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.core.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/04/10 22:00
 * Created with IntelliJ IDEA.
 */
public class TableColumnFieldUtils {

    public static ProtoTableInfo pottingTableInfo(List<ProtoTable> list, String clientId, String uiStyle) {
        return pottingTableInfo(list, clientId, uiStyle, GenConstants.PARENT_MENU_ID);
    }

    public static ProtoTableInfo pottingTableInfo(List<ProtoTable> list, String clientId, String uiStyle, String menuId) {
        TableColumn column;
        TableInfo tableInfo;
        List<ProtoColumns> protoColumns;
        List<TableColumn> columns = new ArrayList<>();
        List<TableInfo> tableInfos = new ArrayList<>();
        int index;
        for (ProtoTable protoTable : list) {
            tableInfo = new TableInfo();
            tableInfo.setId(VelocityUtils.getId());
            tableInfo.setClientId(clientId);
            tableInfo.setCreateTime(LocalDateTime.now());
            tableInfo.setCreateUserId(BizContextHandler.getUserId());
            tableInfo.setCreateUser(BizContextHandler.getUserId());
            tableInfo.setUpdateTime(LocalDateTime.now());
            tableInfo.setUpdateUserId(BizContextHandler.getUserId());
            tableInfo.setUpdateUser(BizContextHandler.getNickName());
            tableInfo.setUiStyle(uiStyle);
            tableInfo.setTableName(protoTable.getTableName());
            tableInfo.setFunctionAuthor(BizContextHandler.getNickName());
            tableInfo.setTableComment(protoTable.getTableComment());
            tableInfo.setClassName(GeneratorUtils.tableToJava(protoTable.getTableName(), ""));
            tableInfo.setPackageName(GenConstants.PACKAGE_NAME);
            tableInfo.setModuleName(GenConstants.MODULE_NAME);
            tableInfo.setFunctionName(protoTable.getTableComment());
            tableInfo.setFunctionAuthor(BizContextHandler.getNickName());
            tableInfo.setGenType("0");
            tableInfo.setTplCategory(GenConstants.TPL_BIZ);
            tableInfo.setContextPath(ContextPathHandler.contextPath());
            tableInfo.setBusinessName("/" + protoTable.getTableName().toLowerCase().replaceAll("_", "/"));
            tableInfo.setMenuId(menuId);
            tableInfo.setIframe("iframe");
            tableInfos.add(tableInfo);

            /* 字段信息 */
            protoColumns = protoTable.getColumns();
            index = 1;
            for (ProtoColumns protoColumn : protoColumns) {
                column = new TableColumn();
                column.setId(VelocityUtils.getId());
                column.setCreateTime(LocalDateTime.now());
                column.setCreateUserId(BizContextHandler.getUserId());
                column.setCreateUser(BizContextHandler.getUserId());
                column.setUpdateTime(LocalDateTime.now());
                column.setUpdateUserId(BizContextHandler.getUserId());
                column.setUpdateUser(BizContextHandler.getNickName());
                column.setTableId(tableInfo.getId());
                column.setColumnName(protoColumn.getColumnName());
                column.setColumnComment(protoColumn.getColumnComment());
                column.setColumnType(protoColumn.getDataType());
                column.setSort(index);
                column.setIsPk("0");
                if ("PRI".equals(protoColumn.getColumnKey()) || "P".equals(protoColumn.getColumnKey())) {
                    column.setIsPk("1");
                }
                TableColumnFieldUtils.initColumnField(column);
                columns.add(column);
                index++;
            }
        }

        return new ProtoTableInfo(tableInfos, columns);
    }

    /***
     * 初始化表类型
     *
     * @author 王大宸
     * @date 2023/4/10 22:01
     * @param column column
     * @return void
     */
    private static void initColumnField(TableColumn column) {
        // 默认不需要
        column.setIsList(GenConstants.NOT_REQUIRE);
        column.setIsQuery(GenConstants.NOT_REQUIRE);
        column.setIsDtoVo(GenConstants.NOT_REQUIRE);
        column.setIsForm(GenConstants.NOT_REQUIRE);
        column.setIsRequired(GenConstants.NOT_REQUIRE);

        // 数据类型
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        // 设置java字段名
        column.setJavaField(GeneratorUtils.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(GenConstants.TYPE_STRING);
        column.setQueryType(GenConstants.QUERY_EQ);
        column.setHtmlType(GenConstants.HTML_INPUT);

        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) || arraysContains(GenConstants.REMARKS_FILED, columnName)) {
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        } else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_INTEGER, dataType)) {
            // Integer 类型
            column.setJavaType(GenConstants.TYPE_INTEGER);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_LONG, dataType)) {
            // Long
            column.setJavaType(GenConstants.TYPE_LONG);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_DECIMAL, dataType)) {
            // BigDecimal
            column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_DOUBLE, dataType)) {
            // Double
            column.setJavaType(GenConstants.TYPE_DOUBLE);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_FLOAT, dataType)) {
            // Float
            column.setJavaType(GenConstants.TYPE_FLOAT);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_BIT, dataType)) {
            // Boolean
            column.setJavaType(GenConstants.TYPE_BOOLEAN);
        }

        // 表单字段
        if (!arraysContains(GenConstants.FIELD_NOT_FORM, columnName)) {
            column.setIsForm(GenConstants.REQUIRE);
        }
        // 必填字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_REQUIRED, columnName)) {
            column.setIsRequired(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_LIST, columnName) && "0".equals(column.getIsPk())) {
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && "0".equals(column.getIsPk())) {
            column.setIsQuery(GenConstants.REQUIRE);
        }
        // dto/vo 字段
        if (!arraysContains(GenConstants.USER_FIELD, columnName)) {
            column.setIsDtoVo(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name") || StringUtils.endsWithIgnoreCase(columnName, "title")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }

        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type") || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
        }
        // 文件字段设置文件上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    private static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue.toLowerCase());
    }

    /***
     * 获取字段长度
     *
     * @author 王大宸
     * @date 2022/8/14 16:04
     * @param columnType columnType
     * @return java.lang.Integer
     */
    private static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }

    private static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            return StringUtils.substringBefore(columnType, "(");
        } else {
            return columnType;
        }
    }

}
