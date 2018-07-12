package com.example.galax.study_20180702_chapter3_4;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleDate implements Parcelable {
    int number;
    String message;

    public SimpleDate(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public SimpleDate(Parcel src){
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public SimpleDate createFromParcel(Parcel src){
            return new SimpleDate(src);
        }

        public SimpleDate[] newArray(int size){
            return new SimpleDate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }
}
