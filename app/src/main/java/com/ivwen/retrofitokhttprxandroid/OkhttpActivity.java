package com.ivwen.retrofitokhttprxandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ivwen.retrofitokhttprxandroid.OKhttp.CacheInterceptor;
import com.ivwen.retrofitokhttprxandroid.OKhttp.HeadInterceptor;
import com.ivwen.retrofitokhttprxandroid.OKhttp.LogInterceptor;
import com.ivwen.retrofitokhttprxandroid.OKhttp.MeipianInterceptor;
import com.ivwen.retrofitokhttprxandroid.bean.Algorithm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener
{
    public static Activity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        instance=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
        findViewById(R.id.bt_4).setOnClickListener(this);
        findViewById(R.id.bt_5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_1:
                simpleRequest();
                break;
            case R.id.bt_2:
                try{
                    gsonJiexi();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.bt_3:
                addInterceptor();
                break;
            case R.id.bt_4:
                try{
                addHeadWithInterceptor();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.bt_5:
                try{
                    cache();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void cache()
    {
        File httpCacheDirectory = new File(this.getCacheDir(), "responses");

        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().addNetworkInterceptor(new CacheInterceptor()).addNetworkInterceptor(new LogInterceptor()).cache(cache).build();



        Request request = new Request.Builder().url("http://gc.ditu.aliyun.com/geocoding?a=南京市").addHeader("aaa","123").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException
            {
                Log.d("okhttp", response.isSuccessful() + "");
                Log.d("okhttp", response.body().string() + "");
            }
        });



    }

    private void addHeadWithInterceptor() throws  Exception
    {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = getAssets().open( "api.pem");
        Certificate ca = cf.generateCertificate(in);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, null);
        keystore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keystore);

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);
        //        sms/fetch
        //        params.put("device_id", Utils.getDeviceID());
        //        params.put("phone_num", phone_num);
        //        params.put("type", type);
        //        sms/fetch   {"phone_num":"13222069511","type":0,"device_id":"865982028793993:02:00:00:00:00:00"}
        //        06-13 19:00:05.798 24347-25645/com.lanjingren.ivwen E/HTTP_REQUEST: https://api.meipian.me/3.1/sms/fetch
        OkHttpClient client=   new OkHttpClient.Builder().sslSocketFactory(context.getSocketFactory())
            .addNetworkInterceptor(new MeipianInterceptor())
            .addNetworkInterceptor(new LogInterceptor()).build();

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("device_id","865982028793993:02:00:00:00:00:00");
        jsonObject.put("phone_num","13222069518");
        jsonObject.put("type", "0");


        final String requestpp = jsonObject.toString();

        Request request1=new Request.Builder().url("https://api.meipian.me/3.1/sms/fetch")
            .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestpp)).build();

        client.newCall(request1).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Log.d("test",response.isSuccessful()+"");
                Log.d("test",response.body().string());
            }
        });

    }

    private void addInterceptor(){

        OkHttpClient client = new OkHttpClient().newBuilder().addNetworkInterceptor(new HeadInterceptor()).addNetworkInterceptor(new LogInterceptor()).build();
        Request request = new Request.Builder().url("http://gc.ditu.aliyun.com/geocoding?a=苏州市").addHeader("aaa","123").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException
            {
                Log.d("okhttp", response.isSuccessful() + "");
                Log.d("okhttp", response.body().string() + "");
            }
        });
    }




    private void simpleRequest()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.meipian.me/").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException
            {
                Log.d("okhttp", response.isSuccessful() + "");
                Log.d("okhttp", response.body().string() + "");
            }
        });
    }

    /**
     * http://gc.ditu.aliyun.com/geocoding?a=苏州市
     * {"lon":120.58531,"level":2,"address":"","cityName":"","alevel":4,"lat":31.29888}
     */
    private void gsonJiexi() throws  Exception{


//        Request request=new Request.Builder().url("").post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),"")).build();

//        client.newCall(request).execute();  //同步
//        client.newCall(request).enqueue(new Callback()//异步
//        new FormBody.Builder().addEncoded("","");

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = getAssets().open( "api.pem");
        Certificate ca = cf.generateCertificate(in);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, null);
        keystore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keystore);

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);
//        sms/fetch
//        params.put("device_id", Utils.getDeviceID());
//        params.put("phone_num", phone_num);
//        params.put("type", type);
//        sms/fetch   {"phone_num":"13222069511","type":0,"device_id":"865982028793993:02:00:00:00:00:00"}
//        06-13 19:00:05.798 24347-25645/com.lanjingren.ivwen E/HTTP_REQUEST: https://api.meipian.me/3.1/sms/fetch
       OkHttpClient client=   new OkHttpClient.Builder().sslSocketFactory(context.getSocketFactory()).build();

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("device_id","865982028793993:02:00:00:00:00:00");
        jsonObject.put("phone_num","13222069513");
        jsonObject.put("type", "0");

        long time = System.currentTimeMillis() / 1000 + 0;  // 校准后的时间戳
        //Log.e("NET", "time: " + time + ", diff: " + mTimeDiff);

        final Date now = new Date(time * 1000);
        final String timestamp = time + "";
        final String requestpp = jsonObject.toString();
        final String auth = genToken("sms/fetch", timestamp, jsonObject);
        Request request1=new Request.Builder().url("https://api.meipian.me/3.1/sms/fetch")
            .addHeader("Authorization", auth).addHeader("Date", now.toGMTString()).post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestpp)).build();

        client.newCall(request1).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Log.d("test",response.isSuccessful()+"");
                Log.d("test",response.body().string());
            }
        });
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
