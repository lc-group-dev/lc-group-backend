package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.WechatUserInfo;

/**
 * @author:Lucas
 * @description:
 * @Date:2019/3/11
 **/
public interface WechatAppRepository extends JpaRepository<WechatUserInfo, String> {
    WechatUserInfo findByOpenId(String openId);

    WechatUserInfo findByMemberId(Long memberId);
    boolean existsByOpenId(String openId);
}
