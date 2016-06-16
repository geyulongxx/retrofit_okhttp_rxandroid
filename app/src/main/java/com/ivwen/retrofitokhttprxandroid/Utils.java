package com.ivwen.retrofitokhttprxandroid;

import android.content.Context;

import com.google.gson.Gson;
import com.ivwen.retrofitokhttprxandroid.OKhttp.LogInterceptor;
import com.ivwen.retrofitokhttprxandroid.OKhttp.MeipianInterceptor;
import com.ivwen.retrofitokhttprxandroid.retrofit.Update;
import com.ivwen.retrofitokhttprxandroid.retrofit.UpdateItem;

import org.json.JSONObject;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meipian on 16/6/16.
 */
public class Utils
{

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {

        if (retrofit != null)
        {
            return retrofit;
        }
        try
        {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream in = App.instance.getAssets().open("api.pem");
            Certificate ca = cf.generateCertificate(in);

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null);
            keystore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keystore);

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, tmf.getTrustManagers(), null);
            OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslcontext.getSocketFactory()).addNetworkInterceptor(new MeipianInterceptor()).addNetworkInterceptor(new LogInterceptor()).build();

            return new Retrofit.Builder().client(client).baseUrl("https://api.meipian.me/3.1/").addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        catch (Exception e)
        {
        }
        return null;
    }
}
