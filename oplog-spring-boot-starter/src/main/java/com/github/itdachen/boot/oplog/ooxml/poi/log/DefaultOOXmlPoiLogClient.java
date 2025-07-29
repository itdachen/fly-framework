package com.github.itdachen.boot.oplog.ooxml.poi.log;

import com.github.itdachen.boot.oplog.ooxml.poi.entity.OplogPoiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultOOXmlPoiLogClient
 *
 * @author 王大宸
 * @date 2025-07-29 17:53
 */
public class DefaultOOXmlPoiLogClient implements IOOXmlPoiLogClient {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOOXmlPoiLogClient.class);

    @Override
    public void saveOOXmlPoiExpLog(OplogPoiModel oplogPoiModel) {
        logger.info("导出日志入库接口未实现, 请实现 IOOXmlPoiLogClient 接口: {}", oplogPoiModel.toString());
    }

    @Override
    public void saveOOXmlPoiImpLog(OplogPoiModel oplogPoiModel) {
        logger.info("导入日志入库接口未实现, 请实现 IOOXmlPoiLogClient 接口: {}", oplogPoiModel.toString());
    }

}
