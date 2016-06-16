package com.ivwen.retrofitokhttprxandroid.OKhttp;

import android.util.Log;

import com.google.gson.Gson;
import com.ivwen.retrofitokhttprxandroid.bean.Algorithm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;

/**
 * Created by meipian on 16/6/14.
 */
public class MeipianInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        try{
        Request request=chain.request();
        if(request.body().contentType().toString().equals("application/json;charset=utf-8"))
        {
            String body=bodyToString(request.body());
            Log.e("MeipianInterceptor", bodyToString(request.body()));
        String action=request.url().toString().replace("https://api.meipian.me/3.1/","");
             JSONObject jsonObject= new JSONObject(body);

            long time = System.currentTimeMillis() / 1000 + 0;  // 校准后的时间戳
            //Log.e("NET", "time: " + time + ", diff: " + mTimeDiff);

            final Date now = new Date(time * 1000);
            final String timestamp = time + "";
            final String auth = genToken(action, timestamp, jsonObject);
//            .addHeader("Authorization", auth).addHeader("Date", now.toGMTString())

            Request request1=request.newBuilder() .addHeader("Authorization", auth).addHeader("Date", now.toGMTString()).build();
            return chain.proceed(request1);
        }

        }catch (Exception e){
            e.printStackTrace();
            return chain.proceed(chain.request());
        }
    return  null;
    }

    public static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }


    private String genToken(String action, String timestamp, JSONObject params)
    {
        TreeMap<String, String> orderedParams = new TreeMap<>();
        try{
            Iterator it = params.keys();
            while (it.hasNext())
            {
                String key = (String) it.next();
                Object value = params.opt(key);
                if (!(value instanceof JSONArray))
                {
                    orderedParams.put(key, params.optString(key));
                }
            }

            String token = action + "\n" + timestamp + "\n";
            for (Map.Entry<String, String> entry : orderedParams.entrySet())
            {
                token += entry.getValue() + "\n";
            }

            token = SIGN_SALT1 + token + SIGN_SALT2;

            return "IVWEN " + Algorithm.MD5(token);
        }catch(Exception e){
            return "";
        }

    }
    private static final String SIGN_SALT1 = "3fdFD0$4a26@49d8";
    private static final String SIGN_SALT2 = "A684^bF4b(8DBe4*";
}
