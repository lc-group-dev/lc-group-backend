package org.whu.cs.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:Lucas
 * @description:微信配置等相关信息，注意保密
 * @Date:2019/3/10
 **/
@Data
@Component
public class AppLoginInfo {
    @Value("${AppLoginInfo.appId}")
    public  String appId;
    @Value("${AppLoginInfo.secret}")
    public  String secret;
    public static final String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * token秘钥，请勿泄露，请勿随便修改
     */
    @Value("${AppLoginInfo.SECRET}")
    public  String SECRETKEY;
}
