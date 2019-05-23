package com.example.android.altomobiletest1;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RadioButton;

public class Subproducts implements Parcelable {
    private String name;
    private float extra;

    public Subproducts(Parcel in){
        name = in.readString();
        extra = in.readFloat();
    }


    public String getName(){
        return  name;
    }

    public float getExtra(){
        return extra;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(extra);
    }

    public static final Parcelable.Creator<Subproducts> CREATOR = new Parcelable.Creator<Subproducts>() {
        public Subproducts createFromParcel(Parcel in) {
            return new Subproducts(in);
        }

        public Subproducts[] newArray(int size) {
            return new Subproducts[size];
        }
    };
}
