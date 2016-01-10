package com.tutors.dev.devhunter.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;


import com.tutors.dev.devhunter.MyApp;
import com.tutors.dev.devhunter.data.Key;
import com.tutors.dev.devhunter.network.common.FileBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

//import com.crittercism.app.Crittercism;

public class FileUtil
{
	private static final String TAG = "FileUtil" + Key.DEBUG_TAG;

	private static File copyFile(String inputDirPath, String inputFileName, String outputDirPath, String outputFileName)
	{
		Bundle retBundle = new Bundle();
		
		try
		{
			// create output directory if it doesn't exist
			File dir = new File(outputDirPath);
			if (!dir.exists())
				dir.mkdirs();
			
			outputFileName = getUniqueFileName(outputDirPath, outputFileName);

			InputStream in = new FileInputStream(inputDirPath + inputFileName);
			OutputStream out = new FileOutputStream(outputDirPath + outputFileName);
		
			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1)
				out.write(buffer, 0, read);

			in.close();

		    // write the output file (You have now copied the file)
		    out.flush();
		    out.close();

		    retBundle.putString("destPath", outputDirPath + outputFileName);
		}
		catch (Exception e)
		{
//			Crashlytics.logException(e);
			return null;
		}
		
		return new File(outputDirPath + outputFileName);
	}

	public static File copyFile(String inputFullPath, String outputDirPath, String outputFileName)
	{
		String dir = StringUtil.getDirPathFromFullPath(inputFullPath);
		String file = StringUtil.getFileNameFromFullPath(inputFullPath);
		return copyFile(dir, file, outputDirPath, outputFileName);
	}

	public static File copyFile(String inputFullPath, String outputDirPath)
	{
		ExLog.v(TAG, "copyFile : "+inputFullPath);
		String dir = StringUtil.getDirPathFromFullPath(inputFullPath);
		String file = StringUtil.getFileNameFromFullPath(inputFullPath);
		return copyFile(dir, file, outputDirPath, file);
	}

	public static boolean moveFile(String inputDirPath, String inputFileName, String outputDirPath)
	{
		InputStream in = null;
		OutputStream out = null;
		try
		{
			// create output directory if it doesn't exist
			File dir = new File(outputDirPath);
			if (!dir.exists())
			    dir.mkdirs();
			
			in = new FileInputStream(inputDirPath + inputFileName);
			out = new FileOutputStream(outputDirPath + inputFileName);
			
			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1)
			    out.write(buffer, 0, read);

			in.close();
			in = null;
			
			// write the output file
			out.flush();
			out.close();
			out = null;
			
			// delete the original file
			new File(inputDirPath + inputFileName).delete();
		} 
		catch (FileNotFoundException e)
		{
			ExLog.e(TAG, e.toString());
			return false;
		}
		catch (Exception e)
		{
			ExLog.e(TAG, e.toString());
			return false;
		}
		return true;
	}
	
	public static boolean deleteFile(String inpuDirPath, String inputFileName)
	{
		try
		{
			// delete the original file
			new File(inpuDirPath + inputFileName).delete();
		}
		catch (Exception e)
		{
			ExLog.e(TAG, e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean deleteDir(File dir)
	{
		if (dir != null && dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
					return false;
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}
	
	public static boolean createFile(String fileName, String text)
	{
		File file = new File(Key.PATH_KIDSNOTE + fileName);
		try
		{
			OutputStream os = new FileOutputStream(file);
			os.write(text.getBytes());
			os.close();
		}
		catch (Exception e)
		{
			ExLog.e(TAG, e.toString());
			return false;
		}
		return true;
	}

	//------------------------------------------------------------------------------------------
	//	S3 파일 경로를 로컬 경로로 변환
	//	ex) https://s3-ap-northeast-1.amazonaws.com/kidsbucketdev/data/img/2013/07/15/nursery_album_v2/7359/t/1062731775_dVqhySse_0.jpg
	//	=> .cache/t_1062731775_dVqhySse_0.jpg
	//------------------------------------------------------------------------------------------
	public static String convertUrl2FilePath(String url)
	{
		String[] strSplit = url.split("/");
		if (strSplit.length < 4)
			return null;
		
		return Key.PATH_KIDSNOTE_CACHE + strSplit[strSplit.length-2] + "_" + strSplit[strSplit.length-1];
	}

	/**
	 * 이미지 리사이징, EXIF 정보 복사
	 * @param bundle
	 * @return
	 */
	public static Bundle getTargetFileList(Bundle bundle)
	{
		Bundle targetFile = new Bundle();

		if (bundle == null)
		{
			//targetFile.putString("error", MyApp.get().getString(R.string.file_conversion_failed));
			return targetFile;
		}

		String strPath = bundle.getString("path");
		if (!new File(strPath).exists())
			return targetFile;

		String ext = StringUtil.getExtFromFileName(strPath);
		String targetPath = null;

		File file = PhotoUtil.setBitmapCompressAndResizing(strPath);
		if (file == null) {
			//targetFile.putString("error", MyApp.get().getString(R.string.low_memory_err_msg));
			return targetFile;
		}

/*
		File file = new File(Key.PATH_KIDSNOTE_TEMP + StringUtil.getFileNameFromFullPath(strPath));

		try
		{
			Bitmap bitmap = PhotoUtil.decode_maxArea(strPath, Key.MAX_UPLOAD_IMAGE_RESOLUTION);
			if (bitmap == null)
			{
				targetFile.putString("error", MyApp.get().getString(R.string.low_memory_err_msg));
				return targetFile;
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
//			String tag_datetime		= exif.getAttribute(ExifInterface.TAG_DATETIME);
//			String tag_gps_lat		= exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
//			String tag_gps_lat_ref	= exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
//			String tag_gps_lon		= exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
//			String tag_gps_lon_ref	= exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
			String[] exifStringValues = new String[exifStringKeys.length];
			for (int i = 0; i < exifStringKeys.length; i++) {
				exifStringValues[i] = exif.getAttribute(exifStringKeys[i]);
			}

			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 94, ostream);
			ostream.close();
			ExLog.d(TAG, "[TEMP_FILE_CREATED] " + file.getAbsolutePath());

			// Save the exif to new file
			ExifInterface exif_new = new ExifInterface(file.getAbsolutePath());
//			if (tag_datetime != null)		exif_new.setAttribute(ExifInterface.TAG_DATETIME, tag_datetime);
//			if (tag_gps_lat != null)		exif_new.setAttribute(ExifInterface.TAG_GPS_LATITUDE, tag_gps_lat);
//			if (tag_gps_lat_ref != null)	exif_new.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, tag_gps_lat_ref);
//			if (tag_gps_lon != null)		exif_new.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, tag_gps_lon);
//			if (tag_gps_lat_ref != null)	exif_new.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, tag_gps_lon_ref);
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
			targetFile.putString("error", MyApp.get().getString(R.string.low_memory_err_msg));
			return targetFile;
		}
*/

		targetPath = file.getAbsolutePath();
		targetFile.putInt("index",bundle.getInt("index"));
		targetFile.putString("path", targetPath);
		targetFile.putString("ori_path",strPath);
		return targetFile;
//		targetFileList.add(targetFile);
	}

//	rspBundle.putParcelableArrayList("list", targetFileList);
//	return rspBundle;


	public static Bundle getTargetFileList(ArrayList<Bundle> uploadFileList)
	{
		Bundle rspBundle = new Bundle();
		
		if (uploadFileList == null)
		{
			//rspBundle.putString("error", MyApp.get().getString(R.string.file_conversion_failed));
			return rspBundle;
		}
		
		ArrayList<Bundle> targetFileList = new ArrayList<Bundle>();
		for (int i=0; i<uploadFileList.size(); i++)
		{
			Bundle targetFile = getTargetFileList(uploadFileList.get(i));
			if(targetFile != null) {
				targetFile.putString("key", "bf_file[" + i + "]");
				targetFileList.add(targetFile);
			}
		}

		rspBundle.putParcelableArrayList("list", targetFileList);
		return rspBundle;
	}
	
	static String mOrgDir, mOrgFileName, mOrgFileExt;
	private static String getUniqueFileName(String dirPath, String fileName)
	{
		mOrgDir = dirPath;
		mOrgFileName = StringUtil.getNameFromFileName(fileName);
		mOrgFileExt = StringUtil.getExtFromFileName(fileName);
		if (mOrgFileExt.length() > 0)
			mOrgFileExt = "." + mOrgFileExt;

		return getUniqueFileName(0);
	}

	private static String getUniqueFileName(int index)
	{
		String fullName;
		
		if (index == 0)
		{
			fullName = mOrgFileName;
		}
		else
		{
			fullName = mOrgFileName + "(" + index +")";
		}
		fullName += mOrgFileExt;
		
		if (new File(mOrgDir + fullName).exists())
		{
			return getUniqueFileName(index+1);
		}
		else
		{
			return fullName;
		}
	}

	public static String getPathFromUri(Context context, Uri uri)
	{
		Cursor cursor = context.getContentResolver().query(uri, null, null, null, null );
		if(cursor == null)
			return "";

		cursor.moveToNext();
		String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
		cursor.close();

		return path;
	}

	public static boolean deleteFileFromMediaStore(final ContentResolver contentResolver, final File file, final int fileType) {
		int result = 0;
		if (contentResolver == null || file == null)
			return false;

		String canonicalPath;
		try {
			canonicalPath = file.getCanonicalPath();
		} catch (IOException e) {
			canonicalPath = file.getAbsolutePath();
		}

		Uri uri = null;
		if (FileBase.FILE_TYPE_IMAGE == fileType) {
			uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//		} else if (FileBase.FILE_TYPE_VIDEO == fileType) {
//			uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
		}
		if (uri != null) {
			result = contentResolver.delete(uri, MediaStore.Files.FileColumns.DATA + "=?", new String[]{canonicalPath});
			if (result == 0) {
				final String absolutePath = file.getAbsolutePath();
				if (!absolutePath.equals(canonicalPath)) {
					result = contentResolver.delete(uri, MediaStore.Files.FileColumns.DATA + "=?", new String[]{absolutePath});
				}
			}
		}
		return result > 0;
	}
}