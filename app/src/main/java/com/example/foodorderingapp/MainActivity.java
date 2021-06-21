package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.RegisterActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView login,register,textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(TextView) findViewById(R.id.login);
        register=(TextView) findViewById(R.id.register);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Home");


        String textlogin="Login";
        String textregister="Register";

        SpannableString sLogin= new SpannableString(textlogin);
        SpannableString sRegister= new SpannableString(textregister);

        ClickableSpan clickableLogin=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
        ClickableSpan clickableRegister=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        };

        sLogin.setSpan(clickableLogin,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(sLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());

        sRegister.setSpan(clickableRegister,0,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(sRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());


    }
}