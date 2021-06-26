package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.CommentsActivity;
import com.example.foodorderingapp.FoodList;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.SQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView textView;
    Button btnFood;
    ImageView imageView;
    public static SQLiteHelper sqLiteHelper;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Menu");


        sqLiteHelper = new SQLiteHelper(this, "Menu.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MenuTable(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, image BLOB)");


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(MenuActivity.this, FoodList.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent2=new Intent(MenuActivity.this, OrderActivity.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                Intent intent3=new Intent(MenuActivity.this, CommentsActivity.class);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }

}
