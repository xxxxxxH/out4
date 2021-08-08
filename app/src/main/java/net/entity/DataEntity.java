package net.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DataEntity implements Parcelable {

    private String from;
    private String to;
    private ArrayList<TransResult> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<TransResult> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(ArrayList<TransResult> trans_result) {
        this.trans_result = trans_result;
    }

    public DataEntity() {
    }


    public DataEntity(String from, String to, ArrayList<TransResult> trans_result) {
        this.from = from;
        this.to = to;
        this.trans_result = trans_result;
    }

    protected DataEntity(Parcel in) {
        from = in.readString();
        to = in.readString();
        trans_result = in.readArrayList(TransResult.class.getClassLoader());
    }

    public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
        @Override
        public DataEntity createFromParcel(Parcel parcel) {
            return new DataEntity(parcel);
        }

        @Override
        public DataEntity[] newArray(int i) {
            return new DataEntity[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(from);
        parcel.writeString(to);
        parcel.writeList(trans_result);
    }
}
