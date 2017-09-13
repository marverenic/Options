package com.marverenic.options.types.sharedpreferences.dialog;

import android.content.SharedPreferences;

import com.marverenic.options.OptionBuilder;
import com.marverenic.options.types.DialogListOption;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesIntDialogOption extends DialogListOption<ParcelableInt> {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private int mDefaultValue;

    private String mTitle;
    private List<Selection<ParcelableInt>> mValues;

    private SharedPreferencesIntDialogOption(SharedPreferences sharedPreferences, String key,
                                                int defaultValue, String title,
                                                List<Selection<Integer>> values) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefaultValue = defaultValue;
        mTitle = title;
        mValues = new ArrayList<>(values.size());

        for (Selection<Integer> selection : values) {
            ParcelableInt parcelableValue = new ParcelableInt(selection.getValue());
            mValues.add(new Selection<>(selection.getName(), parcelableValue));
        }
    }

    @Override
    public List<Selection<ParcelableInt>> getValues() {
        return mValues;
    }

    @Override
    public ParcelableInt getValue() {
        return new ParcelableInt(mSharedPreferences.getInt(mKey, mDefaultValue));
    }

    @Override
    public void onNewValue(Selection<ParcelableInt> value) {
        mSharedPreferences.edit()
                .putInt(mKey, value.getValue().getValue())
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

    public static class Builder implements OptionBuilder<SharedPreferencesIntDialogOption> {

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

        @Override
        public SharedPreferencesIntDialogOption build() {
            if (mSharedPreferences == null) {
                throw new IllegalArgumentException("SharedPreferences cannot be null");
            } else if (mKey == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            return new SharedPreferencesIntDialogOption(mSharedPreferences, mKey,
                    mDefaultValue, mTitle, mValues);
        }

    }
}
