package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommentsActivity extends AppCompatActivity {

    EditText comments,suggestions;
    TextView textView;
    RatingBar ratingBar;
    Button submit;
    DBComments DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ratingBar=findViewById(R.id.rating_bar);
        comments = (EditText) findViewById(R.id.comments);
        suggestions = (EditText) findViewById(R.id.suggestions);
        submit=(Button) findViewById(R.id.submit);
        DB= new DBComments(this);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Comments section");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= String.valueOf(ratingBar.getRating());
                String sComments = comments.getText().toString();
                String sSuggestions = suggestions.getText().toString();
                Boolean insert = DB.insertComments(s, sComments, sSuggestions);
                if (insert == true) {
                    Toast.makeText(CommentsActivity.this, "Your suggestions have been registered successfully. Rating "+s+" Stars", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CommentsActivity.this, "Registration of your suggestions has failed. Please try again.", Toast.LENGTH_SHORT).show();
                }

            }



        });




    }
}