package com.example.alzbot.service;

import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;

public class GenericFileProvider extends FileProvider {
    @Override
    public boolean onCreate() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        return super.onCreate();
    }
}
