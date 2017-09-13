package com.marverenic.options.kotlin

import com.marverenic.options.Option
import com.marverenic.options.OptionBuilder
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


inline fun optionsOf(init: OptionCollection.() -> Unit): List<Option> {
    return OptionCollection()
            .apply(init)
            .toList()
}

class OptionCollection(private val options: MutableList<Option> = mutableListOf()) {

    inline fun switchOption(init: SwitchBuilder.() -> Unit) {
        + SharedPreferenceSwitchOption.Builder().apply(init)
    }

    inline fun checkOption(init: CheckBuilder.() -> Unit) {
        + SharedPreferencesCheckOption.Builder().apply(init)
    }

    inline fun stringDropdownOption(init: StringDropdownBuilder.() -> Unit) {
        + SharedPreferencesStringDropdownOption.Builder().apply(init)
    }

    inline fun intDropdownOption(init: IntDropdownBuilder.() -> Unit) {
        + SharedPreferencesIntDropdownOption.Builder().apply(init)
    }

    inline fun intDialogOption(init: IntDialogBuilder.() -> Unit) {
        + SharedPreferencesIntDialogOption.Builder().apply(init)
    }

    inline fun stringDialogOption(init: StringDialogBuilder.() -> Unit) {
        + SharedPreferencesStringDialogOption.Builder().apply(init)
    }

    operator fun OptionBuilder<*>.unaryPlus() {
        options += this.build()
    }

    operator fun Option.unaryPlus() {
        options += this
    }

    fun toList() = options.toList()

}
