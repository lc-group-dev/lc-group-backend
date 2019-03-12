package org.whu.cs.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.AppLoginInfo;
import org.whu.cs.bean.WeChatUserInfo;
import org.whu.cs.repository.WeChatAppRepository;
import org.whu.cs.service.WeChatAppService;

import java.net.URI;
import java.util.Date;

/**
 * @author:Lucas
 * @description:微信小程序相关的接口
 * @Date:2019/3/10
 **/
@RestController
@RequestMapping("/weChat")
public class WeChatAppController {

    @Autowired
    WeChatAppService weChatAppService;

    @Autowired
    WeChatAppRepository weChatAppRepository;

    @ApiOperation(value = "小程序登录接口", notes = "传入微信的code")
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + AppLoginInfo.appId
                + "&secret=" + AppLoginInfo.secret
                + "&js_code=" + code + "&grant_type=authorization_code";
        String resultString = " ";
        try {
//            构建URI
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
//            构建GET请求

            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(httpGet);
//            返回请求的结果
            int resultCode = response.getStatusLine().getStatusCode();
            if (resultCode == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                return "请求失败，错误码是：" + resultCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        /*当jsonObject的size为2时返回的是错误信息*/
        if (jsonObject == null || jsonObject.size() == 2) {
            return jsonObject.toString();
        }
        String openId = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        WeChatUserInfo weChatUserInfo = new WeChatUserInfo();
        if (StringUtil.isEmpty(openId)) {
            weChatUserInfo.setOpenId(openId);
            weChatUserInfo.setCreatedDt(new Date());
        }
        weChatAppRepository.save(weChatUserInfo);
        String token = weChatAppService.wxCreateToken(openId);


        return token;
    }

}
