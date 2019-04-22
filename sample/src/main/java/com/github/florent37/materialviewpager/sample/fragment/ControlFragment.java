package com.github.florent37.materialviewpager.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ControlFragment extends Fragment {
    private static final String ARG_POSITION = "position";

//    private int position;
//    private static final int[] drawables = {R.mipmap.one, R.mipmap.two, R.mipmap.four, R.mipmap
//            .three, R.mipmap.five};

    public static ControlFragment newInstance(int position) {
        ControlFragment f = new ControlFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams
                .MATCH_PARENT);

        FrameLayout fl = new FrameLayout(getActivity());

        TextView v = new TextView(getActivity());

        fl.addView(v);
        return fl;
    }


}