package com.ivwen.retrofitokhttprxandroid.OKhttp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by meipian on 16/6/14.
 */
public class LogInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        Log.i("LogInterceptor","request url =" +request.url());
        Log.i("LogInterceptor","request connection ="+ chain.connection());
        Log.i("LogInterceptor","request headers =" + request.headers());
        Response response = chain.proceed(request);
        Log.i("LogInterceptor"," response url="+ response.request().url() );
        Log.i("LogInterceptor", " response headers=" + response.headers());
//        Log.i("LogInterceptor"," response headers="+ response.body().string());
//        InputStreamReader isr = new InputStreamReader(response.body().byteStream());
//
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null)
//        {
//            sb.append(line);
//        }
//

//        Log.i("LogInterceptor"," response ="+ sb.toString());


        return response;
    }
}
