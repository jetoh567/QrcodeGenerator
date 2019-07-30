package com.example.muinapplication.bean;

import java.io.Serializable;

public class TextBean implements Serializable {
    public int space; // 1:자유게시판 2: 신고게시판 3: 후기게시판 4: 질문게시판
    public long textId;
    public String txtTitle;
    public String txtDesc;
    public String userName;
    public String regdate;
}
