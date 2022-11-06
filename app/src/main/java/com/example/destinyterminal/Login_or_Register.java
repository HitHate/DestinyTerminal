package com.example.destinyterminal;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;


import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.destinyterminal.Chat.Chat_Manager;

import com.example.destinyterminal.Chat.Chat_Test;
import com.example.destinyterminal.Login.Login;
import com.example.destinyterminal.Register.Register_email;

import com.example.destinyterminal.Utils.ConnectionManager;


import org.jivesoftware.smack.AbstractXMPPConnection;

import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.android.AndroidSmackInitializer;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StanzaFactory;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;
import org.jivesoftware.smackx.offline.OfflineMessageHeader;
import org.jivesoftware.smackx.offline.OfflineMessageManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.List;


public class Login_or_Register extends AppCompatActivity {

    private final String[] username = {null};
    private final String[] passwd = {null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread initialize = new Thread() {
                @Override
                public void run() {
                    AndroidSmackInitializer.initialize(Login_or_Register.this);
                }
            };
            initialize.start();
            initialize.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_or_register);
        EditText username_layout = findViewById(R.id.username);
        EditText passwd_layout = findViewById(R.id.passwd);
        Button register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);

        username_layout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username[0] = s.toString();
            }
        });

        passwd_layout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passwd[0] = s.toString();
            }
        });

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_or_Register.this, Chat_Test.class);
                intent.putExtra("username",username[0]);
                intent.putExtra("passwd",passwd[0]);
                startActivity(intent);
            }
        });

    }

    OnClickListener onClickListener = view -> {
        int id = view.getId();
        if (id == R.id.register) {
            try {
                Intent intent = new Intent(Login_or_Register.this, Register_email.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (id == R.id.login) {

        }
    };
}



