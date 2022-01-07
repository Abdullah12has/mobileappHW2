package com.syedabdullah.hassan.hw2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    Intent values;

    public MyRecyclerViewAdapter(Context context, Intent intent) {
        this.context = context;
        this.values = intent;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Food food = Commons.data.get(position);
        holder.foodname.setText(food.getFoodname());

        Bitmap bitmap = BitmapFactory.decodeByteArray(food.getImage(), 0, food.getImage().length);
        holder.foodImage.setImageBitmap(bitmap);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("food", food);
                values.putExtras(bundle);
                context.startActivity(values);
            }
        });
    }
    @Override
    public int getItemCount() {
        return Commons.data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodname;
        ImageView foodImage;
        ConstraintLayout layout;
        MyViewHolder(View viewItem){
            super(viewItem);
            layout = viewItem.findViewById(R.id.layout5);
            foodname = viewItem.findViewById(R.id.tvnamesurname);
            foodImage = viewItem.findViewById(R.id.imgflag);
        }
    }
}