package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 06-02-2018.
 */

public class ProductData implements Parcelable {

    public ProductData(String ProductName, String ProductCode ){
        this.ProductName = ProductName;
        this.ProductCode = ProductCode;
    }
    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    String ProductName;
    String ProductCode;
    int MRP;

    protected ProductData(Parcel in) {
        ProductName = in.readString();
        ProductCode = in.readString();
        MRP = in.readInt();
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ProductName);
        parcel.writeString(ProductCode);
        parcel.writeInt(MRP);
    }
}
