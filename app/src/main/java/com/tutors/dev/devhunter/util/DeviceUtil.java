package com.tutors.dev.devhunter.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import com.tutors.dev.devhunter.data.Key;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public class DeviceUtil
{
	private static final String TAG = "DeviceUtil";
	
	private static TelephonyManager mTM;
	private static ConnectivityManager mCM;
	private static PackageManager mPM;
	
	public static String mAndroidId;
	public static String mPhoneNumber = "";
	public static String mEmail = "";
	
	public static int mScreenWidth, mScreenHeight;
	public static DisplayMetrics mDispMetrics;

	public static void init(Context context)
	{
		mTM = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		mCM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		mPM = context.getPackageManager();
		
		mAndroidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		// phone number
		// KT	: +821011112222
		// SKT	: 01011112222
		if (mTM.getLine1Number() != null)
		{
			mPhoneNumber = mTM.getLine1Number();
			mPhoneNumber = mPhoneNumber.replace("+82", "0");
		}

		// email
		AccountManager accountManager = AccountManager.get(context);
		Account account = getAccount(accountManager);
		if (account != null)
			mEmail = account.name;

		//printScreenSize(context);	// BroadcastReceiver로 MyApp.onCreate 시 java.lang.NullPointerException 발생하는 경우가 있어 주석 처리
		printAvailableInternalMemory();
		
		// Screen Width, Height
	    WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    if (Build.VERSION.SDK_INT > 12)
	    {
	        Point size = new Point();
	        display.getSize(size);
	        mScreenWidth = size.x;
	        mScreenHeight = size.y;
	    }
	    else
	    {
	        mScreenWidth = display.getWidth();
	        mScreenHeight = display.getHeight();
	    }

	    // Screen density
	    mDispMetrics = context.getResources().getDisplayMetrics();
	    ExLog.d(TAG, "[SCREEN] " + mScreenWidth + " x " + mScreenHeight + " (" + mDispMetrics.densityDpi + "dpi, density:" + mDispMetrics.density + ")");
	}

	public static InputFilter filterNumOnly = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			Pattern ps = Pattern.compile(Key.PATTERN_NUMBER);
			if(!ps.matcher(source).matches())
			{
				return "";
			}
			return null;
		}
	};

	public static InputFilter filterNotSpace = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//			Pattern ps = Pattern.compile("^[\\s]");
