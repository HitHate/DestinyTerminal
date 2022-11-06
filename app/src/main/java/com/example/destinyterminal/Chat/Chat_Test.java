package com.example.destinyterminal.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.destinyterminal.Login.Login;
import com.example.destinyterminal.R;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.MessageBuilder;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

public class Chat_Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_test);


        EditText who = findViewById(R.id.who);
        EditText message = findViewById(R.id.message);
        Button send = findViewById(R.id.send);
        Button you = findViewById(R.id.you);
        Intent intent = getIntent();

        final String[] jid = new String[1];
        final EntityBareJid[] JID = new EntityBareJid[1];
        final String[] message_send = new String[1];
        final String[] username = new String[1];
        final String[] passwd = new String[1];

        username[0] = intent.getStringExtra("username");
        passwd[0] = intent.getStringExtra("passwd");

        Login login1 = new Login(username[0], passwd[0]);
        login1.login();
        ChatManager chatManager = ChatManager.getInstanceFor(login1.getConnection());
        final Chat[] chat = new Chat[1];

        LinearLayout mainLinerLayout = (LinearLayout) this.findViewById(R.id.show);
        NestedScrollView nestedScrollView = this.findViewById(R.id.nestedScrollView2);

        who.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                jid[0] = s.toString()+"@124.221.210.85";
            }
        });

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                message_send[0] = s.toString();
            }
        });

        chatManager.addIncomingListener(new IncomingChatMessageListener() {
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void run() {
                        System.out.println(message.getBody());
                        LinearLayout.LayoutParams params_name = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params_name.setMargins(20,30,100,10);
                        TextView name = new TextView(Chat_Test.this);
                        name.setText(from.toString().replace("@124.221.210.85",""));
                        name.setTextSize(20);
                        name.setLayoutParams(params_name);
                        name.setTextColor(getResources().getColor(R.color.white));


                        TextView text= new TextView(Chat_Test.this);
                        text.setText(message.getBody());

                        text.setBackground(getDrawable(R.drawable.outgoing));
                        LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params_text.setMargins(20,5,100,20);
                        text.setLayoutParams(params_text);
                        text.setTextSize(25);
                        text.setPadding(15,15,15,15);
                        text.setTextColor(getResources().getColor(R.color.black));
                        mainLinerLayout.addView(name);
                        mainLinerLayout.addView(text);
                        nestedScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                nestedScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    }
                });

                System.out.println(message.getBody());

            }
        });


        chatManager.addOutgoingListener(new OutgoingChatMessageListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void newOutgoingMessage(EntityBareJid to, MessageBuilder messageBuilder, Chat chat) {
                LinearLayout.LayoutParams params_name = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params_name.setMargins(100,50,20,10);
                TextView name = new TextView(Chat_Test.this);
                name.setText(username[0]);
                params_name.gravity = Gravity.END;
                name.setTextSize(20);
                name.setLayoutParams(params_name);
                name.setTextColor(getResources().getColor(R.color.white));


                TextView text= new TextView(Chat_Test.this);
                text.setText(message_send[0]);
                text.setBackground(getDrawable(R.drawable.incoming));
                LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params_text.setMargins(100,5,20,0);
                params_text.gravity = Gravity.END;
                text.setLayoutParams(params_text);
                text.setTextSize(25);

                text.setPadding(15,15,15,15);
                text.setTextColor(getResources().getColor(R.color.white));
                mainLinerLayout.addView(name);
                mainLinerLayout.addView(text);
                nestedScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        nestedScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     JID[0] = JidCreate.entityBareFrom(jid[0]);
                     chat[0] = chatManager.chatWith(JID[0]);
                    Toast.makeText(Chat_Test.this, "已选中"+jid[0].replace("@124.221.210.85",""), Toast.LENGTH_SHORT).show();
                } catch (XmppStringprepException e) {
                    e.printStackTrace();
                }

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                try {
                    chat[0].send(message_send[0]);
                    message.setText("");
                } catch (SmackException.NotConnectedException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}