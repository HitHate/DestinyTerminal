package com.example.destinyterminal.Utils;

import android.content.Context;

import org.jivesoftware.smack.AbstractXMPPConnection;

public class ManagerPreferences {
    private String name;
    private Context context;
    private AbstractXMPPConnection connection;

    public ManagerPreferences(String name, Context context, AbstractXMPPConnection connection) {
        this.name = name;
        this.context = context;
        this.connection = connection;
    }

}
