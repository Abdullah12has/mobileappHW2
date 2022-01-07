package com.syedabdullah.hassan.hw2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> countryItem;
    Spinner spCountries;
    ImageView imgM1;
    EditText et1,et2;
    int[] imgIds = new int[]{R.drawable.turkey_flag, R.drawable.pakistan_flag, R.drawable.croatia_flag, R.drawable.china_flag, R.drawable.india_flag};

    String cname= " ";
    TextView tv1, tv2;
    Dialog customDialog;
    TextView dtvmsg, tvtitle;
    Button dback;
    String msg="21", name;
    private GestureDetector mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hiding title bar using code
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv1 = findViewById(R.id.tv1);
        imgM1 = findViewById(R.id.imgM1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        MainActivity.MyGestureDetector mGestureD = new MainActivity.MyGestureDetector();
        mDetector = new GestureDetector(this,mGestureD);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);
        animation.setBackgroundColor(Color.DKGRAY);
        animation.setRepeatMode(Animation.ABSOLUTE);
        animation.setRepeatCount(Animation.INFINITE);
        tv1.setAnimation(animation);
        prepareData();
        spCountries = findViewById(R.id.sp1);
        tv2 = findViewById(R.id.tv2);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, countryItem, imgIds);
        spCountries.setAdapter(adapter);



        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().length() < 8){
                    displayToast(getString(R.string.passwordShort));
                }
                if (editable.toString().length() >= 8){
                    displayToast(getString(R.string.passwordStrong));
                }

            }
        });


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

              tv2.setText("Welcome "+ editable);
              name = editable+"";

            }
        });


        spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {


                Spinner spinnerTemp = (Spinner) parent;
                TextView selectedItemTextView = spinnerTemp.getSelectedView().findViewById(R.id.txtCountry);
                cname = selectedItemTextView.getText().toString();

                if(cname.equalsIgnoreCase("pakistan")){
                    imgM1.setImageResource(R.drawable.pakistan_other);
                }
                else if(cname.equalsIgnoreCase("turkey")){
                    imgM1.setImageResource(R.drawable.turkey_other);
                }else if(cname.equalsIgnoreCase("croatia")){
                    imgM1.setImageResource(R.drawable.croatia_other);
                }else if(cname.equalsIgnoreCase("china")){
                    imgM1.setImageResource(R.drawable.china_other);
                }else if(cname.equalsIgnoreCase("india")){
                    imgM1.setImageResource(R.drawable.india_other);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            msg = data.getStringExtra("res");
            displayToast(msg);
            createDailog(name, msg);
            customDialog.show();

        }
    }

    private void prepareData() {

            countryItem = new ArrayList<>();
            Collections.addAll(countryItem, getString(R.string.turkey), getString(R.string.pakistan), getString(R.string.croatia), getString(R.string.china), getString(R.string.india));

    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, Activity2.class);
        String name = et1.getText().toString();
        Bundle b = new Bundle();
        b.putString("name",name);
        b.putString("cname",cname);
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }

    private void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void createDailog(String name, String message){
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);

        dback = customDialog.findViewById(R.id.btnCancel);
        dtvmsg= customDialog.findViewById(R.id.tvmsg);
        tvtitle= customDialog.findViewById(R.id.tvtitle);

        tvtitle.setText("Welcome back "+name);
        dtvmsg.setText("you converted $ "+message);

        dback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customDialog.dismiss();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureDetector extends  GestureDetector.SimpleOnGestureListener{

        Intent intent = new Intent(MainActivity.this, Activity3.class);

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {

            intent.putExtra("cname",cname);
            startActivity(intent);
            return super.onDoubleTapEvent(e);
        }


        @Override
        public void onLongPress(MotionEvent e) {
            intent = new Intent(MainActivity.this, Activity5.class);
            intent.putExtra("cname",cname);
            startActivity(intent);
        }
    }

}