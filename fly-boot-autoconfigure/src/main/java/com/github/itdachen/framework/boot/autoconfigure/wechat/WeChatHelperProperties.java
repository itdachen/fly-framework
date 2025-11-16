package com.github.itdachen.framework.boot.autoconfigure.wechat;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.wechat.properties.WeChatAppletProperties;

/**
 * WeChatHelperProperties
 *
 * @author 剑鸣秋朔
 * @date 2024-11-21 11:22
 */
public class WeChatHelperProperties {

    public WeChatAppletProperties applet() {
        return AppContextHelper.getBean(WeChatAppletProperties.class);
    }


}
