package com.marverenic.options.types;

import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marverenic.options.R;

import java.util.List;

public abstract class DropdownOption<T> extends MultiSelectOption<T> {

    @Override
    protected View createView(ViewGroup parent) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dropdown_option, parent, false);

        TextView title = (TextView) root.findViewById(R.id.preference_dropdown_title);
        final TextView selection = (TextView) root.findViewById(R.id.preference_dropdown_selection);
        final View anchor = root.findViewById(R.id.preference_dropdown_anchor);

        title.setText(getTitle());
        selection.setText(getSelection().getName());

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(root.getContext(), anchor);
                final List<Selection<T>> options = getValues();

                for (int i = 0; i < options.size(); i++) {
                    menu.getMenu().add(0, i, i, options.get(i).getName());
                }

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Selection<T> selected = options.get(item.getItemId());
                        selection.setText(selected.getName());
                        onNewValue(selected);
                        return true;
                    }
                });

                menu.show();
            }
        });

        return root;
    }

}
