package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.TextBean;

import java.util.ArrayList;
import java.util.List;

public class ReivewSpaceActivity extends AppCompatActivity {

    private ListView mLstWrite;
    private TextAdapter2 mAdapter;
    public List<TextBean> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reivew_space);

        Button btnWrite = findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewTextActivity.class);
                startActivity(i);
            }
        });

        mLstWrite = findViewById(R.id.lstReview);
        mAdapter = new TextAdapter2(getApplicationContext(), textList);
        mLstWrite.setAdapter(mAdapter);
    }// end Oncreate
}// end class
