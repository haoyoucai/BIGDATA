package com.shnu.bigdata.ui.fragment;

import android.os.Bundle;

import com.shnu.bigdata.R;
import com.shnu.bigdatasdk.base.BaseFragment;


/**
 * Created by Dainel on 2017/7/7.
 */

public class VideoFragment extends BaseFragment {

    public static VideoFragment newInstance(){
        Bundle args = new Bundle();
        VideoFragment fragment =  new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initEventAndData() {

    }
}
