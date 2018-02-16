package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable, Parcelable {

    public final static Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    };
    private final static long serialVersionUID = -6172841939854632462L;
    @SerializedName("ProductId")
    @Expose
    private int productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("MRP")
    @Expose
    private double mRP;
    @SerializedName("DealerPrice")
    @Expose
    private double dealerPrice;
    @SerializedName("WholesalePrice")
    @Expose
    private double wholesalePrice;
    @SerializedName("CGST")
    @Expose
    private double cGST;
    @SerializedName("SGST")
    @Expose
    private double sGST;
    @SerializedName("IGST")
    @Expose
    private double iGST;
    @SerializedName("IsActive")
    @Expose
    private boolean isActive;
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

    protected Product(Parcel in) {
        this.productId = ((int) in.readValue((int.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productCode = ((String) in.readValue((String.class.getClassLoader())));
        this.mRP = ((double) in.readValue((double.class.getClassLoader())));
        this.dealerPrice = ((double) in.readValue((double.class.getClassLoader())));
        this.wholesalePrice = ((double) in.readValue((double.class.getClassLoader())));
        this.cGST = ((double) in.readValue((double.class.getClassLoader())));
        this.sGST = ((double) in.readValue((double.class.getClassLoader())));
        this.iGST = ((double) in.readValue((double.class.getClassLoader())));
        this.isActive = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param mRP
     * @param wholesalePrice
     * @param iGST
     * @param productCode
     * @param createdById
     * @param dealerPrice
     * @param productId
     * @param isActive
     * @param lastUpdatedDate
     * @param cGST
     * @param productName
     * @param createdDate
     * @param lastUpdatedById
     * @param sGST
     */
    public Product(int productId, String productName, String productCode, double mRP, double dealerPrice, double wholesalePrice, double cGST, double sGST, double iGST, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.mRP = mRP;
        this.dealerPrice = dealerPrice;
        this.wholesalePrice = wholesalePrice;
        this.cGST = cGST;
        this.sGST = sGST;
        this.iGST = iGST;
        this.isActive = isActive;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getMRP() {
        return mRP;
    }

    public void setMRP(double mRP) {
        this.mRP = mRP;
    }

    public double getDealerPrice() {
        return dealerPrice;
    }

    public void setDealerPrice(double dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(productCode);
        dest.writeValue(mRP);
        dest.writeValue(dealerPrice);
        dest.writeValue(wholesalePrice);
        dest.writeValue(cGST);
        dest.writeValue(sGST);
        dest.writeValue(iGST);
        dest.writeValue(isActive);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return 0;
    }

}
