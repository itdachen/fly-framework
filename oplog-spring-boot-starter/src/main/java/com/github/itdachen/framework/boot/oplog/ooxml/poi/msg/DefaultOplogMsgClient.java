package com.github.itdachen.framework.boot.oplog.ooxml.poi.msg;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.MsgFileModel;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.MsgModel;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultOplogMsgClient
 *
 * @author 剑鸣秋朔
 * @date 2025-07-29 17:58
 */
public class DefaultOplogMsgClient implements IOplogMsgClient {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOplogMsgClient.class);

    @Override
    public void save(MsgModel msgModel, UserInfoDetails userDetails) {
        logger.info("消息入库接口未实现, 请实现 IOplogMsgClient 接口: {}", msgModel.toString());
    }

    @Override
    public void appendContent(MsgModel msgModel) {
        logger.info("追加消息接口未实现, 请实现 IOplogMsgClient 接口: {}", msgModel.toString());
    }

    @Override
    public void saveMsgFile(MsgFileModel msgFileModel) {
        logger.info("添加消息文件接口未实现, 请实现 IOplogMsgClient 接口: {}", msgFileModel.toString());
    }

}
