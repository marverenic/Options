# Options (Deprecated)

⚠ **Options is deprecated** as of January 26, 2020.
This library will continue to work, but will not receive support or updates.
Consider using the [Jetpack Preference library](https://developer.android.com/jetpack/androidx/releases/preference) from Google instead.
[Read more](https://andrewbailey.dev/post/2020/01/26/rebranding-marverenic).

---

Options is a library for building setting pages on Android as an alternative to the [`PreferenceFragment`](https://developer.android.com/reference/android/preference/PreferenceFragment.html) and [`PreferenceFragmentCompat`](https://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) classes provided by Google. Unlike PreferenceFragment, Options allows additional setting types to be easily declared using a straightforward interface that includes the ability to easily store your preferences outside of [`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html). Options also has a straightforward way of programmatically creating settings pages. There are also Kotlin extensions which make this even easier.

## Getting Started
Like `PreferenceFragment`, `OptionFragment` is the base class that's used to show preference screens. To show your first option page, create a class that extends from `OptionFragment` as shown below:

```java
public class SettingsFragment extends OptionFragment {

    @Override
    public List<Option> createOptionList() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        
        return Arrays.asList(
            // Specify your preferences here
            // For example:
            new OptionHeader(getString(R.string.user_favorites_header)),
            new SharedPreferencesStringDropdownOption.Builder()
                            .setSharedPreferences(prefs)
                            .setKey("favorite-color")
                            .setTitle(getString(R.string.favorite_color_question))
                            .setDefaultValue("blue")
                            .setValues(Arrays.asList(
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_red), "red"),
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_orange), "orange"),
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_yellow), "yellow"),
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_green), "green"),
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_blue), "blue"),
                                    new MultiSelectOption.Selection<>(getString(R.string.color_name_purple), "purple")
                            ))
                            .build()
        };
    }

}
```

Once attached to an Activity, the above example will create a settings fragment with a header and a dropdown preference that asks for a user's favorite color. See the [sample activity](https://github.com/marverenic/Options/blob/master/sample/src/main/java/com/marverenic/options/demo/MainActivity.java) for an example of all available Options.

### Kotlin
The above Java example can be written like this in Kotlin:

```kotlin
class SettingsFragment : OptionFragment() {
    
    override fun createOptionList(): List<Option> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext())
        return optionsOf(sharedPreferences) {
            header(getString(R.string.favorite_color_question))
            stringDropdownOption(key = "favorite-color") {
                    title = getString(R.string.favorite_color_question)
                    defaultValue = "blue"
                    values = selectionsOf(
                            getString(R.string.color_name_red) to "red",
                            getString(R.string.color_name_orange) to "orange",
                            getString(R.string.color_name_yellow) to "yellow",
                            getString(R.string.color_name_green) to "green",
                            getString(R.string.color_name_blue) to "blue",
                            getString(R.string.color_name_purple) to "purple"
                    )
                }
            }
        }
    }
    
}
```

Note that `optionsOf(...)` works best when used with SharedPreferences settings. To add custom Options, use the `+` operator as shown below:
```kotlin
optionsOf(sharedPreferences) {
    + MySuperCoolOption(...)
}
```

See the [Kotlin sample activity](https://github.com/marverenic/Options/blob/master/sample-kotlin/src/main/java/com/marverenic/options/demo/MainActivity.kt) for an example of all available Options.

## License
Options is licensed under an Apache License, Version 2.0.
