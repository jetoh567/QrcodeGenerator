package com.example.muinapplication.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.muinapplication.bean.MemberBean;
import com.example.muinapplication.bean.TextBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class FileDB {
    private static final String FILE_DB = "FileDB";
    private static Gson mGson = new Gson();

    private static SharedPreferences getSP(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_DB, Context.MODE_PRIVATE);
        return sp;
    }

    public static List<MemberBean> getMemberList(Context context) {
        String listStr = getSP(context).getString("memberList", null);
        //저장된 리스트가 없을 경우에 새로운 리스트를 리턴한다.
        if(listStr == null) {
            return new ArrayList<MemberBean>();
        }
        //Gson 으로 변환한다.
        List<MemberBean> memberList =
                mGson.fromJson(listStr, new TypeToken<List<MemberBean>>(){}.getType() );
        return memberList;
    }

    //새로운 이용자 추가
    public static void addMember(Context context, MemberBean memberBean) {
        List<MemberBean> memberList = getMemberList(context);
        memberList.add(memberBean);
        String listStr = mGson.toJson(memberList);
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", listStr);
        editor.commit();
    }

    //기존 이용자 교체
    public static void setMember(Context context, MemberBean memberBean) {
        List<MemberBean> memberList = getMemberList(context);
        if(memberList.size() == 0) return;

        for(int i=0; i<memberList.size(); i++) {
            MemberBean bean = memberList.get(i);
            if(TextUtils.equals(bean.memId, memberBean.memId)) {
                memberList.set(i, memberBean);
                break;
            }
        }
        String jsonStr = mGson.toJson(memberList);
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", jsonStr);
        editor.commit();
    }

    public static MemberBean getFindMember(Context context, String memId) {
        //1.멤버리스트를 가져온다
        List<MemberBean> memberList = getMemberList(context);
        //2.for 문 돌면서 해당 아이디를 찾는다.
        for(MemberBean bean : memberList) {
            if(TextUtils.equals(bean.memId, memId)) { //아이디가 같다.
                //3.찾았을 경우는 해당 MemberBean 을 리턴한다.
                return bean;
            }
        }
        //3-2.못찾았을 경우는??? null 리턴
        return null;
    }

    public static void setLoginMember(Context context, MemberBean bean) {
        if(bean != null) {
            String str = mGson.toJson(bean);
            SharedPreferences.Editor editor = getSP(context).edit();
            editor.putString("loginMemberBean", str);
            editor.commit();
        }
    }

    public static MemberBean getLoginMember(Context context) {
        String str = getSP(context).getString("loginMemberBean", null);
        if(str == null) return null;
        MemberBean memberBean = mGson.fromJson(str, MemberBean.class);
        return getFindMember(context, memberBean.memId);
    }

    public static void addText(Context context, String memId, TextBean textBean) {
        MemberBean findMember = getFindMember(context, memId);
        if(findMember == null) return;

        List<TextBean> textList = findMember.textList;
        if(textList == null) {
            textList = new ArrayList<>();
        }
        //고유 메모 ID 를 생성해준다.
        textBean.textId = System.currentTimeMillis();
        textList.add(textBean);
        findMember.textList = textList;
        //저장
        setMember(context, findMember);
    }

    public static void setText(Context context, TextBean textBean) {
        MemberBean memberBean = getLoginMember(context);
        if(memberBean == null || memberBean.textList== null) {
            return;
        }
        List<TextBean> textList = memberBean.textList;
        for(int i=0; i<textList.size(); i++) {
            TextBean mBean = textList.get(i);
            if(mBean.textId == textBean.textId) {
                //찾았다.
                textList.set(i, textBean); //교체
                break;
            }
        }
        //업데이트된 메모 리스트를 저장
        memberBean.textList = textList;
        setMember(context, memberBean);
    }

    public static void delText(Context context, long textId) {
        MemberBean memberBean = getLoginMember(context);
        List<TextBean> textList = memberBean.textList;
        if(textList == null) return;

        for(int i=0; i<textList.size(); i++) {
            TextBean mBean = textList.get(i);
            if(mBean.textId == textId) {
                //찾았다.
                textList.remove(i);
                break;
            }
        }
        //저장
        memberBean.textList = textList;
        setMember(context, memberBean);
    }

    public static TextBean getText(Context context, long textId) {
        MemberBean memberBean = getLoginMember(context);
        List<TextBean> textList = memberBean.textList;
        if(textList == null) return null;

        for(TextBean bean : textList) {
            if(bean.textId == textId) {
                //찾았다.
                return bean;
            }
        }
        return null;
    }

    public static List<TextBean> getTextList(Context context) {
        MemberBean memberBean = getLoginMember(context);
        if(memberBean == null) return null;

        if(memberBean.textList == null) {
            return new ArrayList<>();
        }
        else {
            return memberBean.textList;
        }
    }

}// end class
