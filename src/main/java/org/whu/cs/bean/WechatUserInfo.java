package org.whu.cs.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author:Lucas
 * @description:微信用户登录绑定的信息。
 * @Date:2019/3/11
 **/
@Data
@Entity
public class WechatUserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    //    微信昵称
    private String nick_name;
    //leetCode的地址
    private String address;
    //    微信头像地址
    private String avatarUrl;
    private Date createdDt;
    //    0未知，1男2女
    private int gender;
    private String openId;
    private String phoneNum;
    private Date updatedDt;
    private String userName;
}
