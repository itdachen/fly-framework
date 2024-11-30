package com.github.itdachen.boot.security.third.controller;

import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 校验授权码
 * 测试: http://153.12.72.167:7080/verify/ticket/token/authorize?code=1643160334707593216
 * Created by 王大宸 on 2023/04/03 17:07
 * Created with IntelliJ IDEA.
 */
//@RestController
public class VerifyTicketAuthorizationCodeController {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTicketAuthorizationCodeController.class);
    private final IThirdPlatformVerifyTicketTokenService thirdVerifyTicketTokenService;

    public VerifyTicketAuthorizationCodeController(IThirdPlatformVerifyTicketTokenService thirdVerifyTicketTokenService) {
        this.thirdVerifyTicketTokenService = thirdVerifyTicketTokenService;
    }

    /***
     * 校验验证码
     * 失败: 返回 error
     * 成功: 返回税务机关代码和登录代码, 并删除 redis 缓存
     *
     * @author 王大宸
     * @date 2023/4/4 11:30
     * @param code code
     * @return cn.edu.hubu.framework.core.response.ServerResponse<java.lang.String>
     */
  //  @RequestMapping(SecurityConstants.VERIFY_TICKET_TOKEN)
    public ServerResponse<VerifyTicketToken> verifyTicket(String code) {
        try {
            VerifyTicketToken tokenVo = thirdVerifyTicketTokenService.findVerifyTicketToken(code);
            return ServerResponse.ok(tokenVo);
        } catch (Exception e) {
            logger.error("第三方平台用户票据认证失败: {}", e.getMessage());
            return ServerResponse.err("系统错误, 请联系管理人员!");
        }
    }


}
