package com.my.listapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.listapplication.R;
import com.my.listapplication.adapter.ListViewAdapter;

/**
 * Created by YoungHyup on 2018-05-01.
 */

public final class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.list_layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ListViewAdapter());
        return rootView;
    }
}
