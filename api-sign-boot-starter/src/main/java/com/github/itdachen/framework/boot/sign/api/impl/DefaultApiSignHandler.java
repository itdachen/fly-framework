package com.github.itdachen.framework.boot.sign.api.impl;

import com.github.itdachen.framework.boot.sign.api.IApiSignHandler;
import com.github.itdachen.framework.crypto.hmac.HmacSHA1Utils;

/**
 * 签名默认处理方式
 *
 * @author 王大宸
 * @date 2024-11-04 11:06
 */
public class DefaultApiSignHandler implements IApiSignHandler {

    @Override
    public String signWith(String appId, String appSecret, String timestamp) {
        return HmacSHA1Utils.signWithHmacSha1(appId, appId + "-" + appSecret + "-" + timestamp);
    }

}
