package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.WeChatUserInfo;

/**
 * @author:Lucas
 * @description:
 * @Date:2019/3/11
 **/
public interface WeChatAppRepository extends JpaRepository<WeChatUserInfo, String> {
}
