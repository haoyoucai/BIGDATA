package com.shnu.bigdatasdk.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.shnu.bigdatasdk.utils.StatUtils;
import com.shnu.bigdatasdk.utils.ToolbarHelper;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.RxLifecycle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subjects.BehaviorSubject;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;

/**
 * Created by Dainel on 2017/6/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityLifecycleProvider {


    private Unbinder mUnBinder;
    protected Activity mContext;
    protected ToolbarHelper toolbarHelper;


    public abstract int getLayoutId();

    public abstract void initUI();

    public abstract void initEventData();

    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }
    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        StatusBarUtil.setTransparent(this);
        setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        setContentView(getLayoutId());
        toolbarHelper = new ToolbarHelper(getWindow().getDecorView());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initUI();
        initEventData();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
        StatUtils.onResume(this);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
        StatUtils.onPause(this);
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        mUnBinder.unbind();
    }







    //implements activity life cycle provider

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    public <T> Observable.Transformer<T, T> bindUntilEvent(ActivityEvent event) {
        return RxLifecycle.bindUntilActivityEvent(lifecycleSubject, event);
    }

    @Override
    public <T> Observable.Transformer<T, T> bindToLifecycle() {
        return RxLifecycle.bindActivity(lifecycleSubject);
    }
}
