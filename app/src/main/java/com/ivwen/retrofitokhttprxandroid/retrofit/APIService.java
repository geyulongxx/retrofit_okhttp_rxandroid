package com.ivwen.retrofitokhttprxandroid.retrofit;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by meipian on 16/6/15.
 */
public interface APIService
{
    @GET("service/getIpInfo.php")
    Call<IpInfo> getIpinfo(@Query("ip") String ip);

    @GET("http://www.baidu.com")
    Call<ResponseBody> getHtml();

//    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("sms/fetch")
    Call<ResponseBody>  getSMS(@Body RequestBody json);

    // category/list {"user_id":"2054095","token":"339e8c14d2d38838efac6caaaae6ebf4","max_id":0,"category_id":1100}
    @POST("category/list")
    Call<ResponseBody> getCategory(@Body RequestBody json);



    @POST("article/update")
    Call<ResponseBody> update(@Body MeipianRequestBody json);


    @GET("service/getIpInfo.php")
    Observable<IpInfo> getIpinfoObs(@Query("ip") String ip);
}
