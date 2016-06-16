package com.ivwen.retrofitokhttprxandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
        findViewById(R.id.bt_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_1:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.bt_2:
                startActivity(new Intent(this, OkhttpActivity.class));
                break;
            case R.id.bt_3:
                startActivity(new Intent(this, RXandroidActivity.class));
                break;
            case R.id.bt_4:
                startActivity(new Intent(this, AllActivity.class));
                break;
            default:
                break;
        }
    }
}
