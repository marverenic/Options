package com.marverenic.options.types;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ActivityOption extends LinkOption {

    private final Intent mIntent;

    public ActivityOption(@Nullable String title, @Nullable String description,
                          @NonNull Intent intent) {
        super(title, description);
        mIntent = intent;
    }

    @Override
    protected boolean canClick() {
        return true;
    }

    @Override
    protected void onClick() {
        getFragment().startActivity(mIntent);
    }
}
