package com.github.itdachen.framework.boot.sign.api;

/**
 * 签名校验 IApiSignVerifyHandler
 *
 * @author 剑鸣秋朔
 * @date 2024-11-04 10:47
 */
public interface IApiSignHandler {


    String signWith(String appId, String appSecret, String timestamp);


}
