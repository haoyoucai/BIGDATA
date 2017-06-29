package com.shnu.bigdatasdk.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Dainel on 2017/6/29.
 */

public class ToastUtil {
    private static Toast sToastInstance;

    private static Toast createToast(final Context context, String text, int duration) {
        if (null == sToastInstance) {
            WeakReference<Context> reference = new WeakReference<>(context);
            sToastInstance = Toast.makeText(reference.get(), text, duration);
        }

        return sToastInstance;
    }

    public static void showToast(final Context context, String text, int duration) {
        if (sToastInstance != null) {
            sToastInstance.setText(text);
        } else {
            sToastInstance = createToast(context, text, duration);
        }

        sToastInstance.show();
    }

    public static void showToast(final Context context, String text) {
        if (sToastInstance != null) {
            sToastInstance.setText(text);
        } else {
            sToastInstance = createToast(context, text, Toast.LENGTH_SHORT);
        }

        sToastInstance.show();
    }


    public static void showToast(final Context context, String text, int x, int y) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }
}