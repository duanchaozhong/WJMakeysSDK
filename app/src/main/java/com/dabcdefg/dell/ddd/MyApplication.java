package com.dabcdefg.dell.ddd;

import android.app.Application;

import com.example.authorlibrary.JARAuthorization;


/**
 * Created by DELL on 2018/5/28.
 */

public class MyApplication extends Application{
    public static AuBean.DataBean bean;
    @Override
    public void onCreate() {
        super.onCreate();
        JARAuthorization.init(this,"SampleClientId","http://your_callback_uri ","c","d");
    }
}
