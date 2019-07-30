package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.TextBean;
import com.example.muinapplication.db.FileDB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FreeSpaceActivity extends AppCompatActivity {

    private ListView mLstText;
    private TextAdapter1 mAdapter;
    public List<TextBean> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_space);

        Button btnWrite = findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewTextActivity.class);
                startActivity(i);
            }
        });

        mLstText = findViewById(R.id.lstFree);
        mAdapter = new TextAdapter1(getApplicationContext(), textList);
        mLstText.setAdapter(mAdapter);
    }// end Oncreate

    @Override
    protected void onResume() {
        super.onResume();


    }
}// end class
