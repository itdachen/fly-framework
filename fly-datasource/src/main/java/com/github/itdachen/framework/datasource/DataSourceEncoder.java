package com.github.itdachen.framework.datasource;

/**
 * Description: 加解密接口
 * Created by 王大宸 on 2023/01/28 15:11
 * Created with IntelliJ IDEA.
 */
public interface DataSourceEncoder {


    /***
     * 加密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要加密的字符串
     * @return java.lang.String
     */
    String encrypt(String str);

    /***
     * 解密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要解密的字符串
     * @return java.lang.String
     */
    String decrypt(String str);


}
