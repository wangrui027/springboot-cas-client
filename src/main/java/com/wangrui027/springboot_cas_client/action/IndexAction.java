package com.wangrui027.springboot_cas_client.action;

import org.jasig.cas.client.boot.configuration.CasClientConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class IndexAction {

    /**
     * CAS客户端配置类
     */
    @Resource
    private CasClientConfigurationProperties casProperties;

    /**
     * 首页
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/")
    public String hello() {
        String message = String.format("登录成功，请在浏览器键入：【%s/logout】以退出CAS", casProperties.getClientHostUrl());
        return message;
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        StringBuilder sb = new StringBuilder();
        sb.append("redirect:").append(casProperties.getServerUrlPrefix()).append("/logout?service=").append(casProperties.getClientHostUrl());
        return sb.toString();
    }

}
