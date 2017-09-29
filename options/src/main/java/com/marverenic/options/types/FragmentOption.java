package com.marverenic.options.types;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

public class FragmentOption extends LinkOption {

    private final String mFragmentTag;
    private final Fragment mFragment;

    public FragmentOption(@Nullable String title, @Nullable String description,
                          @NonNull Fragment fragment, @NonNull String fragmentTag) {
        super(title, description);
        mFragment = fragment;
        mFragmentTag = fragmentTag;
    }

    @Override
    protected boolean canClick() {
        return true;
    }

    @Override
    protected void onClick() {
        if (getFragment().getView() == null) {
            throw new IllegalStateException("Parent fragment has no view");
        }

        ViewGroup parentContainer = (ViewGroup) getFragment().getView().getParent();
        getFragment().getFragmentManager().beginTransaction()
                .replace(parentContainer.getId(), mFragment, mFragmentTag)
                .addToBackStack(mFragmentTag)
                .commit();
    }
}
