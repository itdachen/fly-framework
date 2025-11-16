package com.github.itdachen.framework.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建工具类时要将工具类设置成单例的，
 * 这样不仅可以保证线程安全，也可以保证在系统全局只能创建一个对象，避免频繁创建对象的成本
 *
 * @author 剑鸣秋朔
 * @date 2025-05-06 14:28
 */
public enum ObjectMapperInstance {


    INSTANCE;

    private final ObjectMapper objectMapper = new ObjectMapper();

    ObjectMapperInstance() {

    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    /****** 个性化单例配置 **********************************************************************************/

    //    INSTANCE;
//
//    private final ObjectMapper objectMapper;
//
//    ObjectMapperInstance() {
//        objectMapper = new ObjectMapper();
//        // 注册自定义模块
//        initialize();
//    }
//
//    private void initialize() {
//        CustomJsonModule customJsonModule = new CustomJsonModule();
//        objectMapper.registerModule(customJsonModule);
//    }
//
//
//    public ObjectMapper getObjectMapper() {
//        return objectMapper;
//    }


}
