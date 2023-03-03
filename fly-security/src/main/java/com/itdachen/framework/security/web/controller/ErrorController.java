package com.itdachen.framework.security.web.controller;

import com.itdachen.framework.security.properties.SecurityBrowserProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 错误页面访问目录
 * Created by 王大宸 on 2022-09-25 14:38
 * Created with IntelliJ IDEA.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    private static final String PATH_PREFIX = "error";

    private final SecurityBrowserProperties securityBrowserProperties;

    public ErrorController(SecurityBrowserProperties securityBrowserProperties) {
        this.securityBrowserProperties = securityBrowserProperties;
    }


    @GetMapping("/401")
    public String error401(ModelMap modelMap) {
        modelMap.put("uri",securityBrowserProperties.getSignInPage());
        return PATH_PREFIX + "/401";
    }

    @GetMapping("/403")
    public String error403(ModelMap modelMap) {
        modelMap.put("uri",securityBrowserProperties.getSuccessForwardUrl());
        return PATH_PREFIX + "/403";
    }

    @GetMapping("/404")
    public String error404(ModelMap modelMap) {
        modelMap.put("uri",securityBrowserProperties.getSuccessForwardUrl());
        return PATH_PREFIX + "/404";
    }

    @GetMapping("/500")
    public String error505(ModelMap modelMap) {
        modelMap.put("uri",securityBrowserProperties.getSuccessForwardUrl());
        return PATH_PREFIX + "/500";
    }


}
