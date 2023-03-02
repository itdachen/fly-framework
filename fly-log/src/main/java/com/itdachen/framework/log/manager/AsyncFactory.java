package com.itdachen.framework.log.manager;

import com.itdachen.framework.core.exception.BizException;
import com.itdachen.framework.log.entity.ApiLogClient;
import com.itdachen.framework.log.manager.service.IApiLogClientService;
import com.itdachen.framework.spring.SpringBeanUtils;
import com.itdachen.framework.tools.ip.AddressUtils;

import java.util.TimerTask;

/**
 * Description:
 * Created by 王大宸 on 2021-12-01 17:20
 * Created with IntelliJ IDEA.
 */
public class AsyncFactory {

    /**
     * 操作日志记录
     *
     * @param apiLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final ApiLogClient apiLog) throws BizException {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                apiLog.setRemoteAddress(AddressUtils.getRealAddressByIP(apiLog.getRemoteIp()));
                SpringBeanUtils.getBean(IApiLogClientService.class).save(apiLog);
            }
        };
    }

}
