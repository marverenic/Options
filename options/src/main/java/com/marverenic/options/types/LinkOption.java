package com.marverenic.options.types;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marverenic.options.Option;
import com.marverenic.options.R;

public abstract class LinkOption extends Option {

    private final String mTitle;
    private final String mDescription;

    public LinkOption(@Nullable String title, @Nullable String description) {
        mTitle = title;
        mDescription = description;
    }

    @Override
    protected View createView(ViewGroup parent) {
        View option = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.link_option, parent, false);

        option.<TextView>findViewById(R.id.preference_link_title).setText(mTitle);
        option.<TextView>findViewById(R.id.preference_link_body).setText(mDescription);

        if (canClick()) {
            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinkOption.this.onClick();
                }
            });
        }

        if (canLongClick()) {
            option.setLongClickable(true);
            option.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    LinkOption.this.onLongClick();
                    return true;
                }
            });
        }

        return option;
    }

    protected boolean canClick() {
        return true;
    }

    protected boolean canLongClick() {
        return false;
    }

    protected void onClick() {

    }

    protected void onLongClick() {

    }

}
