package com.tutors.dev.devhunter.network.common;

import com.google.gson.annotations.Expose;

import java.io.File;

/**
 * Created by shs on 2015-04-14.
 */
public abstract class FileBase extends CommonClass{

    public static int FILE_TYPE_VIDEO = 0;
    public static int FILE_TYPE_IMAGE = 1;
    public static int FILE_TYPE_FILE  = 2;

    public static String URI_TYPE_CONTENT   = "content";
    public static String URI_TYPE_FILE      = "file";
    public static int URI_NOT_CONTENT_URI   = -1;

    @Expose
    public Integer id;
    @Expose
    public String access_key;
    @Expose
    public String original_file_name;
    @Expose
    public String original_file_path;
    @Expose
    public Long file_size;

    //inner api
    public File file;
    public int mFileType;
    public int sequence;
    public Integer uploadStatus;

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public void requestInit()
    {
        this.id = null;
        this.access_key = null;
        this.original_file_path = null;
        this.original_file_name = null;
    }

    public abstract String getThumbnailUrl();

    public abstract String getImageUrl();

    public abstract String getOriginalImageUrl();

    public abstract String getStreamingUrl();

    public FileBase()
    {
        requestInit();
    }


}
