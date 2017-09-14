package com.marverenic.options.demo

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.marverenic.options.Option
import com.marverenic.options.OptionFragment
import com.marverenic.options.demo.kotlin.R
import com.marverenic.options.types.MultiSelectOption
import com.marverenic.options.types.OptionHeader
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption
import com.marverenic.options.types.sharedpreferences.SharedPreferencesCheckOption
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesIntDialogOption
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesStringDialogOption
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesIntDropdownOption
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesStringDropdownOption
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.preference_container, PreferenceFragment())
                .commit()
    }

    class PreferenceFragment : OptionFragment() {

        override fun createOptionList(): List<Option> {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)

            return Arrays.asList(
                    OptionHeader("Toggle Options"),
                    SharedPreferenceSwitchOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("switch-1")
                            .setTitle("Switch 1")
                            .setEnabledDescription("Switch 1 is enabled")
                            .setDisabledDescription("Switch 1 is disabled")
                            .build(),
                    SharedPreferencesCheckOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("check-1")
                            .setTitle("CheckBox 1")
                            .setEnabledDescription("CheckBox 1 is enabled")
                            .setDisabledDescription("CheckBox 1 is disabled")
                            .setDefaultValue(true)
                            .build(),
                    OptionHeader("Dropdown Options"),
                    SharedPreferencesIntDropdownOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("int-select-1")
                            .setTitle("Integer Dropdown")
                            .setDefaultValue(4)
                            .setValues(Arrays.asList(
                                    MultiSelectOption.Selection("One", 1),
                                    MultiSelectOption.Selection("Two", 2),
                                    MultiSelectOption.Selection("Three", 3),
                                    MultiSelectOption.Selection("Four", 4)
                            ))
                            .build(),
                    SharedPreferencesStringDropdownOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("string-select-1")
                            .setTitle("String Dropdown")
                            .setDefaultValue("medium")
                            .setValues(Arrays.asList(
                                    MultiSelectOption.Selection("Never", "low"),
                                    MultiSelectOption.Selection("Sometimes", "medium"),
                                    MultiSelectOption.Selection("Always", "high")
                            ))
                            .build(),
                    OptionHeader("Dialog Options"),
                    SharedPreferencesIntDialogOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("int-select-2")
                            .setTitle("Integer Dialog")
                            .setDefaultValue(0)
                            .setValues(Arrays.asList(
                                    MultiSelectOption.Selection("None", 0),
                                    MultiSelectOption.Selection("One", 1),
                                    MultiSelectOption.Selection("A Couple", 2),
                                    MultiSelectOption.Selection("Several", 3),
                                    MultiSelectOption.Selection("Many", 4)
                            ))
                            .build(),
                    SharedPreferencesStringDialogOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("string-select-2")
                            .setTitle("String Dialog")
                            .setDefaultValue("orange")
                            .setValues(Arrays.asList(
                                    MultiSelectOption.Selection("Red", "red"),
                                    MultiSelectOption.Selection("Orange", "orange"),
                                    MultiSelectOption.Selection("Yellow", "yellow"),
                                    MultiSelectOption.Selection("Green", "green")
                            ))
                            .build()
            )
        }
    }
}
