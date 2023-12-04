package com.allsaints.share

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager


/**
 *  author : xuwenbin
 *  date : 2023/11/21 18:14
 *  description :
 */
object AppUitls {
    @JvmStatic
    fun isAppInstalled(packageName: String): Boolean {
        return try {
            ShareApplication.getInstance().packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    @JvmStatic
    fun isAppInstall(context: Context?, packageName: String): Boolean {
        return try {
            context?.packageManager?.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun getActivity(context: Context): Activity? {
        var ctx = context
        while (ctx is ContextWrapper) {
            if (ctx is Activity) {
                return ctx
            }
            ctx = ctx.baseContext
        }
        return null
    }
}