package com.marverenic.options;

import android.view.View;
import android.view.ViewGroup;

import com.marverenic.adapter.EnhancedViewHolder;
import com.marverenic.adapter.HeterogeneousAdapter;

class OptionSingletonSection extends HeterogeneousAdapter.SingletonSection<Option> {

    private Option mOption;
    private View mOptionView;

    OptionSingletonSection(Option option) {
        super(option);
        mOption = option;
    }

    @Override
    public EnhancedViewHolder<Option> createViewHolder(HeterogeneousAdapter adapter,
                                                       ViewGroup parent) {
        if (mOptionView == null) {
            mOptionView = mOption.createView(parent);
        }
        return new ViewHolder(mOptionView);
    }

    private class ViewHolder extends EnhancedViewHolder<Option> {

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onUpdate(Option item, int position) {
            // Do nothing. This view will never need to be updated when it's
            // reattached to a RecyclerView.
        }
    }

}
