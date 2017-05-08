package com.marverenic.options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marverenic.adapter.HeterogeneousAdapter;

import java.util.List;

public abstract class OptionFragment extends Fragment {

    public abstract List<Option> createOptionList();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.options_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.options_fragment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(createAdapter());

        return root;
    }

    private HeterogeneousAdapter createAdapter() {
        HeterogeneousAdapter adapter = new HeterogeneousAdapter();

        for (Option option : createOptionList()) {
            adapter.addSection(new OptionSingletonSection(option));
        }

        return adapter;
    }

}
