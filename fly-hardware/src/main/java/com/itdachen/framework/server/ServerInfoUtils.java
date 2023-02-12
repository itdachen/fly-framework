package com.itdachen.framework.server;

import com.itdachen.framework.server.hardware.ServerInfo;

/**
 * Description: 获取服务器信息
 * Created by 王大宸 on 2023/02/13 0:18
 * Created with IntelliJ IDEA.
 */
public class ServerInfoUtils {

    public static ServerInfo getServerInfo() {
        ServerInfo server = new ServerInfo();
        server.copyTo();
        return server;
    }

}
