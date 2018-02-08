package com.example.ranad.nodalsystems.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Rana D on 2/3/2018.
 */
@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                @Index(value = "id", unique = true)
        })


public class Products {
    @Id(autoincrement = true)
    Long id;

    private String productName;
    private float mRP;
    private float dealerPrice;
    private float wholesalePrice;
    private float cGST;
    private float sGST;
    private float iGST;
    private boolean isActive;
    private int createdById;
    private Date createdDate;
    private int lastUpdatedById;
    private Date lastUpdatedDate;

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
     */
    public Products(String productName, float mRP, float dealerPrice, float wholesalePrice, float cGST, float sGST, float iGST, boolean isActive, int createdById, Date createdDate, int lastUpdatedById, Date lastUpdatedDate) {
        super();
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

    @Generated(hash = 747382157)
    public Products(Long id, String productName, float mRP, float dealerPrice, float wholesalePrice, float cGST, float sGST, float iGST, boolean isActive, int createdById, Date createdDate, int lastUpdatedById, Date lastUpdatedDate) {
        this.id = id;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getMRP() {
        return mRP;
    }

    public void setMRP(float mRP) {
        this.mRP = mRP;
    }

    public float getDealerPrice() {
        return dealerPrice;
    }

    public void setDealerPrice(float dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public float getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(float wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public float getCGST() {
        return cGST;
    }

    public void setCGST(float cGST) {
        this.cGST = cGST;
    }

    public float getSGST() {
        return sGST;
    }

    public void setSGST(float sGST) {
        this.sGST = sGST;
    }

    public float getIGST() {
        return iGST;
    }

    public void setIGST(float iGST) {
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


    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
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