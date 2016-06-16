package com.ivwen.retrofitokhttprxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ivwen.retrofitokhttprxandroid.OKhttp.LogInterceptor;
import com.ivwen.retrofitokhttprxandroid.OKhttp.MeipianInterceptor;
import com.ivwen.retrofitokhttprxandroid.retrofit.APIService;
import com.ivwen.retrofitokhttprxandroid.retrofit.IpInfo;
import com.ivwen.retrofitokhttprxandroid.retrofit.MeipianRequestBody;
import com.ivwen.retrofitokhttprxandroid.retrofit.Update;
import com.ivwen.retrofitokhttprxandroid.retrofit.UpdateItem;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
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
                try
                {
                    simpleDemo();
                }catch (Exception e){e.printStackTrace();};
                break;
            case R.id.bt_2:
                try
                {
                    retrofit_okhttp();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_3:
                break;
            case R.id.bt_4:
                break;
            case R.id.bt_5:
                break;

        }
    }

    private void retrofit_okhttp() throws Exception
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
            .addNetworkInterceptor(new LogInterceptor())
            .build();



        JSONObject jsonObject= new JSONObject();

        //sms
//        jsonObject.put("device_id","865982028793993:02:00:00:00:00:00");
//        jsonObject.put("phone_num","18100609558");
//        jsonObject.put("type", "0");

        //category
//        jsonObject.put("user_id","2054095");
//        jsonObject.put("token","339e8c14d2d38838efac6caaaae6ebf4");
//        jsonObject.put("max_id", "0");
//        jsonObject.put("category_id", "1100");

//        Bean bean = new Bean("2054095","339e8c14d2d38838efac6caaaae6ebf4","0","1100");
        Gson gson= new Gson();
        //update
//        jsonObject.put("user_id","2054095");
//        jsonObject.put("token","339e8c14d2d38838efac6caaaae6ebf4");
//        jsonObject.put("article_id","2r5m35l");
//        jsonObject.put("title","我的美篇1");
//        jsonObject.put("cover_img_url","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg");
//        jsonObject.put("music_url","");
//        jsonObject.put("music_desc","");
//        jsonObject.put("theme","0");
//        jsonObject.put("abstract","1234");
//
//        JSONArray jsonArray= new JSONArray();
//
//        JSONObject image= new JSONObject();
//        image.put("text","1234");
//        image.put("img_url","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg");
//        image.put("img_width","124");
//        image.put("img_height","400");
//        jsonArray.put(image);
//
//        jsonObject.put("content",jsonArray);





        UpdateItem updateItem= new UpdateItem("1234","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg","124","124");
        List l= new ArrayList();
        l.add(updateItem);

        Update update= new Update("2054095","339e8c14d2d38838efac6caaaae6ebf4","2r5m35l","我的美篇123","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg",
            "","","0","1234",l);

        List list= new ArrayList();
        list.add(updateItem);
        HashMap hashMap= new HashMap();

        hashMap.put("user_id","2054095");
        hashMap.put("token","339e8c14d2d38838efac6caaaae6ebf4");
        hashMap.put("article_id","2r5m35l");
        hashMap.put("title","我的美篇1");
        hashMap.put("cover_img_url","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg");
        hashMap.put("music_url","");
        hashMap.put("music_desc","");
        hashMap.put("theme","0");
        hashMap.put("abstract","1234");

//                JSONArray jsonArray= new JSONArray();
//
//                JSONObject image= new JSONObject();
//                image.put("text","1234");
//                image.put("img_url","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg");
//                image.put("img_width","124");
//                image.put("img_height","400");
        hashMap.put("content",list);


        Retrofit retrofit= new Retrofit.Builder().client(client).baseUrl("https://api.meipian.me/3.1/").addConverterFactory(GsonConverterFactory.create()).build();


        APIService apiService= retrofit.create(APIService.class);

        Call<ResponseBody> call= apiService.update(new MeipianRequestBody(hashMap));
//        Call<ResponseBody> call= apiService.update(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), gson.toJson(update)));

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

                try
                {
                    Log.d("test",response.isSuccessful()+"");
                    Log.d("test",response.body().string());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {

            }
        });

//        Request request1=new Request.Builder().url("https://api.meipian.me/3.1/sms/fetch")
//            .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestpp)).build();
//
//        client.newCall(request1).enqueue(new okhttp3.Callback()
//        {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e)
//            {
//
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException
//            {
//                Log.d("test",response.isSuccessful()+"");
//                Log.d("test",response.body().string());
//            }
//        });


    }

    //http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33
    private void simpleDemo() throws  Exception
    {
            //
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://ip.taobao.com/").addConverterFactory(GsonConverterFactory.create()).build();

        APIService apiService=retrofit.create(APIService.class);

        Call<IpInfo> call= apiService.getIpinfo("180.111.32.190");
        call.enqueue(new Callback<IpInfo>()
        {
            @Override
            public void onResponse(Call<IpInfo> call, Response<IpInfo> response)
            {
                Toast.makeText(RetrofitActivity.this, "111", Toast.LENGTH_SHORT).show();
                IpInfo info=response.body();
                Log.d("tag",info.data.toString());
            }

            @Override
            public void onFailure(Call<IpInfo> call, Throwable t)
            {
                t.printStackTrace();
                Log.d("tag","onFailure");
            }
        });

//        Call<ResponseBody> call=apiService.getHtml();
//        call.enqueue(new Callback<ResponseBody>()
//        {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
//            {
//                try{
//                    Log.d("tag",response.body().string());
//                }catch (Exception e){}
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t)
//            {
//
//            }
//        });
    }


    public <T> void  aa(HashMap hashMap,Callback<T> callback){
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.meipian.me/3.1/").addConverterFactory(GsonConverterFactory.create()).build();


        APIService apiService= retrofit.create(APIService.class);

        Call<ResponseBody> call= apiService.update(new MeipianRequestBody(hashMap));

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                try
                {
                    Log.d("test",response.isSuccessful()+"");
                    Log.d("test",response.body().string());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {

            }
        });
    }
}
