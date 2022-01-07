package com.syedabdullah.hassan.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {


    Food food = null;
    TextView tv6, tv7,tv8;
    ImageView img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        img4 = findViewById(R.id.img4);
        Intent receivedIntent = getIntent();
        Bundle b = receivedIntent.getExtras();
        food = b.getParcelable("food");
        tv6.setText(food.getFoodname().toString());
        tv7.setText(food.getDescription().toString());
        tv8.setText(food.getCountryName().toString());
        img4.setImageBitmap(BitmapFactory.decodeByteArray(food.getImage(), 0, food.getImage().length));
    }
}