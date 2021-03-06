package com.marverenic.options.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.marverenic.options.Option;
import com.marverenic.options.OptionFragment;
import com.marverenic.options.types.ActivityOption;
import com.marverenic.options.types.FragmentOption;
import com.marverenic.options.types.MultiSelectOption;
import com.marverenic.options.types.OptionHeader;
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption;
import com.marverenic.options.types.sharedpreferences.SharedPreferencesCheckOption;
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesIntDialogOption;
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesStringDialogOption;
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesIntDropdownOption;
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesStringDropdownOption;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SUB_SETTINGS_TAG = "subpanel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.preference_container, new PreferenceFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    public static class PreferenceFragment extends OptionFragment {

        @Override
        public List<Option> createOptionList() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

            return Arrays.asList(
                    new OptionHeader("Toggle Options"),
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
                    new OptionHeader("Dropdown Options"),
                    new SharedPreferencesIntDropdownOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("int-select-1")
                            .setTitle("Integer Dropdown")
                            .setDefaultValue(4)
                            .setValues(Arrays.asList(
                                    new MultiSelectOption.Selection<>("One", 1),
                                    new MultiSelectOption.Selection<>("Two", 2),
                                    new MultiSelectOption.Selection<>("Three", 3),
                                    new MultiSelectOption.Selection<>("Four", 4)
                            ))
                            .build(),
                    new SharedPreferencesStringDropdownOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("string-select-1")
                            .setTitle("String Dropdown")
                            .setDefaultValue("medium")
                            .setValues(Arrays.asList(
                                    new MultiSelectOption.Selection<>("Never", "low"),
                                    new MultiSelectOption.Selection<>("Sometimes", "medium"),
                                    new MultiSelectOption.Selection<>("Always", "high")
                            ))
                            .build(),
                    new OptionHeader("Dialog Options"),
                    new SharedPreferencesIntDialogOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("int-select-2")
                            .setTitle("Integer Dialog")
                            .setDefaultValue(0)
                            .setValues(Arrays.asList(
                                    new MultiSelectOption.Selection<>("None", 0),
                                    new MultiSelectOption.Selection<>("One", 1),
                                    new MultiSelectOption.Selection<>("A Couple", 2),
                                    new MultiSelectOption.Selection<>("Several", 3),
                                    new MultiSelectOption.Selection<>("Many", 4)
                            ))
                            .build(),
                    new SharedPreferencesStringDialogOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("string-select-2")
                            .setTitle("String Dialog")
                            .setDefaultValue("orange")
                            .setValues(Arrays.asList(
                                    new MultiSelectOption.Selection<>("Red", "red"),
                                    new MultiSelectOption.Selection<>("Orange", "orange"),
                                    new MultiSelectOption.Selection<>("Yellow", "yellow"),
                                    new MultiSelectOption.Selection<>("Green", "green")
                            ))
                            .build(),
                    new OptionHeader("Link Options"),
                    new ActivityOption("Device Settings", "Launch settings activity",
                            new Intent(Settings.ACTION_SETTINGS)),
                    new FragmentOption("More Settings", "Submenu",
                            new SubPreferenceFragment(), SUB_SETTINGS_TAG)
            );
        }
    }

    public static class SubPreferenceFragment extends OptionFragment {

        @Override
        public List<Option> createOptionList() {
            return Collections.emptyList();
        }
    }
}
