package com.ivwen.retrofitokhttprxandroid.all;

/**
 * Created by meipian on 16/6/16.
 */
public class article
{
//    "id": "15646",
//        "domain": "a.meipian.me",
//        "article_id": "2rnac4e",
//        "title": "走过的路……",
//        "cover_img_url": "http:\/\/static2.ivwen.com\/users\/1026823\/dd9623422bb04fd7b9220d24db07de2a.jpg-cover",
//        "create_time": "1466007575",
//        "visit_count": "85",
//        "praise_count": "8",
//        "comment_count": "0",
//        "author": "随意而安  摄影／文字",
//        "author_id": "1026823",
//        "author_head": "http:\/\/static2.ivwen.com\/users\/1026823\/aa2d8efc00254036b025c62e3bbf4671.jpg",
//        "rcmd_state": "3",
//        "category_id": "1203"

    public String id;
    public String domain;
    public String article_id;
    public String title;
    public String cover_img_url;
    public String create_time;
    public String visit_count;
    public String praise_count;
    public String comment_count;
    public String author;
    public String author_id;
    public String author_head;
    public String rcmd_state;
    public String category_id;

    @Override
    public String toString()
    {
        return "article{" +
            "id='" + id + '\'' +
            ", domain='" + domain + '\'' +
            ", article_id='" + article_id + '\'' +
            ", title='" + title + '\'' +
            ", cover_img_url='" + cover_img_url + '\'' +
            ", create_time='" + create_time + '\'' +
            ", visit_count='" + visit_count + '\'' +
            ", praise_count='" + praise_count + '\'' +
            ", comment_count='" + comment_count + '\'' +
            ", author='" + author + '\'' +
            ", author_id='" + author_id + '\'' +
            ", author_head='" + author_head + '\'' +
            ", rcmd_state='" + rcmd_state + '\'' +
            ", category_id='" + category_id + '\'' +
            '}';
    }
}
