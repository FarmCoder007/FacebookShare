package com.allsaints.shares.interfaces

import android.content.Context
import android.content.ContextWrapper


interface ShareInterface {
    /**
     *  分享链接
     */
    fun shareLink(context: Context,link:String)

    /**
     * 分享图片
     */
//    fun sharePhoto()
}