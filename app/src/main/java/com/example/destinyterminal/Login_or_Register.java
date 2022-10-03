package com.example.destinyterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Login_or_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        Button button = findViewById(R.id.register);
        button.setOnClickListener(view -> startActivity(new Intent().setClass(Login_or_Register.this,Register_email.class)));
    }
}