package com.github.itdachen.boot.security.third.service.impl;

import com.github.itdachen.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThirdPlatformVerifyTicketTokenServiceImpl
 *
 * @author 王大宸
 * @date 2023-11-14 10:04
 */
public class DefaultThirdPlatformVerifyTicketToken implements IThirdPlatformVerifyTicketTokenService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultThirdPlatformVerifyTicketToken.class);

    @Override
    public String findVerifyThirdPlatformAskUri(String code) throws Exception {
        logger.warn("为实现接口, 请实现 IThirdPlatformVerifyTicketTokenService 接口");
        return null;
    }

    @Override
    public void saveVerifyTicketTokenInfo(VerifyTicketToken token) throws Exception {
        logger.warn("为实现接口, 请实现 IThirdPlatformVerifyTicketTokenService 接口");
    }

    @Override
    public VerifyTicketToken findVerifyTicketToken(String id) throws Exception {
        logger.warn("为实现接口, 请实现 IThirdPlatformVerifyTicketTokenService 接口");
        return new VerifyTicketToken();
    }


}
