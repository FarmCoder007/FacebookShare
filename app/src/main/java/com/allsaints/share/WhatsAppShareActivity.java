package com.allsaints.share;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;


/**
 * Created by zlove on 2018/2/1.
 */

public class WhatsAppShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";

    private Button btnShareLink;
    private Button btnShareImg;
    private Button btnShareVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_share);

        btnShareLink = findViewById(R.id.share_link);
        btnShareImg = findViewById(R.id.share_img);
        btnShareVideo = findViewById(R.id.share_video);

        btnShareLink.setOnClickListener(this);
        btnShareImg.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnShareLink) {
            shareLink();
        } else if (view == btnShareImg) {
            shareImg();
        } else if (view == btnShareVideo) {
            shareVideo();
        }
    }

    private void shareLink() {
        if (!AppUitls.isAppInstalled(WHATSAPP_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装WhatsApp...", Toast.LENGTH_SHORT).show();
        }
        String type = "text/*";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType(type);
        share.putExtra(Intent.EXTRA_TEXT, "分享链接："+AppUtils.URL);
        share.setPackage(WHATSAPP_PACKAGE_NAME);
        startActivity(Intent.createChooser(share, "Share to"));
    }

    private void shareImg() {
        if (!AppUitls.isAppInstalled(WHATSAPP_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装WhatsApp...", Toast.LENGTH_SHORT).show();
        }

        String type = "image/*";
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(R.mipmap.share) + "/"
                + getResources().getResourceTypeName(R.mipmap.share) + "/"
                + getResources().getResourceEntryName(R.mipmap.share));

        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        share.putExtra(Intent.EXTRA_TEXT, "分享说明");
        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(WHATSAPP_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }

    private void shareVideo() {
        if (!AppUitls.isAppInstalled(WHATSAPP_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装WhatsApp...", Toast.LENGTH_SHORT).show();
        }

        String type = "video/*";
        String mediaPath = "/storage/emulated/0/相机/729d2e15-0204-4e14-95a6-086436aa805b.mp4";
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        share.putExtra(Intent.EXTRA_TEXT, "分享说明");
        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);
        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(WHATSAPP_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }
}
