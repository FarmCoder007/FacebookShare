package com.allsaints.shares

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast


/**
 *  author : xuwenbin
 *  date : 2023/11/16 15:34
 *  description :
 */
object CopyUtils {
    fun copy(context: Context, shareBean: ShareBean) {
        // 获取剪贴板管理器
        val clipboard: ClipboardManager? =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        // 创建一个 ClipData 对象，包含要复制的文本
        val clip = ClipData.newPlainText(
            "label",
            "现在${shareBean.hostNickName}正在直播，来和我一起支持Ta吧，复制下方链接，打开${shareBean.appName}，直接观看直播${shareBean.liveUrl}"
        )
        // 将 ClipData 设置到剪贴板
        clipboard?.setPrimaryClip(clip)

        // 提示用户已复制到剪贴板
        Toast.makeText(context, "链接已复制到剪贴板", Toast.LENGTH_SHORT).show()
    }
}