package com.github.itdachen.framework.cloud.jwt.parse.helper;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.parse.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.key.AuthClientTokenSecretKey;
import com.github.itdachen.framework.context.exception.BizException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:28
 * Created with IntelliJ IDEA.
 */
@Component
public class VerifyTicketTokenHelperImpl implements IVerifyTicketTokenHelper {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTicketTokenHelperImpl.class);
    private final AuthClientTokenSecretKey authClientTokenSecretKey;
    private final ParseTokenHelper parseTokenHelper;

    public VerifyTicketTokenHelperImpl(AuthClientTokenSecretKey authClientTokenSecretKey,
                                       ParseTokenHelper parseTokenHelper) {
        this.authClientTokenSecretKey = authClientTokenSecretKey;
        this.parseTokenHelper = parseTokenHelper;
    }

    @Override
    public IJwtInfo verifyTicketToken(String token) throws Exception {
        try {
            return parseTokenHelper.parseToken(token, authClientTokenSecretKey.getTokenPublicKey());
        } catch (ExpiredJwtException e) {
            throw new BizException("认证令牌已过期！");
        } catch (IllegalArgumentException e) {
            throw new BizException("秘钥错误！");
        } catch (Exception e){
            throw new BizException("token 解析失败！");
        }
    }


}
