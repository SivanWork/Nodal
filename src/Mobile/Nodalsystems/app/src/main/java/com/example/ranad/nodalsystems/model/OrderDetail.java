
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("OrderId")
    @Expose
    private int orderId;
    @SerializedName("ProductId")
    @Expose
    private int productId;
    @SerializedName("Quantity")
    @Expose
    private int quantity;
    @SerializedName("CGST")
    @Expose
    private double cGST;
    @SerializedName("SGST")
    @Expose
    private double sGST;
    @SerializedName("IGST")
    @Expose
    private double iGST;
    @SerializedName("Discount")
    @Expose
    private double discount;
    @SerializedName("NetPrice")
    @Expose
    private double netPrice;
    @SerializedName("CreatedById")
    @Expose
    private int createdById;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("LastUpdatedById")
    @Expose
    private int lastUpdatedById;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    public final static Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        public OrderDetail[] newArray(int size) {
            return (new OrderDetail[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1504361261285731827L;

    protected OrderDetail(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.productId = ((int) in.readValue((int.class.getClassLoader())));
        this.quantity = ((int) in.readValue((int.class.getClassLoader())));
        this.cGST = ((double) in.readValue((double.class.getClassLoader())));
        this.sGST = ((double) in.readValue((double.class.getClassLoader())));
        this.iGST = ((double) in.readValue((double.class.getClassLoader())));
        this.discount = ((double) in.readValue((double.class.getClassLoader())));
        this.netPrice = ((double) in.readValue((double.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderDetail() {
    }

    /**
     * 
     * @param iGST
     * @param createdById
     * @param netPrice
     * @param productId
     * @param discount
     * @param id
     * @param lastUpdatedDate
     * @param quantity
     * @param cGST
     * @param createdDate
     * @param orderId
     * @param sGST
     * @param lastUpdatedById
     */
    public OrderDetail(int id, int orderId, int productId, int quantity, double cGST, double sGST, double iGST, double discount, double netPrice, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.cGST = cGST;
        this.sGST = sGST;
        this.iGST = iGST;
        this.discount = discount;
        this.netPrice = netPrice;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCGST() {
        return cGST;
    }

    public void setCGST(double cGST) {
        this.cGST = cGST;
    }

    public double getSGST() {
        return sGST;
    }

    public void setSGST(double sGST) {
        this.sGST = sGST;
    }

    public double getIGST() {
        return iGST;
    }

    public void setIGST(double iGST) {
        this.iGST = iGST;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(orderId);
        dest.writeValue(productId);
        dest.writeValue(quantity);
        dest.writeValue(cGST);
        dest.writeValue(sGST);
        dest.writeValue(iGST);
        dest.writeValue(discount);
        dest.writeValue(netPrice);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return  0;
    }

}
