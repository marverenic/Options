package com.marverenic.options.types.sharedpreferences.dialog;

import android.content.SharedPreferences;

import com.marverenic.options.OptionBuilder;
import com.marverenic.options.types.DialogListOption;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesStringDialogOption extends DialogListOption<ParcelableString> {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private String mDefaultValue;

    private String mTitle;
    private List<Selection<ParcelableString>> mValues;

    private SharedPreferencesStringDialogOption(SharedPreferences sharedPreferences, String key,
                                                String defaultValue, String title,
                                                List<Selection<String>> values) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefaultValue = defaultValue;
        mTitle = title;
        mValues = new ArrayList<>(values.size());

        for (Selection<String> selection : values) {
            ParcelableString parcelableValue = new ParcelableString(selection.getValue());
            mValues.add(new Selection<>(selection.getName(), parcelableValue));
        }
    }

    @Override
    public List<Selection<ParcelableString>> getValues() {
        return mValues;
    }

    @Override
    public ParcelableString getValue() {
        return new ParcelableString(mSharedPreferences.getString(mKey, mDefaultValue));
    }

    @Override
    public void onNewValue(Selection<ParcelableString> value) {
        mSharedPreferences.edit()
                .putString(mKey, value.getValue().toString())
                .apply();
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    protected int getOptionId() {
        return mKey.hashCode();
    }

    public static class Builder implements OptionBuilder<SharedPreferencesStringDialogOption> {

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

        @Override
        public SharedPreferencesStringDialogOption build() {
            if (mSharedPreferences == null) {
                throw new IllegalArgumentException("SharedPreferences cannot be null");
            } else if (mKey == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            return new SharedPreferencesStringDialogOption(mSharedPreferences, mKey,
                    mDefaultValue, mTitle, mValues);
        }

    }
}
