package com.github.itdachen.framework.boot.oplog.manager.service;

import com.github.itdachen.framework.boot.oplog.entity.LogInfo;

/**
 * Description: 日志信息记录
 * Created by 剑鸣秋朔 on 2021-12-01 16:42
 * Created with IntelliJ IDEA.
 */
public interface IOplogClientHandler {

    void save( LogInfo oplogInfo);

}
