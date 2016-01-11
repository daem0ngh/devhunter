package com.tutors.dev.devhunter;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tutors.dev.devhunter.network.ApiManager;
import com.tutors.dev.devhunter.network.ApiService;
import com.tutors.dev.devhunter.util.DeviceUtil;

/**
 * Created by shs on 2016-01-09.
 */
public class MyApp extends Application {

    private static MyApp myApp;
    public static ApiService mApiService;
    public static DisplayImageOptions mDIOThumbPhoto, mDIOThumbEmpty;

    public static Context get()	{ return myApp; }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        //Fabric.with(this, new Crashlytics());
        mApiService = ApiManager.rebuildAdapter();

        initAndroidUniversalImageLoader();
    }

    private void initAndroidUniversalImageLoader() {
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this)
//				.memoryCache(new LruMemoryCache(cacheSize))
//				.memoryCacheSize(cacheSize)
//				.memoryCacheExtraOptions(DeviceUtil.mScreenWidth, DeviceUtil.mScreenHeight) // default = device screen dimensions
//				.memoryCacheSizePercentage(13) // default
                //.diskCache(new UnlimitedDiskCache(cacheDir)) // default
                //.diskCacheFileCount(100)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheExtraOptions(DeviceUtil.mScreenWidth, DeviceUtil.mScreenHeight, null)
                .threadPoolSize(3) // default (3)
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.LIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                ;
        if (BuildConfig.DEBUG) {
            //builder.writeDebugLogs();
        }
        ImageLoader.getInstance().init(builder.build());

        DisplayImageOptions.Builder DIOBuilder = new DisplayImageOptions.Builder()
//				.cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888);
        mDIOThumbEmpty = DIOBuilder
                .build();
        mDIOThumbPhoto = DIOBuilder
                .showImageOnLoading(R.drawable.button_shape_photobg) // 로딩중에 나타나는 이미지
                .showImageForEmptyUri(R.mipmap.profile01_adult) // 값이 없을때
                .showImageOnFail(R.mipmap.profile01_adult) // 에러 났을때 나타나는 이미지
                .build();

    }
}
