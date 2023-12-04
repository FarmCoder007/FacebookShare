//package com.allsaints.share
//
//import java.util.TreeSet
//
///**
// *  author : xuwenbin
// *  date : 2023/11/22 20:22
// *  description :
// */
//class FacebookShareWrapper<Activity> {
//
//    private val FACEBOOK_PACKAGE_NAME = "com.facebook.katana"
//
//    private val TAG = "FacebookShareWrapper"
//
//    fun startFacebookShareChecking(activity: Activity, shareAction: (() -> Unit)?): Disposable? {
//
//        //这一行是从NativeProtocol中fetchAllAvailableProtocolVersionsForAppInfo方法复制过来的代码
//        val versionSet = FacebookProtocolVersionHelper.fetchAllAvailableProtocolVersionsForAppInfo(activity, FACEBOOK_PACKAGE_NAME)
//
//        val am = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//
//        var isFacebookStarted = false
//
//        //facebook未安装，或者facebook进程无法启动
//        if (versionSet.size <= 0) {
//            val intent = activity.packageManager.getLaunchIntentForPackage(FACEBOOK_PACKAGE_NAME)
//            if (intent != null) {
//                //Facebook进程无法启动，启动它吧！！
//                try {
//                    isFacebookStarted = true
//                    activity.startActivity(intent)
//                } catch (e: Exception) {
//                    isFacebookStarted = false
//                    Log.e(TAG, " start activity failed intent = $intent, error msg = ${e.message}", e)
//                }
//            }
//        }
//
//        //facebook没有被启动，走正常分享流程就行
//        if (!isFacebookStarted) {
//            hookNativeProtocalFacebookAppInfoList(versionSet, FACEBOOK_PACKAGE_NAME)
//            shareAction?.invoke()
//            return null
//        }
//
//        //检查facebook启动情况的标志
//        var intervalCheckFacebookFlag = false
//
//        //一直轮询，直到可以取到facebook的ProtocolVersion数据
//        return Flowable.intervalRange(0, 20, 0, 20, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
//            .subscribe {
//                if (!intervalCheckFacebookFlag) {
//                    val versionSet = FacebookProtocolVersionHelper.fetchAllAvailableProtocolVersionsForAppInfo(activity, FACEBOOK_PACKAGE_NAME)
//                    if (versionSet.size > 0) {
//                        hookNativeProtocalFacebookAppInfoList(versionSet, FACEBOOK_PACKAGE_NAME)
//                        intervalCheckFacebookFlag = true
//
//                        //将应用界面移到前台，并开始facebook分享
//                        am.moveTaskToFront(activity.taskId, 0)
//                        shareAction?.invoke()
//                    }
//                }
//            }
//    }
//
//    private fun hookNativeProtocalFacebookAppInfoList(protocolVersionSet: TreeSet<Int>, packageName: String) {
//        try {
//            if (protocolVersionSet.isEmpty()) {
//                return
//            }
//            val facebookAppInfoList = ReflectUtils.getField("com.facebook.internal.NativeProtocol", "facebookAppInfoList") as? List<*>
//
//            if (facebookAppInfoList?.isEmpty() != false) {
//                return
//            }
//            facebookAppInfoList.forEach {
//                val thisPackageName = ReflectUtils.callMethod(it, "getPackage")
//                Log.d(TAG, " hookNativeProtocalFacebookAppInfoList thisPackageName = $thisPackageName")
//                if (thisPackageName == packageName) {
//                    ReflectUtils.setField(it, "availableVersions", protocolVersionSet)
//                }
//            }
//        } catch (e: Exception) {
//            Log.d(TAG, " hookNativeProtocalFacebookAppInfoList failed ", e)
//        }
//    }
//}