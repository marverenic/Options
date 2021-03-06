package com.marverenic.options.types.sharedpreferences;

import android.content.SharedPreferences;

import com.marverenic.options.OptionBuilder;
import com.marverenic.options.types.CheckOption;

public class SharedPreferencesCheckOption extends CheckOption {

    private SharedPreferences mSharedPreferences;
    private String mKey;
    private boolean mDefaultValue;

    private String mTitle;
    private String mDescriptionOn;
    private String mDescriptionOff;

    private SharedPreferencesCheckOption(SharedPreferences sharedPreferences,
                                         String key, boolean defaultValue,
                                         String title, String descriptionOn, String descriptionOff) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefaultValue = defaultValue;

        mTitle = title;
        mDescriptionOn = descriptionOn;
        mDescriptionOff = descriptionOff;
    }

    @Override
    public boolean isToggled() {
        return mSharedPreferences.getBoolean(mKey, mDefaultValue);
    }

    @Override
    protected void onSwitchToggle(boolean enabled) {
        mSharedPreferences.edit()
                .putBoolean(mKey, enabled)
                .apply();
    }

    @Override
    protected String getTitle() {
        return mTitle;
    }

    @Override
    protected String getDescription(boolean enabled) {
        if (enabled) {
            return mDescriptionOn;
        } else {
            return mDescriptionOff;
        }
    }

    public static class Builder implements OptionBuilder<SharedPreferencesCheckOption> {

        private SharedPreferences mSharedPreferences;
        private String mKey;
        private boolean mDefaultValue;

        private String mTitle;
        private String mDescriptionOn;
        private String mDescriptionOff;

        public Builder() {}

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

        public Builder setDefaultValue(boolean defaultValue) {
            mDefaultValue = defaultValue;
            return this;
        }

        public boolean getDefaultValue() {
            return mDefaultValue;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public String getTitle() {
            return mTitle;
        }

        public Builder setDescription(String description) {
            setEnabledDescription(description);
            setDisabledDescription(description);
            return this;
        }

        public String getDescription() {
            return mDescriptionOn;
        }

        public Builder setEnabledDescription(String description) {
            mDescriptionOn = description;
            return this;
        }

        public String getEnabledDescription() {
            return mDescriptionOn;
        }

        public Builder setDisabledDescription(String description) {
            mDescriptionOff = description;
            return this;
        }

        public String getDisabledDescription() {
            return mDescriptionOff;
        }

        @Override
        public SharedPreferencesCheckOption build() {
            if (mSharedPreferences == null) {
                throw new IllegalArgumentException("SharedPreferences cannot be null");
            } else if (mKey == null) {
                throw new IllegalArgumentException("Preference key cannot be null");
            }

            return new SharedPreferencesCheckOption(mSharedPreferences, mKey, mDefaultValue,
                    mTitle, mDescriptionOn, mDescriptionOff);
        }

    }
}
