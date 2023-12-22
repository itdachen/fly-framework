package com.github.itdachen.framework.cloud.jwt.core.token;

import java.security.KeyPair;

/**
 * 获取 token 秘钥
 *
 * @author 王大宸
 * @date 2023-12-17 20:22
 */
public interface IKeyPairHandler {


    KeyPair pair() throws Exception;


}
