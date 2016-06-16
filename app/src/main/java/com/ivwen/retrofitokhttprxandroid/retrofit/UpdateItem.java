package com.ivwen.retrofitokhttprxandroid.retrofit;

/**
 * Created by meipian on 16/6/15.
 */
public class UpdateItem
{
    //    image.put("text","1234");
    //        image.put("img_url","http://static2.ivwen.com/users/2054095/c7cff5353638439f91073429239fb46f.jpg");
    //        image.put("img_width","124");
    //        image.put("img_height","400");
    public String text;
    public String img_url;
    public String img_width;
    public String img_height;

    public UpdateItem(String text, String img_url, String img_width, String img_height)
    {
        this.text = text;
        this.img_url = img_url;
        this.img_width = img_width;
        this.img_height = img_height;
    }
}
