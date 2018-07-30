package com.example.android.thelaundrybasket;



import android.os.Parcel;
import android.os.Parcelable;

public class order implements Parcelable {
    private String mQuantity;
    private String mDate;
    private String mSlot;
    private String mService;
    private String mAddress;
    private String mKey;
    private String mDeliveyDate;
    private String mDeliveySlot;

    public order(){

    }

    public order(Parcel in){
        mQuantity = in.readString();
        mDate = in.readString();
        mSlot = in.readString();
        mService = in.readString();
        mAddress = in.readString();
        mKey = in.readString();
        mDeliveyDate = in.readString();
        mDeliveySlot = in.readString();

    }

    public order(String quantity, String date, String slot
            , String service, String address,String key
            ,String deliveryDate, String deliverySlot){
        this.mQuantity = quantity;
        this.mDate = date;
        this.mSlot = slot;
        this.mService = service;
        this.mAddress = address;
        this.mKey = key;
        this.mDeliveyDate = deliveryDate;
        this.mDeliveySlot = deliverySlot;
    }

    public String getQuantity() {
        return mQuantity;
    }
    public void setQuantity(String quantity){this.mQuantity = quantity;}

    public String getDate() {
        return mDate;
    }
    public void setDate(String date){this.mDate = date;}

    public String getSlot() {
        return mSlot;
    }
    public void setSlot(String slot){this.mSlot = slot;}

    public String getService() {
        return mService;
    }
    public void setService(String service){this.mService = service;}

    public String getAddress() {
        return mAddress;
    }
    public void setAddress(String address){this.mAddress = address;}

    public String getKey() {
        return mKey;
    }
    public void setKey(String key){this.mKey = key;}

    public String getDeliveryDate() {
        return mDeliveyDate;
    }
    public void setDeliveryDate(String deliveryDate){this.mDeliveyDate = deliveryDate;}

    public String getDeliverySlot() {
        return mDeliveySlot;
    }
    public void setDeliverySlot(String deliverySlot){this.mDeliveySlot = deliverySlot;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mDate);
        dest.writeString(mSlot);
        dest.writeString(mService);
        dest.writeString(mAddress);
        dest.writeString(mKey);
        dest.writeString(mDeliveyDate);
        dest.writeString(mDeliveySlot);

    }

    public static final Parcelable.Creator<order> CREATOR = new Parcelable.Creator<order>(){
        public order createFromParcel(Parcel in) {
            return new order(in);
        }

        public order[] newArray(int size) {
            return new order[size];
        }
    };
}

