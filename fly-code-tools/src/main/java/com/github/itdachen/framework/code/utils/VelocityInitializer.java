package com.github.itdachen.framework.code.utils;

import com.github.itdachen.framework.context.constants.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * Description: VelocityEngine工厂
 * Created by 王大宸 on 2022-07-27 23:43
 * Created with IntelliJ IDEA.
 */
public class VelocityInitializer {

    /**
     * 初始化 vm 方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
