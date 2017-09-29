package com.marverenic.options;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

public abstract class Option {

    private Fragment mFragment;

    void attach(OptionFragment fragment) {
        mFragment = fragment;
    }

    protected Fragment getFragment() {
        if (mFragment == null) {
            throw new IllegalStateException("Option has not been bound to a fragment");
        }
        return mFragment;
    }

    protected void onResult(int requestCode, int resultCode, Intent data) {
        // Optionally implement this method if you need to start fragments/activities for a result
    }

    protected abstract View createView(ViewGroup parent);

}
