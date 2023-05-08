package com.github.itdachen.framework.code.sdk.vo;

import com.github.itdachen.framework.core.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


/**
 * 代码生成业务表字段 Vo
 *
 * @author 王大宸
 * @date 2022-08-10 15:11:50
 */
public class TableColumnVo implements Serializable {
    private static final long serialVersionUID = 1854453052698290195L;

    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 归属表编号
     */
    private String tableId;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * JAVA类型
     */
    private String javaType;

    /**
     * JAVA字段名
     */
    private String javaField;

    /**
     * 前端TS类型
     */
    private String tsType;

    /**
     * 是否主键（1是）
     */
    private String isPk;

    /**
     * 是否Dto/Vo类字段
     */
    private String isDtoVo;

    /**
     * 是否必填（1是）
     */
    private String isRequired;

    /**
     * 是否为插入字段（1是）
     */
    private String isForm;

    /**
     * 是否列表字段（1是）
     */
    private String isList;

    /**
     * 是否查询字段（1是）
     */
    private String isQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    private String queryType;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序
     */
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getIsPk() {
        return isPk;
    }

    public String getIsDtoVo() {
        return isDtoVo;
    }

    public void setIsDtoVo(String isDtoVo) {
        this.isDtoVo = isDtoVo;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public String getIsForm() {
        return isForm;
    }

    public void setIsForm(String isForm) {
        this.isForm = isForm;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getIsList() {
        return isList;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictType() {
        return dictType;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public boolean isSuperColumn() {
        return isSuperColumn2(this.javaField);
    }

    public static boolean isSuperColumn2(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                "id", "tenantId", "createUser", "createUserId", "createTime",
                "updateTime", "updateUser", "updateUserId", "remark", "remarks",
                "parentName", "parentId", "orderNum", "ancestors");
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtils.equals("1", isList);
    }


    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    public String getTsType() {
        return tsType;
    }

    public void setTsType(String tsType) {
        this.tsType = tsType;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tableId", getTableId())
                .append("columnName", getColumnName())
                .append("columnComment", getColumnComment())
                .append("columnType", getColumnType())
                .append("javaType", getJavaType())
                .append("javaField", getJavaField())
                .append("isPk", getIsPk())
                .append("isDtoVo", getIsDtoVo())
                .append("isRequired", getIsRequired())
                .append("isForm", getIsForm())
                .append("isList", getIsList())
                .append("isQuery", getIsQuery())
                .append("queryType", getQueryType())
                .append("htmlType", getHtmlType())
                .append("dictType", getDictType())
                .append("sort", getSort())
                .toString();
    }

}
