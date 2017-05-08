package com.marverenic.options.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.marverenic.options.Option;
import com.marverenic.options.OptionFragment;
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption;
import com.marverenic.options.types.sharedpreferences.SharedPreferencesCheckOption;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.preference_container, new PreferenceFragment())
                .commit();
    }

    public static class PreferenceFragment extends OptionFragment {

        @Override
        public List<Option> createOptionList() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

            return Arrays.asList(new Option[] {
                    new SharedPreferenceSwitchOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("switch-1")
                            .setTitle("Switch 1")
                            .setEnabledDescription("Switch 1 is enabled")
                            .setDisabledDescription("Switch 1 is disabled")
                            .build(),
                    new SharedPreferencesCheckOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("check-1")
                            .setTitle("CheckBox 1")
                            .setEnabledDescription("CheckBox 1 is enabled")
                            .setDisabledDescription("CheckBox 1 is disabled")
                            .setDefaultValue(true)
                            .build(),
            });
        }
    }
}
