package com.marverenic.options.types.sharedpreferences.dialog;

import android.os.Parcel;
import android.os.Parcelable;

final class ParcelableString implements CharSequence, Parcelable {

    private String mString;

    ParcelableString(String string) {
        mString = string;
    }

    private ParcelableString(Parcel in) {
        mString = in.readString();
    }

    public static final Creator<ParcelableString> CREATOR = new Creator<ParcelableString>() {
        @Override
        public ParcelableString createFromParcel(Parcel in) {
            return new ParcelableString(in);
        }

        @Override
        public ParcelableString[] newArray(int size) {
            return new ParcelableString[size];
        }
    };

    @Override
    public int length() {
        return mString.length();
    }

    @Override
    public char charAt(int index) {
        return mString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return mString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return mString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParcelableString that = (ParcelableString) o;
        return mString.equals(that.mString);
    }

    @Override
    public int hashCode() {
        return mString.hashCode();
    }
}
