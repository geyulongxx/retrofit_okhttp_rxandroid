package com.ivwen.retrofitokhttprxandroid.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by meipian on 16/6/15.
 */
public class Update
{
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

    private String user_id;
    private String token;
    private String article_id;
    private String title;
    private String cover_img_url;
    private String music_url;
    private String music_desc;
    private String theme;
    @SerializedName("abstract")
    private String abstract1;

    private List<UpdateItem> content;


    public Update(String user_id, String token, String article_id, String title, String cover_img_url, String music_url, String music_desc, String theme, String abstract1, List<UpdateItem> content)
    {
        this.user_id = user_id;
        this.token = token;
        this.article_id = article_id;
        this.title = title;
        this.cover_img_url = cover_img_url;
        this.music_url = music_url;
        this.music_desc = music_desc;
        this.theme = theme;
        this.abstract1 = abstract1;
        this.content = content;
    }
}
