package com.github.itdachen.framework.oplog.manager.service;

import com.github.itdachen.framework.oplog.entity.OplogClient;

/**
 * Description: 日志信息记录
 * Created by 王大宸 on 2021-12-01 16:42
 * Created with IntelliJ IDEA.
 */
public interface IOplogClientService {

    void save(OplogClient oplogClient);

}
