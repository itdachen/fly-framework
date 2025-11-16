package com.github.itdachen.framework.boot.oplog.manager.service.impl;

import com.github.itdachen.framework.boot.oplog.entity.LogInfo;
import com.github.itdachen.framework.boot.oplog.manager.service.IOplogClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 默认操作日志展示方式,打印到控制台
 * Created by 剑鸣秋朔 on 2021-12-01 16:44
 * Created with IntelliJ IDEA.
 */
public class DefaultOplogClientHandlerImpl implements IOplogClientHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOplogClientHandlerImpl.class);

    @Override
    public void save( LogInfo oplogInfo) {
        logger.info("操作日志: {}", oplogInfo.toString());
    }

}
