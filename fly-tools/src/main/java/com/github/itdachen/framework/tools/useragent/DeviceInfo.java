package com.github.itdachen.framework.tools.useragent;

/**
 * Description: 设备信息对象
 * Created by 王大宸 on 2023-08-17 21:55
 * Created with IntelliJ IDEA.
 */
public class DeviceInfo {

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 系统
     */
    private String os;

    /**
     * 系统版本
     */
    private String osVersion;


    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 厂商
     */
    private String deviceManufacturer;


    /**
     * 终端用户唯一标识
     */
    private String uuid;


    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "browserName='" + browserName + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceManufacturer='" + deviceManufacturer + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}
