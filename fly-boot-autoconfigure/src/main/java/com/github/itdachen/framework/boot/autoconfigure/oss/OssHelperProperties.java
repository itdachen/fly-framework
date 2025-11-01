package com.github.itdachen.framework.boot.autoconfigure.oss;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssProperties;

/**
 * OssHelperProperties
 *
 * @author 王大宸
 * @date 2024-06-19 16:40
 */
public class OssHelperProperties {

    //         OssProperties.class,
    //        OssLocalAutoconfigureProperties.class,
    //        OssAliYunAutoconfigureProperties.class,


    public OssProperties properties() {
        return AppContextHelper.getBean(OssProperties.class);
    }


    public OssLocalAutoconfigureProperties local() {
        return AppContextHelper.getBean(OssLocalAutoconfigureProperties.class);
    }

    public OssAliYunAutoconfigureProperties ali() {
        return AppContextHelper.getBean(OssAliYunAutoconfigureProperties.class);
    }



}
