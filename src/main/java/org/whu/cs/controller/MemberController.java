package org.whu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whu.cs.bean.Member;
import org.whu.cs.service.MemberService;
import springfox.documentation.annotations.ApiIgnore;


@RequestMapping(value = "/member")
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ApiIgnore

    @PostMapping(value = "/create")
    @ResponseBody
    public Member create(String address){

        Member member = new Member();
        member.setAddress(address);
        return member;
    }
}
