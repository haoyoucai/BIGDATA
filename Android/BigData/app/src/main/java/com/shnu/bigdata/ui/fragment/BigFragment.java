package com.shnu.bigdata.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.shnu.bigdata.R;
import com.shnu.bigdatasdk.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Dainel on 2017/7/7.
 */

public class BigFragment extends BaseFragment{
    @BindView(R.id.tv_big)
    TextView tvBig;

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

        RxView.clicks(tvBig)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(aVoid -> showShare());

    }
    private void showShare() {
        Toast.makeText(getActivity(),"123123",Toast.LENGTH_LONG).show();
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }




}
