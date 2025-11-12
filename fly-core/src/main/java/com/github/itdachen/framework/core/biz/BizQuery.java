package com.github.itdachen.framework.core.biz;

import com.github.itdachen.framework.context.BizContextHandler;

import java.io.Serializable;
import jakarta.validation.constraints.*;

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
    @Max(value = 100, message = "【每页条数】不能超过100")
    private int limit = 10;

    /**
     * 租户ID
     */
    public String tenantId = BizContextHandler.getTenantId();

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}
