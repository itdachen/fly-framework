package com.github.itdachen.framework.boot.oplog;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.fileupload.DefaultOOXmlPoiFileUploadHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.fileupload.IOOXmlPoiFileUploadHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.log.DefaultOOXmlPoiLogClient;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.log.IOOXmlPoiLogClient;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.msg.DefaultOplogMsgClient;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.msg.IOplogMsgClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Poi 导入导出自动化配置
 *
 * @author 王大宸
 * @date 2025-07-29 17:48
 */
@Configuration
public class OplogOOXmlPoiAutoConfiguration {

    /***
     * 导入导出/文件上传
     *
     * @author 王大宸
     * @date 2025/7/29 17:50
     * @return com.github.itdachen.boot.oplog.ooxml.poi.fileupload.IOOXmlPoiFileUploadHandler
     */
    @Bean
    @ConditionalOnMissingBean(IOOXmlPoiFileUploadHandler.class)
    public IOOXmlPoiFileUploadHandler defaultOOXmlPoiFileUploadHandler() {
        return new DefaultOOXmlPoiFileUploadHandler();
    }

    /***
     * 导入导出日志接口
     *
     * @author 王大宸
     * @date 2025/7/29 17:55
     * @return com.github.itdachen.boot.oplog.ooxml.poi.log.IOOXmlPoiLogClient
     */
    @Bean
    @ConditionalOnMissingBean(IOOXmlPoiLogClient.class)
    public IOOXmlPoiLogClient defaultOOXmlPoiLogClient() {
        return new DefaultOOXmlPoiLogClient();
    }

    /***
     * 添加消息接口
     *
     * @author 王大宸
     * @date 2025/7/29 18:01
     * @return com.github.itdachen.boot.oplog.ooxml.poi.msg.IOplogMsgClient
     */
    @Bean
    @ConditionalOnMissingBean(IOplogMsgClient.class)
    public IOplogMsgClient defaultOplogMsgClient() {
        return new DefaultOplogMsgClient();
    }


}
