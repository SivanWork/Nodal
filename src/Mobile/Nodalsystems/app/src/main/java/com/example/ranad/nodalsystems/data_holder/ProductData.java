package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Kavya V on 06-02-2018.
 */
@Entity
public class ProductData implements Parcelable {

    @Id
    int ProductId;
    String ProductName;
    String ProductCode;
    long MR;
    long DealerPrice;
    long WholesalePrice;
    int CGST;
    int SGST;
    int IGST;
    boolean IsActive;
    int CreatedById;
    String CreatedDate;
    int LastUpdatedById;
    String LastUpdatedDate;

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

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

    public long getMR() {
        return MR;
    }

    public void setMR(long MR) {
        this.MR = MR;
    }

    public long getDealerPrice() {
        return DealerPrice;
    }

    public void setDealerPrice(long dealerPrice) {
        DealerPrice = dealerPrice;
    }

    public long getWholesalePrice() {
        return WholesalePrice;
    }

    public void setWholesalePrice(long wholesalePrice) {
        WholesalePrice = wholesalePrice;
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

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public int getCreatedById() {
        return CreatedById;
    }

    public void setCreatedById(int createdById) {
        CreatedById = createdById;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public int getLastUpdatedById() {
        return LastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        LastUpdatedById = lastUpdatedById;
    }

    public String getLastUpdatedDate() {
        return LastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        LastUpdatedDate = lastUpdatedDate;
    }



    protected ProductData(Parcel in) {
        ProductId = in.readInt();
        ProductName = in.readString();
        ProductCode = in.readString();
        MR = in.readLong();
        DealerPrice = in.readLong();
        WholesalePrice = in.readLong();
        CGST = in.readInt();
        SGST = in.readInt();
        IGST = in.readInt();
        IsActive = in.readByte() != 0;
        CreatedById = in.readInt();
        CreatedDate = in.readString();
        LastUpdatedById = in.readInt();
        LastUpdatedDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ProductId);
        dest.writeString(ProductName);
        dest.writeString(ProductCode);
        dest.writeLong(MR);
        dest.writeLong(DealerPrice);
        dest.writeLong(WholesalePrice);
        dest.writeInt(CGST);
        dest.writeInt(SGST);
        dest.writeInt(IGST);
        dest.writeByte((byte) (IsActive ? 1 : 0));
        dest.writeInt(CreatedById);
        dest.writeString(CreatedDate);
        dest.writeInt(LastUpdatedById);
        dest.writeString(LastUpdatedDate);
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

    @Override
    public String toString() {
        return "ProductData{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductCode='" + ProductCode + '\'' +
                ", MR=" + MR +
                ", DealerPrice=" + DealerPrice +
                ", WholesalePrice=" + WholesalePrice +
                ", CGST=" + CGST +
                ", SGST=" + SGST +
                ", IGST=" + IGST +
                ", IsActive=" + IsActive +
                ", CreatedById=" + CreatedById +
                ", CreatedDate='" + CreatedDate + '\'' +
                ", LastUpdatedById=" + LastUpdatedById +
                ", LastUpdatedDate='" + LastUpdatedDate + '\'' +
                '}';
    }


}
