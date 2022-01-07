package com.syedabdullah.hassan.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    private int id;
    private String foodname;
    private String countryName;
    private byte[] image;
    private String description;

    public Food(int id, String foodname, String countryName, byte[] image, String description) {
        this.id = id;
        this.foodname = foodname;
        this.countryName = countryName;
        this.image = image;
        this.description = description;
    }

    protected Food(Parcel in) {
        id = in.readInt();
        foodname = in.readString();
        countryName = in.readString();
        image = in.createByteArray();
        description = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(foodname);
        parcel.writeString(countryName);
        parcel.writeByteArray(image);
        parcel.writeString(description);
    }
}