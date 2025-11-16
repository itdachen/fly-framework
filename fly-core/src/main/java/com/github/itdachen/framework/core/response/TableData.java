package com.github.itdachen.framework.core.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回数据格式
 *
 * @author 剑鸣秋朔
 * @date 2023/02/12 22:51
 */
public class TableData<T> implements Serializable {
    private static final long serialVersionUID = 6361435953431364220L;

    /**
     * 数据总条数
     */
    long total = 0;

    /**
     * 查询返回数据
     */
    List<T> rows = new ArrayList<>();

    public TableData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public TableData() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
