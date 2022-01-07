package com.syedabdullah.hassan.hw2;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Recycler2Adapter extends RecyclerView.Adapter<Recycler2Adapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Student> mArrayList;

    public Recycler2Adapter(Context mContext, ArrayList<Student> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;

    }

    // Each object of the ViewHolder will be created here
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = (View) mLayoutInflater.inflate(R.layout.recyclerview_layout2, parent, false);
        ViewHolder mViewHolder = new ViewHolder(itemView);

        return mViewHolder;
    }

    // This method will be called to assign data to each row or cell

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Student student = mArrayList.get(position);

        holder.nameSurname.setText(student.getName()+" "+student.getSurname());
        holder.cname.setText(student.getNationality().toString());
        if(student.getNationality().toString().equalsIgnoreCase("Turkey") ){
            holder.countryImg.setImageResource(R.drawable.turkey_flag);
        }
        else{
            holder.countryImg.setImageResource(R.drawable.pakistan_flag);
        }
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        //implements View.OnClickListener
        TextView nameSurname, cname;
        ImageView countryImg;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.layout5);
            nameSurname = (TextView) itemView.findViewById(R.id.tvnamesurname);
            countryImg = (ImageView) itemView.findViewById(R.id.imgflag);
            cname =(TextView) itemView.findViewById(R.id.tvcountry2);
        }
    }


}