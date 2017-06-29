package com.shnu.bigdatasdk.utils;

import android.content.Context;

import com.shnu.bigdatasdk.BaseApplication;
import com.shnu.bigdatasdk.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;
import java.util.Random;

/**
 * Created by Dainel on 2017/6/29.
 */

public class StatUtils {


    private static boolean SENDWIFIONLY = true;//仅wifi上传log
    private static boolean FORCE = true;//强制上传
    private static int SNEDPROBABILITY = 9;//0~9;
    public static void init() {
    }

    public static void onResume(Context activity) {
        MobclickAgent.onResume(activity);
    }

    public static void onPause(Context activity) {
        MobclickAgent.onPause(activity);
    }

    public static void onStop(BaseActivity baseActivity) {

    }

    public static void onEvent(Context context, String eventId) {
        if (getProbability() >= SNEDPROBABILITY) {
            if (SystemUtil.isWifiConnected() && SENDWIFIONLY) {
                if (BaseApplication.isDebug) {
                    MobclickAgent.onEvent(context, "debug_"+eventId);
                }else{
                    MobclickAgent.onEvent(context, eventId);
                }
            } else if (SystemUtil.isMobileNetworkConnected() && FORCE) {
                if (BaseApplication.isDebug) {
                    MobclickAgent.onEvent(context, "debug_"+eventId);
                }else{
                    MobclickAgent.onEvent(context, eventId);
                }
            }
        }
    }

    public static void onProfileSignIn(String provider,String userId){
        MobclickAgent.onProfileSignIn(provider,userId);
    }

    public static void onProfileSignOff(){
        MobclickAgent.onProfileSignOff();
    }

    public static void onMutiEvent(Context context, String eventId, Map<String, String> map) {
        if (getProbability() >= SNEDPROBABILITY) {
            if (SystemUtil.isWifiConnected() && SENDWIFIONLY) {
                if (BaseApplication.isDebug) {
                    MobclickAgent.onEvent(context, "debug_"+eventId, map);
                }else{
                    MobclickAgent.onEvent(context, eventId, map);
                }

            } else if (SystemUtil.isMobileNetworkConnected() && FORCE) {
                if (BaseApplication.isDebug) {
                    MobclickAgent.onEvent(context, "debug_"+eventId, map);
                }else{
                    MobclickAgent.onEvent(context, eventId, map);
                }
            }
        }
    }

    private static int getProbability() {
        Random rn = new Random();
        return rn.nextInt(10);
    }


    public static void onPageStart(String mPageName) {
        MobclickAgent.onPageStart(mPageName);
    }

    public static void onPageEnd(String mPageName) {
        MobclickAgent.onPageEnd(mPageName);
    }

    public static class Builder {

        public Builder() {
        }

        public Builder setWifiOnly(boolean wifiOnly) {
            SENDWIFIONLY = wifiOnly;
            return this;
        }

        public Builder setForce(boolean isForce) {
            FORCE = isForce;
            return this;
        }

        public Builder setProbability(int value) {
            if (value >= 9) {
                value = 9;
            } else if (value <= 0) {
                value = 0;
            }
            SNEDPROBABILITY = value;
            return this;
        }

        public Builder setDebug(boolean debug){
            MobclickAgent.setDebugMode(debug);
            return this;
        }
    }

}
