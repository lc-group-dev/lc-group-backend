package org.whu.cs.service;

import org.springframework.stereotype.Service;

/**
 * 数据校验类
 */


@Service
public class ValidService {

    /**
     * Is valid leetcode url boolean.
     *
     * @param url the url
     * @return the boolean
     */
    public boolean isValidLeetcodeUrl(String url) {
       return url != null;
    }
}
