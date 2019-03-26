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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.AppLoginInfo;
import org.whu.cs.bean.Member;
import org.whu.cs.bean.WechatUserInfo;
import org.whu.cs.repository.MemberRepository;
import org.whu.cs.repository.WechatAppRepository;
import org.whu.cs.service.WechatAppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:Lucas
 * @description:微信小程序相关的接口
 * @Date:2019/3/10
 **/
@RestController
@RequestMapping("/weChat")
public class WechatAppController {

    @Autowired
    WechatAppRepository wechatAppRepository;
    @Autowired
    WechatAppService wechatAppService;

    @Autowired
   private AppLoginInfo appLoginInfo;

    @Autowired
    private MemberRepository memberRepository;

    @ApiOperation(value = "小程序登录接口", notes = "传入微信的code")
    @GetMapping("/login")
    @ResponseBody
    public Map<Object, Object> login(@RequestParam String code) {
        Map<Object, Object> map = new HashMap<>();
        if (StringUtil.isEmpty(code)) {
            String error = "null";

        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appLoginInfo.getAppId()
                + "&secret=" + appLoginInfo.getSecret()
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
                map.put("请求失败，错误码是", resultCode);
                return map;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        /*当jsonObject的size为2时返回的是错误信息*/
        if (jsonObject == null || jsonObject.size() == 2) {
            map.put("错误：", jsonObject.toString());
            return map;
        }
        String openId = jsonObject.getString("openid");
        if (wechatAppService.vailOpenId(openId)){
            int status = wechatAppService.checkWeChatStatus(openId);
            map.put("token", wechatAppService.wxCreateToken(openId));
            map.put("status", status);
            return map;
        } else {
            //        String sessionKey = jsonObject.getString("session_key");
            WechatUserInfo wechatUserInfo = new WechatUserInfo();
            if (StringUtil.isEmpty(openId)) {
                map.put("openId为空", openId);
                return map;
            }
            wechatUserInfo.setOpenId(openId);
            wechatUserInfo.setCreatedDt(new Date());
            wechatUserInfo.setLoginTime(new Date());
            wechatAppRepository.save(wechatUserInfo);
            String token = wechatAppService.wxCreateToken(openId);
            map.put("token", token);
            map.put("status", 1);
            return map;
        }

    }

    @ApiOperation(value = "小程序登录用户信息保存接口", notes = "传入微信的getUserinfo")
    @PostMapping("/store")
    @ResponseBody
    public Map<Object, Object> getUserInfo(@RequestBody WechatUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> map = new HashMap<>();
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            map.put("token:", "不存在");
            return map;
        }
        WechatUserInfo info = wechatAppService.vailUserByToken(token);
        if (info == null) {
            map.put("用户：", "不存在，请重新登录");
        }
        if (userInfo.getUserName() != null || userInfo.getNick_name() != null || userInfo.getAddress() != null) {
            Member member= memberRepository.findByUsername(userInfo.getUserName());
            if (member==null||member.getMemberId()==null){
                member.setUrl(userInfo.getAddress());
                member.setUsername(userInfo.getUserName());
                member.setStatus(0);
                member.setGmt_create(new Date());
                member.setGmt_modified(new Date());
                try {
                    memberRepository.save(member);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                info.setMemberId(member.getMemberId());
            }
            info.setAddress(userInfo.getAddress());
            info.setAvatarUrl(userInfo.getAvatarUrl() == null ? null : userInfo.getAvatarUrl());
            info.setUserName(userInfo.getUserName());
            info.setGender(userInfo.getGender());
            info.setNick_name(userInfo.getNick_name());
            info.setMemberId(member.getMemberId());
            info.setLoginTime(new Date());
            info.setUpdatedDt(new Date());
            try {
                wechatAppRepository.save(info);

            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(" save success", new Date());
        } else {
            map.put("信息不完全,请重新传入", info);
        }
        return map;
    }
//    @ApiOperation(value = "测试写入接口", notes = "传入微信的code")
//    @PostMapping(value = "/Save")
//    @ResponseBody
//    public String saveEntity(@RequestBody String openId) {
//        WechatUserInfo wechatUserInfo=new WechatUserInfo();
//        if (!StringUtil.isEmpty(openId)) {
//            wechatUserInfo.setOpenId(openId);
//            wechatUserInfo.setCreatedDt(new Date());
//        }
//
//        wechatAppRepository.save(wechatUserInfo);
//        String token = wechatAppService.wxCreateToken(openId);
//        return token;
//    }

//    @ApiOperation(value = "测试解密接口", notes = "传入微信的code")
//    @PostMapping(value = "/drcy")
//    @ResponseBody
//    public WechatUserInfo drcy(@RequestBody String token){
//       WechatUserInfo wechatUserInfo = wechatAppService.vailUserByToken(token);
//       return wechatUserInfo;
//    }
}
