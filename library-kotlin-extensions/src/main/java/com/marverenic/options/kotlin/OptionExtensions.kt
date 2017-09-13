package com.marverenic.options.kotlin

import com.marverenic.options.Option
import com.marverenic.options.OptionBuilder
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption
import com.marverenic.options.types.sharedpreferences.SharedPreferencesCheckOption

private typealias SwitchBuilder = SharedPreferenceSwitchOption.Builder
private typealias CheckBuilder = SharedPreferencesCheckOption.Builder

inline fun optionsOf(init: OptionCollection.() -> Unit): List<Option> {
    return OptionCollection()
            .apply(init)
            .toList()
}

inline fun switchOption(init: SwitchBuilder.() -> Unit): SwitchBuilder {
    return SharedPreferenceSwitchOption.Builder()
            .apply(init)
}

inline fun checkOption(init: CheckBuilder.() -> Unit): CheckBuilder {
    return SharedPreferencesCheckOption.Builder()
            .apply(init)
}

class OptionCollection(private val options: MutableList<Option> = mutableListOf()) {

    operator fun OptionBuilder<*>.unaryPlus() {
        options += this.build()
    }

    operator fun Option.unaryPlus() {
        options += this
    }

    fun toList() = options.toList()

}
