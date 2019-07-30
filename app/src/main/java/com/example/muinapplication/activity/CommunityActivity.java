package com.example.muinapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.muinapplication.R;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        //자유게시판 버튼
        Button btnFreeSpace = findViewById(R.id.btnFreeSpace);
        btnFreeSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FreeSpaceActivity.class);
                startActivity(i);
            }
        });

        //신고게시판 버튼
        Button btnNoticeSpace = findViewById(R.id.btnNoticeSpace);
        btnNoticeSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NoticeSpaceActivity.class);
                startActivity(i);
            }
        });

        //후기게시판 버튼
        Button btnReviewSpace = findViewById(R.id.btnReviewSpace);
        btnReviewSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ReivewSpaceActivity.class);
                startActivity(i);
            }
        });

        //질문게시판 버튼
        Button btnQuestionSpace = findViewById(R.id.btnQuestionSpace);
        btnQuestionSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), QuestionSpaceActivity.class);
                startActivity(i);
            }
        });
    }// end Oncreate
}// end class
