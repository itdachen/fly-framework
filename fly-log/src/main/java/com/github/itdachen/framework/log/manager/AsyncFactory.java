package com.github.itdachen.framework.log.manager;

import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.entity.ApiLogClient;
import com.github.itdachen.framework.log.manager.service.IApiLogClientService;
import com.github.itdachen.framework.spring.SpringBeanUtils;
import com.github.itdachen.framework.tools.ip.AddressUtils;

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
                apiLog.setMakeUseAddress(AddressUtils.getRealAddressByIP(apiLog.getMakeUseIp()));
                SpringBeanUtils.getBean(IApiLogClientService.class).save(apiLog);
            }
        };
    }

}