//			if(ps.matcher(source).matches())
//			{
//				return "";
//			}
			if(source.equals(" "))
			{
				return "";
			}
			return null;
		}
	};


	public static String getMyCountryCode()
    {
        return mTM.getSimCountryIso();
    }

	
	// device id
	// ex) 00000000-5d12-d778-0033-c58700000000
	public static String createDeviceId()
	{
		String deviceId = "null";
		try
		{
			String tmDevice = "" + mTM.getDeviceId();
			String tmSerial = "" + mTM.getSimSerialNumber();
			String androidId = "" + mAndroidId;
			
			UUID uuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
			deviceId = uuid.toString();
		}
		catch(Exception e) {}
		
		return deviceId;
	}
	
	private static Account getAccount(AccountManager accountManager)
	{
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0)
		  account = accounts[0];      
		else
		  account = null;

		return account;
	}
	
	public static long getSDCardTotalSpace()
	{
		long cbAvailable;
		try {
			StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
			cbAvailable = (long)stat.getBlockSize() * (long)stat.getBlockCount();
		} catch (IllegalArgumentException e) {
			cbAvailable = -1;
		}
		return cbAvailable;
	}

	public static long getSDCardFreeSpace()
	{
		long cbAvailable;
		try {
			StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
			cbAvailable = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
		} catch (IllegalArgumentException e) {
			cbAvailable = -1;
		}
		return cbAvailable;
	}
	
	public static int getConnectionType()
	{
		NetworkInfo ni = mCM.getActiveNetworkInfo();
		if (ni == null)
			return -1;
		
		return ni.getType();
	}

	public static String getConnectionTypeString()
	{
		int connType = getConnectionType();
		switch (connType) {
		case -1:									return "no connection";
		case ConnectivityManager.TYPE_WIFI:			return "TYPE_WIFI";
		case ConnectivityManager.TYPE_MOBILE:		return "TYPE_MOBILE";
		case ConnectivityManager.TYPE_ETHERNET:		return "TYPE_ETHERNET";
		case ConnectivityManager.TYPE_BLUETOOTH:	return "TYPE_BLUETOOTH";
		case ConnectivityManager.TYPE_WIMAX:		return "TYPE_WIMAX";
		default:									return "TYPE_UNKNOWN";
		}
	}

	public static boolean isNetworkAvailable()
	{
		boolean connected = false;
		NetworkInfo ni = mCM.getActiveNetworkInfo();
		if (ni != null)
			connected = ni.isConnected();

		return connected;
	}

	public static boolean isPackageExists(String regularExpression)
	{
		List<ApplicationInfo> appList = mPM.getInstalledApplications(0);
		for (ApplicationInfo app : appList)
		{
			if (app.packageName.matches(regularExpression))
				return true;
		}
		return false;
    }

    public static boolean isViewerExists(String ext)
    {
//        if (ext.equalsIgnoreCase("hwp"))
//            return isPackageExists("kr.co.hancom.hancomviewer.androidmarket|com.hancom.office.hwp.playstore|com.hancom.office.hwp.editor.hwp_apk.gplaystore");//2010 뷰어 / 2010정품 / 2014 정품
//        else if (ext.equalsIgnoreCase("pdf"))
//            return isPackageExists("com.adobe.reader");
//        else if (ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("xlsx"))
//            return isPackageExists("com.tf.thinkdroid.amlite");
        if (ext.equalsIgnoreCase("hwp"))
            return isViewerExists(ext, "hancom");
        else if (ext.equalsIgnoreCase("pdf"))
            return isViewerExists(ext, "adobe");
        else if (ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("xlsx"))
            return isViewerExists(ext, "thinkdroid");
        return true;
    }

	/**
	 * 확장자 'exe'를 볼수 있는 뷰어 app의 존재 여부
	 * @param ext	문서의 확장자
	 * @param packageNameFilter	app pacakgeName filter
	 * @return
	 */
	public static boolean isViewerExists(String ext, String packageNameFilter) {
		if (ext == null || ext.length() < 1)
			return false;

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setType("application/" + ext.toLowerCase(Locale.getDefault()));

		List<ResolveInfo> list = mPM.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		if (packageNameFilter != null && packageNameFilter.length()>0) {
			for (ResolveInfo info:list) {
				if (info.activityInfo != null && info.activityInfo.packageName != null && info.activityInfo.packageName.length()>0 ) {
					if (info.activityInfo.packageName.contains(packageNameFilter)) {
						return true;
					}
				}
			}
			return false;
		} else {
			return list.size() > 0;
		}
	}

	public static void getPackageVersion(ArrayList<Bundle> itemList)
	{
		for (Bundle item : itemList)
			item.remove("versionCode");

	    List<PackageInfo> pkgList = mPM.getInstalledPackages(0);
	    for (PackageInfo pkg : pkgList)
	    {
	        if (pkg.versionName == null)
	            continue;

			for (Bundle item : itemList)
			{
				if (pkg.packageName.equals(item.getString("pkg")))
				{
			        //item.putString("appName", pkg.applicationInfo.loadLabel(mPM).toString());
			        //item.putString("versionName", pkg.versionName);
			        //pkg.applicationInfo.loadIcon(mPM);
					item.putInt("versionCode", pkg.versionCode);
					break;
				}
			}
	    }
	}

	public static boolean isInstalledApp(String packageName)
	{
		try
		{
			mPM.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
		}
		catch (NameNotFoundException e)
		{
			return false;
		}
		return true;
	}
	
	public static String getMemoryInfoString()
	{
		Runtime rt = Runtime.getRuntime();
		return rt.freeMemory() + " / " + rt.totalMemory() + " / " + rt.maxMemory();
	}
	
	public static String getTimeZoneOffset()
	{
		TimeZone tz = TimeZone.getDefault();
		int tzOffsetMin = tz.getRawOffset() / 60000;
		return String.valueOf(tzOffsetMin);
	}

	public static String getReport()
	{
		return "[PHONE_NUMBER] " + mPhoneNumber + "\n[EMAIL] " + mEmail + "\n[CONNECTION_TYPE] " + getConnectionTypeString() +
			   "\n[MEM] " + getMemoryInfoString();
	}
	
	public static ArrayList<String> getExternalMounts()
	{
	    final ArrayList<String> out = new ArrayList<String>();
	    String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
	    String s = "";
	    try
	    {
	        final Process process = new ProcessBuilder().command("mount").redirectErrorStream(true).start();
	        process.waitFor();
	        final InputStream is = process.getInputStream();
	        final byte[] buffer = new byte[1024];
	        while (is.read(buffer) != -1) {
	            s = s + new String(buffer);
	        }
	        is.close();
	    }
	    catch (Exception e)
	    {
	    	ExLog.e(TAG, e.toString());
	    }

	    // parse output
	    final String[] lines = s.split("\n");
	    for (String line : lines)
	    {
	        if (!line.toLowerCase(Locale.US).contains("asec")) {
	            if (line.matches(reg)) {
	                String[] parts = line.split(" ");
	                for (String part : parts) {
	                    if (part.startsWith("/"))
	                        if (!part.toLowerCase(Locale.US).contains("vold"))
	                            out.add(part);
	                }
	            }
	        }
	    }
	    return out;
	}
	
	/**
	 * Gets the number of cores available in this device, across all processors.
	 * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
	 * @return The number of cores, or 1 if failed to get result
	 */
	public static int getNumCores() {
	    //Private Class to display only CPU devices in the directory listing
	    class CpuFilter implements FileFilter {
	        @Override
	        public boolean accept(File pathname) {
	            //Check if filename is "cpu", followed by a single digit number
	            if(Pattern.matches("cpu[0-9]", pathname.getName())) {
	                return true;
	            }
	            return false;
	        }      
	    }

	    try {
	        //Get directory containing CPU info
	        File dir = new File("/sys/devices/system/cpu/");
	        //Filter to only list the devices we care about
	        File[] files = dir.listFiles(new CpuFilter());
	        //Return the number of cores (virtual CPU devices)
	        return files.length;
	    } catch(Exception e) {
	        //Default to return 1 core
	        return 1;
	    }
	}

	private static void printScreenSize(Context context)
	{
		Configuration cfg = context.getResources().getConfiguration();	// BroadcastReceiver로 MyApp.onCreate 시 context.getResources()의 return 값이 null인 경우가 발생
		if (cfg == null)
			return;

		switch (cfg.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) {
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			ExLog.d(TAG, "[DEVICE_INFO] screen_size:small");
			break;
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			ExLog.d(TAG, "[DEVICE_INFO] screen_size:normal");
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			ExLog.d(TAG, "[DEVICE_INFO] screen_size:large");
			break;
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			ExLog.d(TAG, "[DEVICE_INFO] screen_size:xlarge");
			break;
		default:
			ExLog.d(TAG, "[DEVICE_INFO] screen_size:???");
		}
	}
	
	private static void printAvailableInternalMemory()
	{
		float availMB;
		File path ;
		StatFs stat;
		long blockSize;            
		long availableBlocks;
		try
		{
			path = Environment.getDataDirectory();
			stat = new StatFs(path.getPath());
			blockSize = stat.getBlockSize();
			availableBlocks = stat.getAvailableBlocks();
			availMB = (availableBlocks*blockSize) / (1024*1024);
			ExLog.d(TAG, "[DEVICE_INFO] available_internal_memory:" + availMB + "MB");
		}
		catch (Exception e) {}
	}



}