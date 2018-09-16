package com.wang.View;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.myapplication.R;
import com.wang.Data.HttpJson;
import com.wang.Data.OnHttpPostListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class Products extends Activity{

    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        content = (TextView)findViewById(R.id.content);
        test();
    }

   public Handler   handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    content.setText( msg.obj.toString());
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public void test(){
        new Thread(new Runnable() {//创建子线程
            @Override
            public void run() {
                try {
                    Log.i("","1111111");
                    GetJson();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("","IOException:"+e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("","JSONException:"+e.toString());
                }
            }
        }).start();//启动子线程
    }
    public void GetJson() throws IOException, JSONException {
        //服务器地址
//        String urlPath = "https://www.baidu.com/";
        //1,创建URL
        URL url = new URL("https://www.baidu.com/");//放网站
        //2,openConnection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //3，InputStream
        InputStream inputStream = httpURLConnection.getInputStream();
        //4，InputStreamReader
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        //5，水BufferedReader
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String temp = null;
        while ((temp = bufferedReader.readLine()) != null) {
            //如果不为空就一直取
            buffer.append(temp);
        }
        //这里需要分析服务器回传的json格式数据，
        JSONObject jsonObject = new JSONObject(buffer.toString());
        String code = jsonObject.getString("CODE");
        String msg = jsonObject.getString("MSG");
        code = URLDecoder.decode(code, "utf8");//将数据转换成UTF-8的格式
        msg= URLDecoder.decode(msg, "utf8");
        //解析LIST数组中的数据
        JSONArray jsonArray = jsonObject.getJSONArray("LIST");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = (JSONObject) jsonArray.opt(i);
            String news_date = json.getString("news_date");
            String id = json.getString("id");
            String title = json.getString("title");
            news_date= URLDecoder.decode(news_date, "utf8");
            id= URLDecoder.decode(id, "utf8");
            title= URLDecoder.decode(title, "utf8");
        }
        bufferedReader.close();//记得关闭
        reader.close();
        inputStream.close();
    }
}
