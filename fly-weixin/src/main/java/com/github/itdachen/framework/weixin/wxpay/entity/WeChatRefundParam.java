package com.github.itdachen.framework.weixin.wxpay.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description: 退款申请
 * 微信支付订单号，微信支付订单号和商家订单号二选一，这个是必不可少的，原订单金额也是必填的，微信会做二次验证。
 * Created by 剑鸣秋朔 on 2023/04/11 22:31
 * Created with IntelliJ IDEA.
 */
public class WeChatRefundParam {


    /**
     * 微信支付订单号，微信支付订单号和商家订单号二选一
     */
    private String transactionId;

    /**
     * 商家订单号，对应 out_trade_no，
     */
    private String outTradeNo;

    /**
     * 商户退款单号，对应 out_refund_no
     */
    private String outRefundNo;

    /**
     * 退款原因，选填
     */
    private String reason;

    /**
     * 通知回调地址
     */
    private String notifyUrl;

    /**
     * 退款资金来源
     */
    private String fundsAccount;

    /**
     * 订单金额信息
     */
    private WeChatRefundAmount amount = new WeChatRefundAmount();

    /**
     * 指定商品退款需要传此参数，其他场景无需传递
     */
    public List<WeChatRefundGoodsDetail> goodsDetail;


    public static class WeChatRefundAmount {

        /**
         * 退款金额
         */
        private BigDecimal refund;

        /**
         * 原订单金额
         */
        private BigDecimal total;

        /**
         * 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
         */
        private String currency = "CNY";

        private List<WeChatRefundAmountForm> from;

        public static class WeChatRefundAmountForm {

            /**
             * 下面枚举值多选一。
             * 枚举值：
             * AVAILABLE : 可用余额
             * UNAVAILABLE : 不可用余额
             */
            private String account;

            /**
             * 对应账户出资金额
             */
            private BigDecimal amount;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public BigDecimal getAmount() {
                return amount;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }
        }

        public BigDecimal getRefund() {
            return refund;
        }

        public void setRefund(BigDecimal refund) {
            this.refund = refund;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public List<WeChatRefundAmountForm> getFrom() {
            return from;
        }

        public void setFrom(List<WeChatRefundAmountForm> from) {
            this.from = from;
        }
    }


    public static class WeChatRefundGoodsDetail {

        /**
         * 商户侧商品编码
         */
        private String merchantGoodsId;

        /**
         * 微信支付商品编码
         */
        private String wechatpayGoodsId;

        /**
         * 商品名称
         */
        private String goodsName;

        /**
         * 商品单价
         */
        private BigDecimal unitPrice;

        /**
         * 商品退款金额
         */
        private BigDecimal refundAmount;

        /**
         * 单品的退款数量
         */
        private Integer refundQuantity;

        public String getMerchantGoodsId() {
            return merchantGoodsId;
        }

        public void setMerchantGoodsId(String merchantGoodsId) {
            this.merchantGoodsId = merchantGoodsId;
        }

        public String getWechatpayGoodsId() {
            return wechatpayGoodsId;
        }

        public void setWechatpayGoodsId(String wechatpayGoodsId) {
            this.wechatpayGoodsId = wechatpayGoodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(BigDecimal refundAmount) {
            this.refundAmount = refundAmount;
        }

        public Integer getRefundQuantity() {
            return refundQuantity;
        }

        public void setRefundQuantity(Integer refundQuantity) {
            this.refundQuantity = refundQuantity;
        }
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public void setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
    }

    public WeChatRefundAmount getAmount() {
        return amount;
    }

    public void setAmount(WeChatRefundAmount amount) {
        this.amount = amount;
    }

    public List<WeChatRefundGoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<WeChatRefundGoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

}
