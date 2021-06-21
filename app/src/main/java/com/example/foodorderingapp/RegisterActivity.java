package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup;
    TextView login,textView;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        signup= (Button) findViewById(R.id.btnsignup);
        login=(TextView) findViewById(R.id.login);
        DB= new DBHelper(this);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Register");

        String logintext="Login here if you already have an account";

        SpannableString sLogin= new SpannableString(logintext);
        ClickableSpan clickableLogin= new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        };

        sLogin.setSpan(clickableLogin,0,41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(sLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("") || pass.equals("")|| repass.equals(""))
                    Toast.makeText(RegisterActivity.this,"Empty fields are required", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert =DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this,"You have been registered successfully. Now please login to continue.", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"This user already exists. Please try again with a different username",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Password are not matching. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}