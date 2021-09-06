package com.rockex6.app.foodsslots.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class PhoneSizeUtil {
    private static PhoneSizeUtil sPhoneSizeUtil = null;
    private static DisplayMetrics sDisplayMetrics;

    public static PhoneSizeUtil getInstance(Context context) {
        if (sPhoneSizeUtil == null) {
            sPhoneSizeUtil = new PhoneSizeUtil(context);
        }
        return sPhoneSizeUtil;
    }

    private PhoneSizeUtil(Context context) {
        sDisplayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(sDisplayMetrics);
    }

    public int getPhoneWidth() {
        return sDisplayMetrics.widthPixels;
    }

    public int getPhoneHeight() {
        return sDisplayMetrics.heightPixels;
    }

    public DisplayMetrics getDisplayMetrics() {
        if (sDisplayMetrics != null) {
            return sDisplayMetrics;
        } else {
            return new DisplayMetrics();
        }
    }
}
