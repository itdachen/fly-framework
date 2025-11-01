package com.github.itdachen.framework.cloud.jwt.crypto.token;


import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractCreateTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 21:54
 */
public abstract class AbstractCreateTokenHandler implements ICryptoTokenService {
    //定义默认有效期为10分钟 单位：毫秒
    protected static final Long JWT_Default_Expires = 10 * 60 * 1000L;
    private final SignatureAlgorithm signatureAlgorithm;

    public AbstractCreateTokenHandler(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    protected String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) {
        long nowMillis = System.currentTimeMillis();
        //签发时间
        Date now = new Date(nowMillis);
        if (null == expires) {
            expires = JWT_Default_Expires;
        }
        long expiresTime = nowMillis + expires;
        Date expDate = new Date(expiresTime);

      //  expiration(new Date(System.currentTimeMillis()+7200 * 1000)). // 设置token过期时间为2H
      //  Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));
        Map<String, Object> claimsMap = claimsMap(jwtInfo);

        String tokenId = jwtInfo.getTokenId();
        if (null == tokenId || tokenId.isEmpty()) {
            tokenId = IdUtils.guid();
        }

        // 使用私钥加密
        return Jwts.builder()
                .id(tokenId)
                .subject(jwtInfo.getSubject())
                .issuer(issuer)
                .issuedAt(now)
                .expiration(expDate)
                .claims(claimsMap)
                .signWith(privateKey, signatureAlgorithm) // <-- 私钥
                .compact();
    }


    /***
     * 将用户信息封装成 Map
     *
     * @author 王大宸
     * @date 2023/4/24 0:45
     * @param jwtInfo jwtInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    protected Map<String, Object> claimsMap(IJwtInfo jwtInfo) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(UserInfoConstant.USER_ID, jwtInfo.getUserId());
        claimsMap.put(UserInfoConstant.NICK_NAME, jwtInfo.getNickName());
        claimsMap.put(UserInfoConstant.ACCOUNT, jwtInfo.getUniqueName());
        claimsMap.put(UserInfoConstant.TENANT_ID, jwtInfo.getTenantId());

        Map<String, String> otherInfo = jwtInfo.getOtherInfo();

        claimsMap.put(UserInfoConstant.USER_TYPE, otherInfo.get(UserInfoConstant.USER_TYPE));

        claimsMap.putAll(jwtInfo.getOtherInfo());
        return claimsMap;
    }


}
