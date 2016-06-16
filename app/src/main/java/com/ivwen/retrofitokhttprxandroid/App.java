package com.ivwen.retrofitokhttprxandroid;

import android.app.Application;

/**
 * Created by meipian on 16/6/16.
 */
public class App extends Application
{

    public  static  App instance=null;
    @Override
    public void onCreate()
    {
        instance=this;
        super.onCreate();
    }
}
