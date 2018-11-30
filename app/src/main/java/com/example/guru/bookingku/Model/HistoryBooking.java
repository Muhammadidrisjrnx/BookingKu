package com.example.guru.bookingku.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryBooking implements Parcelable {

    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("order_img")
    @Expose
    private String orderImg;
    @SerializedName("order_desc")
    @Expose
    private String orderDesc;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;

    public HistoryBooking(String order, String orderImg, String orderDesc, String date, String status) {
        this.order = order;
        this.orderImg = orderImg;
        this.orderDesc = orderDesc;
        this.date = date;
        this.status = status;
    }

    protected HistoryBooking(Parcel in) {
        order = in.readString();
        orderImg = in.readString();
        orderDesc = in.readString();
        date = in.readString();
        status = in.readString();
    }

    public static final Creator<HistoryBooking> CREATOR = new Creator<HistoryBooking>() {
        @Override
        public HistoryBooking createFromParcel(Parcel in) {
            return new HistoryBooking(in);
        }

        @Override
        public HistoryBooking[] newArray(int size) {
            return new HistoryBooking[size];
        }
    };

    @SerializedName("created_at")


    public String getOrder() {
        return order;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderImg() {
        return orderImg;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(order);
        dest.writeString(orderImg);
        dest.writeString(orderDesc);
        dest.writeString(date);
        dest.writeString(status);
    }
}
