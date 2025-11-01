package com.github.itdachen.framework.cloud.jwt.parse.verified;

import jakarta.servlet.http.HttpServletRequest;

/**
 * VerifiedTicketUrlServiceImpl
 *
 * @author 王大宸
 * @date 2024-12-18 10:22
 */
public class DefaultVerifiedTicketUrlService implements IVerifiedTicketUrlService {

    @Override
    public boolean handler(HttpServletRequest request) {
        return true;

//        String header = request.getHeader(UserInfoConstant.VERIFIED_TICKET);
//        boolean passFlag = UserInfoConstant.VERIFIED_TICKET_VALUE.equals(header);
//        if (!passFlag) {
//            // 是否内部调用
//            header = request.getHeader(UserInfoConstant.NAL_FEIGN);
//            passFlag = UserInfoConstant.NAL_FEIGN_VALUE.equals(header);
//        }
//        /* 既没有通过网关鉴权, 也不是内网调用, 返回非法请求 */
//        return passFlag;
    }

}
