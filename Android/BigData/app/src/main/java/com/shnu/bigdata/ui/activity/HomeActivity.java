package com.shnu.bigdata.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.TimeUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.shnu.bigdata.R;
import com.shnu.bigdata.ui.fragment.BigFragment;
import com.shnu.bigdata.ui.fragment.MatchFragment;
import com.shnu.bigdata.ui.fragment.VideoFragment;
import com.shnu.bigdatasdk.base.BaseActivity;
import com.shnu.bigdatasdk.utils.StatUtils;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dainel on 2017/6/23.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.ll_big)
    LinearLayout llBig;
    @BindView(R.id.ll_match)
    LinearLayout llMatch;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;

    @BindView(R.id.container)
    FrameLayout container;


    private int pagerId;
    private List<Fragment> fragmentList;



    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI() {

        fragmentList = new ArrayList<>();
        fragmentList.add(BigFragment.newInstance());
        fragmentList.add(MatchFragment.newInstance());
        fragmentList.add(VideoFragment.newInstance());

    }

    @Override
    public void initEventData() {
        ButterKnife.bind(this);
        showOrHideFragment(0); //默认使用第一个页面

        RxView.clicks(llBig)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(aVoid -> showOrHideFragment(0));

        RxView.clicks(llMatch)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(aVoid -> showOrHideFragment(1));

        RxView.clicks(llVideo)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(aVoid -> showOrHideFragment(2));
    }

    private void showOrHideFragment(int position) {
        pagerId = position;
//        switch (position) {
//            case 0:
//                toolbarHelper.setToolbarVisible(View.GONE);
//                toolbarHelper.setTitle("精品推荐");
//                toolbarHelper.setLeftIconVisible(View.GONE);
//                toolbarHelper.setRightIconVisible(View.GONE);
//                toolbarHelper.setSecondRightVisible(View.GONE);
//                break;
//            case 1:
////                toolbarHelper.setLeftIconVisible(View.VISIBLE);
////                toolbarHelper.setRightIconVisible(View.VISIBLE);
////                toolbarHelper.setSecondRightVisible(View.VISIBLE);
//                break;
//        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragmentList.get(position);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.container, fragment).commitAllowingStateLoss();
        } else {
            fragmentTransaction.show(fragment);
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i != position) {
                    fragmentTransaction.hide(fragmentList.get(i));
                }
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
