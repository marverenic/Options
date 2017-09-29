package com.marverenic.options.types;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marverenic.options.R;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class DialogListOption<T extends Parcelable> extends MultiSelectOption<T> {

    private static final String TAG = "DialogListOption";
    private static final String DIALOG_TAG = "Options.DialogListOption.DIALOG_FRAGMENT";
    private static final String EXTRA_SELECTION_RESULT = "Options.ListDialogFragment.RESULT";

    private WeakReference<TextView> mDetailTextView = new WeakReference<>(null);

    protected abstract int getOptionId();

    @Override
    protected View createView(ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_option, parent, false);

        TextView title = (TextView) root.findViewById(R.id.preference_dialog_title);
        TextView detail = (TextView) root.findViewById(R.id.preference_dialog_description);
        mDetailTextView = new WeakReference<>(detail);

        title.setText(getTitle());
        detail.setText(getSelection().getName());

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = ListDialogFragment.create(getValues(), getSelection());
                fragment.setTargetFragment(getFragment(), getOptionId());
                fragment.show(getFragment().getFragmentManager(), DIALOG_TAG);
            }
        });

        return root;
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == getOptionId() && resultCode == Activity.RESULT_OK) {
            T chosen = data.getParcelableExtra(EXTRA_SELECTION_RESULT);
            onNewValue(getSelectionByValue(chosen));

            TextView detailText = mDetailTextView.get();
            if (detailText != null) {
                detailText.setText(getSelection().getName());
            }
        }
    }

    public static final class ListDialogFragment<T extends Parcelable> extends DialogFragment {

        private static final String KEY_SELECTION_NAMES = "Options.ListDialogFragment.NAMES";
        private static final String KEY_SELECTION_VALUES = "Options.ListDialogFragment.VALUES";
        private static final String KEY_SELECTION_CURRENT = "Options.ListDialogFragment.CURRENT";

        private String[] mValueNames;
        private T[] mValues;
        private int mCurrentSelectionIndex;

        protected static <T extends Parcelable> ListDialogFragment<T> create(
                List<Selection<T>> options, Selection<T> current) {

            Bundle args = new Bundle();

            // Split value pairs to store them (since Selection does not implement Parcelable)
            String[] names = new String[options.size()];
            //noinspection unchecked
            T[] values = (T[]) new Parcelable[options.size()];

            for (int i = 0; i < options.size(); i++) {
                names[i] = options.get(i).getName();
                values[i] = options.get(i).getValue();
            }

            args.putStringArray(KEY_SELECTION_NAMES, names);
            args.putParcelableArray(KEY_SELECTION_VALUES, values);
            args.putInt(KEY_SELECTION_CURRENT, options.indexOf(current));

            ListDialogFragment<T> fragment = new ListDialogFragment<>();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            mValueNames = getArguments().getStringArray(KEY_SELECTION_NAMES);
            //noinspection unchecked
            mValues = (T[]) getArguments().getParcelableArray(KEY_SELECTION_VALUES);

            mCurrentSelectionIndex = getArguments().getInt(KEY_SELECTION_CURRENT);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getContext())
                    .setSingleChoiceItems(mValueNames, mCurrentSelectionIndex,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    postResult(mValues[which]);
                                    dismiss();
                                }
                            })
                    .setNegativeButton(R.string.action_cancel, null)
                    .create();
        }

        private void postResult(T selected) {
            if (getTargetFragment() == null) {
                Log.w(TAG, "No target fragment to send result to. Dropping selection.");
                return;
            }

            Intent data = new Intent();
            data.putExtra(EXTRA_SELECTION_RESULT, selected);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, data);
        }

    }

}
