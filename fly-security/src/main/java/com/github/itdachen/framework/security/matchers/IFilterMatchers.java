package com.github.itdachen.framework.security.matchers;

import java.util.List;

/**
 * Description: 不拦截路径对外接口 (系统内部不拦截接口 + 自定义不拦截接口)
 * Created by 王大宸 on 2022-09-23 17:23
 * Created with IntelliJ IDEA.
 */
public interface IFilterMatchers {

    String[] matchers();

    List<String> requestMatchers();

}
