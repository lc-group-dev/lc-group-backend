package org.whu.cs.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.whu.cs.bean.WeChatAppInfo;

import java.net.URI;

/**
 * @author:Lucas
 * @description:微信小程序相关的接口
 * @Date:2019/3/10
 **/
@RestController
@RequestMapping("/weChat")
public class WeChatAppController {

    @ApiOperation(value = "小程序登录接口", notes = "传入微信的code")
    @RequestMapping("/login")
    @ResponseBody
    public String login(String code) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatAppInfo.appId + "&secret=" + WeChatAppInfo.secret + "&js_code=" + code + "&grant_type=authorization_code";
        String resultString = " ";
        try {
//            构建URI
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
//            构建GET请求

            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(httpGet);
//            返回请求的结果
            int resultCode =response.getStatusLine().getStatusCode();
            if (resultCode == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else {
                return "请求失败，错误码是："+resultCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String openId = jsonObject.getString("openId");
        String sessionKey =jsonObject.getString("sessionKey");

        return "返回openid和token";
    }

}
