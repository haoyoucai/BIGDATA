package com.shnu.bigdatasdk.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shnu.bigdatasdk.utils.StatUtils;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.FragmentLifecycleProvider;
import com.trello.rxlifecycle.RxLifecycle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Dainel on 2017/6/29.
 */

public abstract class BaseFragment extends Fragment implements FragmentLifecycleProvider {

    protected View mView;
    protected Context mContext;
    private Unbinder mUnBinder;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    protected View findViewById(int id) {
        if (mView == null) {
            throw new ClassCastException("NullPointerException, not init getLayoutId method ");
        }
        return mView.findViewById(id);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
        }
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
            }
        }
        initEventAndData();
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
        StatUtils.onResume(getActivity());
    }

    @Override
    @CallSuper
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
        StatUtils.onPause(getActivity());
    }

    @Override
    @CallSuper
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        mUnBinder.unbind();
        super.onDestroy();
    }

    @Override
    @CallSuper
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }

    //implements Fragment lifecycleProvider
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    public Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    public <T> Observable.Transformer<T, T> bindUntilEvent(FragmentEvent event) {
        return RxLifecycle.bindUntilFragmentEvent(lifecycleSubject, event);
    }

    @Override
    public <T> Observable.Transformer<T, T> bindToLifecycle() {
        return RxLifecycle.bindFragment(lifecycleSubject);
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
