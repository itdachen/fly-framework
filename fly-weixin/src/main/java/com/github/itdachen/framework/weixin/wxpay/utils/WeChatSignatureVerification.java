package com.github.itdachen.framework.weixin.wxpay.utils;

import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.constant.WechatPayHttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;

/**
 * Description: 微信验签工具类
 * Created by 王大宸 on 2023/04/11 22:15
 * Created with IntelliJ IDEA.
 */
public class WeChatSignatureVerification {

    private static final Logger logger = LoggerFactory.getLogger(WeChatSignatureVerification.class);

    /**
     * 应答超时时间，单位为分钟
     */
    protected static final long RESPONSE_EXPIRED_MINUTES = 5;
    protected final Verifier verifier;
    protected final String body;
    protected final String requestId;

    public WeChatSignatureVerification(Verifier verifier, String body, String requestId) {
        this.verifier = verifier;
        this.body = body;
        this.requestId = requestId;
    }

    public static IllegalArgumentException parameterError(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("parameter error: " + message);
    }

    public static IllegalArgumentException verifyFail(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("signature verify fail: " + message);
    }

    public Boolean validate(HttpServletRequest request) throws IOException {
        try {
            validateParameters(request);
            String message = buildMessage(request);
            String serial = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_SERIAL); // WECHAT_PAY_SERIAL
            String signature = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_SIGNATURE); // WECHAT_PAY_SIGNATURE

            if (!verifier.verify(serial, message.getBytes(StandardCharsets.UTF_8), signature)) {
                throw verifyFail("serial=[%s] message=[%s] sign=[%s], request-id=[%s]",
                        serial, message, signature, request.getHeader(WechatPayHttpHeaders.REQUEST_ID));
            }
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return false;
        }

        return true;
    }

    private void validateParameters(HttpServletRequest request) {
        String[] headers = {WechatPayHttpHeaders.WECHAT_PAY_SERIAL,
                WechatPayHttpHeaders.WECHAT_PAY_SIGNATURE,
                WechatPayHttpHeaders.WECHAT_PAY_NONCE,
                WechatPayHttpHeaders.WECHAT_PAY_TIMESTAMP};

        String header = null;
        for (String headerName : headers) {
            header = request.getHeader(headerName);
            if (header == null) {
                throw parameterError("empty [%s], request-id=[%s]", headerName, requestId);
            }
        }
        String timestampStr = header;
        try {
            Instant responseTime = Instant.ofEpochSecond(Long.parseLong(timestampStr));
            // 拒绝过期应答
            if (Duration.between(responseTime, Instant.now()).abs().toMinutes() >= RESPONSE_EXPIRED_MINUTES) {
                throw parameterError("timestamp=[%s] expires, request-id=[%s]", timestampStr, requestId);
            }
        } catch (DateTimeException | NumberFormatException e) {
            throw parameterError("invalid timestamp=[%s], request-id=[%s]", timestampStr, requestId);
        }
    }

    private String buildMessage(HttpServletRequest request) throws IOException {
        String timestamp = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_TIMESTAMP); //  WECHAT_PAY_TIMESTAMP
        String nonce = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_NONCE); // WECHAT_PAY_NONCE
        return timestamp + "\n"
                + nonce + "\n"
                + body + "\n";
    }



}
