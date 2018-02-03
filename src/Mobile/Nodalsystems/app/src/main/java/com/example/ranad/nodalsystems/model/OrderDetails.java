package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rana D on 2/1/2018.
 */

public class OrderDetails implements Parcelable {

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getCGST() {
        return CGST;
    }

    public void setCGST(int CGST) {
        this.CGST = CGST;
    }

    public int getSGST() {
        return SGST;
    }

    public void setSGST(int SGST) {
        this.SGST = SGST;
    }

    public int getIGST() {
        return IGST;
    }

    public void setIGST(int IGST) {
        this.IGST = IGST;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getNetPrice() {
        return NetPrice;
    }

    public void setNetPrice(int netPrice) {
        NetPrice = netPrice;
    }

    int Id;
    int OrderId;
    int ProductId;
    int Quantity;
    int CGST;
    int SGST;
    int IGST;
    int Discount;
    int NetPrice;


    protected OrderDetails(Parcel in) {
        Id = in.readInt();
        OrderId = in.readInt();
        ProductId = in.readInt();
        Quantity = in.readInt();
        CGST = in.readInt();
        SGST = in.readInt();
        IGST = in.readInt();
        Discount = in.readInt();
        NetPrice = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeInt(OrderId);
        dest.writeInt(ProductId);
        dest.writeInt(Quantity);
        dest.writeInt(CGST);
        dest.writeInt(SGST);
        dest.writeInt(IGST);
        dest.writeInt(Discount);
        dest.writeInt(NetPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDetails> CREATOR = new Creator<OrderDetails>() {
        @Override
        public OrderDetails createFromParcel(Parcel in) {
            return new OrderDetails(in);
        }

        @Override
        public OrderDetails[] newArray(int size) {
            return new OrderDetails[size];
        }
    };
}
