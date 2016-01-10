package com.tutors.dev.devhunter.network.common;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.tutors.dev.devhunter.network.common.FileBase;

import java.io.File;

/**
 * Created by shs on 2015-04-14.
 */
public class ImageInfo extends FileBase {

    @Expose
    public Integer width;
    @Expose
    public Integer height;
    @Expose
    public String original;
    @Expose
    public String large;
    @Expose
    public String small;

    //inner
    public int resolutionType = -1;  // resizingType

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public String getOriginal_file_path()
    {
        return original_file_path;
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
        this.width = null;
        this.height = null;
        this.original = null;
        this.large = null;
        this.small = null;
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
        return null;
    }

    @Override
    public String getStreamingUrl() {
        return null;
    }

    public ImageInfo()
    {
        requestInit();
    }

//    /**
//     * video 일 경우 accesskey 가 존재하고, 이미지일경우 original,large,small url 주소형태로 존재.
//     * @return
//     */
//    public String getThumbnailUrl()
//    {
//        if(small == null)
//            return KageFileManager.getImageThumbnailUrl(access_key);
//
//        return this.small;
//    }
//
//    public String getImageUrl()
//    {
//        if(large == null)
//            return KageFileManager.getImageUrl(access_key);
//
//        return this.large;
//    }
//
//    public String getOriginalImageUrl()
//    {
//        if(original == null)
//            return KageFileManager.getImageOriginalUrl(access_key);
//
//        return this.original;
//    }
//
//    public String getStreamingUrl()
//    {
//        return KageFileManager.getStreamingURL(access_key,KageFileManager.KAGE_VIDEO_HIGH);
//    }
//
//
//    public void resizingCropImage()
//    {
//        File convertFile = this.file;
//        if(convertFile != null && convertFile.getAbsolutePath() != null)
//            convertFile = PhotoUtil.setBitmapCompressAndResizing(file.getAbsolutePath());
//
//        this.file = convertFile;
//        Log.i("<Imageinfo>", "convertFile=" + this.file.getName());
//    }




}
