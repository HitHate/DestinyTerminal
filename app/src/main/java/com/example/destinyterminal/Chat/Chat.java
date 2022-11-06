package com.example.destinyterminal.Chat;

import com.example.destinyterminal.Login.Login;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;
import org.jivesoftware.smack.packet.MessageBuilder;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.EntityJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

class ChatWith {
    private Login login;
    private AbstractXMPPConnection connection;

    public ChatWith(Login login, AbstractXMPPConnection connection) {
        this.login = login;
        this.connection = connection;
    }

    public boolean SendMessage(String user, String message ){
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        try {
            EntityBareJid jid = JidCreate.entityBareFrom(user);
            Chat chat = chatManager.chatWith(jid);
            chatManager.addOutgoingListener(new OutgoingChatMessageListener() {
                @Override
                public void newOutgoingMessage(EntityBareJid to, MessageBuilder messageBuilder, Chat chat) {
                    System.out.println("发送成功");
                }
            });
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
        return false;
    }
}
