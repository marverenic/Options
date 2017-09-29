package com.marverenic.options.types;

import com.marverenic.options.Option;

public abstract class ToggleOption extends Option {

    public abstract boolean isToggled();

    protected abstract void onSwitchToggle(boolean enabled);

    protected abstract String getTitle();

    protected abstract String getDescription(boolean enabled);

}
