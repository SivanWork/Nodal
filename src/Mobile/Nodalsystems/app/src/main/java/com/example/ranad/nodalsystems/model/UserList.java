
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList implements Serializable, Parcelable
{

    @SerializedName("UserId")
    @Expose
    private int userId;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("Address2")
    @Expose
    private String address2;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Pin")
    @Expose
    private String pin;
    @SerializedName("UserGroupType")
    @Expose
    private String userGroupType;
    @SerializedName("UserElementCode")
    @Expose
    private String userElementCode;
    @SerializedName("ActiveFrom")
    @Expose
    private String activeFrom;
    @SerializedName("ActiveTo")
    @Expose
    private String activeTo;
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
    public final static Creator<UserList> CREATOR = new Creator<UserList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserList createFromParcel(Parcel in) {
            return new UserList(in);
        }

        public UserList[] newArray(int size) {
            return (new UserList[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2244345351911964412L;

    protected UserList(Parcel in) {
        this.userId = ((int) in.readValue((int.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.middleName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.address2 = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.pin = ((String) in.readValue((String.class.getClassLoader())));
        this.userGroupType = ((String) in.readValue((String.class.getClassLoader())));
        this.userElementCode = ((String) in.readValue((String.class.getClassLoader())));
        this.activeFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.activeTo = ((String) in.readValue((String.class.getClassLoader())));
        this.isActive = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserList() {
    }

    /**
     * 
     * @param middleName
     * @param lastName
     * @param userGroupType
     * @param createdById
     * @param activeTo
     * @param state
     * @param userElementCode
     * @param address1
     * @param address2
     * @param password
     * @param country
     * @param city
     * @param isActive
     * @param username
     * @param pin
     * @param email
     * @param userId
     * @param lastUpdatedDate
     * @param firstName
     * @param createdDate
     * @param lastUpdatedById
     * @param activeFrom
     * @param mobile
     */
    public UserList(int userId, String username, String password, String firstName, String middleName, String lastName, String mobile, String email, String address1, String address2, String city, String state, String country, String pin, String userGroupType, String userElementCode, String activeFrom, String activeTo, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pin = pin;
        this.userGroupType = userGroupType;
        this.userElementCode = userElementCode;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
        this.isActive = isActive;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserGroupType() {
        return userGroupType;
    }

    public void setUserGroupType(String userGroupType) {
        this.userGroupType = userGroupType;
    }

    public String getUserElementCode() {
        return userElementCode;
    }

    public void setUserElementCode(String userElementCode) {
        this.userElementCode = userElementCode;
    }

    public String getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(String activeFrom) {
        this.activeFrom = activeFrom;
    }

    public String getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(String activeTo) {
        this.activeTo = activeTo;
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
        dest.writeValue(userId);
        dest.writeValue(username);
        dest.writeValue(password);
        dest.writeValue(firstName);
        dest.writeValue(middleName);
        dest.writeValue(lastName);
        dest.writeValue(mobile);
        dest.writeValue(email);
        dest.writeValue(address1);
        dest.writeValue(address2);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(country);
        dest.writeValue(pin);
        dest.writeValue(userGroupType);
        dest.writeValue(userElementCode);
        dest.writeValue(activeFrom);
        dest.writeValue(activeTo);
        dest.writeValue(isActive);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return  0;
    }

}
