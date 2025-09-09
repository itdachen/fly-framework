package com.github.itdachen.boot.oplog.manager.service;

import com.github.itdachen.boot.oplog.entity.PageViewLog;

/**
 * 页面访问日志入口接口
 *
 * @author 王大宸
 * @date 2025/9/9 22:09
 */
public interface IPageViewClientHandler {


    void save(PageViewLog pageViewLog);


}
