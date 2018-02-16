package com.example.ranad.nodalsystems.database;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;


@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                @Index(value = "customerId , ProductId ", unique = true)
        })
public class CartItem implements Parcelable {
    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
    @Id(autoincrement = true)
    @Unique
    Long id;
    int customerId, ProductId;
    String productName;
    int Quantity;
    float netPrice;


    public CartItem() {

    }

    public CartItem(int ProductId, int Quantity) {
        this.ProductId = ProductId;
        this.Quantity = Quantity;
    }

    protected CartItem(Parcel in) {
        customerId = in.readInt();
        ProductId = in.readInt();
        Quantity = in.readInt();
        netPrice = in.readFloat();
    }


    @Generated(hash = 1653419056)
    public CartItem(Long id, int customerId, int ProductId, String productName, int Quantity, float netPrice) {
        this.id = id;
        this.customerId = customerId;
        this.ProductId = ProductId;
        this.productName = productName;
        this.Quantity = Quantity;
        this.netPrice = netPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(customerId);
        parcel.writeInt(ProductId);
        parcel.writeInt(Quantity);
        parcel.writeFloat(netPrice);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CartItem) {
            if (((CartItem) obj).getProductId() == this.getProductId() && ((CartItem) obj).getCustomerId() == this.getCustomerId()) {
                return true;
            } else {
                return false;
            }
        }
        return super.equals(obj);

    }

    @Override
    public String toString() {
        return "cust id = " + customerId + " proid = " + getProductId();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
