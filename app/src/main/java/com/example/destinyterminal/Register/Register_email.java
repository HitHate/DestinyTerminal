package com.example.destinyterminal.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.destinyterminal.Verify.Check;
import com.example.destinyterminal.R;
import com.example.destinyterminal.Verify.Send_and_Save_Code;

import java.security.SecureRandom;


public class Register_email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        //用以接收用户输入的email地址
        final String[] email = {null};

        //用于控制提示信息的信号，sign[1]：检测email地址是否则正确；sign[2]：检测验证码是否为空
        final boolean[] sign = {false,false,false};

        //用以接收用户输入的验证码
        final String[] check_code = {null};

        SecureRandom secureRandom = new SecureRandom();

        //获取id为email的EditView组件
        EditText email_tv = findViewById(R.id.email);

        //获取id为check_code的EditView组件
        EditText check_code_tv = findViewById(R.id.check_code);

        //获取id为get_check_code的TextView组件
        TextView get_code = findViewById(R.id.get_check_code);

        //获取id为tip的TextView组件
        TextView tip = findViewById(R.id.tip);

        //获取id为next的Button组件
        Button next = findViewById(R.id.next);

        //设置next为不可按动
        next.setEnabled(false);

        //设置tip不可见
        tip.setVisibility(View.GONE);

        //设置用于检测email地址的正则表达式
        String check = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

        //创建spannableStringBuilder对象，用于实现文本功能
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        //为spannableStringBuilder设置文本
        spannableStringBuilder.append("获取验证码");

        //设置get_code文本大小
        get_code.setTextSize(18);

        //注册文本监听器，用于监听email的文本输入
        email_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().matches(check)) {
                    //将tip设为可见
                    tip.setVisibility(View.VISIBLE);
                    sign[1] = false;
                } else {
                    //将tip设为不可见
                    tip.setVisibility(View.GONE);
                    sign[1] = true;
                }

                //设置next的可否点击的状态
                next.setEnabled(sign[1] && sign[2]);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                //将email输入的文本存在email[0]中
                email[0] = editable.toString();
            }
        });

        //设置text监听器，用于监听check_code_tv的输入文本
        check_code_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                sign[2] = !charSequence.toString().equals("");

                //设置next的可否点击的状态
                next.setEnabled(sign[1] && sign[2]);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                //将check_code_tv输入的文本存在check_code[0]中
                check_code[0] = editable.toString();
            }
        });

        //创建可点击文本 对象clickableSpan，用于项用户发送验证码
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            //注册可点击文本方法
            public void onClick(@NonNull View view) {

                if(sign[1]){

                    String verifyCode = String.valueOf(secureRandom.nextInt(900000) + 100000);

                    //创建一个子线程，用于请求网络并发送邮件
                    new Thread(new Send_and_Save_Code(email[0],verifyCode)).start();

                    Toast.makeText(Register_email.this, "验证码已发往邮箱地址!", Toast.LENGTH_SHORT).show();

                    //用于设计倒计时，防止短时间内多次获取验证码
                    new CountDownTimer(60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            String s = millisUntilFinished/1000 + "秒后获取";
                            get_code.setText(s);
                        }

                        public void onFinish() {
                            get_code.setText(spannableStringBuilder);
                        }
                    }.start();

                }else{
                    Toast.makeText(Register_email.this, "请输入正确的邮箱地址，再尝试获取！", Toast.LENGTH_SHORT).show();
                }
            }

            //将下划线去除
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check check = new Check(email[0],check_code[0]);
                try {
                    Thread thread = new Thread(check);
                    thread.start();
                    thread.join();
                    if(check.getSign()){
                        startActivity(new Intent().setClass(Register_email.this,Register_password.class));
                        Toast.makeText(Register_email.this, "验证码输入正确！", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Register_email.this, "验证码输入错误！", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        get_code.setHighlightColor(getResources().getColor(android.R.color.transparent));

        //将clickableSpan注册至spannableStringBuilder中
        spannableStringBuilder.setSpan(clickableSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        //将spannableStringBuilder注册至get_code中
        get_code.setText(spannableStringBuilder);

        //为get_code注册clickableSpan实现方法
        get_code.setMovementMethod(LinkMovementMethod.getInstance());
    }
}