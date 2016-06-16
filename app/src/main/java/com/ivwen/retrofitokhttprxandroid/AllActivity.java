package com.ivwen.retrofitokhttprxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ivwen.retrofitokhttprxandroid.all.ArticleResponse;
import com.ivwen.retrofitokhttprxandroid.all.MeipianSubscriber;
import com.ivwen.retrofitokhttprxandroid.retrofit.APIService;
import com.ivwen.retrofitokhttprxandroid.retrofit.IpInfo;
import com.ivwen.retrofitokhttprxandroid.retrofit.Update;
import com.ivwen.retrofitokhttprxandroid.retrofit.UpdateItem;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AllActivity extends Activity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_1:

                demo();

                break;
            case R.id.bt_2:

                demo2();

                break;
            case R.id.bt_3:

                demo3();

                break;
        }
    }

    private void demo3()
    {

        HashMap hashMap= new HashMap();

        hashMap.put("user_id","2054095");
        hashMap.put("token", "339e8c14d2d38838efac6caaaae6ebf4");
        hashMap.put("max_id", "0");
        hashMap.put("category_id", "1200");



        MeipianService meipianService= new MeipianService();


        meipianService.getArticle(hashMap, new Subscriber<ArticleResponse>()
        {
            @Override
            public void onCompleted()
            {
                Log.d("onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d("onError",e.getMessage());
            }

            @Override
            public void onNext(ArticleResponse articleResponse)
                {

                Log.d("now",articleResponse.toString());
                }
        });





















    }

    private void demo2()
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://ip.taobao.com/").addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

        APIService apiService=retrofit.create(APIService.class);

        Observable<IpInfo> observable=apiService.getIpinfoObs("180.111.32.190");

        observable.flatMap(new Func1<IpInfo, Observable<IpInfo>>()
        {
            @Override
            public Observable<IpInfo> call(IpInfo ipInfo)
            {
                Log.d("flatMap", Thread.currentThread().getName() + "-----" + "Observable");
                return Observable.just(ipInfo);
            }
            })
            .flatMap(new Func1<IpInfo, Observable<IpInfo>>()
            {
                @Override
                public Observable<IpInfo> call(IpInfo ipInfo)
                {
                    Log.d("flatMap1", Thread.currentThread().getName() + "-----" + "Observable");
                    return Observable.just(ipInfo);
                }
            })
            .subscribe(new Subscriber<IpInfo>()
            {
                @Override
                public void onCompleted()
                {
                    Toast.makeText(AllActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e)
                {
                    e.printStackTrace();
                    Toast.makeText(AllActivity.this, "onError" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(IpInfo ipInfo)
                {

                    Log.d("onNext", Thread.currentThread().getName() + "-----" + ipInfo.toString());
                }
            });
    }


    private void demo()
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://ip.taobao.com/").addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

        APIService apiService=retrofit.create(APIService.class);

        Observable<IpInfo> observable=apiService.getIpinfoObs("180.111.32.190");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<IpInfo, Observable<IpInfo>>()
        {
            @Override
            public Observable<IpInfo> call(IpInfo ipInfo)
            {
              return Observable.just(ipInfo);
            }
        })
        .subscribe(new Subscriber<IpInfo>()
        {
            @Override
            public void onCompleted()
            {
                Toast.makeText(AllActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e)
            {
                Toast.makeText(AllActivity.this, "onError" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(IpInfo ipInfo)
            {
                Log.d("onNext", ipInfo.toString());
            }
        });
    }
}
