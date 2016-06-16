package com.ivwen.retrofitokhttprxandroid.OKhttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.ivwen.retrofitokhttprxandroid.OkhttpActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by meipian on 16/6/14.
 */
public class CacheInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Response originalResponse = chain.proceed(chain.request());
        if (isNetworkAvailable()) {
            Log.e("CacheInterceptor","has net work");
            int maxAge = 60; // 在线缓存在1分钟内可读取
            return originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build();
        } else {
            Log.e("CacheInterceptor","no net work");
            int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
            return originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build();
        }
    }

    public  boolean isNetworkAvailable(){
        Context context = OkhttpActivity.instance.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        for(int i=0;i<networkInfo.length;i++)
        {
            if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
            {
                return true;
            }
        }
        return false;
    }
}
