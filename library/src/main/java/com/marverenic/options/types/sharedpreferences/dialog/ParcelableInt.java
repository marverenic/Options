package com.marverenic.options.types.sharedpreferences.dialog;

import android.os.Parcel;
import android.os.Parcelable;

final class ParcelableInt implements Parcelable {

    private int mInt;

    ParcelableInt(int integer) {
        mInt = integer;
    }

    private ParcelableInt(Parcel in) {
        mInt = in.readInt();
    }

    int getValue() {
        return mInt;
    }

    public static final Creator<ParcelableInt> CREATOR = new Creator<ParcelableInt>() {
        @Override
        public ParcelableInt createFromParcel(Parcel in) {
            return new ParcelableInt(in);
        }

        @Override
        public ParcelableInt[] newArray(int size) {
            return new ParcelableInt[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mInt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParcelableInt that = (ParcelableInt) o;

        return mInt == that.mInt;

    }

    @Override
    public int hashCode() {
        return mInt;
    }
}
