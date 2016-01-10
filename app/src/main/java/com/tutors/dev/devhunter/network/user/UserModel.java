package com.tutors.dev.devhunter.network.user;


import com.google.gson.annotations.Expose;
import com.tutors.dev.devhunter.network.common.CommonClass;
import com.tutors.dev.devhunter.network.common.ImageInfo;
import com.tutors.dev.devhunter.util.StringUtil;

import java.util.Date;

public class UserModel extends CommonClass
{
    public static String USER_TYPE_ADMIN    = "admin";
    public static String USER_TYPE_TEACHER  = "teacher";
    public static String USER_TYPE_PARENT   = "parent";
    public static String USER_TYPE_UNKNOWN  = "unknown";

    @Expose
    public Integer id;
    @Expose
    public String type;
    @Expose
    public String description;
    @Expose
    public ImageInfo picture;

    //RW
    @Expose
    public String username;     //아이디. 영문자, 숫자, _만 입력가능. 5~32자 허용
    @Expose
    public String name;         //이름
    @Expose
    public String password;     //비밀번호. 6~20자 허용.
    @Expose
    public String country_code; //ISO 3166-1 alpha-2 형식으로 입력
    @Expose
    public String phone;        //숫자 형식만 입력 가능
    @Expose
    public String email;        //이메일 형식만 입력 가능
    @Expose
    public boolean subscription; //마케팅 수신 동의 여부. default: false
    @Expose
    public Date date_joined;    // 가입날짜

    public boolean isThmubnail()
    {
        return picture != null;
    }

    public void requestInit()
    {
        this.id = null;
        this.type = null;
        this.username = null;
        this.name = null;
        this.description = null;
        this.country_code = null;
        this.phone = null;
        this.email = null;
        this.picture = null;
        this.password = null;
        this.subscription = false;
    }

    public int getHashValue()
    {
        String plain = "";

        if(StringUtil.isNotNull(this.type))
            plain += this.type;
        if(StringUtil.isNotNull(this.description))
            plain += this.description;
        if(StringUtil.isNotNull(this.username))
            plain += this.username;
        if(StringUtil.isNotNull(this.name))
            plain += this.name;
        if(StringUtil.isNotNull(this.password))
            plain += this.password;
        if(StringUtil.isNotNull(this.country_code))
            plain += this.country_code;
        if(StringUtil.isNotNull(this.phone))
            plain += this.phone;
        if(StringUtil.isNotNull(this.email))
            plain += this.email;
        if(picture != null)
            plain += picture.access_key;

        return plain.hashCode();
    }




}

