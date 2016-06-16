package com.ivwen.retrofitokhttprxandroid.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;

/**
 * Created by meipian on 16/6/15.
 */
public class MeipianRequestBody extends RequestBody
{

//    public  RequestBody conver(String jsonString)
//    {
//        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonString.toString());
//    }
    //RequestBody.create(MediaType.parse("application/json;charset=utf-8"), gson.toJson(update))


    private String ss;
    public  MeipianRequestBody(HashMap hashMap){
        this.ss=new Gson().toJson(hashMap);
    }



    Charset charset = Util.UTF_8;


    @Override
    public MediaType contentType()
    {
        return MediaType.parse("application/json;charset=utf-8");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException
    {
        byte[] bytes = ss.getBytes(charset);
        sink.write(bytes,0,bytes.length);
    }
}
