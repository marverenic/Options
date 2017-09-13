package com.marverenic.options.kotlin

import com.marverenic.options.Option
import com.marverenic.options.OptionBuilder
import com.marverenic.options.types.sharedpreferences.SharedPreferenceSwitchOption

private typealias SwitchBuilder = SharedPreferenceSwitchOption.Builder

inline fun optionsOf(init: OptionCollection.() -> Unit): List<Option> {
    return OptionCollection()
            .apply(init)
            .toList()
}

inline fun switchOption(init: SwitchBuilder.() -> Unit): SwitchBuilder {
    return SharedPreferenceSwitchOption.Builder()
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
