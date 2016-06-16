package com.ivwen.retrofitokhttprxandroid.all;

import java.util.ArrayList;

/**
 * Created by meipian on 16/6/16.
 */
public class ArticleResponse
{
    public ArrayList<article >  articles;

    public String code;

    @Override
    public String toString()
    {
        return "ArticleResponse{" +
            "articles=" + articles +
            ", code='" + code + '\'' +
            '}';
    }
}
