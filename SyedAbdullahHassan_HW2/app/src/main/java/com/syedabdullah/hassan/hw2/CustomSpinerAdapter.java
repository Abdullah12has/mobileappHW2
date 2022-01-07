package com.syedabdullah.hassan.hw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


class CustomSpinnerAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> spinnerItemValues;
    int [] imgIds = null;
    public CustomSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<String> spinnerItems, int [] imgIds) {
        super(context, R.layout.country, spinnerItems);
        this.context = context;
        spinnerItemValues = spinnerItems;
        this.imgIds = imgIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.country, parent, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.itemConstraintLayout);
        ImageView imgItemSocial = view.findViewById(R.id.imgCountry);
        TextView tvItemSocialName = view.findViewById(R.id.txtCountry);

        tvItemSocialName.setText(spinnerItemValues.get(position));
        imgItemSocial.setImageResource(imgIds[position]);
//        if(position % 2 == 0)
////            constraintLayout.setBackgroundColor(Color.BLUE);
//        else
////            constraintLayout.setBackgroundColor(Color.YELLOW);

        return view;
    }
}
