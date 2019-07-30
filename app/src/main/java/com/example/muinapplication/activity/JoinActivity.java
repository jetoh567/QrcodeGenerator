package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.MemberBean;
import com.example.muinapplication.db.FileDB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {

    private EditText mEdtId, mEdtName, mEdtPwd1, mEdtPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mEdtName = findViewById(R.id.edtName);
        mEdtId = findViewById(R.id.edtId);
        mEdtPwd1 = findViewById(R.id.edtPwd1);
        mEdtPwd2 = findViewById(R.id.edtPwd2);

        findViewById(R.id.btnReg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinProcess();
            }
        });
    }// end Oncreate

    private void joinProcess() {
        MemberBean memberBean = new MemberBean();
        memberBean.memName = mEdtName.getText().toString();
        memberBean.memId = mEdtId.getText().toString();

        String pw1 = mEdtPwd1.getText().toString();
        String pw2 = mEdtPwd2.getText().toString();

        if(TextUtils.isEmpty(memberBean.memId)){
            Toast.makeText(this,"회원 아이디를 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!TextUtils.equals(pw1,pw2)){
            Toast.makeText(this,"패스워드 불일치",Toast.LENGTH_SHORT).show();
            return;
        }

        MemberBean findMemBean = FileDB.getFindMember(this, memberBean.memId);

        if(findMemBean != null){
            Toast.makeText(this,"입력하신 아이디는 이미 존재합니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        memberBean.memPwd = pw1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        memberBean.memDate = sdf.format(new Date());

        FileDB.addMember(this,memberBean);
        Toast.makeText(this,"회원가입이 완료 되었습니다.",Toast.LENGTH_SHORT).show();

        finish();
    }
}// end class
