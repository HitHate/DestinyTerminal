package com.example.destinyterminal.Login;


import android.content.Context;
import android.webkit.HttpAuthHandler;


import com.example.destinyterminal.Utils.Connection;
import com.example.destinyterminal.Utils.ConnectionManager;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smackx.iqregister.AccountManager;

import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

import java.io.IOException;


public class Login {

    AbstractXMPPConnection connection;
    private final String username;
    private final String passwd;

    public Login(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    public void login() {
        try {
            Connection conn = new Connection();
            Thread thread = new Thread(conn);
            thread.start();
            thread.join();
            connection = conn.getConnection();
            connection.login(username, passwd);
//            AccountManager accountManager = AccountManager.getInstance(connection);
//            accountManager.sensitiveOperationOverInsecureConnection(true);
//            VCardManager vCardManager = VCardManager.getInstanceFor(connection);
//            VCard card = vCardManager.loadVCard();
//
//            vCardManager.saveVCard(card);
//            System.out.println(card.getNickName());
//            ConnectionManager cm = new ConnectionManager();
//            cm.set(connection);

        } catch (XMPPException | SmackException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean isLogin() {

        return connection.isAuthenticated();
    }

    public AbstractXMPPConnection getConnection() {
        return connection;
    }
}
