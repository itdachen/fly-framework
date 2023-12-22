package com.github.itdachen.boot.security.third.service;


import com.github.itdachen.boot.security.authentication.VerifyTicketToken;

/**
 * 获取平台信息, 获取用户认证票据接口, 使用时需要自行实现下面三个接口
 *
 * @author 王大宸
 * @date 2023-11-14 10:04
 */
public interface IThirdPlatformVerifyTicketTokenService {


    /***
     * 根据编码, 查询第三方平台信息(需要系统自己实现)
     *
     * @author 王大宸
     * @date 2023/11/14 11:29
     * @param code code
     * @return java.lang.String
     */
    String findVerifyThirdPlatformAskUri(String code) throws Exception;


    /***
     * 添加跳转认证信息
     *
     * @author 王大宸
     * @date 2023/11/14 10:16
     * @param token token
     * @return void
     */
    void saveVerifyTicketTokenInfo(VerifyTicketToken token) throws Exception;


    /***
     * 根据编码(ID), 查询校验信息
     *
     * @author 王大宸
     * @date 2023/11/14 10:10
     * @param id 校验唯一标识
     * @return cn.edu.hubu.framework.security.authentication.VerifyTicketToken
     */
    VerifyTicketToken findVerifyTicketToken(String id) throws Exception;


}
