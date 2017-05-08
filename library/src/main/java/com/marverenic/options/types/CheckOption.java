package com.marverenic.options.types;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.marverenic.options.R;

public abstract class CheckOption extends ToggleOption {

    @Override
    protected View createView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.check_option, parent, false);

        TextView titleText = (TextView) view.findViewById(R.id.preference_check_title);
        final TextView detailText = (TextView) view.findViewById(R.id.preference_check_body);
        final CheckBox toggle = (CheckBox) view.findViewById(R.id.preference_check_toggle);

        titleText.setText(getTitle());
        detailText.setText(getDescription(isToggled()));
        toggle.setChecked(isToggled());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle.setChecked(!isToggled());
            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onSwitchToggle(isChecked);
                detailText.setText(getDescription(isChecked));
            }
        });

        return view;
    }

}
