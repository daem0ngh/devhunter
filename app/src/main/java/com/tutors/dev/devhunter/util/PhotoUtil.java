package com.tutors.dev.devhunter.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.SparseArray;


import com.tutors.dev.devhunter.BuildConfig;
import com.tutors.dev.devhunter.MyApp;
import com.tutors.dev.devhunter.data.Key;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoUtil
{
	private static final String TAG = "PhotoUtil" + Key.DEBUG_TAG;

	private static SparseArray<Bitmap> bitmapCache;
	
	public static Bitmap decode_maxLength(String strFilePath, int pxMaxLength)
	{
	    try
	    {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(strFilePath, o);
	        
	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxLength/2)나 (pxMaxArea/4)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
	        int scale=1;
			int width = o.outWidth;
			int height = o.outHeight;
			while (width >= pxMaxLength && height > pxMaxLength) {
				width /= 2;
				height /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;
	        
	        Bitmap bmp = BitmapFactory.decodeFile(strFilePath, o);
//        	int degree = getExifOrientation(strFilePath);
//        	return getRotatedBitmap(bmp, degree);
			return bmp;
	    }
	    catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}
	
	public static Bitmap decode_maxLength(byte[] data, int pxMaxLength)
	{
	    try
	    {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, o);

	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxLength/2)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
	        int scale=1;
			int width = o.outWidth;
			int height = o.outHeight;
			while (width >= pxMaxLength && height > pxMaxLength) {
				width /= 2;
				height /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;
	        
	        return BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, o);
	    }
	    catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}

	public static Bitmap decode_maxArea(String strFilePath, int pxMaxArea)
	{
		ExLog.v(TAG, "decode_maxArea, strFilePath:"+strFilePath);
	    try
	    {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(strFilePath, o);
	        
	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxLength/2)나 (pxMaxArea/4)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
	        int scale = 1;
			int width = o.outWidth;
			int height = o.outHeight;
	        while (width * height > pxMaxArea) {
				width /= 2;
				height /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;
	        
	        Bitmap bmp = BitmapFactory.decodeFile(strFilePath, o);
//        	int degree = getExifOrientation(strFilePath);
//        	return getRotatedBitmap(bmp, degree);
			return bmp;
	    }
		catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}
	
	public static Bitmap decode_maxArea(byte[] data, int pxMaxArea)
	{
	    try
	    {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, o);

	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxArea/4)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
			int scale = 1;
			int width = o.outWidth;
			int height = o.outHeight;
			while (width * height > pxMaxArea) {
				width /= 2;
				height /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;
	        
	        return BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, o);
	    }
	    catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}
	
	public static Bitmap decode_maxWidth(String strFilePath, int pxMaxWidth)
	{
		ExLog.v(TAG, "decode_maxWidth, strFilePath:"+strFilePath);
	    try
	    {
			Bitmap bmp = getBitmapCache(strFilePath, null);
			if(bmp != null)
				return bmp;

	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(strFilePath, o);
	        
	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxLength/2)나 (pxMaxArea/4)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
			int scale = 1;
			int width = o.outWidth;
			while (width > pxMaxWidth) {
				width /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;

	        bmp = BitmapFactory.decodeFile(strFilePath, o);
//			int degree = getExifOrientation(strFilePath);
//			bmp = getRotatedBitmap(bmp, degree);
        	return getBitmapCache(strFilePath, bmp);
	    }
		catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}
	
	public static Bitmap decode_maxWidth(InputStream is, int pxMaxWidth)
	{
	    try
	    {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(is, null, o);
	        
	        // Find the correct scale value. It should be the power of 2.
	        // (pxMaxLength/2)나 (pxMaxArea/4)보다는 작아지게 하지 않는 최대 scale 값을 찾는다.
	        int scale = 1;
			int width = o.outWidth;
			while (width > pxMaxWidth) {
				width /= 2;
				scale *= 2;
			}

	        o.inJustDecodeBounds = false;
	        o.inSampleSize = scale;
	        o.inPurgeable = true;
	        o.inDither = true;
	        
	        Bitmap bmp = BitmapFactory.decodeStream(is, null, o);
	        return bmp;
	    }
		catch (OutOfMemoryError e) { ExLog.e(TAG, e.toString()); }
	    catch (Exception e) { ExLog.e(TAG, e.toString()); }
	    
	    return null;
	}

	public static void setExifOrientation(String path, int value)
	{
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(path);
			if(exif != null)
			{
				exif.setAttribute(ExifInterface.TAG_ORIENTATION, ""+value);
				exif.saveAttributes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static int getExifOrientation(String strFilePath)
	{
	    int degree = 0;
	    ExifInterface exif = null;
	    
	    try {
	        exif = new ExifInterface(strFilePath);
	    }
	    catch (IOException e) {
	        ExLog.e(TAG, e.toString());
	    }
	    
	    if (exif != null) 
	    {
	        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
			ExLog.v(TAG, "PhotoUtil, orientation:"+orientation);

	        if (orientation != -1) 
	        {
	            // We only recognize a subset of orientation tag values.
	            switch(orientation) {
	            case ExifInterface.ORIENTATION_ROTATE_90:	degree = 90;	break;
                case ExifInterface.ORIENTATION_ROTATE_180:	degree = 180;	break;
                case ExifInterface.ORIENTATION_ROTATE_270:	degree = 270;	break;
	            }
	        }
	    }
	    
	    return degree;
	}
	
	public synchronized static Bitmap getRotatedBitmap(Bitmap bitmap, int degrees)
	{
	    if (degrees == 0 || bitmap == null)
	    	return bitmap;

        Matrix m = new Matrix();
        m.setRotate(degrees, (float)bitmap.getWidth() / 2, (float)bitmap.getHeight() / 2);
        try 
        {
        	ExLog.d(TAG, "[BITMAP_ROTATING...] " + degrees + " degrees");
            Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            if (bitmap != b2) 
            {
            	bitmap.recycle();
            	bitmap = b2;
            }
            return bitmap;
        } 
        catch (OutOfMemoryError ex) {}
        catch (Exception e) {}
	    
	    return null;
	}

	public static Bitmap getBitmapCache(String key, Bitmap bmp)
	{
		if(bitmapCache == null)
			bitmapCache = new SparseArray<Bitmap>();

		Bitmap bitmap = null;
		if(key != null) {
			bitmap = bitmapCache.get(key.hashCode());
			if(bitmap == null) {
				bitmapCache.put(key.hashCode(), bmp);
				bitmap = bmp;
				ExLog.i(TAG,"[PhotoUtil] Add MemCache!!");
			}
		}
		return bitmap;
	}

	public static Bitmap getBitmapCache(String uri)
	{
		Bitmap bitmap = null;
		if(bitmapCache == null)
			return bitmap;

		ExLog.i(TAG,"[PhotoUtil] Hit! MemCache!!");
		return bitmapCache.get(uri.hashCode());
	}

	public static File setBitmapCompressAndResizing(String strPath)
	{
		File file = new File(Key.PATH_KIDSNOTE_TEMP + StringUtil.getFileNameFromFullPath(strPath));

		try
		{
			Bitmap bitmap = PhotoUtil.decode_maxArea(strPath, Key.MAX_UPLOAD_IMAGE_RESOLUTION);
			if (bitmap == null)
			{
				return null;
			}

			String[] exifStringKeys = {ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_DATETIME,
					ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_FLASH, ExifInterface.TAG_GPS_LATITUDE,
					ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE_REF,
					ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_APERTURE, ExifInterface.TAG_ISO,
					ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_WHITE_BALANCE, ExifInterface.TAG_GPS_PROCESSING_METHOD,

					//TIFF Tag Reference, Exif Tags (http://www.awaresystems.be/imaging/tiff/tifftags/privateifd/exif.html)
					"ExifVersion", "DateTimeOriginal", "DateTimeDigitized", "ExposureProgram", "SpectralSensitivity", "OECF",
					"ShutterSpeedValue", "ApertureValue", "BrightnessValue", "ExposureBiasValue", "MaxApertureValue", "SubjectDistance", "MeteringMode",
					"LightSource", "MakerNote", "UserComment"
			};

			// Get the exif from upload file
			ExifInterface exif = new ExifInterface(strPath);
			String[] exifStringValues = new String[exifStringKeys.length];
			for (int i = 0; i < exifStringKeys.length; i++) {
				exifStringValues[i] = exif.getAttribute(exifStringKeys[i]);
				if (BuildConfig.DEBUG) {
					ExLog.v(TAG, "exif, " + exifStringKeys[i] + ":\t" + exifStringValues[i]);
				}
			}

			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 94, ostream);
			ostream.close();
			ExLog.d(TAG, "[TEMP_FILE_CREATED] " + file.getAbsolutePath());

			// Save the exif to new file
			ExifInterface exif_new = new ExifInterface(file.getAbsolutePath());
			for (int i = 0; i < exifStringKeys.length; i++) {
				if (StringUtil.isNotNull(exifStringValues[i])) {
					exif_new.setAttribute(exifStringKeys[i], exifStringValues[i]);
				}
			}

			exif_new.saveAttributes();
		}
		catch (Exception e)
		{
			ExLog.e(TAG, e.toString());
			//ExLog.i("error", MyApp.get().getString(R.string.low_memory_err_msg));
			return null;
		}

		return file;
	}
}