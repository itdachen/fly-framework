package com.github.itdachen.framework.datasource.encoder.processor;

/**
 * Description:
 * Created by 王大宸 on 2023/03/28 16:30
 * Created with IntelliJ IDEA.
 */
public interface DataSourceEncoderProcessor {

    /***
     * 加密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要加密的 url/username/password
     * @return java.lang.String
     */
    String encrypt(String str);

    /***
     * 解密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要解密的 url/username/password
     * @return java.lang.String
     */
    String decrypt(String str);

}
