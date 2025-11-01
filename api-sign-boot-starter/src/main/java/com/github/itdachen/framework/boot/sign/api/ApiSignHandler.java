package com.github.itdachen.framework.boot.sign.api;


import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;

/**
 * 接口签名
 *
 * @author 王大宸
 * @date 2024-11-04 11:01
 */
public class ApiSignHandler {


    /***
     * 获取签名接口
     *
     * @author 王大宸
     * @date 2024/11/4 11:02
     * @return com.github.itdachen.boot.sign.api.IApiSignHandler
     */
    public IApiSignHandler apiSignWith() {
        return AppContextHelper.getBean(IApiSignHandler.class);
    }


}
