package org.whu.cs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:Lucas
 * @description:这是token生成和验证工具。
 * @Date:2019/3/9
 **/
public class JwtTokenUtil {

    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj
     */
    public static final String SECRET = "JKKLJOoasdlfj";
    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param memberId 登录成功后用户memberId, 参数memberId不可传空
     */
    public static String createToken(Long memberId) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> HeadMap = new HashMap<>();
        HeadMap.put("alg", "HS256");
        HeadMap.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        try {
            String token = JWT.create().withHeader(HeadMap) // header
                    .withClaim("iss", "Service") // payload
                    .withClaim("aud", "WXAPP")
                    .withClaim("memberId", null == memberId ? null : memberId.toString())
                    .withIssuedAt(iatDate) // sign time
                    .withExpiresAt(expiresDate) // expire time
                    .sign(Algorithm.HMAC256(SECRET)); // signature
            return token;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据Token获取memberId
     *
     * @param token
     * @return memberId
     */
    public static Long getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim memberId_claim = claims.get("memberId");
        if (null == memberId_claim || StringUtils.isEmpty(memberId_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return Long.valueOf(memberId_claim.asString());
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }

}
