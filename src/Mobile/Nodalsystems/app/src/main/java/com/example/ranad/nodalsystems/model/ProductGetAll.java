package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductGetAll implements Serializable, Parcelable {

    public final static Creator<ProductGetAll> CREATOR = new Creator<ProductGetAll>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductGetAll createFromParcel(Parcel in) {
            return new ProductGetAll(in);
        }

        public ProductGetAll[] newArray(int size) {
            return (new ProductGetAll[size]);
        }

    };
    private final static long serialVersionUID = -4087283041282759513L;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("productList")
    @Expose
    private List<ProductList> productList = null;
    @SerializedName("Success")
    @Expose
    private boolean success;
    @SerializedName("IsWarning")
    @Expose
    private boolean isWarning;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StackTrace")
    @Expose
    private String stackTrace;

    protected ProductGetAll(Parcel in) {
        this.product = ((Product) in.readValue((Product.class.getClassLoader())));
        in.readList(this.productList, (ProductList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ProductGetAll() {
    }

    /**
     * @param message
     * @param product
     * @param productList
     * @param isWarning
     * @param success
     * @param stackTrace
     */
    public ProductGetAll(Product product, List<ProductList> productList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.product = product;
        this.productList = productList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsWarning() {
        return isWarning;
    }

    public void setIsWarning(boolean isWarning) {
        this.isWarning = isWarning;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(product);
        dest.writeList(productList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return 0;
    }

}
