package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.Member;
import org.whu.cs.service.MemberService;
import org.whu.cs.service.ValidService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping(value = "/api/member")
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ValidService validService;


    @ApiOperation(value = "提交LeetCode地址", notes = "链接需要校验格式，地址不可重复")
    @ApiImplicitParam(name = "url", value = "用户LeetCode或LeetCode-cn主页地址，参考格式： https://leetcode.com/alexlj/ ", required = true, dataType = "String")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> create(String url) {
        Map<String, Object> map = new HashMap<>();
        if (!validService.isValidLeetcodeUrl(url)) {
            map.put("error", url + " 格式有误");
        } else if (!memberService.existsByAddress(url)) {
            Member member = new Member();
            member.setGmt_create(new Date());
            member.setUrl(url);
            memberService.save(member);
            map.put("success", member);
        } else {
            map.put("error", url + " 已存在");
        }
        return map;

    }

    // 后期分页
    @ApiOperation(value = "根据用户状态，查询用户LeetCode地址", notes = "三种状态，normal: 0, deleted：1， locked：2，只爬取normal状态的用户信息")
    @ApiImplicitParam(name = "status", value = "用户状态 ", required = true, dataType = "int")
    @GetMapping(value = "/getMemberAddressList")
    @ResponseBody
    public List<Member> getMemberByStatus(@RequestParam int status) {
        return memberService.findALlByStatus(status);
    }
}
