package com.github.itdachen.framework.weixin.wxpay.config.utils;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PrivateKey;

/**
 * Description: 获取商户的私钥文件
 * Created by 剑鸣秋朔 on 2023/04/11 21:38
 * Created with IntelliJ IDEA.
 */
public class PrivateKeyUtils {

    /***
     * 获取商户的私钥文件
     *
     * @author 剑鸣秋朔
     * @date 2023/4/11 21:38
     * @param privateKeyPath privateKeyPath
     * @return java.security.PrivateKey
     */
    public static PrivateKey getPrivateKeyPath(String privateKeyPath) throws Exception {
        try {
//            if (privateKeyPath.startsWith("classpath:")) {
//                File file = ResourceUtils.getFile(privateKeyPath);
//                return PemUtil.loadPrivateKey(new FileInputStream(file));
//            }
            //   return PemUtil.loadPrivateKey(new ByteArrayInputStream(privateKeyPath.getBytes(StandardCharsets.UTF_8)));

            final File file = ResourceUtils.getFile(privateKeyPath);
            return PemUtil.loadPrivateKey(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new Exception("私钥文件不存在");
        }
    }

}
