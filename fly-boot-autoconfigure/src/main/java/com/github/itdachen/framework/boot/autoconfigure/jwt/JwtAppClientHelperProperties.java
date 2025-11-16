package com.github.itdachen.framework.boot.autoconfigure.jwt;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;

/**
 * jwt 配置
 *
 * @author 剑鸣秋朔
 * @date 2024-06-19 16:20
 */
public class JwtAppClientHelperProperties {


    public JwtAutoconfigureProperties jwt() {
        return AppContextHelper.getBean(JwtAutoconfigureProperties.class);
    }



}
