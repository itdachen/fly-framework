package com.github.itdachen.framework.weixin.wxpay;

import cn.hutool.core.util.RandomUtil;
import com.github.itdachen.framework.weixin.wxpay.config.utils.PrivateKeyUtils;
import com.github.itdachen.framework.weixin.wxpay.entity.WeChatPayOrderInfo;
import com.github.itdachen.framework.weixin.wxpay.response.AppletPayResponse;
import com.github.itdachen.framework.weixin.wxpay.utils.PaySignUtils;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Description: 微信小程序支付
 * Created by 王大宸 on 2023/04/11 21:47
 * Created with IntelliJ IDEA.
 */
public class AppletPayUtils {


    public static AppletPayResponse pay(WeChatPayOrderInfo payOrderInfo,
                                        CloseableHttpClient wxPayClient,
                                        String privateKeyPath) throws Exception {
        final String prepay_id = JSApiPayUtils.pay(payOrderInfo, wxPayClient);

        final String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        final String prepayId = "prepay_id=" + prepay_id;
        final String nonceStr = RandomUtil.randomString(32);
        final String paySign = PaySignUtils.getSign(payOrderInfo.getAppId(),
                timeStamp,
                nonceStr,
                prepayId,
                PrivateKeyUtils.getPrivateKeyPath(privateKeyPath));

        AppletPayResponse response = new AppletPayResponse();
        response.setNonceStr(nonceStr);
        response.setPaySign(paySign);
        response.setSignType("RSA");
        response.setOutTradeNo(payOrderInfo.getOrderId());
        response.setPackagePrepayId(prepayId);
        response.setTimeStamp(timeStamp);
        return response;
    }

}
