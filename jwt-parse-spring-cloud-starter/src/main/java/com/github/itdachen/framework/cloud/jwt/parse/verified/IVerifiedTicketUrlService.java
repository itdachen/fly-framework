package com.github.itdachen.framework.cloud.jwt.parse.verified;

import jakarta.servlet.http.HttpServletRequest;

/**
 * IVerifiedTicketUrlService
 *
 * @author 王大宸
 * @date 2024-12-18 10:22
 */
public interface IVerifiedTicketUrlService {


    boolean handler(HttpServletRequest request);

}
