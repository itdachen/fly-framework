package com.github.itdachen.boot.oplog.ooxml.poi.msg;

import com.github.itdachen.boot.oplog.ooxml.poi.entity.MsgFileModel;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.MsgModel;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;

/**
 * 消息入库操作
 *
 * @author 王大宸
 * @date 2025-07-22 11:13
 */
public interface IOplogMsgClient {


    /***
     * 新增消息信息
     *
     * @author 王大宸
     * @date 2025/7/22 11:14
     * @param msgModel notifyMsgInfo
     * @return void
     */
    void save(MsgModel msgModel, UserInfoDetails userDetails);


    /***
     * 追加消息信息
     *
     * @author 王大宸
     * @date 2025/7/22 11:16
     * @param msgModel notifyMsgInfo
     * @return void
     */
    void appendContent(MsgModel msgModel);


    /***
     * 添加消息文件
     *
     * @author 王大宸
     * @date 2025/7/25 22:42
     * @param msgFileModel msgFileModel
     * @return void
     */
    void saveMsgFile(MsgFileModel msgFileModel);


}
