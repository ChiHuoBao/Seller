package com.example.dell.chihuobao.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.chihuobao.R;
import com.example.dell.chihuobao.util.BaseLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;

    @ViewInject(R.id.login_user_name)
    private EditText etUserName;

    @ViewInject(R.id.login_user_pwd)
    private EditText erUserPwd;

    @ViewInject(R.id.login)
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);


    }
    @Event(R.id.login)
    private void onLoginClick(View view) {
        BaseLog.i("登录点击");
    }
}
