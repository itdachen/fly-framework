package com.github.itdachen.boot.jwt.parse.factory;

import com.github.itdachen.boot.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PublicKey;

/**
 * VerifyTicketTokenHelper
 *
 * @author 王大宸
 * @date 2023-12-23 22:32
 */
public class VerifyTicketTokenHelper implements IVerifyTicketTokenHelper {

    private final ParseTokenFactory parseTokenFactory;

    public VerifyTicketTokenHelper(ParseTokenFactory parseTokenFactory) {
        this.parseTokenFactory = parseTokenFactory;
    }

    @Override
    public IJwtInfo parseToken(String token, PublicKey publicKey) throws Exception {
        return parseTokenFactory.build().parse(token, publicKey);
    }
}
