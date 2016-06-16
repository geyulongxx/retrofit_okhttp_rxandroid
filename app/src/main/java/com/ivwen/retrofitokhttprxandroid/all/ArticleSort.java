package com.ivwen.retrofitokhttprxandroid.all;

import java.util.Comparator;

/**
 * Created by meipian on 16/6/16.
 */
public class ArticleSort implements Comparator<article>
{

    @Override
    public int compare(article lhs, article rhs)
    {
        return lhs.article_id.compareTo(rhs.article_id);
    }
}
