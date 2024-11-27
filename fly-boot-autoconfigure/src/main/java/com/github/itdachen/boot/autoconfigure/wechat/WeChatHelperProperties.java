package com.github.itdachen.boot.autoconfigure.wechat;

import com.github.itdachen.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.boot.autoconfigure.wechat.properties.WeChatAppletProperties;

/**
 * WeChatHelperProperties
 *
 * @author 王大宸
 * @date 2024-11-21 11:22
 */
public class WeChatHelperProperties {

    public WeChatAppletProperties applet() {
        return AppContextHelper.getBean(WeChatAppletProperties.class);
    }


}
