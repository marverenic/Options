package com.marverenic.options.types.sharedpreferences.dropdown;

import android.content.SharedPreferences;

import com.marverenic.options.OptionBuilder;
import com.marverenic.options.types.DropdownOption;

import java.util.List;

public class SharedPreferencesStringDropdownOption extends DropdownOption<String> {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private String mDefaultValue;

    private String mTitle;
    private List<Selection<String>> mValues;

    private SharedPreferencesStringDropdownOption(SharedPreferences sharedPreferences, String key,
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

    public static class Builder implements OptionBuilder<SharedPreferencesStringDropdownOption> {

        private SharedPreferences mSharedPreferences;
        private String mKey;
        private String mDefaultValue;

        private String mTitle;
        private List<Selection<String>> mValues;

        public Builder setSharedPreferences(SharedPreferences sharedPreferences) {
            mSharedPreferences = sharedPreferences;
            return this;
        }

        public SharedPreferences getSharedPreferences() {
            return mSharedPreferences;
        }

        public Builder setKey(String key) {
            mKey = key;
            return this;
        }

        public String getKey() {
            return mKey;
        }

        public Builder setDefaultValue(String defaultValue) {
            mDefaultValue = defaultValue;
            return this;
        }

        public String getDefaultValue() {
            return mDefaultValue;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public String getTitle() {
            return mTitle;
        }

        public Builder setValues(List<Selection<String>> values) {
            mValues = values;
            return this;
        }

        public List<Selection<String>> getValues() {
            return mValues;
        }

        @Override
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