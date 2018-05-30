package com.example.authorlibrary;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

public class AuthorBaseActivity extends AppCompatActivity {
    private Gson mGson = new Gson();
    protected void onChangeUserInfo(AuthorBean string) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String a = getIntent().getStringExtra("json");
        JARAuthorization.en=true;
        Log.i("dcz",a+"zzz");
        if(a!=null){
            if(a.equals("error")){
                Toast.makeText(this,"失败", Toast.LENGTH_SHORT).show();
            }else {
                AuthorBean result = mGson.fromJson(a, AuthorBean.class);
                if(result.getCode().equals("20000")){
                    onChangeUserInfo(result);
                }else {
                    Toast.makeText(this,result.getDesc(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        String a = getIntent().getStringExtra("json");
        Log.i("json",a+"zzz");
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String a = getIntent().getStringExtra("json");
        Log.i("dcz2","执行了"+a);
        if(a==null){
            if(BService.data!=null){
                if(BService.data.equals("error")){
                    Toast.makeText(this,"失败", Toast.LENGTH_SHORT).show();
                    JARAuthorization.en=true;
                }else {
                    AuthorBean result = mGson.fromJson(BService.data, AuthorBean.class);
                    if(result.getCode().equals("20000")){
                        onChangeUserInfo(result);
                    }else {
                        JARAuthorization.en=true;
                        Toast.makeText(this,result.getDesc(), Toast.LENGTH_SHORT).show();
                    }
                }
                BService.data=null;
            }
        }
    }
}
