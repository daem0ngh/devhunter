package com.tutors.dev.devhunter.network.common;

import com.google.gson.annotations.Expose;

import java.io.File;

/**
 * Created by shs on 2015-04-14.
 */
public class VideoInfo extends FileBase {

    @Expose
    public VideoDetailInfo detailinfo;
    @Expose
    public String source_type; // kage or youtube

    @Expose
    public String high;
    @Expose
    public String low;
    @Expose
    public String preview;
    @Expose
    public String preview_small;

    // deprecate
    public String imageUrl;
    public String thumbnail_url;

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
        this.detailinfo = null;
        this.original_file_path = null;
        this.original_file_name = null;
        this.file_size = null;
        this.file = null;
        this.source_type = null;

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

    public VideoInfo()
    {
        requestInit();
    }

//    /**
//     * video 일 경우 accesskey 가 존재하고, 이미지일경우 original,large,small url 주소형태로 존재.
//     * @return
//     */
//    public String getThumbnailUrl()
//    {
//        //return KageFileManager.getVideoThumbnailUrl(access_key);
//        return this.preview_small == null ? KageFileManager.getVideoThumbnailUrl(access_key) : this.preview_small;
//    }
//
//    public String getThumbnailOriginal()
//    {
//        //return KageFileManager.getVideoThumbnailUrl(access_key);
//        return this.preview == null ? KageFileManager.getVideoThumbnailOriginal(access_key) : this.preview;
//    }
//
//
//    public String getImageUrl()
//    {
//        return KageFileManager.getImageUrl(access_key);
//    }
//
//    @Override
//    public String getOriginalImageUrl() {
//        return null;
//    }
//
//    public String getStreamingUrl()
//    {
//        //return this.high == null ? KageFileManager.getStreamingURL(access_key,KageFileManager.KAGE_VIDEO_HIGH) : this.high;
//        if (KageFileManager.VIDEO_TYPE_YOUTUBE.equals(source_type)) {
//            return high;
//        } else {
//            return KageFileManager.getStreamingURL(access_key,KageFileManager.KAGE_VIDEO_HIGH);
        }
//    }




//}
