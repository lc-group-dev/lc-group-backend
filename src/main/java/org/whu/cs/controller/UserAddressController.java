package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.UserAddress;
import org.whu.cs.service.UserAddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "提交LeetCode地址", notes = "链接需要校验，格式保持一致，且地址不可有重复记录")
    @ApiImplicitParam(name = "address", value = "用户LeetCode或LeetCode-cn主页地址，参考格式： https://leetcode.com/alexlj/ ", required = true, dataType = "String")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> create(UserAddress userAddress) {
        Map<String, Object> map = new HashMap<>();

        if (userAddressService.findByAddress(userAddress.getAddress()) == null) {
            userAddressService.create(userAddress);
            map.put("success", userAddress);
            return map;
        }
        map.put("error", "address重复");

        return map;
    }

    // 后期分页
    @ApiOperation(value = "根据用户状态，查询用户LeetCode地址", notes = "三种状态，normal: 0, deleted：1， locked：2，只爬取normal状态的用户信息")
    @ApiImplicitParam(name = "status", value = "用户状态 ", required = true, dataType = "int")
    @GetMapping(value = "/getUserAddressList")
    @ResponseBody
    public List<UserAddress> getUserAddressByStatus(@RequestParam int status) {
        return userAddressService.getUserAddressByStatus(status);
    }
}
