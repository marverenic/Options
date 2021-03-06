package com.marverenic.options.kotlin

import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import com.marverenic.options.Option
import com.marverenic.options.OptionBuilder
import com.marverenic.options.types.ActivityOption
import com.marverenic.options.types.FragmentOption
import com.marverenic.options.types.MultiSelectOption
import com.marverenic.options.types.OptionHeader
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption
import com.marverenic.options.types.sharedpreferences.SharedPreferencesCheckOption
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesIntDialogOption
import com.marverenic.options.types.sharedpreferences.dialog.SharedPreferencesStringDialogOption
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesIntDropdownOption
import com.marverenic.options.types.sharedpreferences.dropdown.SharedPreferencesStringDropdownOption

private typealias SwitchBuilder = SharedPreferenceSwitchOption.Builder
private typealias CheckBuilder = SharedPreferencesCheckOption.Builder
private typealias StringDropdownBuilder = SharedPreferencesStringDropdownOption.Builder
private typealias IntDropdownBuilder = SharedPreferencesIntDropdownOption.Builder
private typealias StringDialogBuilder = SharedPreferencesStringDialogOption.Builder
private typealias IntDialogBuilder = SharedPreferencesIntDialogOption.Builder

inline fun optionsOf(sharedPreferences: SharedPreferences,
                     init: OptionCollection.() -> Unit): List<Option> {
    return OptionCollection(sharedPreferences)
            .apply(init)
            .toList()
}

fun <T> selectionsOf(vararg selections: Pair<String, T>) =
        selections.map { (key, value) ->
            MultiSelectOption.Selection(key, value)
        }

class OptionCollection(
        val sharedPrefs: SharedPreferences,
        private val options: MutableList<Option> = mutableListOf()) {

    inline fun switchOption(key: String, init: SwitchBuilder.() -> Unit) {
        + SharedPreferenceSwitchOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    inline fun checkOption(key: String, init: CheckBuilder.() -> Unit) {
        + SharedPreferencesCheckOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    inline fun stringDropdownOption(key: String, init: StringDropdownBuilder.() -> Unit) {
        + SharedPreferencesStringDropdownOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    inline fun intDropdownOption(key: String, init: IntDropdownBuilder.() -> Unit) {
        + SharedPreferencesIntDropdownOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    inline fun intDialogOption(key: String, init: IntDialogBuilder.() -> Unit) {
        + SharedPreferencesIntDialogOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    inline fun stringDialogOption(key: String, init: StringDialogBuilder.() -> Unit) {
        + SharedPreferencesStringDialogOption.Builder().apply {
            this.sharedPreferences = sharedPrefs
            this.key = key
            init()
        }
    }

    fun activityOption(title: String? = null, description: String? = null, intent: Intent) {
        + ActivityOption(title, description, intent)
    }

    fun fragmentOption(title: String? = null, description: String? = null,
                       fragment: Fragment, tag: String) {
        + FragmentOption(title, description, fragment, tag)
    }

    fun header(title: String) {
        + OptionHeader(title)
    }

    operator fun OptionBuilder<*>.unaryPlus() {
        options += this.build()
    }

    operator fun Option.unaryPlus() {
        options += this
    }

    fun toList() = options.toList()

}
