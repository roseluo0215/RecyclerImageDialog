package com.lc.recycleimagedialog;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

public class DisplayUtils {
  public static int px2dip(float pxValue, Context context) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static int dip2px(float dipValue, Context context) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  public static int getScreenWidth(final Context context) {
    final DisplayMetrics metrics = getDisplayMetrics(context);
    return metrics.widthPixels;
  }

  public static int getScreenHeight(final Context context) {
    final DisplayMetrics metrics = getDisplayMetrics(context);
    return metrics.heightPixels;
  }

  public static boolean isPortrait(Context context) {
    return isPortrait(context.getResources().getConfiguration());
  }

  public static boolean isPortrait(Configuration newConfig) {
    if (newConfig != null && newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      return false;
    }
    return true;
  }

  public static DisplayMetrics getDisplayMetrics(final Context context) {
    return context.getResources().getDisplayMetrics();
  }
}
