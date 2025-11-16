package com.github.itdachen.framework.boot.security.session;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session 过期监听
 *
 * @author 剑鸣秋朔
 * @date 2024-08-13 14:41
 */
public class HttpSessionExpiredListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(HttpSessionExpiredListener.class);

//    /***
//    * Session创建时的逻辑
//    *
//    * @author 剑鸣秋朔
//    * @date 2024/8/26 16:20
//     * @param se se
//    * @return void
//    */
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionCreated(se);
//    }

    /***
     * Session 销毁监听
     *
     * @author 剑鸣秋朔
     * @date 2024/8/13 14:43
     * @param se se
     * @return void
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        // 其他逻辑处理, 例如删除数据库在线用户信息
    }
}
