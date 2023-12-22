package com.github.itdachen.boot.security.third.controller;

import com.github.itdachen.boot.autoconfigure.client.ClientProperties;
import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.autoconfigure.security.properties.third.SecurityThirdProperties;
import com.github.itdachen.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.boot.security.constants.ServerForwardKey;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.core.snowflake.SnowflakeUtils;
import com.github.itdachen.framework.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

/***
 *  服务转发, 跳转到其他应用服务
 *  使用方法:
 *  如果有第三方应用信息, 可用填写平台信息, 以英文冒号隔开, 例如: /platform/forward/verify/ticket?uri=TEST_P:/authentication/third/platform
 *  如果没有第三方应用信息, 可直接填写 http 请求地址, 例如: /platform/forward/verify/ticket?uri=https://127.0.0.1:8080/authentication/third/platform
 *  推荐使用第一种
 * @author 王大宸
 * @date 2023/11/14 10:26
 */
@Controller
public class ThirdPlatformForwardController {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final IThirdPlatformVerifyTicketTokenService verifyTicketTokenService;
    private final ClientProperties clientProperties;
    private final SecurityThirdProperties securityThirdProperties;

    public ThirdPlatformForwardController(IThirdPlatformVerifyTicketTokenService verifyTicketTokenService,
                                          ClientProperties clientProperties,
                                          SecurityThirdProperties securityThirdProperties) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.clientProperties = clientProperties;
        this.securityThirdProperties = securityThirdProperties;
    }


    /***
     * 服务转发, 添加转发参数
     * 1、税务机关代码
     * 2、登录代码
     * 3、携带临时 code，有效时限五分钟，用于其他平台登录校验
     * @author 王大宸
     * @date 2023/4/3 17:04
     * @param request request
     * @param response response
     * @param params params
     * @return void
     */
    @GetMapping(SecurityConstants.VERIFY_TICKET_PLATFORM_FORWARD)
    public void serviceForward(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam Map<String, String> params) throws Exception {
        String uri = params.get(ServerForwardKey.URI_KEY);
        StringBuilder targetUri;
        String platformThemeCode = "";
        /* 如果是 http 开头的, 不用去查询平台信息 */
        if (uri.startsWith("http://") || uri.startsWith("https://")) {
            String replace = uri.replace("http://", "").replace("https://", "");
            platformThemeCode = replace.substring(0, replace.indexOf("/")); // 得到: 127.0.0.1:8080
            targetUri = new StringBuilder(uri);
        } else {
            /* 获取平台编码, 根据平台编码, 查询访问地址, 例如: http://127.0.0.1 */
            platformThemeCode = uri.substring(0, uri.indexOf(":"));
            String platformAskUri = verifyTicketTokenService.findVerifyThirdPlatformAskUri(platformThemeCode);

            if (null == platformAskUri || StringUtils.isEmpty(platformAskUri)) {
                redirectStrategy.sendRedirect(request, response, ServerForwardKey.ERROR_URI);
                return;
            }

            targetUri = new StringBuilder(uri.replace(platformThemeCode + ":", platformAskUri));
        }


//        if (!"http://".startsWith(platformAskUri) && !"https://".startsWith(platformAskUri)) {
//            askUri = "http://" + platformAskUri;
//        }


        /* 认证票据标识 */
        final String id = SnowflakeUtils.getId();

        /* 拼接最终访问地址 */
        targetUri = new StringBuilder(targetUrl(targetUri.toString(), id));
        // 拼接参数, 删除请求地址
        params.remove(ServerForwardKey.URI_KEY);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            targetUri.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        /* 添加获取用户信息回调地址 */
        String askUri = clientProperties.getAskUri() + SecurityConstants.VERIFY_TICKET_TOKEN;
        targetUri.append("&").append(ServerForwardKey.TICKET_URL).append("=").append(askUri);

        /* 信息入库 */
        final VerifyTicketToken token = new VerifyTicketToken();
        token.setExpTime(LocalDateTime.now().plusMinutes(securityThirdProperties.getExpTime()));
        verifyTicketTokenService.saveVerifyTicketTokenInfo(token);

        response.sendRedirect(targetUri.toString());
    }

    /***
     * 拼接权限标识码
     *
     * @author 王大宸
     * @date 2023/11/14 15:57
     * @param uri uri
     * @param preAuthCode preAuthCode
     * @return java.lang.String
     */
    private static String targetUrl(String uri, String preAuthCode) {
        final String targetUrl = String.format(ServerForwardKey.AUTHORIZATION_INFORMATION, preAuthCode);
        if (!uri.contains("?")) {
            return uri + "?" + targetUrl;
        }
        return uri + "&" + targetUrl;
    }


}
