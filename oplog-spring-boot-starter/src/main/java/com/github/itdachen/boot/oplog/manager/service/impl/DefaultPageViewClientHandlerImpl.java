package com.github.itdachen.boot.oplog.manager.service.impl;

import com.github.itdachen.boot.oplog.entity.PageViewLog;
import com.github.itdachen.boot.oplog.manager.service.IPageViewClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultPageViewClientHandlerImpl
 *
 * @author 王大宸
 * @date 2025/9/9 22:22
 */
public class DefaultPageViewClientHandlerImpl implements IPageViewClientHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPageViewClientHandlerImpl.class);

    @Override
    public void save(PageViewLog pageViewLog) {
        logger.info("页面访问日志: {}", pageViewLog.toString());
    }
}
