package com.example.destinyterminal.Utils;



import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;


import java.io.Serializable;
import java.net.InetAddress;


public class Connection implements Runnable {

    AbstractXMPPConnection connection;
    private static final String C_HOST = "YOU_IP";//可访问到openfire服务的IP地址
    private static final int C_PORT = 5222;//端口，默认5222
    private static final String C_DOMAIN = "YOU_DOMAIN";

    @Override
    public void run() {
        try {
            System.out.println("正在连接Chat服务。。。");
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHostAddress(InetAddress.getByName(C_HOST))//IP
                    .setPort(C_PORT)//端口
                    .setXmppDomain(C_DOMAIN)//服务器名称
//                    .setSendPresence(false)//以离线方式登录,以便获取离线消息
                    .setCompressionEnabled(true)//压缩信息传输，以节省流量
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)//取消验证，如有证书，则可删去
                    .build();
            connection = new XMPPTCPConnection(config);
            connection.connect();
            System.out.println("Chat服务连接成功");
        } catch (Exception e) {
            System.out.println("Chat服务连接失败" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public AbstractXMPPConnection getConnection(){
        return connection;
    }

    public boolean getIsConnected(){
        return connection.isConnected();
    }

}
