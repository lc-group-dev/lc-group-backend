package org.whu.cs.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.whu.cs.bean.WechatUserInfo;
import org.whu.cs.repository.WechatAppRepository;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.whu.cs.bean.AppLoginInfo.SECRET;

/**
 * @author:Lucas
 * @description:
 * @Date:2019/3/11
 **/
@Service
public class WechatAppService {

    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;
    @Autowired
    WechatAppRepository wechatAppRepository;

    public boolean testSave(WechatUserInfo wechatUserInfo) {
        try {
            wechatAppRepository.save(wechatUserInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WechatUserInfo vailUserByToken(String token) {
        WechatUserInfo wechatUserInfo;
        if (StringUtils.isEmpty(token)) {
            return new WechatUserInfo();
        }
        Map<String, Claim> getUserByToken = decryToken(token);
        if (getUserByToken == null) {
            return null;
        }
//        获取token里面的openid
        String openId = getUserByToken.get("openId").asString();
        if (StringUtils.isEmpty(openId)) {
            return null;
        }
        wechatUserInfo = wechatAppRepository.findByOpenId(openId);
        if (wechatUserInfo == null) {
            return null;
        }
        return wechatUserInfo;
    }

    //解密wx的token
    public Map<String, Claim> decryToken(String token) {
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

    /*构建微信专属token*/
    public String wxCreateToken(String openId) {
        if (StringUtil.isEmpty(openId)) {
            return null;
        }
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
                    .withClaim("openId", openId)
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
}
