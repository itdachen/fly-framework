package com.github.itdachen.framework.security.web.controller;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.permission.PermissionInfo;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.security.client.WebClientConfig;
import com.github.itdachen.framework.security.web.service.IPermsAuthWebService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 * Created by 王大宸 on 2022-09-25 15:08
 * Created with IntelliJ IDEA.
 */
@Controller
public class AdminDashboardController {

    private static final String PATH_PREFIX = "backstage/layui";
    private static final String LYEAR_PREFIX = "backstage/lyear";
    private static final String OK_PATH_PREFIX = "backstage/okadmin";

    private final Environment environment;
    private final WebClientConfig webClientConfig;
    private final IPermsAuthWebService permsAuthService;

    public AdminDashboardController(Environment environment,
                                    WebClientConfig webClientConfig,
                                    IPermsAuthWebService permsAuthService) {
        this.environment = environment;
        this.webClientConfig = webClientConfig;
        this.permsAuthService = permsAuthService;
    }

    /***
     * 登录成功之后跳转页面 (LayuiAdmin)
     *
     * @author 王大宸
     * @date 2022/9/6 22:44
     * @param modelMap modelMap
     *
     * @return java.lang.String
     */
    @GetMapping({"/admin/index"})
    public String index(ModelMap modelMap) {
        String permsAuthUri = contextPath() + "/perms/auth/menu";
        modelMap.put("permsAuthUri", permsAuthUri);
        clientInfo(modelMap);
        return PATH_PREFIX + "/index";
    }

    /***
     * 跳转到首页 (OK-Admin)
     *
     * @author 王大宸
     * @date 2022/10/7 13:49
     * @param modelMap modelMap
     * @return java.lang.String
     */
    @GetMapping("/home/index")
    public String okAdminIndex(ModelMap modelMap) {
        clientInfo(modelMap);
        return OK_PATH_PREFIX + "/index";
    }

    /***
     * 跳转到后台(光年模板)
     *
     * @author 王大宸
     * @date 2023/2/3 10:04
     * @param modelMap modelMap
     * @return java.lang.String
     */
    @GetMapping("/lyear/index")
    public String lyearAdminIndex(ModelMap modelMap) {
        clientInfo(modelMap);
        return LYEAR_PREFIX + "/index";
    }

    /***
     * 跳转到首页
     *
     * @author 王大宸
     * @date 2022/9/6 22:44
     * @return java.lang.String
     */
    @GetMapping("/dashboard/console")
    public String homeConsole() {
        return PATH_PREFIX + "/console2";
    }

    /***
     * 跳转到修改密码页面
     *
     * @author 王大宸
     * @date 2022/9/7 16:42
     * @return java.lang.String
     */
    @GetMapping("/paw")
    public String paw() {
        return PATH_PREFIX + "/paw";
    }

    /***
     * 统一返回给前端页面信息
     *
     * @author 王大宸
     * @date 2023/2/2 17:22
     * @param modelMap modelMap
     * @return void
     */
    private void clientInfo(ModelMap modelMap) {
        /* 登录用户头像和姓名 */
        modelMap.put("userName", BizContextHandler.getNickName());
        modelMap.put("avatar", BizContextHandler.getAvatar());

        /* 系统信息 */
        modelMap.put("copyrightYear", webClientConfig.getCopyrightYear());
        modelMap.put("clientName", webClientConfig.getName());
        modelMap.put("version", webClientConfig.getVersion());
        modelMap.put("issuer", webClientConfig.getIssuer());

        /* 默认跳转页面 */
        PermissionInfo homeUri = permsAuthService.getHomeUri(webClientConfig.getId());
        modelMap.put("homeUri", homeUri.getUri());
        modelMap.put("menuName", homeUri.getName());

        /* 退出登录 */
        modelMap.put("logoutUri", contextPath() + "/logout");
    }

    /***
     * 获取当前上下文
     *
     * @author 王大宸
     * @date 2023/2/15 16:12
     * @return java.lang.String
     */
    private String contextPath() {
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            return "";
        }
        return contextPath;
    }


}
