package com.github.itdachen.boot.oplog.manager.service.impl;

import com.github.itdachen.boot.oplog.entity.OplogClient;
import com.github.itdachen.boot.oplog.manager.service.IOplogClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 默认操作日志展示方式,打印到控制台
 * Created by 王大宸 on 2021-12-01 16:44
 * Created with IntelliJ IDEA.
 */
public class DefaultOplogClientServiceImpl implements IOplogClientService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOplogClientServiceImpl.class);

    @Override
    public void save(OplogClient apiLog) {
        logger.info("操作日志: {}", apiLog.toString());
    }

}
