package com.example.destinyterminal.Register;


import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.destinyterminal.R;

public class Register_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);

        boolean[] sign = {false,false,false,false};

        final String[] pw = {null};

        String check_up = ".*[A-Z]+.*";
        String check_low = ".*[a-z]+.*";
        String check_symbols = ".*[\\p{P}\\p{S}\\s+]+.*";

        TextView length = findViewById(R.id.length);

        TextView low_and_up = findViewById(R.id.low_and_up);

        TextView symbols = findViewById(R.id.symbols);

        EditText passwd = findViewById(R.id.password);

        ImageButton show_or_close = findViewById(R.id.show_or_close);

        Button next = findViewById(R.id.next);

        next.setEnabled(false);

        passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().matches(check_up) && charSequence.toString().matches(check_low)){
                    low_and_up.setText(R.string.low_and_up_true);
                    low_and_up.setTextColor(getResources().getColor(R.color.green));
                    sign[1] = true;
                }else{
                    low_and_up.setText(R.string.low_and_up);
                    low_and_up.setTextColor(getResources().getColor(R.color.red));
                    sign[1] = false;
                }
                if(charSequence.toString().matches(check_symbols)){
                    symbols.setText(R.string.symbols_true);
                    symbols.setTextColor(getResources().getColor(R.color.green));
                    sign[2] = true;
                }else{
                    symbols.setText(R.string.symbols);
                    symbols.setTextColor(getResources().getColor(R.color.red));
                    sign[2] = false;
                }
                if(charSequence.toString().length() >= 8 && charSequence.toString().length() <= 16){
                    length.setText(R.string.length_true);
                    length.setTextColor(getResources().getColor(R.color.green));
                    sign[3] = true;
                }else{
                    length.setText(R.string.length);
                    length.setTextColor(getResources().getColor(R.color.red));
                    sign[3] = false;
                }

                next.setEnabled(sign[1] && sign[2] && sign[3]);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                pw[0] = editable.toString();
            }
        });

        show_or_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sign[0]){
                    show_or_close.setImageResource(R.drawable.show);
                    passwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwd.setSelection(passwd.getText().toString().length());
                    sign[0] = true;
                }else {
                    show_or_close.setImageResource(R.drawable.close);
                    passwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwd.setSelection(passwd.getText().toString().length());
                    sign[0] = false;
                }
            }
        });

    }
}