package com.marverenic.options.types;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marverenic.options.Option;
import com.marverenic.options.R;

public class OptionHeader extends Option {

    private String mTitle;

    public OptionHeader(String title) {
        mTitle = title;
    }

    @Override
    protected View createView(ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option_header, parent, false);

        TextView headerText = (TextView) root.findViewById(R.id.preference_header);
        headerText.setText(mTitle);

        return root;
    }

}
