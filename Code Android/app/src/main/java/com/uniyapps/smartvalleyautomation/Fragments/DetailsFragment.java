package com.uniyapps.smartvalleyautomation.Fragments;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniyapps.smartvalleyautomation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BottomSheetDialogFragment {


    public static final String TAG = "bottomsheet";

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog,container,false);
    }

}
