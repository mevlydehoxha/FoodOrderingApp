package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {


    EditText ordername,quantity,extra,address,phonenumber,notes;
    Button submit,location;
    TextView comments,textView;
    DBOrders DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ordername = (EditText) findViewById(R.id.ordername);
        quantity = (EditText) findViewById(R.id.quantity);
        extra = (EditText) findViewById(R.id.extra);
//        address = (EditText) findViewById(R.id.address);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        notes = (EditText) findViewById(R.id.notes);
        location=(Button) findViewById(R.id.location);
        submit=(Button) findViewById(R.id.submit);
        comments=(TextView) findViewById(R.id.comments);
        DB= new DBOrders(this);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Order here");

        String textcomment="Leave a comment";

        SpannableString sComment=new SpannableString(textcomment);

        ClickableSpan clickableComment=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent=new Intent(getApplicationContext(),CommentsActivity.class);
                startActivity(intent);

            }
        };
        sComment.setSpan(clickableComment,0,15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        comments.setText(sComment);
        comments.setMovementMethod(LinkMovementMethod.getInstance());

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getApplicationContext(),PlacePicker.class);
                startActivity(intent2);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ordername.getText().toString();
                String qty=quantity.getText().toString();
                String extr=extra.getText().toString();
                String addr=address.getText().toString();
                String phoneno=phonenumber.getText().toString();
                String note=notes.getText().toString();



                if(name.equals("") || qty.equals("")|| extr.equals("") || addr.equals("")|| phoneno.equals(""))
                    Toast.makeText(OrderActivity.this,"Empty fields are required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert =DB.insertOrder(name, qty, extr, addr, phoneno, note);
                    if(insert==true){
                        Toast.makeText(OrderActivity.this,"Your order have been registered successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(OrderActivity.this,"Registration of your order has failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });



    }
}