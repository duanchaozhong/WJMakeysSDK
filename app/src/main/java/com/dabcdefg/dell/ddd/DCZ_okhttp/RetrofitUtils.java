package com.dabcdefg.dell.ddd.DCZ_okhttp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *  Retrofit
 */
public class RetrofitUtils {
    private static String Url_ROOT="";

    public static void setUrl_ROOT(String url_root) {
       Url_ROOT = url_root;
    }

    private static Retrofit singleton;
    private static Retrofit tworestAdapter;

    /**
     * 获取api
     * @param clazz
     * @param <T>
     * @return  返回call
     */
    public static <T> T createApiForGson( Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(Url_ROOT);
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.client(OkHttpUtils.getInstance());
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }

    /**
     * 获取api（对于网络数据返回null字段的处理:统一解析成"";）
     * @param clazz
     * @param <T>
     * @return  返回call
     */
    public static <T> T createApiForGson2( Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    //对于网络数据返回null字段的处理:统一解析成"";
                    // 取body 数据
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
                    //然后用上面一行写的gson来序列化和反序列化实体类type
                    //gson.fromJson(json, type);
                    //gson.toJson(type);
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(Url_ROOT);
                    builder.addConverterFactory(GsonConverterFactory.create(gson));
                    builder.client(OkHttpUtils.getInstance());
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }
    /**
     * 获取api
     * @param clazz
     * @param <T>
     * @return  返回obersive
     */
    public static <T> T createApiForRxjava(Class<T> clazz){
        if (tworestAdapter == null) {
            synchronized (RetrofitUtils.class) {
                if (tworestAdapter == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(Url_ROOT);
                    builder.addConverterFactory(GsonConverterFactory.create());
//                    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    builder.client(OkHttpUtils.getInstance());
                    tworestAdapter = builder.build();
                }
            }
        }
        return tworestAdapter.create(clazz);
    }



    private static Retrofit singletonForLogin;
    public static <T> T createApi(Class<T> clazz) {
        if (singletonForLogin == null) {
            synchronized (RetrofitUtils.class) {
                if (singletonForLogin == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl("http://120.25.212.156");
                    builder.addConverterFactory(GsonConverterFactory.create());
//                    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    builder.client(OkHttpUtils.getInstance());
                    singletonForLogin = builder.build();
                }
            }
        }
        return singletonForLogin.create(clazz);
    }


}
