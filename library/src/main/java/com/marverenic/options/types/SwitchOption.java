package com.marverenic.options.types;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.marverenic.options.R;

public abstract class SwitchOption extends ToggleOption {

    @Override
    protected View createView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.switch_option, parent, false);

        TextView titleText = (TextView) view.findViewById(R.id.preference_switch_title);
        final TextView detailText = (TextView) view.findViewById(R.id.preference_switch_body);
        final Switch toggle = (Switch) view.findViewById(R.id.preference_switch_toggle);

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
