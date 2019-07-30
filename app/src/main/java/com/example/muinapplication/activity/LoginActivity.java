package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.MemberBean;
import com.example.muinapplication.db.FileDB;

public class LoginActivity extends AppCompatActivity {
    private EditText mEdtId, mEdtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtId = findViewById(R.id.edtId);
        mEdtPwd = findViewById(R.id.edtPwd);

        //로그인 버튼
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        //회원가입 버튼
        Button btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(i);
            }
        });
    }// end Oncreate

    private void checkLogin() {
        MemberBean memberBean = FileDB.getFindMember(LoginActivity.this, mEdtId.getText().toString());

        if(memberBean == null) {
            Toast.makeText(LoginActivity.this,"해당 아이디는 가입이 되어있지 않습니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.equals(memberBean.memPwd, mEdtPwd.getText().toString())) {
            FileDB.setLoginMember(LoginActivity.this, memberBean);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            mEdtId.setText("");
            mEdtPwd.setText("");
        } else {
            Toast.makeText(LoginActivity.this, "패스워드를 잘못입력했습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}// end class
