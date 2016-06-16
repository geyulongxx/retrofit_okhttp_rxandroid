package com.ivwen.retrofitokhttprxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RXandroidActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
        findViewById(R.id.bt_4).setOnClickListener(this);
        findViewById(R.id.bt_5).setOnClickListener(this);
        findViewById(R.id.bt_6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_1:
                simpleDemo();
                break;
            case R.id.bt_2:
                justAction1();
                break;
            case R.id.bt_3:
                justAction1_Map();
                break;
            case R.id.bt_4:
                thead();
                break;
            case R.id.bt_5:
                break;
            case R.id.bt_6:
                break;
            default:
                break;
        }
    }

    private void thead()
    {
        Observable.just("thead").map(new Func1<String, String>()
        {
            @Override
            public String call(String s)
            {
                return s + "--map";
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                Toast.makeText(RXandroidActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void justAction1_Map()
    {
        Observable.just("just").map(new Func1<String, String>()
        {
            @Override
            public String call(String s)
            {
                return s+"--map";
            }
        }).subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                Toast.makeText(RXandroidActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void justAction1()
    {
        Observable.just("just").subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                Toast.makeText(RXandroidActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpleDemo()
    {
       Observable ob= Observable.create(new Observable.OnSubscribe<String>()
       {
           @Override
           public void call(Subscriber<? super String> subscriber)
           {
               subscriber.onNext("Nihao ");
           }
       });
//        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber sub= new Subscriber()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(Object o)
            {
                Log.d("Subscriber",o.toString());
                Toast.makeText(RXandroidActivity.this,o.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        ob.subscribe(sub);
    }
}
