package com.tutors.dev.devhunter.network.common;

import com.google.gson.annotations.Expose;

import java.io.File;

/**
 * Created by shs on 2015-04-14.
 */
public class FileInfo extends FileBase {

    @Expose
    public String original;

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void requestInit()
    {
        this.id = null;
        this.file = null;
        this.original_file_path = null;
        this.original_file_name = null;
        this.file_size = null;
        this.file = null;
        this.original = null;
    }

    @Override
    public String getThumbnailUrl() {
        return null;
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getOriginalImageUrl() {
        return this.original;
    }

    @Override
    public String getStreamingUrl() {
        return null;
    }

    public FileInfo()
    {
        requestInit();
    }



}
