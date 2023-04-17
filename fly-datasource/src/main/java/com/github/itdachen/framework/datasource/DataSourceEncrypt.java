package com.github.itdachen.framework.datasource;

/**
 * Description: 对外加密接口
 * Created by 王大宸 on 2023/04/17 22:42
 * Created with IntelliJ IDEA.
 */
public interface DataSourceEncrypt {

    String encrypt(String str, String type);

}
