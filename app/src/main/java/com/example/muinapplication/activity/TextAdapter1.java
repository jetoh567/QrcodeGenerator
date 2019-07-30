package com.example.muinapplication.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.muinapplication.R;
import com.example.muinapplication.bean.TextBean;

import java.util.List;

public class TextAdapter1 extends BaseAdapter {

    private Context mContext;
    private List<TextBean> mTextList;

    public TextAdapter1(Context context, List<TextBean> textList) {
        mContext = context;
        mTextList = textList;
    }

    @Override
    public int getCount() {
        return mTextList.size();
    }

    @Override
    public Object getItem(int i) {
        return mTextList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.write_form_1,null);

        final TextBean textBean = mTextList.get(i);

        //상세보기

        return view;
    }
}
