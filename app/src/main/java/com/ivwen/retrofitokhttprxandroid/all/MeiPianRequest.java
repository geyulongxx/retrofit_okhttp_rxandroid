package com.ivwen.retrofitokhttprxandroid.all;

import com.ivwen.retrofitokhttprxandroid.retrofit.MeipianRequestBody;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by meipian on 16/6/16.
 */
public interface MeiPianRequest
{

    @POST("category/list")
    Observable<ArticleResponse> getCategory(@Body MeipianRequestBody json);
}
