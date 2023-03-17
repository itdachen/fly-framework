package com.github.itdachen.framework.core.biz;

import java.io.Serializable;

/**
 * Description: 查询基础类
 * Created by 王大宸 on 2023/02/12 22:00
 * Created with IntelliJ IDEA.
 */
public class BizQuery implements Serializable {
    private static final long serialVersionUID = 4227832629867882551L;

    /**
     * 默认查询第一页
     */
    private int page = 1;

    /**
     * 默认每页查询十条
     */
    private int limit = 10;

    public BizQuery() {
    }

    public BizQuery(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
