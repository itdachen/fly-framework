package com.github.itdachen.framework.weixin.oplatform.utils;

import cn.hutool.core.util.XmlUtil;
import com.github.itdachen.framework.weixin.common.mp.aes.WXBizMsgCrypt;
import com.github.itdachen.framework.weixin.oplatform.contents.VerifyTicketInfoType;
import com.github.itdachen.framework.weixin.oplatform.entity.VerifyTicketInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * Description: 微信后台推送 xml 解析
 * Created by 王大宸 on 2023/03/19 1:42
 * Created with IntelliJ IDEA.
 */
public class ComponentVerifyTicketUtils {
    private static final Logger logger = LoggerFactory.getLogger(ComponentVerifyTicketUtils.class);


    public static Map<String, Object> verifyTicket(HttpServletRequest request,
                                                   String appId,
                                                   String token,
                                                   String aesKey) throws Exception {
        try {
            request.setCharacterEncoding("UTF-8");
            // 微信加密签名
            String msg_signature = request.getParameter("msg_signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 从请求中读取整个post数据
            InputStream inputStream = request.getInputStream();
            //获取接收到消息里的XML密文，存放在postData中
            String postData = IOUtils.toString(inputStream, "UTF-8");

            //从XML中获取<Encrypt></Encrypt>标签内的密文文本
            //加密处理
            Map<String, Object> encryptMap = XmlUtil.xmlToMap(postData);
            String encrypt = String.valueOf(encryptMap.get("Encrypt"));
            //格式化密文文本，否则没有<ToUserName>标签，会解密失败，参考官方的加解密代码JAVA版本
            String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
            String fromXML = String.format(format, encrypt);
            //解密后的明文
            String msg = "";
            if (StringUtils.isEmpty(encrypt)) {
                msg = fromXML;
            } else {
                WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, appId);
                // 解密消息
                msg = wxcpt.decryptMsg(msg_signature, timestamp, nonce, fromXML);
            }

            return XmlUtil.xmlToMap(msg);
        } catch (Exception e) {
            logger.error("解析 xml 票据推送信息失败: {}", e.toString());
            throw new Exception("解析 xml 票据推送信息失败");
        }
    }

    /***
     * 微信后台推送验证票据解析
     *
     * @author 王大宸
     * @date 2023/3/19 1:53
     * @param msgMap msgMap
     * @return com.github.itdachen.framework.weixin.oplatform.entity.VerifyTicketInfo
     */
    public static VerifyTicketInfo componentVerifyTicket(Map<String, Object> msgMap) {
        VerifyTicketInfo info = new VerifyTicketInfo();
        info.setInfoType(msgMap.get(VerifyTicketInfoType.INFO_TYPE).toString());
        info.setVerifyTicket(msgMap.get(VerifyTicketInfoType.VERIFY_TICKET).toString());
        info.setAppId(msgMap.get(VerifyTicketInfoType.APP_ID).toString());
        info.setCreateTime(msgMap.get(VerifyTicketInfoType.CREATE_TIME).toString());
        return info;
    }


}
