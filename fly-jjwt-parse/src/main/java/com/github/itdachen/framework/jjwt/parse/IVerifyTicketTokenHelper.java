package com.github.itdachen.framework.jjwt.parse;


import com.github.itdachen.framework.jjwt.core.IJwtInfo;

import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:27
 * Created with IntelliJ IDEA.
 */
public interface IVerifyTicketTokenHelper {

    IJwtInfo verifyTicketToken(String token) throws Exception;


}
