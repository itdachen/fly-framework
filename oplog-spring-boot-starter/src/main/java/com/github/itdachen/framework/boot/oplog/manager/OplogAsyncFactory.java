package com.github.itdachen.framework.boot.oplog.manager;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.oplog.entity.LogInfo;
import com.github.itdachen.framework.boot.oplog.manager.service.IOplogClientHandler;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.tools.ip.AddressUtils;

import java.util.TimerTask;

/**
 * Description:
 * Created by 王大宸 on 2021-12-01 17:20
 * Created with IntelliJ IDEA.
 */
public class OplogAsyncFactory {

    /**
     * 操作日志记录
     *
     * @param apiLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOplog(final LogInfo apiLog) throws BizException {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                apiLog.setHostAddress(AddressUtils.getRealAddressByIP(apiLog.getHostIp()));
                AppContextHelper.getBean(IOplogClientHandler.class).save(apiLog);
            }
        };
    }

}
