package com.example.destinyterminal.Chat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


import com.example.destinyterminal.R;



public class Chat_Manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_manager);
        onCreateActionBar();

    }

    public void onCreateActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.top_bar);
        toolbar.setTitle("Dwada");
        toolbar.setSubtitle("dwadawdawd");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

}
