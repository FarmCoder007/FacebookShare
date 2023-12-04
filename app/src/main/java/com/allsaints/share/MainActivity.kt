package com.allsaints.share

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.allsaints.shares.CopyUtils
import com.allsaints.shares.ShareBean

class MainActivity : AppCompatActivity() {
    lateinit var edit: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit = findViewById<EditText>(R.id.et)
        findViewById<TextView>(R.id.tv_text).text = AppUtils.getAppName(this)
    }

    fun clickCopy(view: View) {
        CopyUtils.copy(this, ShareBean("XDLive", "King", "http://www.baidu.com"))
    }

    private fun check(){
        if(edit.text.isNotEmpty()){
            AppUtils.URL = edit.text.toString()
        }
    }

    fun clickFacebook(view: View) {
//         MyBottomSheetDialogFragment().show(supportFragmentManager,"MyBottomSheetDialogFragment")
        check()
        startActivity(Intent(this, FacebookShareActivity::class.java))
    }

    fun clickIns(view: View) {
        check()
        startActivity(Intent(this, InstagramShareActivity::class.java))
    }

    fun clickMessenger(view: View) {
        check()
        startActivity(Intent(this, MessengerShareActivity::class.java))
    }

    fun clickWhatsApp(view: View) {
        check()
        startActivity(Intent(this, WhatsAppShareActivity::class.java))
    }

}