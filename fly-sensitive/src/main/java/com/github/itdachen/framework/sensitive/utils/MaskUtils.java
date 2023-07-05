package com.github.itdachen.framework.sensitive.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Description: 数据脱敏
 * Created by 王大宸 on 2023-07-04 17:02
 * Created with IntelliJ IDEA.
 */
public class MaskUtils {

    /**
     * 手机号显示首3末4位，中间用*号隐藏代替，如：188****5593
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() <= 8) {
            return mobile;
        }
        return wordMask(mobile, 3, 4, "*");
    }

    /**
     * 电话号码显示区号及末4位，中间用*号隐藏代替，如：055****6666
     *
     * @param telephone
     * @return
     */
    public static String maskTelephone(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return telephone;
        }
        String result;
        if (telephone.length() > 8) {
            if (telephone.contains("-")) {
                String[] temp = telephone.split("-");
                result = temp[0] + "****" + temp[1].substring(temp[1].length() - 4, temp[1].length());
            } else {
                result = telephone.substring(0, 3) + "****" + telephone.substring(telephone.length() - 4, telephone.length());
            }
        } else {
            result = "****" + telephone.substring(telephone.length() - 4, telephone.length());
        }
        return result;
    }

    /**
     * 身份证号显示首6末4位，中间用4个*号隐藏代替，如：340121****3754
     *
     * @param idCard
     * @return
     */
    public static String maskIDCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return idCard;
        }

        return wordMask(idCard, 3, 4, "*");
    }

    /**
     * 银行卡显示首6末4位，中间用4个*号隐藏代替，如：622202****4123
     *
     * @param cardNo
     * @return
     */
    public static String maskBankCard(String cardNo) {
        if (StringUtils.isBlank(cardNo) || cardNo.length() < 10) {
            return cardNo;
        }
        return wordMask(cardNo, 6, 4, "*");
    }

    /**
     * 邮箱像是前两位及最后一位字符，及@后邮箱域名信息，如：ch****y@163.com
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return email;
        }
        String[] temp = email.split("@");

        return wordMask(temp[0], 2, 1, "*") + "@" + temp[1];
    }

    /**
     * 汉字掩码
     * 0-1字，如：用（用）
     * 2字，如：用于（*于）
     * 3-4字，如：用于掩（用*掩）、用于掩码（用**码）
     * 5-6字，如：用于掩码测（用于*码测）、用于掩码测试（用于**测试）
     * 大于6字，如：用于掩码测试的字符串（用于掩****字符串）
     *
     * @param name
     * @return
     */
    public static String maskName(String name) {
        int lenth = StringUtils.length(name);
        return switch (lenth) {
            case 0, 1 -> name;
            case 2 -> "*" + name.charAt(1);
            case 3, 4 -> wordMask(name, 1, 1, "*");
            case 5, 6 -> wordMask(name, 2, 2, "*");
            default -> wordMask(name, 3, 3, "*");
        };
    }

    /**
     * 全隐藏，如： ***
     *
     * @param str
     * @return
     */
    public static String maskAll(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return "******";
    }

    /***
     * 对字符串进行脱敏处理
     *
     * @author 王大宸
     * @date 2023/7/4 17:12
     * @param word        被脱敏的字符
     * @param startLength 被保留的开始长度 前余n位
     * @param endLength   被保留的结束长度 后余n位
     * @param pad         填充字符
     * @return java.lang.String
     */
    public static String wordMask(String word, int startLength, int endLength, String pad) {
        if (startLength + endLength > word.length()) {
            return StringUtils.leftPad("", word.length() - 1, pad);
        }
        String startStr = word.substring(0, startLength);
        String endStr = word.substring(word.length() - endLength, word.length());
        return startStr + StringUtils.leftPad("", word.length() - startLength - endLength, pad) + endStr;
    }

    private static final int SIZE = 6;
    private static final String SYMBOL = "*";


    /***
     * 通用脱敏方法
     *
     * @author 王大宸
     * @date 2023/7/4 17:07
     * @param value value
     * @return java.lang.String
     */
    public static String toConceal(String value) {
        if (null == value || "".equals(value)) {
            return value;
        }
        int len = value.length();
        int pamaone = len / 2;
        int pamatwo = pamaone - 1;
        int pamathree = len % 2;
        StringBuilder stringBuilder = new StringBuilder();
        if (len <= 2) {
            if (pamathree == 1) {
                return SYMBOL;
            }
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
        } else {
            if (pamatwo <= 0) {
                stringBuilder.append(value.substring(0, 1));
                stringBuilder.append(SYMBOL);
                stringBuilder.append(value.substring(len - 1, len));

            } else if (pamatwo >= SIZE / 2 && SIZE + 1 != len) {
                int pamafive = (len - SIZE) / 2;
                stringBuilder.append(value.substring(0, pamafive));
                for (int i = 0; i < SIZE; i++) {
                    stringBuilder.append(SYMBOL);
                }
                if ((pamathree == 0 && SIZE / 2 == 0) || (pamathree != 0 && SIZE % 2 != 0)) {
                    stringBuilder.append(value.substring(len - pamafive, len));
                } else {
                    stringBuilder.append(value.substring(len - (pamafive + 1), len));
                }
            } else {
                int pamafour = len - 2;
                stringBuilder.append(value.substring(0, 1));
                for (int i = 0; i < pamafour; i++) {
                    stringBuilder.append(SYMBOL);
                }
                stringBuilder.append(value.substring(len - 1, len));
            }
        }
        return stringBuilder.toString();

    }

}
