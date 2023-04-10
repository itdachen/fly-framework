package com.github.itdachen.framework.code.sdk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;


/**
 * 代码生成业务表 Vo
 *
 * @author 王大宸
 * @date 2022-08-10 15:11:50
 */
public class TableInfoVo implements Serializable {
    private static final long serialVersionUID = 2500472977873547010L;

    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * UI 样式
     */
    private String uiStyle;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 所属菜单目录id
     */
    private String menuId;

    /**
     * 所属菜单目录名称
     */
    private String menuName;

    /**
     * 实体类名称
     */
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 服务上下文
     */
    private String contextPath;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成业务名
     */
    private String businessName;

    /**
     * 生成功能名
     */
    private String functionName;

    /**
     * 生成功能作者
     */
    private String functionAuthor;

    /**
     * 生成代码方式（0 zip压缩包 1自定义路径）
     */
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 备注
     */
    private String remarks;

    private String iframe;

    private String clientId;

    /**
     * 主键信息
     */
    private TableColumnVo pkColumn;

    /**
     * 列
     */
    private List<TableColumnVo> columns;

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory;
    }

    public String getTplCategory() {
        return tplCategory;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }

    public String getFunctionAuthor() {
        return functionAuthor;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public TableColumnVo getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(TableColumnVo pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<TableColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumnVo> columns) {
        this.columns = columns;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getUiStyle() {
        return uiStyle;
    }

    public void setUiStyle(String uiStyle) {
        this.uiStyle = uiStyle;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tableName", getTableName())
                .append("tableComment", getTableComment())
                .append("className", getClassName())
                .append("tplCategory", getTplCategory())
                .append("packageName", getPackageName())
                .append("contextPath", getContextPath())
                .append("moduleName", getModuleName())
                .append("businessName", getBusinessName())
                .append("functionName", getFunctionName())
                .append("functionAuthor", getFunctionAuthor())
                .append("menuId", getMenuId())
                .append("menuName", getMenuName())
                .append("genType", getGenType())
                .append("genPath", getGenPath())
                .append("options", getOptions())
                .append("remarks", getRemarks())
                .append("iframe", getIframe())
                .append("clientId", getClientId())
                .append("uiStyle", getUiStyle())
                .append("pkColumn", getPkColumn())
                .append("columns", getColumns())
                .toString();
    }

}
