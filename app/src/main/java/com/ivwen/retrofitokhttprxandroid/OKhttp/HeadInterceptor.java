package com.ivwen.retrofitokhttprxandroid.OKhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by meipian on 16/6/14.
 */
public class HeadInterceptor  implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request=chain.request().newBuilder().addHeader("add","add").build();
        Log.d("HeadInterceptor","HeadInterceptor");

        return chain.proceed(request);
    }
}
