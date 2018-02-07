package com.example.ranad.nodalsystems.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.util.HashMap;
import java.util.Map;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Rana D on 2/3/2018.
 */
@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                //    @Index(value = "productId,productName,mRP,dealerPrice,wholesalePrice,cGST,sGST,iGST,isActive,createdById,createdDate,lastUpdatedById,lastUpdatedDate", unique = true)
        })


public class Products {
    @Id(autoincrement = true)
    Long id;

    private int productId;
    private String productName;
    private double mRP;
    private double dealerPrice;
    private double wholesalePrice;
    private double cGST;
    private double sGST;
    private double iGST;
    private boolean isActive;
    private int createdById;
    private String createdDate;
    private int lastUpdatedById;
    private String lastUpdatedDate;

    /**
     * No args constructor for use in serialization
     */
    public Products() {
    }

    /**
     * @param isActive
     * @param mRP
     * @param iGST
     * @param wholesalePrice
     * @param createdById
     * @param lastUpdatedDate
     * @param dealerPrice
     * @param cGST
     * @param createdDate
     * @param productName
     * @param lastUpdatedById
     * @param sGST
     * @param productId
     */
    public Products(int productId, String productName, double mRP, double dealerPrice, double wholesalePrice, double cGST, double sGST, double iGST, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.productId = productId;
        this.productName = productName;
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

    @Generated(hash = 2034053822)
    public Products(Long id, int productId, String productName, double mRP, double dealerPrice, double wholesalePrice, double cGST, double sGST, double iGST, boolean isActive, int createdById, String createdDate, int lastUpdatedById,
                    String lastUpdatedDate) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return this.isActive;
    }


}