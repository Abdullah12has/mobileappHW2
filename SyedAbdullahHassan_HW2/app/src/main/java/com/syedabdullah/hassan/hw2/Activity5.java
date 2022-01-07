package com.syedabdullah.hassan.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Activity5 extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Recycler2Adapter mRecyclerAdapter;

    private String jsonStr;
    private JSONArray students;

    private JSONObject allJSON;
    ProgressBar pb;

    private ArrayList<Student> mArrayList=new ArrayList();
    public static final String TAG_STUDENTS = "students";
    public static final String TAG_NAME = "name";
    public static final String TAG_SURNAME = "surname";
    public static final String TAG_COUNTRY = "nationality";

    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent receivedIntent = getIntent();


        textView2 = findViewById(R.id.textView2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv2);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);


    }

    public void showw(View v) {

                pb.setVisibility(View.VISIBLE);
                // Reading the JSON file from the assets folder and storing it in a String
                jsonStr = loadFileFromAssets("students.json");
                Log.d("TAG", "\n" + jsonStr);
                // Call to AsyncTask
                new GetStudentsJSON().execute();
    }

    private class GetStudentsJSON extends AsyncTask<Void, Void, Void> {

        // Main job should be done here
        @Override
        protected Void doInBackground(Void... params) {
            //Log.d("TAG", "HERE.....");

            if (jsonStr != null) {
                try {
                    allJSON = new JSONObject(jsonStr);

                    students = allJSON.getJSONArray(TAG_STUDENTS);

                    // looping through all students
                    for (int i = 0; i < students.length(); i++) {

                        JSONObject s = students.getJSONObject(i);

                        int id = i;
                        String name = s.getString(TAG_NAME);
                        String surname = s.getString(TAG_SURNAME);
                        String nationaity = s.getString(TAG_COUNTRY);

                        Student stu = new Student(id, name, surname,nationaity);

                        mArrayList.add(stu);
                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // What do you want to do after doInBackground() finishes
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the progress dialog

            pb.setVisibility(View.INVISIBLE);
            if (mArrayList != null) {
                mRecyclerAdapter = new Recycler2Adapter(Activity5.this, mArrayList);
                mRecyclerView.setAdapter(mRecyclerAdapter);
            } else
                Toast.makeText(Activity5.this, "Not Found", Toast.LENGTH_LONG).show();
        }

    }
    private String loadFileFromAssets(String fileName) {
        String file = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            file = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return file;
    }

}