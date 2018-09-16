package com.wang.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.myapplication.R;
import com.wang.Data.UserLoginModel;
import com.wang.sqldemo.UserLoginDB;
import java.util.List;

public class LoginHistory extends Activity {
    private List<UserLoginModel> list;
    private MyAdapter myAdapter;
    private ListView listView;
    private UserLoginDB userLoginList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);
        listView = (ListView) findViewById(R.id.login_history);
        userLoginList = new UserLoginDB(this);
        list = userLoginList.queryAllLogin();
        LoginHistory();
    }


    private void LoginHistory() {
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(LoginHistory.this, R.layout.history_item, null);
            TextView userNameTxt = (TextView) convertView.findViewById(R.id.user);
            TextView loginTypeTxt = (TextView) convertView.findViewById(R.id.description);
            TextView loginTimeTxt = (TextView) convertView.findViewById(R.id.date);
            userNameTxt.setText(list.get(position).getUserName());
            loginTypeTxt.setText(list.get(position).getLogintype().toString());
            loginTimeTxt.setText(list.get(position).getLoginTime());
            return convertView;
        }
    }
}
