package com.example.authorlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by DELL on 2018/5/25.
 */

public class JARAuthorization {
    static String packName="com.example.duan.chao";
    static String packService="com.example.duan.chao.DCZ_util.BService";

    static String packaAtivity="com.example.duan.chao.DCZ_activity.AuthorActivity";
    static Messenger messenger;
    static Message message;
    static String App_key="1";
    static String redirect_uri="2";     //重定向地址
    static String scope="3";            //以空格分割的权限列表
    static String state="4";            //用于保持请求和回调的状态
    public static Boolean en=true;      //是否允许用户再次点击授权
    private static onButtonCLickListener listener;

    public interface onButtonCLickListener{
        public void onHui(Message string);
    }

    public static void init(Context context, String AppKey2, String redirect_uri2, String scope2, String state2){
        App_key=AppKey2;redirect_uri=redirect_uri2;scope=scope2;state=state2;
    }

    private static void setViews(final Context context) {     //初次连接
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("kk", "链接开启");
                messenger = new Messenger(service);
                sendMessageToB(context);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("kk", "链接断开！"); messenger=null;
            }
        };
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packName,packService));
        context.startService(intent);
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
    private static void setView(Context context) {      //手动连接
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("kk", "链接开启");
                messenger = new Messenger(service);
                message = Message.obtain(null, 1);
                message.replyTo = replyMessenger;
                Bundle bundle = new Bundle();
                bundle.putString("App_key",App_key); bundle.putString("redirect_uri",redirect_uri);
                bundle.putString("scope",scope);bundle.putString("state",state);
                message.setData(bundle);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("kk", "链接断开！"); messenger=null;
            }
        };
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packName,packService));
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
    /**
     *  跳转到指定activity
     *  packname:包名
     *  pathName:当前类的完整路径
     * */
    public static void startAuthor(final Context context, String packname, String pathName){
        if(en==true){
            if (checkPackInfo(packName,context)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packName,"com.example.duan.chao.DCZ_activity.AppStartActivity"));
                intent.putExtra("App_key",App_key);
                intent.putExtra("redirect_uri",redirect_uri);
                intent.putExtra("scope",scope);
                intent.putExtra("state",state);
                intent.putExtra("packname",packname);
                intent.putExtra("pathname",pathName);
                context.startActivity(intent);
            } else {
                new MiddleDialog(context,new MiddleDialog.onButtonCLickListener2() {
                    @Override
                    public void onActivieButtonClick(Object bean, int po) {
                        if(bean==null){
                        }else {
                            Intent intent= new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse("http://test-makeys.qeveworld.com");
                            intent.setData(content_url);
                            context.startActivity(intent);
                        }
                    }
                }, R.style.registDialog).show();
                return;
            }
        }

    }
    /**
     *  启动APP
     * */
/*    public static void start(Context context){
        PackageManager packageManager = context.getPackageManager();
        if (checkPackInfo(packName,context)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packName);
            context.startActivity(intent);
        } else {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse("http://test-makeys.qeveworld.com");
            intent.setData(content_url);
            context.startActivity(intent);
        }
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private static boolean checkPackInfo(String packname, Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }
    public static void sendMessageToB(Context context) {
        Message message = Message.obtain(null, 1);
        message.replyTo = replyMessenger;
        Bundle bundle = new Bundle();
        bundle.putString("App_key",App_key); bundle.putString("redirect_uri",redirect_uri);
        bundle.putString("scope",scope);bundle.putString("state",state);
        message.setData(bundle);
        try {
            if(messenger==null){
                setView(context);//重新连接一下
            }else {
                messenger.send(message);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private String Aes(String str){
        String random = JarRandomUtil.generateString(32);
        String tk=JARAESCodeer.getKey(random);
        String sign = JARAESCodeer.AESEncode(tk, str);
        Log.i("加密",sign+"");
        return sign;
    }
    private String jie(){
        JARAESCodeer.AESDncode("tk","sign");
        return  JARAESCodeer.AESDncode("tk","sign");
    }

    static Messenger replyMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("kk", msg.getData().getString("intent")+"");
            Log.e("kk", msg.getData().getString("type")+"");
            String type = msg.getData().getString("type");
            if(type.equals("1")){
                listener.onHui(msg);
            }else {
                if(msg.getData().getString("intent")!=null){
                    packaAtivity=msg.getData().getString("intent");
                }
            }
            super.handleMessage(msg);
        }
    });
}
