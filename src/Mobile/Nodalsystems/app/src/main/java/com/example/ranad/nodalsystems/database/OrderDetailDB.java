package com.example.ranad.nodalsystems.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                //     @Index(value = "productId,orderId,quantity,cGST,sGST,iGST,discount,netPrice,createdById,createdDate,lastUpdatedById,lastUpdatedDate,status ")
        })


public class OrderDetailDB {

    @Id(autoincrement = true)
    Long id;
    private int productId;
    private int orderId;
    private int quantity;
    private float cGST;
    private float sGST;
    private float iGST;
    private float discount;
    private float netPrice;
    private int createdById;
    private Date createdDate;
    private int lastUpdatedById;
    private Date lastUpdatedDate;
    private boolean status;
    public final static Parcelable.Creator<OrderDetailDB> CREATOR = new Creator<OrderDetailDB>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderDetailDB createFromParcel(Parcel in) {
            return new OrderDetailDB(in);
        }

        public OrderDetailDB[] newArray(int size) {
            return (new OrderDetailDB[size]);
        }

    };

    protected OrderDetailDB(Parcel in) {
        this.productId = ((int) in.readValue((int.class.getClassLoader())));
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.quantity = ((int) in.readValue((int.class.getClassLoader())));
        this.cGST = ((float) in.readValue((float.class.getClassLoader())));
        this.sGST = ((float) in.readValue((float.class.getClassLoader())));
        this.iGST = ((float) in.readValue((float.class.getClassLoader())));
        this.discount = ((float) in.readValue((float.class.getClassLoader())));
        this.netPrice = ((float) in.readValue((float.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((Date) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((Date) in.readValue((String.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OrderDetailDB() {
    }

    /**
     * @param lastUpdatedDate
     * @param iGST
     * @param status
     * @param createdById
     * @param netPrice
     * @param productId
     * @param discount
     * @param quantity
     * @param cGST
     * @param createdDate
     * @param orderId
     * @param sGST
     * @param lastUpdatedById
     */
    public OrderDetailDB(int productId, int orderId, int quantity, float cGST, float sGST, float iGST, float discount, float netPrice, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate, boolean status) {
        super();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");


        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.cGST = cGST;
        this.sGST = sGST;
        this.iGST = iGST;
        this.discount = discount;
        this.netPrice = netPrice;
        this.createdById = createdById;
        try {
            this.createdDate = formatter.parse(createdDate);
            this.lastUpdatedDate = formatter.parse(lastUpdatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.lastUpdatedById = lastUpdatedById;

        this.status = status;
    }

    @Generated(hash = 43014688)
    public OrderDetailDB(Long id, int productId, int orderId, int quantity, float cGST, float sGST, float iGST, float discount, float netPrice, int createdById, Date createdDate, int lastUpdatedById, Date lastUpdatedDate, boolean status) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
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
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public Date getLastupdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastupdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   /* @Override
    public String toString() {
        return new ToStringBuilder(this).append("productId", productId).append("orderId", orderId).append("quantity", quantity).append("cGST", cGST).append("sGST", sGST).append("iGST", iGST).append("discount", discount).append("netPrice", netPrice).append("createdById", createdById).append("createdDate", createdDate).append("lastUpdatedById", lastUpdatedById).append("lastUpdatedDate", lastUpdatedDate).append("status", status).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lastUpdatedDate).append(iGST).append(status).append(createdById).append(netPrice).append(discount).append(productId).append(quantity).append(cGST).append(createdDate).append(lastUpdatedById).append(sGST).append(orderId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderDetailDB) == false) {
            return false;
        }
        OrderDetailDB rhs = ((OrderDetailDB) other);
        return new EqualsBuilder().append(lastUpdatedDate, rhs.lastUpdatedDate).append(iGST, rhs.iGST).append(status, rhs.status).append(createdById, rhs.createdById).append(netPrice, rhs.netPrice).append(discount, rhs.discount).append(productId, rhs.productId).append(quantity, rhs.quantity).append(cGST, rhs.cGST).append(createdDate, rhs.createdDate).append(lastUpdatedById, rhs.lastUpdatedById).append(sGST, rhs.sGST).append(orderId, rhs.orderId).isEquals();
    }*/

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(orderId);
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
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public boolean getStatus() {
        return this.status;
    }

}
