package org.whu.cs.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Lucas
 * @description:微信小程序相关的接口
 * @Date:2019/3/10
 **/
@RestController
@RequestMapping("/weChat")
public class WeChatAppController {

    @ApiOperation(value = "小程序登录接口",notes = "传入微信的code")
    @RequestMapping("/login")
    @ResponseBody
    public String login (String code){
        return "返回openid和token";
    }

}
