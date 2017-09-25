package com.marverenic.options.demo

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import com.marverenic.options.Option
import com.marverenic.options.OptionFragment
import com.marverenic.options.demo.kotlin.R
import com.marverenic.options.kotlin.optionsOf
import com.marverenic.options.kotlin.selectionsOf

private const val SUB_SETTINGS_TAG = "subpanel"

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
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return optionsOf(sharedPreferences) {
                header(title = "Toggle Options")
                switchOption(key = "switch-1") {
                    title = "Switch 1"
                    enabledDescription = "Switch 1 is enabled"
                    disabledDescription = "Switch 1 is disabled"
                }
                checkOption(key = "check-1") {
                    title = "CheckBox 1"
                    enabledDescription = "CheckBox 1 is enabled"
                    disabledDescription = "CheckBox 1 is disabled"
                }


                header(title = "Dropdown options")
                intDropdownOption(key = "int-select-1") {
                    title = "Integer Dropdown"
                    defaultValue = 4
                    values = selectionsOf(
                            "One" to 1,
                            "Two" to 2,
                            "Three" to 3,
                            "Four" to 4
                    )
                }
                stringDropdownOption(key = "string-select-1") {
                    title = "String Dropdown"
                    defaultValue = "medium"
                    values = selectionsOf(
                            "Never" to "low",
                            "Sometimes" to "medium",
                            "Always" to "high"
                    )
                }


                header(title = "Dialog options")
                intDialogOption(key = "int-select-2") {
                    title = "Integer Dialog"
                    defaultValue = 0
                    values = selectionsOf(
                            "None" to 0,
                            "One" to 1,
                            "A Couple" to 2,
                            "Several" to 3,
                            "Mnay" to 4
                    )
                }
                stringDialogOption(key = "string-select-2") {
                    title = "String Dialog"
                    defaultValue = "orange"
                    values = selectionsOf(
                            "Red" to "red",
                            "Orange" to "orange",
                            "Yellow" to "yellow",
                            "Green" to "green"
                    )
                }

                header(title = "Link Options")
                activityOption(
                        title = "Device settings",
                        description = "Open settings activity",
                        intent = Intent(Settings.ACTION_SETTINGS)
                )
                fragmentOption(
                        title = "More Settings",
                        description = "Submenu",
                        fragment = SubPreferenceFragment(),
                        tag = SUB_SETTINGS_TAG
                )
            }
        }
    }

    class SubPreferenceFragment : OptionFragment() {

        override fun createOptionList(): List<Option> {
            return emptyList<Option>()
        }


    }
}
