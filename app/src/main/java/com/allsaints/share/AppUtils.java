package com.allsaints.share;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zlove on 2018/2/1.
 */

public class AppUtils {
//    public static String URL = "https://www.tiktok.com/t/ZT8UsAhSb/";
//    public static String URL = "https://dev-sg-o-livestreamingh5.allsaints.tv/#/broadcast-log?lastpage=0&show_title=0&app_sessionid=&language=zh-CN&version=12&versionCode=800350000&dark=0&bgColor=FAFAFA&status_height=32&nav_height=0&api_version=1";
    public static String URL = "https://dev-sg-o-livestreamingh5.allsaints.tv/#/share";
//    public static boolean isAppInstalled(String packageName) {
//        PackageManager pm = ShareApplication.getInstance().getPackageManager();
//        try {
//            pm.getPackageInfo(packageName, 0);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    private boolean isAppInstalled(String packageName) {
//        Intent intent = ShareApplication.getInstance().getPackageManager().getLaunchIntentForPackage(packageName);
//        return intent != null;
//    }

    public static boolean isAppInstalled(String packageName) {
        try {
            ShareApplication.getInstance().getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断某应用在当前设备上是否安装
     *
     * @param context
     * @param packageName 该应用的完整包名
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();

            // 获取应用信息
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);

            // 获取应用名称
            CharSequence appName = packageManager.getApplicationLabel(applicationInfo);

            return appName.toString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "app";
        }
    }

}
