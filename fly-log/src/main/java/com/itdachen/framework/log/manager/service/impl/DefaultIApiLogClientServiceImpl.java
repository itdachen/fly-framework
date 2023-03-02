package com.itdachen.framework.log.manager.service.impl;

import com.itdachen.framework.log.entity.ApiLogClient;
import com.itdachen.framework.log.manager.service.IApiLogClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 默认操作日志展示方式,打印到控制台
 * Created by 王大宸 on 2021-12-01 16:44
 * Created with IntelliJ IDEA.
 */
public class DefaultIApiLogClientServiceImpl implements IApiLogClientService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultIApiLogClientServiceImpl.class);

    @Override
    public void save(ApiLogClient apiLog){
        logger.info("操作日志: ");
        logger.info(apiLog.toString());
    }
}
