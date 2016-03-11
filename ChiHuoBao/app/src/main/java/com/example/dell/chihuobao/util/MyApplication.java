package com.example.dell.chihuobao.util;

import android.app.Application;


import com.example.dell.chihuobao.BuildConfig;

import org.xutils.x;

import cn.smssdk.SMSSDK;

/**
 * Created by wyouflf on 15/10/28.
 */
public class MyApplication extends Application {

    // 在application的onCreate中初始化
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

    }
}
