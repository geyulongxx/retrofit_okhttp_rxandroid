package com.ivwen.retrofitokhttprxandroid.retrofit;

/**
 * Created by meipian on 16/6/15.
 */
public class Bean
{
    //        jsonObject.put("user_id","2054095");
    //        jsonObject.put("token","339e8c14d2d38838efac6caaaae6ebf4");
    //        jsonObject.put("max_id", "0");
    //        jsonObject.put("category_id", "1100");
    public String user_id;
    public String token;
    public String max_id;
    public String category_id;

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public Bean(String user_id, String token, String max_id, String category_id)
    {
        this.user_id = user_id;
        this.token = token;
        this.max_id = max_id;
        this.category_id = category_id;
    }
}
