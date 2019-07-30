package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.MemberBean;
import com.example.muinapplication.bean.TextBean;
import com.example.muinapplication.db.FileDB;

import org.w3c.dom.Text;

import java.io.File;

public class NewTextActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);

        Spinner space = findViewById(R.id.space);
        Spinner city = findViewById(R.id.city);
        Spinner town = findViewById(R.id.town);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.spaces, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.cities, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.towns, R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        space.setAdapter(adapter1);
        space.setOnItemSelectedListener(this);

        city.setAdapter(adapter2);
        city.setOnItemSelectedListener(this);

        town.setAdapter(adapter3);
        town.setOnItemSelectedListener(this);

        //글 작성완료 버튼
        Button btnWriteComplete = findViewById(R.id.btnWriteComplete);
        btnWriteComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProc();
            }
        });

        //글작성 취소 버튼
        Button btnWriteCancel = findViewById(R.id.btnWriteCancel);
        btnWriteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }// end Oncreate

    private void saveProc() {
        String txtTitleStr = "";
        String txtDescStr = "";

        EditText edtTitle = findViewById(R.id.edtTitle);
        txtTitleStr = edtTitle.getText().toString();
        EditText edtDesc = findViewById(R.id.edtDesc);
        txtDescStr = edtDesc.getText().toString();

        TextBean textBean = new TextBean();

        if(txtTitleStr.equals("")) {
            Toast.makeText(this, "제목을 작성해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtDescStr.equals("")) {
            Toast.makeText(this,"내용을 입력해주세요", Toast.LENGTH_SHORT).show();
        }

        MemberBean loginMember = FileDB.getLoginMember(this);
        textBean.txtTitle = edtTitle.getText().toString();
        textBean.txtDesc = edtDesc.getText().toString();
        //textBean.userName = loginMember.memName;
        textBean.space = getIntent().getIntExtra("space",0);

        FileDB.addText(this, loginMember.memId, textBean);

        Toast.makeText(this, "글을 작성했습니다.", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //userNum = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}// end class
