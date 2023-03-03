package com.itdachen.framework.security.web.controller;


import com.itdachen.framework.context.BizContextHandler;
import com.itdachen.framework.context.annotation.IgnoreResponseAdvice;
import com.itdachen.framework.core.exception.BizException;
import com.itdachen.framework.core.response.ServerResponse;
import com.itdachen.framework.security.client.WebClientConfig;
import com.itdachen.framework.security.user.LYearAdminMenu;
import com.itdachen.framework.security.user.LayuiAdminMenu;
import com.itdachen.framework.security.user.OkAdminMenu;
import com.itdachen.framework.security.web.service.IPermsAuthWebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-09-25 15:27
 * Created with IntelliJ IDEA.
 */
@RestController
public class AdminPermsAuthMenuController {

    private final IPermsAuthWebService permsAuthService;
    private final WebClientConfig webClientConfig;

    public AdminPermsAuthMenuController(IPermsAuthWebService permsAuthService, WebClientConfig webClientConfig) {
        this.permsAuthService = permsAuthService;
        this.webClientConfig = webClientConfig;
    }

    /***
     * layuiAdmin 界面菜单
     *
     * @author 王大宸
     * @date 2022/10/7 16:26
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     */
    @RequestMapping(value = "/perms/auth/menu")
    public ServerResponse<List<LayuiAdminMenu>> findPermsAuthMenu() throws BizException {
        return ServerResponse.okData(permsAuthService.findPermsAuthMenu(webClientConfig.getId(), BizContextHandler.getUserType(), BizContextHandler.getUserId()));
    }

    /***
     * ok-admin 界面菜单
     *
     * @author 王大宸
     * @date 2022/10/7 16:25
     * @return java.util.List<com.itdachen.auth.core.user.OkAdminMenu>
     */
    @GetMapping("/perms/admin/menu")
    @IgnoreResponseAdvice
    public List<OkAdminMenu> findOkAdminPermsAuthMenu() throws BizException {
        return permsAuthService.getUserOkAdminMenu(webClientConfig.getId(), BizContextHandler.getUserType(), BizContextHandler.getUserId());
    }

    /***
     * 获取光年后台管理模板菜单
     *
     * @author 王大宸
     * @date 2023/2/3 9:16
     * @return cn.edu.hubu.framework.core.response.ServerResponse<java.util.List < com.itdachen.framework.user.LYearAdminMenu>>
     */
    @GetMapping("/lyear/admin/menu")
    public ServerResponse<List<LYearAdminMenu>> findLYearAdminMenu() throws Exception {
        return ServerResponse.okData(permsAuthService.findLYearAdminMenu(webClientConfig.getId(),
                BizContextHandler.getUserType(),
                BizContextHandler.getUserId())
        );
    }

}
