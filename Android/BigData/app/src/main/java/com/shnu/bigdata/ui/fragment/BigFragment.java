package com.shnu.bigdata.ui.fragment;

import android.os.Bundle;

import com.shnu.bigdata.R;
import com.shnu.bigdatasdk.base.BaseFragment;

/**
 * Created by Dainel on 2017/7/7.
 */

public class BigFragment extends BaseFragment{

    public static BigFragment newInstance(){
        Bundle args = new Bundle();
        BigFragment fragment =  new BigFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_big;
    }

    @Override
    protected void initEventAndData() {

    }
}
