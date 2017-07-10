package com.shnu.bigdata.ui.fragment;

import android.os.Bundle;

import com.shnu.bigdata.R;
import com.shnu.bigdatasdk.base.BaseFragment;

/**
 * Created by Dainel on 2017/7/7.
 */

public class MatchFragment extends BaseFragment {

    public static MatchFragment newInstance(){
        Bundle args = new Bundle();
        MatchFragment fragment =  new MatchFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match;
    }

    @Override
    protected void initEventAndData() {

    }
}
