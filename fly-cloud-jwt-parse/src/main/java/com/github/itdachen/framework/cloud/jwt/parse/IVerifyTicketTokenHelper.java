package com.github.itdachen.framework.cloud.jwt.parse;


import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:27
 * Created with IntelliJ IDEA.
 */
public interface IVerifyTicketTokenHelper {

    IJwtInfo verifyTicketToken(String token) throws Exception;

}
