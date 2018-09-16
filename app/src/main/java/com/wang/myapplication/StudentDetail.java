package com.wang.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.myapplication.R;


public class StudentDetail extends Activity implements View.OnClickListener {
    Button btnSava, btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    private int _Student_ID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSava = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        editTextName = (EditText)findViewById(R.id.etName);
        editTextEmail = (EditText)findViewById(R.id.etEmail);
        editTextAge = (EditText)findViewById(R.id.etAge);

        btnSava.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Student_ID = 0;
        Intent intent = new Intent();
        _Student_ID = intent.getIntExtra("student_Id",0);
        StudentRepo repo = new StudentRepo(this);

        Student student;
        student = repo.getStudentById(_Student_ID);

        editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(student.name);
        editTextEmail.setText(student.email);
    }


    @Override
    public void onClick(View v) {
        StudentRepo repo = new StudentRepo(this);
        if (v == findViewById(R.id.btnSave)){
            Student student = new Student();
            student.age = Integer.parseInt(editTextAge.getText().toString());
            student.email = editTextEmail.getText().toString();
            student.name = editTextName.getText().toString();
            student.student_ID = _Student_ID;

            if(_Student_ID==0){
                _Student_ID = repo.insert(student);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }else{
                repo.update(student);
                Toast.makeText(this,"Student Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (v == findViewById(R.id.btnDelete)){
            repo.delete(_Student_ID);
            Toast.makeText(this,"Student Record deleted",Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == findViewById(R.id.btnClose)){
            finish();
        }
    }
}
