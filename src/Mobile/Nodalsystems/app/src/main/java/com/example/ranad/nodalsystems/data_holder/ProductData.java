package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 06-02-2018.
 */

public class ProductData implements Parcelable {
    String ProductName;
    String ProductCode;
    int MRP;
    int DealerPrice;
    int WholePrice;
    int cgst;
    int sgst;
    int igst;
    boolean IsActive;

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

    public int getDealerPrice() {
        return DealerPrice;
    }

    public void setDealerPrice(int dealerPrice) {
        DealerPrice = dealerPrice;
    }

    public int getWholePrice() {
        return WholePrice;
    }

    public void setWholePrice(int wholePrice) {
        WholePrice = wholePrice;
    }

    public int getCgst() {
        return cgst;
    }

    public void setCgst(int cgst) {
        this.cgst = cgst;
    }

    public int getSgst() {
        return sgst;
    }

    public void setSgst(int sgst) {
        this.sgst = sgst;
    }

    public int getIgst() {
        return igst;
    }

    public void setIgst(int igst) {
        this.igst = igst;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }


    protected ProductData(Parcel in) {
        ProductName = in.readString();
        ProductCode = in.readString();
        MRP = in.readInt();
        DealerPrice = in.readInt();
        WholePrice = in.readInt();
        cgst = in.readInt();
        sgst = in.readInt();
        igst = in.readInt();
        IsActive = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ProductName);
        dest.writeString(ProductCode);
        dest.writeInt(MRP);
        dest.writeInt(DealerPrice);
        dest.writeInt(WholePrice);
        dest.writeInt(cgst);
        dest.writeInt(sgst);
        dest.writeInt(igst);
        dest.writeByte((byte) (IsActive ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
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
}
