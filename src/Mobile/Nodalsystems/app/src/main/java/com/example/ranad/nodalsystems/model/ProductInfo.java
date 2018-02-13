package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ranad.nodalsystems.database.Products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kavya V on 13-02-2018.
 */

public class ProductInfo implements Serializable, Parcelable {

    @SerializedName("product")
    @Expose
    Products product;


    public ProductInfo() {
    }

    public ProductInfo(Products products) {
        super();
        this.product = products;
    }

    protected ProductInfo(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {
        @Override
        public ProductInfo createFromParcel(Parcel in) {
            return new ProductInfo(in);
        }

        @Override
        public ProductInfo[] newArray(int size) {
            return new ProductInfo[size];
        }
    };
}
