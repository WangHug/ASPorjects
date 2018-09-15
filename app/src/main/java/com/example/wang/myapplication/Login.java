package com.example.wang.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends Activity implements View.OnClickListener {
    private Button loginBtn;
    private EditText nameEdit, pwdEdit;
    private TextView locationTxt;

    /**
     * 模拟的假数据
     */
    private List<String> testData;
    /** popup窗口里的ListView */
    private ListView mTypeLv;
    /** 数据适配器 */
    private ArrayAdapter<String> testDataAdapter;
    /** popup窗口 */
    private PopupWindow typeSelectPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameEdit = (EditText) findViewById(R.id.login_name);
        pwdEdit = (EditText) findViewById(R.id.login_pwd);
        locationTxt = (TextView)findViewById(R.id.login_location);
        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        locationTxt.setOnClickListener(this);
    }

    private void TestData() {
        testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String str = new String("数据" + i);
            testData.add(str);
        }
    }

    private void initSelectPopup() {
        mTypeLv = new ListView(this);
        TestData();
        // 设置适配器
        testDataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData);
        mTypeLv.setAdapter(testDataAdapter);

        // 设置ListView点击事件监听
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = testData.get(position);
                // 把选择的数据展示对应的TextView上
                locationTxt.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background );
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if(nameEdit.getText().equals("")){
                    Toast.makeText(this,"请输入姓名",Toast.LENGTH_LONG );
                }else if(pwdEdit.getText().equals("")){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG );
                }else if(locationTxt.getText().equals("")){
                    Toast.makeText(this,"请输入地址",Toast.LENGTH_LONG );
                }else{
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
            case R.id.login_location:
                Toast.makeText(Login.this,"xxxxx", Toast.LENGTH_LONG);
                initSelectPopup();
                // 使用isShowing()检查popup窗口是否在显示状态
                if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
                    typeSelectPopup.showAsDropDown(locationTxt, 0, 10);
                }
                break;
        }
    }
}
