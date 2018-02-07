package com.example.ranad.nodalsystems.database;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rana D on 2/3/2018.
 */
@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                //@Index(value = "customerCode,amountLimit,mobile,email,address1,address2,city,state,country,pin,isActive,createdById,createdDate,lastUpdatedById,lastUpdatedDate")
        })

public class Customers {
    @Id(autoincrement = true)
    Long id;

    private int customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String customerCode;
    private double amountLimit;
    private String mobile;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pin;
    private boolean isActive;
    private int createdById;
    private String createdDate;
    private int lastUpdatedById;
    private String lastUpdatedDate;

    /**
     * No args constructor for use in serialization
     */
    public Customers() {
    }

    /**
     * @param middleName
     * @param lastName
     * @param createdById
     * @param state
     * @param address1
     * @param address2
     * @param country
     * @param city
     * @param isActive
     * @param customerId
     * @param pin
     * @param email
     * @param customerCode
     * @param lastUpdatedDate
     * @param firstName
     * @param createdDate
     * @param amountLimit
     * @param lastUpdatedById
     * @param mobile
     */
    public Customers(int customerId, String firstName, String middleName, String lastName, String customerCode, double amountLimit, String mobile, String email, String address1, String address2, String city, String state, String country, String pin, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerCode = customerCode;
        this.amountLimit = amountLimit;
        this.mobile = mobile;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pin = pin;
        this.isActive = isActive;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Generated(hash = 550099825)
    public Customers(Long id, int customerId, String firstName, String middleName, String lastName, String customerCode, double amountLimit, String mobile, String email, String address1, String address2, String city, String state, String country, String pin, boolean isActive, int createdById, String createdDate, int lastUpdatedById,
                     String lastUpdatedDate) {
        this.id = id;
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerCode = customerCode;
        this.amountLimit = amountLimit;
        this.mobile = mobile;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pin = pin;
        this.isActive = isActive;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public double getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(double amountLimit) {
        this.amountLimit = amountLimit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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