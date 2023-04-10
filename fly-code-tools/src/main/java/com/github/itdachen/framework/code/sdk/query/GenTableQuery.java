package com.github.itdachen.framework.code.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-08-10 15:32
 * Created with IntelliJ IDEA.
 */
public class GenTableQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 8166465115584139076L;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 排除的表名
     */
    private List<String> tables;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tableName", getTableName())
                .append("tableComment", getTableComment())
                .append("page", getPage())
                .append("limit", getLimit())
                .append("tables", getTables())
                .toString();
    }

}
