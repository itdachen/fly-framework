package com.github.itdachen.framework.weixin.wxpay.utils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * Description: 获取签名
 * Created by 王大宸 on 2023/04/11 21:52
 * Created with IntelliJ IDEA.
 */
public class PaySignUtils {

    public static String getSign(String appId,
                                 String timeStamp,
                                 String nonceStr,
                                 String prepayId,
                                 PrivateKey privateKey) throws Exception {
        final String signatureStr = appId + "\n"
                + timeStamp + "\n"
                + nonceStr + "\n"
                + prepayId + "\n";
        final Signature sign = Signature.getInstance("SHA256withRSA");
//        final PrivateKey merchantPrivateKey = PrivateKeyUtils.getPrivateKeyPath(privateKeyPath);
//        if (privateKey.startsWith("classpath:")) {
//            File file = ResourceUtils.getFile(privateKey);
//            merchantPrivateKey = PemUtil.loadPrivateKey(new FileInputStream(file));
//        } else {
//            merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(privateKey.getBytes(StandardCharsets.UTF_8)));
//        }
        sign.initSign(privateKey);
        sign.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }

}
