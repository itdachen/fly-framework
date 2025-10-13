package com.github.itdachen.boot.oplog.ooxml.poi.exp.handler;

import com.github.itdachen.boot.oplog.ooxml.poi.exp.ExpParamsSettings;
import org.springframework.util.StopWatch;

/**
 * 开始 结束
 *
 * @author 王大宸
 * @date 2025/10/12 13:13
 */
public interface IWorkBookExpStartToFinishHandler<Q> {

    /***
     * 开始
     *
     * @author 王大宸
     * @date 2025/10/12 13:35
     * @param msgId msgId
     * @param stopWatch stopWatch
     * @param settings settings
     * @return void
     */
    void start(String msgId, StopWatch stopWatch, ExpParamsSettings<Q> settings);

    /***
     * 完成
     *
     * @author 王大宸
     * @date 2025/10/12 13:35
     * @param msgId msgId
     * @param stopWatch stopWatch
     * @param settings settings
     * @return void
     */
    void finish(String msgId, StopWatch stopWatch, ExpParamsSettings<Q> settings);


    void complete(String msgId, ExpParamsSettings<Q> settings);


}
