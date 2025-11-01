package com.github.itdachen.framework.boot.oplog.ooxml.poi.log;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.OplogPoiModel;

/**
 * 导入导出日志入库接口
 *
 * @author 王大宸
 * @date 2025-07-29 17:51
 */
public interface IOOXmlPoiLogClient {

    /***
     * 导出日志入库接口
     *
     * @author 王大宸
     * @date 2025/7/29 17:52
     * @param oplogPoiModel oplogPoiModel
     * @return void
     */
    void saveOOXmlPoiExpLog(OplogPoiModel oplogPoiModel);

    /***
     * 导入日志入库接口
     *
     * @author 王大宸
     * @date 2025/7/29 17:52
     * @param oplogPoiModel oplogPoiModel
     * @return void
     */
    void saveOOXmlPoiImpLog(OplogPoiModel oplogPoiModel);


}
