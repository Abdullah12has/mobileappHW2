package com.syedabdullah.hassan.hw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Activity2 extends AppCompatActivity {


    private static final DecimalFormat df = new DecimalFormat("0.00");
    double[] rates = new double[]{9.69,170.15, 6.50, 6.40, 74.19};
    Intent intent;
    String out = "";
    TextView tvs2, tvs3;
    EditText ets1;
    double converted ;
    String msg, cname;
    ImageView imgs1;
    SeekBar sb1;
    int[] imgIds = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvs2 = findViewById(R.id.tvs2);
        tvs3 = findViewById(R.id.tvs3);
        ets1 = findViewById(R.id.ets1);
        sb1 = findViewById(R.id.sb1);
        imgs1 = findViewById(R.id.imgs1);


        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                imgs1.setImageResource(imgIds[i]);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ets1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String temp = editable.toString();

                amount = Integer.parseInt(temp);
                out = temp;
            }
        });


        intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("name");
        cname = b.getString("cname");

        tvs2.setText("Welcome "+ name);

        String out = "1 USD is ";

        if(cname.equalsIgnoreCase("turkey")){
            out+= rates[0]+"TL";
            converted = amount * rates[0];
        }
        else if(cname.equalsIgnoreCase("pakistan")){
            out+= rates[1]+"PKR";
            converted = amount * rates[1];
        }
        else if(cname.equalsIgnoreCase("croatia")){
            out+= rates[2]+"KN";
            converted = amount * rates[2];
        }
        else if(cname.equalsIgnoreCase("china")){
            out+= rates[3]+"CNY";
            converted = amount * rates[3];
        }
        else if(cname.equalsIgnoreCase("india")){
            out+= rates[4]+"INR";
            converted = amount * rates[4];
        }

        tvs3.setText(out);



    }

    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);

        box.setIcon(R.drawable.five); // find a good icon
        box.setTitle("Currency Conversion");
        box.setMessage(message);
        box.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        box.create();
        box.show();
    }
    public void back(View view) {
        intent = new Intent();
        intent.putExtra("res",out);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void calc(View view) {

        if(cname.equalsIgnoreCase("turkey")){

            converted = amount* rates[0];
        }
        else if(cname.equalsIgnoreCase("pakistan")){

            converted = amount * rates[1];
        }
        else if(cname.equalsIgnoreCase("croatia")){

            converted = amount * rates[2];
        }
        else if(cname.equalsIgnoreCase("china")){

            converted = amount * rates[3];
        }
        else if(cname.equalsIgnoreCase("india")){

            converted = amount * rates[4];
        }

        makeAndShowDialog("$"+ amount +" is: "+ df.format(converted) + " in"+cname);
    }
}