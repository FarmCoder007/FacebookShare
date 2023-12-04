package com.allsaints.shares.interfaces.impl

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.net.Uri
import com.allsaints.shares.interfaces.ShareInterface
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.share.model.ShareContent
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog

/**
 *  author : xuwenbin
 *  date : 2023/11/16 20:30
 *  description : facebook分享实现
 */
class FacebookShareImpl : ShareInterface {

    override fun shareLink(context: Context, link: String) {
        val content =
            ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(link))
                .setShareHashtag(ShareHashtag.Builder().setHashtag("test").build())
                .build()
        share(context, content)
    }


//    override fun sharePhoto(context: Context,
//                            drawableIds: List<Int>,
//                            hashtag: String? = null,) {
//        val content =
//            SharePhotoContent.Builder()
//                .setPhotos(
//                    drawableIds.map { image ->
//                        SharePhoto.Builder()
//                            .setBitmap(
//                                BitmapFactory.decodeResource(getApplicationContext().resources, image))
//                            .build()
//                    })
//                .setShareHashtag(ShareHashtag.Builder().setHashtag(hashtag).build())
//                .build()
//        share(context, content)
//    }

    private fun share(
        context: Context,
        content: ShareContent<*, *>,
        mode: ShareDialog.Mode = ShareDialog.Mode.AUTOMATIC
    ): Boolean {
        val activity = getActivity(context) ?: return false
        ShareDialog(activity).show(content, mode)
        return true
    }

    private fun getActivity(context: Context): Activity? {
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