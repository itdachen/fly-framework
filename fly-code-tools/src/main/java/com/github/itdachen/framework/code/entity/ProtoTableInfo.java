package com.github.itdachen.framework.code.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/04/10 22:03
 * Created with IntelliJ IDEA.
 */
public class ProtoTableInfo implements Serializable {
    private static final long serialVersionUID = 218579643311099318L;

    private List<TableInfo> tableInfos = new ArrayList<>();

    private List<TableColumn> tableColumns = new ArrayList<>();

    public ProtoTableInfo() {
    }

    public ProtoTableInfo(List<TableInfo> tableInfos, List<TableColumn> tableColumns) {
        this.tableInfos = tableInfos;
        this.tableColumns = tableColumns;
    }


    public List<TableInfo> getTableInfos() {
        return tableInfos;
    }

    public void setTableInfos(List<TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }

    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }
}
