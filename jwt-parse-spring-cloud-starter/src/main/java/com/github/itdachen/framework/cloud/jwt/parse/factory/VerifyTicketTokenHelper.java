package com.github.itdachen.framework.cloud.jwt.parse.factory;

import com.github.itdachen.framework.cloud.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.TokenPublicKey;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.exception.ClientTokenException;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * VerifyTicketTokenHelper
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 22:32
 */
public class VerifyTicketTokenHelper implements IVerifyTicketTokenHelper {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTicketTokenHelper.class);

    private final ParseTokenFactory parseTokenFactory;

    @Autowired
    private TokenPublicKey publicKey;

    public VerifyTicketTokenHelper(ParseTokenFactory parseTokenFactory) {
        this.parseTokenFactory = parseTokenFactory;
    }

    @Override
    public IJwtInfo parseToken(String token) throws Exception {
        BizContextHandler.setToken(token);
        return parseTokenInfo(token);
    }


    @Override
    public void parse(String token) throws Exception {
        IJwtInfo iJwtInfo = parseTokenInfo(token);
        BizContextHandler.setContextHandler(iJwtInfo);
        BizContextHandler.setToken(token);
    }


    /***
     * 统一解析 token
     *
     * @author 剑鸣秋朔
     * @date 2024/9/6 14:20
     * @param token token
     * @return com.github.itdachen.framework.context.jwt.IJwtInfo
     */
    private IJwtInfo parseTokenInfo(String token) throws Exception {
        try {
            return parseTokenFactory.build().parse(token, publicKey.getPublicKey());
        } catch (SignatureException se) {
            logger.error("密钥错误: ", se);
            throw new ClientTokenException("密钥错误");
        } catch (MalformedJwtException me) {
            logger.error("密钥算法或者密钥转换错误: ", me);
            throw new ClientTokenException("密钥算法或者密钥转换错误");
        } catch (MissingClaimException mce) {
            logger.error("密钥缺少校验数据: ", mce);
            throw new ClientTokenException("密钥缺少校验数据");
        } catch (ExpiredJwtException mce) {
            logger.error("密钥已过期: ", mce);
            throw new ClientTokenException("密钥已过期");
        } catch (JwtException jwte) {
            logger.error("密钥解析错误: ", jwte);
            throw new ClientTokenException("密钥解析错误");
        }

    }


}
