package com.github.itdachen.boot.sign.api;

/**
 * 签名校验 IApiSignVerifyHandler
 *
 * @author 王大宸
 * @date 2024-11-04 10:47
 */
public interface IApiSignHandler {


    String signWith(String appId, String appSecret, String timestamp);


}
