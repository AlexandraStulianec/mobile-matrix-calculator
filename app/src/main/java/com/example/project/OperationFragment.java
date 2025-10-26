package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

public class OperationFragment extends Fragment {
    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    public OperationFragment() {
    }

    public static OperationFragment newInstance(@LayoutRes int layoutResId) {
        OperationFragment fragment = new OperationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            int layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
            // Inflate the layout for this fragment
            return inflater.inflate(layoutResId, container, false);
        } else {
            // Inflate a default layout or throw an error
            return null;
        }
    }
}
