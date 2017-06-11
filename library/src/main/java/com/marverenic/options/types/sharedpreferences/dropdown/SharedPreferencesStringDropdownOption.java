package com.marverenic.options.types.sharedpreferences.dropdown;

import android.content.SharedPreferences;

import com.marverenic.options.types.DropdownOption;

import java.util.List;

public class SharedPreferencesStringDropdownOption extends DropdownOption<String> {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private String mDefaultValue;

    private String mTitle;
    private List<Selection<String>> mValues;

    SharedPreferencesStringDropdownOption(SharedPreferences sharedPreferences, String key,
                                          String defaultValue, String title,
                                          List<Selection<String>> values) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefaultValue = defaultValue;
        mTitle = title;
        mValues = values;
    }

    @Override
    public List<Selection<String>> getValues() {
        return mValues;
    }

    @Override
    public String getValue() {
        return mSharedPreferences.getString(mKey, mDefaultValue);
    }

    @Override
    public void onNewValue(Selection<String> value) {
        mSharedPreferences.edit()
                .putString(mKey, value.getValue())
                .apply();
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public static class Builder {

        private SharedPreferences mSharedPreferences;
        private String mKey;
        private String mDefaultValue;

        private String mTitle;
        private List<Selection<String>> mValues;

        public Builder setSharedPreferences(SharedPreferences sharedPreferences) {
            mSharedPreferences = sharedPreferences;
            return this;
        }

        public Builder setKey(String key) {
            mKey = key;
            return this;
        }

        public Builder setDefaultValue(String defaultValue) {
            mDefaultValue = defaultValue;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setValues(List<Selection<String>> values) {
            mValues = values;
            return this;
        }

        public SharedPreferencesStringDropdownOption build() {
            if (mSharedPreferences == null) {
                throw new IllegalArgumentException("SharedPreferences cannot be null");
            } else if (mKey == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            return new SharedPreferencesStringDropdownOption(mSharedPreferences, mKey,
                    mDefaultValue, mTitle, mValues);
        }

    }

}