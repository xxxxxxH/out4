package net.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TransResult implements Parcelable {

    private String src;
    private String dst;

    public TransResult() {

    }

    public TransResult(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    protected TransResult(Parcel in) {
        src = in.readString();
        dst = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(src);
        parcel.writeString(dst);
    }

    public static final Creator<TransResult> CREATOR = new Creator<TransResult>() {
        @Override
        public TransResult createFromParcel(Parcel parcel) {
            return new TransResult(parcel);
        }

        @Override
        public TransResult[] newArray(int i) {
            return new TransResult[i];
        }
    };

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
