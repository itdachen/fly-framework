package com.github.itdachen.framework.code.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-07-13 16:40
 * Created with IntelliJ IDEA.
 */
public class ProtoTable implements Serializable {
    private static final long serialVersionUID = 3385444231561942454L;

    private String id;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 表字段
     */
    private List<ProtoColumns> columns;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<ProtoColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<ProtoColumns> columns) {
        this.columns = columns;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("tableName" , getTableName())
                .append("tableComment" , getTableComment())
                .append("columns" , getColumns())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }

}
