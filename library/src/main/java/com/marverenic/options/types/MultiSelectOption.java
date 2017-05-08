package com.marverenic.options.types;

import com.marverenic.options.Option;

import java.util.List;

public abstract class MultiSelectOption<T> extends Option {

    public abstract List<Selection<T>> getValues();

    public abstract T getValue();

    public Selection<T> getSelection() {
        T selected = getValue();

        for (Selection<T> selection : getValues()) {
            if (selection.getValue().equals(selected)) {
                return selection;
            }
        }

        throw new IllegalArgumentException(selected + " is not a valid value");
    }

    public abstract void onNewValue(Selection<T> value);

    public abstract String getTitle();

    public static final class Selection<T> {

        private String mName;
        private T mValue;

        public Selection(String name, T value) {
            mName = name;
            mValue = value;
        }

        public String getName() {
            return mName;
        }

        public T getValue() {
            return mValue;
        }

    }

}
