package com.syedabdullah.hassan.hw2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    TextView textView3;
    DatabaseHelper dbHelper;
    MyRecyclerViewAdapter adapter;
    Intent intent;
    RecyclerView foodRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
            String fileToDatabase = "/data/data/" + getPackageName() + "/databases/"+DatabaseHelper.DATABASE_NAME;
            File file = new File(fileToDatabase);
            File pathToDatabasesFolder = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                pathToDatabasesFolder.mkdirs();
                Log.d("BURDA", "BURDA");
                CopyDB( getResources().getAssets().open(DatabaseHelper.DATABASE_NAME),
                        new FileOutputStream(fileToDatabase));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView3 = findViewById(R.id.textView3);
        foodRecycler = findViewById(R.id.rv1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        intent = new Intent(this, Activity4.class);
        dbHelper = new DatabaseHelper(this);

        Commons.setData( (ArrayList<Food>) FoodsTable.getAll(dbHelper));
//        textView3.setText(Commons.getData().toString());
        intent = new Intent(this, Activity4.class);

        adapter = new MyRecyclerViewAdapter(this, intent);

        foodRecycler.setAdapter(adapter);
        foodRecycler.setLayoutManager(layoutManager);

    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        Log.d("BURDA", "BURDA2");
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("BURDA", "BURDA3");
        }
        inputStream.close();
        outputStream.close();
    }

}