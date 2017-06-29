package com.shnu.bigdatasdk;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.shnu.bigdatasdk.utils.LogUtil;
import com.shnu.bigdatasdk.utils.StatUtils;

/**
 * Created by Dainel on 2017/6/23.
 */

public abstract class BaseApplication extends Application {
    public static BaseApplication app;
    public static BaseApplication getInstance() {
        return app;
    }
    public static boolean isDebug = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        new LogUtil.Builder()
                .setLogSwitch(true)// 设置log总开关，默认开
                .setGlobalTag("BigData")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(LogUtil.V);// log过滤器，和logcat过滤器同理，默认Verbose


        //umeng
        new StatUtils.Builder()
//                .setDebug(true)
                .setProbability(9)
                .setForce(true)
                .setWifiOnly(true);


        initSDK();

    }
    public abstract void initSDK();
}
