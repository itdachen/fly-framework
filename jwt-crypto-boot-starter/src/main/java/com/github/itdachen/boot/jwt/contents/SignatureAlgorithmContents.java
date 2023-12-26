package com.github.itdachen.boot.jwt.contents;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

/**
 * SignatureAlgorithmContents
 *
 * @author 王大宸
 * @date 2023-12-26 22:12
 */
public class SignatureAlgorithmContents {


    public static final SignatureAlgorithm ES512 = Jwts.SIG.ES512;

    public static final SignatureAlgorithm RS512 = Jwts.SIG.RS512;


}
