package com.example.dell.chihuobao.util;

import android.util.Log;

/**
 * Created by fanghao on 2016/3/3.
 * Log封装类
 */
public class BaseLog {
    //Info
    public void i(String tag,String msg) {
        Log.i(tag, msg);
    }
    public void i(String msg) {
        Log.i("Info", msg);
    }
    //error
    public void e(String tag,String msg) {
        Log.e(tag, msg);
    }
    public void e(String msg) {
        Log.e("error", msg);
    }
    //Debug
    public void d(String tag,String msg) {
        Log.d(tag, msg);
    }
    public void d(String msg) {
        Log.d("Debug", msg);
    }
    //Warning
    public void w(String tag,String msg) {
        Log.w(tag, msg);
    }
    public void w(String msg) {
        Log.w("Warning", msg);
    }
    //Verbose
    public void v(String tag,String msg) {
        Log.v(tag, msg);
    }
    public void v(String msg) {
        Log.v("Verbose", msg);
    }

}
