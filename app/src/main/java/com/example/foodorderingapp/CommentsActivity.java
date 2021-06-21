package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.R;

public class CommentsActivity extends AppCompatActivity {

    EditText rating,comments,suggestions;
    TextView textView;
    Button submit;
    DBComments DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        rating = (EditText) findViewById(R.id.rating);
        comments = (EditText) findViewById(R.id.comments);
        suggestions = (EditText) findViewById(R.id.suggestions);
        submit=(Button) findViewById(R.id.submit);
        DB= new DBComments(this);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Comments section");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sRating = rating.getText().toString();
                String sComments = comments.getText().toString();
                String sSuggestions = suggestions.getText().toString();

                if (sRating.equals("1") || sRating.equals("2") || sRating.equals("3") || sRating.equals("4") || sRating.equals("5")) {
                    Boolean insert = DB.insertComments(sRating, sComments, sSuggestions);
                    if (insert == true) {
                        Toast.makeText(CommentsActivity.this, "Your suggestions have been registered successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CommentsActivity.this, "Registration of your suggestions has failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CommentsActivity.this," Ratings should be in a 1-5 range. Please try again.",Toast.LENGTH_SHORT).show();
                }
            }



        });




    }
}