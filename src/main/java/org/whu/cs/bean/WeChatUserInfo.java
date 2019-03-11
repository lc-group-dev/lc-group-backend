package org.whu.cs.bean;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author:Lucas
 * @description:微信用户登录绑定的信息。
 * @Date:2019/3/11
 **/
@Data
@Entity
public class WeChatUserInfo {


    private String openId;
    private Date createdDt;
}
