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

public class InstagramShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";

    private Button btnShareImg;
    private Button btnShareVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_share);

        btnShareImg = findViewById(R.id.share_img);
        btnShareVideo = findViewById(R.id.share_video);

        btnShareImg.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);
        findViewById(R.id.share_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 也可分享动态或快拍，可以自行处理，这里不做展示
                if (!AppUitls.isAppInstall(InstagramShareActivity.this,INSTAGRAM_PACKAGE_NAME)) {
                    Toast.makeText(InstagramShareActivity.this, "请先安装Instagram...", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("text/*");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, AppUtils.URL);
                    shareIntent.setPackage("com.instagram.android");
                    startActivity(shareIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnShareImg) {
            shareImg();
        } else if (view == btnShareVideo) {
            shareVideo();
        }
    }

    private void shareImg() {
        if (!AppUitls.isAppInstalled(INSTAGRAM_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装Instagram...", Toast.LENGTH_SHORT).show();
        }
        String type = "image/*";
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(R.mipmap.share) + "/"
                + getResources().getResourceTypeName(R.mipmap.share) + "/"
                + getResources().getResourceEntryName(R.mipmap.share));

        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(INSTAGRAM_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));

    }

    private void shareVideo() {
        if (!AppUitls.isAppInstalled(INSTAGRAM_PACKAGE_NAME)) {
            Toast.makeText(this, "请先安装Instagram...", Toast.LENGTH_SHORT).show();
        }
        String type = "video/*";
        String mediaPath = "/storage/emulated/0/相机/729d2e15-0204-4e14-95a6-086436aa805b.mp4";
                // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);
        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage(INSTAGRAM_PACKAGE_NAME);
        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }
}
