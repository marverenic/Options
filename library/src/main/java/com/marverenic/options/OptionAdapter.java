package com.marverenic.options;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private List<Option> mOptions;

    OptionAdapter(List<Option> options) {
        mOptions = options;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int itemViewType) {
        // We use itemViewTypes as indices so that each Option is guaranteed to have a non-recycled
        // item view. This reduces performance, but should be negligible considering the size of
        // a typical settings page.
        View itemView = mOptions.get(itemViewType).createView(parent);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder optionViewHolder, int position) {
        // Do nothing. We're never going to recycle item views, so we never have to update data.
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    static class OptionViewHolder extends ViewHolder {

        OptionViewHolder(View itemView) {
            super(itemView);
        }
    }

}
