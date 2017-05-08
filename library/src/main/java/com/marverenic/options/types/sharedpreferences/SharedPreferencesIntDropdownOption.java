package com.marverenic.options.types.sharedpreferences;

import android.content.SharedPreferences;

import com.marverenic.options.types.DropdownOption;

import java.util.List;

public class SharedPreferencesIntDropdownOption extends DropdownOption<Integer> {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private int mDefaultValue;

    private String mTitle;
    private List<Selection<Integer>> mValues;

    SharedPreferencesIntDropdownOption(SharedPreferences sharedPreferences, String key,
                                       int defaultValue, String title,
                                       List<Selection<Integer>> values) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefaultValue = defaultValue;
        mTitle = title;
        mValues = values;
    }

    @Override
    public List<Selection<Integer>> getValues() {
        return mValues;
    }

    @Override
    public Integer getValue() {
        return mSharedPreferences.getInt(mKey, mDefaultValue);
    }

    @Override
    public void onNewValue(Selection<Integer> value) {
        mSharedPreferences.edit()
                .putInt(mKey, value.getValue())
                .apply();
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public static class Builder {

        private SharedPreferences mSharedPreferences;
        private String mKey;
        private int mDefaultValue;

        private String mTitle;
        private List<Selection<Integer>> mValues;

        public Builder setSharedPreferences(SharedPreferences sharedPreferences) {
            mSharedPreferences = sharedPreferences;
            return this;
        }

        public Builder setKey(String key) {
            mKey = key;
            return this;
        }

        public Builder setDefaultValue(int defaultValue) {
            mDefaultValue = defaultValue;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setValues(List<Selection<Integer>> values) {
            mValues = values;
            return this;
        }

        public SharedPreferencesIntDropdownOption build() {
            if (mSharedPreferences == null) {
                throw new IllegalArgumentException("SharedPreferences cannot be null");
            } else if (mKey == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            return new SharedPreferencesIntDropdownOption(mSharedPreferences, mKey, mDefaultValue,
                    mTitle, mValues);
        }

    }

}