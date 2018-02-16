package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


@Entity
public class ProductData implements Parcelable {
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
    @Id
    int ProductId;
    String ProductName;
    String ProductCode;
    float MR;
    double DealerPrice;
    double WholesalePrice;
    float CGST;
    float SGST;
    float IGST;
    boolean IsActive;
    int CreatedById;
    String CreatedDate;
    int LastUpdatedById;
    String LastUpdatedDate;

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

    @Generated(hash = 513794503)
    public ProductData(int ProductId, String ProductName, String ProductCode,
                       float MR, double DealerPrice, double WholesalePrice, float CGST,
                       float SGST, float IGST, boolean IsActive, int CreatedById,
                       String CreatedDate, int LastUpdatedById, String LastUpdatedDate) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.ProductCode = ProductCode;
        this.MR = MR;
        this.DealerPrice = DealerPrice;
        this.WholesalePrice = WholesalePrice;
        this.CGST = CGST;
        this.SGST = SGST;
        this.IGST = IGST;
        this.IsActive = IsActive;
        this.CreatedById = CreatedById;
        this.CreatedDate = CreatedDate;
        this.LastUpdatedById = LastUpdatedById;
        this.LastUpdatedDate = LastUpdatedDate;
    }
    @Generated(hash = 1035286127)
    public ProductData() {
    }

    public float getMR() {
        return MR;
    }

    public void setMR(long MR) {
        this.MR = MR;
    }

    public void setMR(float MR) {
        this.MR = MR;
    }

    public double getDealerPrice() {
        return DealerPrice;
    }

    public void setDealerPrice(long dealerPrice) {
        DealerPrice = dealerPrice;
    }

    public void setDealerPrice(double dealerPrice) {
        DealerPrice = dealerPrice;
    }

    public double getWholesalePrice() {
        return WholesalePrice;
    }

    public void setWholesalePrice(double WholesalePrice) {
        this.WholesalePrice = WholesalePrice;
    }

    public void setWholesalePrice(float wholesalePrice) {
        WholesalePrice = wholesalePrice;
    }

    public float getCGST() {
        return CGST;
    }

    public void setCGST(int CGST) {
        this.CGST = CGST;
    }

    public void setCGST(float CGST) {
        this.CGST = CGST;
    }

    public float getSGST() {
        return SGST;
    }

    public void setSGST(int SGST) {
        this.SGST = SGST;
    }

    public void setSGST(float SGST) {
        this.SGST = SGST;
    }

    public float getIGST() {
        return IGST;
    }

    public void setIGST(int IGST) {
        this.IGST = IGST;
    }

    public void setIGST(float IGST) {
        this.IGST = IGST;
    }

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

    public void setWholesalePrice(long wholesalePrice) {
        WholesalePrice = wholesalePrice;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ProductId);
        dest.writeString(ProductName);
        dest.writeString(ProductCode);
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

    public boolean getIsActive() {
        return this.IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }
}
