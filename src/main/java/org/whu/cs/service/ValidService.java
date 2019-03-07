package org.whu.cs.service;

import org.springframework.stereotype.Service;

@Service
public class ValidService {

    public boolean isValidLeetcodeUrl(String url) {
       return url != null;
    }
}
