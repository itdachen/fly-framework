package com.github.itdachen.framework.weixin.wxpay.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023/04/11 22:38
 * Created with IntelliJ IDEA.
 */
public class WeChatTransferBatchesParam {

    /**
     * 直连商户的 appid
     */
    private String appId;


    /**
     * 商户系统内部的商家批次单号，对应 out_batch_no，
     */
    private String outBatchNo;

    /**
     * 该笔批量转账的名称，对应 batch_name，示例值：2019年1月深圳分部报销单
     */
    private String batchName;

    /**
     * 批次备注
     */
    private String batchRemark;

    /**
     * 转账总金额
     */
    private BigDecimal totalAmount;

    /**
     * 转账明细列表
     */
    private List<transferDetail> transferDetailList;

    public static class transferDetail {

        /**
         * 商户系统内部的商家批次单号，对应 out_detail_no，
         */
        private String outDetailNo;

        /**
         * 转账金额
         */
        private BigDecimal transferAmount;

        /**
         * 转账备注
         */
        private String transferRemark;

        /**
         * openid是微信用户在公众号（小程序）appid下的唯一用户标识
         */
        private String openid;

        public String getOutDetailNo() {
            return outDetailNo;
        }

        public void setOutDetailNo(String outDetailNo) {
            this.outDetailNo = outDetailNo;
        }

        public BigDecimal getTransferAmount() {
            return transferAmount;
        }

        public void setTransferAmount(BigDecimal transferAmount) {
            this.transferAmount = transferAmount;
        }

        public String getTransferRemark() {
            return transferRemark;
        }

        public void setTransferRemark(String transferRemark) {
            this.transferRemark = transferRemark;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getOutBatchNo() {
        return outBatchNo;
    }

    public void setOutBatchNo(String outBatchNo) {
        this.outBatchNo = outBatchNo;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchRemark() {
        return batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
        this.batchRemark = batchRemark;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<transferDetail> getTransferDetailList() {
        return transferDetailList;
    }

    public void setTransferDetailList(List<transferDetail> transferDetailList) {
        this.transferDetailList = transferDetailList;
    }


}
