package com.wang.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wang.myapplication.R;

public class Main extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.stock_take:
                intent = new Intent(Main.this,Products.class);
                break;
            case R.id.bluetooth:
                intent = new Intent(Main.this,Bluetooth.class);
                break;
            case R.id.download:
                intent = new Intent(Main.this,Download.class);
                break;
            case R.id.upload:
                intent = new Intent(Main.this,Upload.class);
                break;
            case R.id.history:
                intent = new Intent(Main.this,LoginHistory.class);
                break;
            case R.id.logout:
                intent = new Intent(Main.this,Login.class);
                finish();
                break;
        }
        if(intent != null)
            startActivity(intent);
    }
}
