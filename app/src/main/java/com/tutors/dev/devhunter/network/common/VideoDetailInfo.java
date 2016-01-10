package com.tutors.dev.devhunter.network.common;

import android.net.Uri;

import com.google.gson.annotations.Expose;

/**
 * Created by shs on 2015-05-08.
 */
public class VideoDetailInfo {

    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public Long duration;
    @Expose
    public Uri localUri;
    @Expose
    public Boolean uploaded;
    @Expose
    public long date;

}
