package com.dabcdefg.dell.ddd;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dabcdefg.dell.ddd.DCZ_okhttp.HttpServiceClient;
import com.example.authorlibrary.AuthorBaseActivity;
import com.example.authorlibrary.AuthorBean;
import com.example.authorlibrary.JARAuthorization;
import com.example.authorlibrary.JarRandomUtil;
import com.example.dell.myapplication2.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AuthorBaseActivity{
    private TextView tv;

    @Override
    protected void onChangeUserInfo(AuthorBean result) {
        super.onChangeUserInfo(result);
        Log.i("数据：",result.getCode()+"zzz");
        tv.setEnabled(false);
        getData(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("dcz","resume");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JARAuthorization.startAuthor(MainActivity.this,"com.example.dell.myapplication2","com.dabcdefg.dell.ddd.MainActivity");
                /*Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.duan.chao2","com.example.duan.chao2.DCZ_activity.AppStartActivity"));
                intent.putExtra("author","author");intent.putExtra("App_key","SampleClientId");
                intent.putExtra("redirect_uri","http://your_callback_uri");
                intent.putExtra("scope","c");
                intent.putExtra("state","d");
                startActivity(intent);*/
                /*JARAuthorization.startIntent(MainActivity.this, new JARAuthorization.onButtonCLickListener() {
                    @Override
                    public void onHui(Message message) {
                        Log.i("dcz","收到回传消息");
                        if(message.getData().get("json")==null){
                            Toast.makeText(MainActivity.this,"失败", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.i("dcz","授权成功");
                            Log.i("dcz",message.getData().get("json").toString());
                            tv.setEnabled(false);
                            AuthorBean result = mGson.fromJson(message.getData().get("json").toString(), AuthorBean.class);
                            getData(result);
                        }
                    }
                });*/
            }
        });
    }

    private void getData(AuthorBean result){
        String state = null;
        if(result.getData().getState()==null){
            state=null;
        }else {
            state=result.getData().getState()+"";
        }
        HttpServiceClient.getInstance().getData(result.getData().getCode(),result.getData().getRedirect_uri(),state, JarRandomUtil.RandomNumber()).enqueue(new Callback<AuBean>() {
            @Override
            public void onResponse(Call<AuBean> call, Response<AuBean> response) {
                if(response.isSuccessful()){
                    Log.d("dcz","获取数据成功");
                    if(response.body().getCode().equals("20000")){
                        MyApplication.bean=response.body().getData();
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AuBean> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
