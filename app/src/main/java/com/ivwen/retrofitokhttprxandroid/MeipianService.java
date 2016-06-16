package com.ivwen.retrofitokhttprxandroid;

import android.util.Log;

import com.ivwen.retrofitokhttprxandroid.all.ArticleResponse;
import com.ivwen.retrofitokhttprxandroid.all.ArticleSort;
import com.ivwen.retrofitokhttprxandroid.all.MeiPianRequest;
import com.ivwen.retrofitokhttprxandroid.retrofit.MeipianRequestBody;

import java.util.Collections;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by meipian on 16/6/16.
 */
public class MeipianService
{

    public void getArticle(HashMap hashMap,Subscriber<ArticleResponse> subscriber){

        Observable<ArticleResponse> observable= Utils.getRetrofit().create(MeiPianRequest.class).getCategory(new MeipianRequestBody(hashMap));

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<ArticleResponse, Observable<ArticleResponse>>()
        {
            @Override
            public Observable<ArticleResponse> call(ArticleResponse articleResponse)
            {
                Log.d("org",articleResponse.code);
                Log.d("org",articleResponse.articles.toString());
                Collections.sort(articleResponse.articles,new ArticleSort());
                return Observable.just(articleResponse);
            }
        }).subscribe(subscriber);

    }


}
