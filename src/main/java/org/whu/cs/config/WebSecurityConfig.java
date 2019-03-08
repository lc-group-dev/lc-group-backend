package org.whu.cs.config;

/**
 * @program: leetcode-group
 * @description: ${description}
 * @author: caozhen
 * @create: 2019-03-09 00:45
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf,所有请求允许访问
        http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
}