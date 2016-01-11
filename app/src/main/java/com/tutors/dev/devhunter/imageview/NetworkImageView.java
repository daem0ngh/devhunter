/**
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tutors.dev.devhunter.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tutors.dev.devhunter.MyApp;
import com.tutors.dev.devhunter.data.Key;
import com.tutors.dev.devhunter.network.common.ImageInfo;
import com.tutors.dev.devhunter.util.DeviceUtil;
import com.tutors.dev.devhunter.util.StringUtil;


import java.io.File;

/**
 * Handles fetching an image from a URL as well as the life-cycle of the
 * associated request.
 */
public class NetworkImageView extends ImageView {
	private static final String TAG = "NetworkImageView" + Key.DEBUG_TAG;
	private String url;

		public NetworkImageView(Context context) {
		super(context);
	}

	public NetworkImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public void setVideoThumbnail(int id) {
		Bitmap bmp = MediaStore.Video.Thumbnails.getThumbnail(MyApp.get().getContentResolver(), id, MediaStore.Video.Thumbnails.MICRO_KIND, null);
		setImageBitmap(bmp);
	}

	public void setThumbnailFromVideoFile(String path, DisplayImageOptions options) {
		ImageLoader.getInstance().displayImage(Uri.fromFile(new File(path)).toString(), this, options);
	}

	public void setImageUrl(String url, DisplayImageOptions options) {
		setImageUrl(url, options, null);
	}

	public void setImageUrl(ImageInfo imageinfo) {
		if(imageinfo != null && imageinfo.access_key != null)
			setImageUrl(imageinfo.getThumbnailUrl(), MyApp.mDIOThumbPhoto);
		else
			setImageUrl(null, MyApp.mDIOThumbPhoto);
	}

	public void setImageUrl(String url, DisplayImageOptions options, ProgressBar progressBar) {

		if (StringUtil.isNotNull(url)) {

			File imageFile = new File(url);
			if (!url.startsWith("http") && imageFile.exists()) {
				url = "file://" + url;
			}
//			if (url.equals(this.url)) {
//				//동일한 url을 set할때 refresh 시 화면 깜빡이는게 보인는거 같은데...
//				return;
//			}
		}
		ImageLoader.getInstance().displayImage(url, this, options);
		this.url = url;
	}

	/**
	 * 다운 받는 URL의 이미지를 wrap_content로 뿌리는 경우, device density에 따라 이미지 리사이즈가 필요하므로, 사용함
	 * @param url
	 * @param density
	 * @param options
	 */
	public void setImageUrl(final String url, final float density, final DisplayImageOptions options) {

		ImageLoader.getInstance().displayImage(url, this, options, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				setImageBitmap(loadedImage);
				setResize(loadedImage, density);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
			}
		});
	}

	public void setResize(final Bitmap loadedImage, float imageDensity) {
		float deviceDensity;
		if (DeviceUtil.mDispMetrics == null || loadedImage == null) {
			return;
		}
		deviceDensity = DeviceUtil.mDispMetrics.density;
		if (imageDensity != deviceDensity) {
			ViewGroup.LayoutParams lp = getLayoutParams();
			lp.width = (int)(loadedImage.getWidth() * deviceDensity / imageDensity);
			lp.height = (int)(loadedImage.getHeight() * deviceDensity / imageDensity);
			setLayoutParams(lp);
		}
	}

}