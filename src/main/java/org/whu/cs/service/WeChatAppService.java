package org.whu.cs.service;

import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.whu.cs.util.JwtTokenUtil;

/**
 * @author:Lucas
 * @description:
 * @Date:2019/3/11
 **/
@Service
public class WeChatAppService {

    public String wxCreateToken(String openId){
        if (StringUtil.isEmpty(openId)){
            return null;
        }

        return "";
    }

}
