package com.github.itdachen.framework.code.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


/**
 * 代码生成业务表 Query
 *
 * @author 王大宸
 * @date 2022-08-10 15:11:50
 */
public class TableInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 实体类名称
     */
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 服务上下文
     */
    private String contextPath;


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
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


    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getContextPath() {
        return contextPath;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tableName", getTableName())
                .append("className", getClassName())
                .append("tplCategory", getTplCategory())
                .append("contextPath", getContextPath())
                .toString();
    }

}
