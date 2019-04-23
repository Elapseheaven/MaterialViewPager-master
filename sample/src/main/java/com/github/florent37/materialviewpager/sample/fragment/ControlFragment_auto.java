package com.github.florent37.materialviewpager.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.florent37.materialviewpager.sample.R;

public class ControlFragment_auto extends Fragment {
    private static final String ARG_POSITION = "position";

//    private int position;
//    private static final int[] drawables = {R.mipmap.one, R.mipmap.two, R.mipmap.four, R.mipmap
//            .three, R.mipmap.five};

    public static ControlFragment_auto newInstance2(int position) {
        ControlFragment_auto f = new ControlFragment_auto();
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

        View view1 =inflater.inflate(R.layout.fragment_auto,container,false);
        return view1;
    }


}

