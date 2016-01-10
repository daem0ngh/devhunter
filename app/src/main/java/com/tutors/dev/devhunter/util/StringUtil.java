package com.tutors.dev.devhunter.util;

import android.content.Context;
import android.location.Location;
import android.media.ExifInterface;

import com.tutors.dev.devhunter.MyApp;
import com.tutors.dev.devhunter.data.Key;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtil
{
	public static boolean isNull(String value)
	{
	    if (value == null || value.trim().length() == 0)
	        return true;
	    else
	        return false;
	}
	
	public static boolean isNotNull(String value)
	{
	    if (value == null || value.trim().length() == 0)
	        return false;
	    else
	        return true;
	}

	// contact@kidsnote.com
	public static boolean validateEmailFormat(String email)
	{
		if (email.indexOf(' ') != -1)
			return false;
		
		int pos1 = email.indexOf("@");
		if (pos1 == -1)
			return false;

		int pos2 = email.lastIndexOf(".");
		if (pos2 == -1)
			return false;
		
		if (pos1 >= pos2)
			return false;

		return true;
	}

	// kidsnote.com
	public static boolean validateEmail2Format(String email)
	{
		if (email.indexOf(' ') != -1)
			return false;
		
		int pos2 = email.lastIndexOf(".");
		if (pos2 == -1)
			return false;
		
		return true;
	}

    public static String getFileNameFromSpeciallatter(String convertFileName)
    {
        String filename = getNameFromFileName(convertFileName);
        String ext = getExtFromFileName(convertFileName);

        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        filename = filename.replaceAll(match, "_");

        if(filename.length() < 1) filename = "kidsnote";
        if(ext.length() < 1) ext = "";

        return filename + "." +ext;
    }

	//------------------------------------------------------------------------------------------
	//	파일 경로로부터 파일 이름만 추출한다.
	//	ex) test.txt ==> test
	//	ex) /data/test.txt ==> test
	//------------------------------------------------------------------------------------------
	public static String getNameFromFileName(String strFileName)
	{
		String name = strFileName.substring(strFileName.lastIndexOf("/") + 1);
		int posDot = name.lastIndexOf(".");
		if (posDot != -1)
			name = name.substring(0, posDot);
		return name;
	}
	
	//------------------------------------------------------------------------------------------
	//	파일 경로로부터 확장자를 추출한다.
	//	ex) test.txt ==> txt
	//	ex) /data/test.txt ==> txt
	//------------------------------------------------------------------------------------------
	public static String getExtFromFileName(String strFileName)
	{
		String ext = "";
		int posDot = strFileName.lastIndexOf(".");
		if (posDot != -1)
			ext = strFileName.substring(posDot + 1).toLowerCase();
		return ext;
	}

	//------------------------------------------------------------------------------------------
	//	파일 경로로부터 디렉토리명을 추출한다.
	//	ex) /mnt/sdcard/DCIM/Camera/test.txt ==> /mnt/sdcard/DCIM/Camera/
	//------------------------------------------------------------------------------------------
	public static String getDirPathFromFullPath(String strPath)
	{
		String dirPath = strPath;
		int posDot = strPath.lastIndexOf("/");
		if (posDot != -1)
			dirPath = strPath.substring(0, posDot + 1);	// '/' 포함
		return dirPath;
	}
	
	//------------------------------------------------------------------------------------------
	//	파일 경로로부터 파일명을 추출한다.
	//	ex) test.txt ==> test.txt
	//	ex) /data/test.txt ==> test.txt
	//------------------------------------------------------------------------------------------
	public static String getFileNameFromFullPath(String strPath)
	{
		String fileName = strPath;
		int posDot = strPath.lastIndexOf("/");
		if (posDot != -1)
			fileName = strPath.substring(posDot + 1);
		return fileName;
	}
	
	// GPS 좌표를 문자열(도,분,초)로 변환
	public static String convertGps(double coordinate)
	{
        double alon = Math.abs(coordinate);
        String dms = Location.convert(alon, Location.FORMAT_SECONDS);
        String[] splits = dms.split(":");
        String[] secnds = (splits[2]).split("\\.");
        String seconds = secnds.length == 0 ? splits[2] : secnds[0];
        return splits[0] + "/1," + splits[1] + "/1," + seconds + "/1";

        //exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, splits[0] + "/1," + splits[1] + "/1," + seconds + "/1");
        //exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, splits[0] + "/1," + splits[1] + "/1," + seconds + "/1");
        //exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, coordinate > 0 ? "N" : "S");
        //exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, coordinate > 0 ? "E" : "W");
        //exif.saveAttributes();
	}
	
	public static String convertGps(ExifInterface exif)
	{
		String tag_gps_lat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
		String tag_gps_lat_ref = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
		String tag_gps_lon = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
		String tag_gps_lon_ref = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
		if (StringUtil.isNull(tag_gps_lat) || StringUtil.isNull(tag_gps_lat_ref) || StringUtil.isNull(tag_gps_lon) || StringUtil.isNull(tag_gps_lon_ref))
			return null;
		
		double lat = convertToDegree(tag_gps_lat);
		double lon = convertToDegree(tag_gps_lon);
		
		if (tag_gps_lat_ref.equals("S"))
			lat = -lat;
			 
		if (tag_gps_lon_ref.equals("W"))
			lon = -lon;
			  
		return lat + "," + lon;
	}
	
	// 문자열(도,분,초)를 GPS 좌표로 변환
	public static double convertToDegree(String stringDMS)
	{
		if (StringUtil.isNull(stringDMS))
			return 0;
		
		try
		{
			String[] DMS = stringDMS.split(",", 3);
			
			String[] stringD = DMS[0].split("/", 2);
			double D0 = Double.parseDouble(stringD[0]);
			double D1 = Double.parseDouble(stringD[1]);
			double FloatD = D0/D1;
			
			String[] stringM = DMS[1].split("/", 2);
			double M0 = Double.parseDouble(stringM[0]);
			double M1 = Double.parseDouble(stringM[1]);
			double FloatM = M0/M1;
			
			String[] stringS = DMS[2].split("/", 2);
			double S0 = Double.parseDouble(stringS[0]);
			double S1 = Double.parseDouble(stringS[1]);
			double FloatS = S0/S1;
			
			return FloatD + FloatM/60 + FloatS/3600;
		}
		catch (Exception e)
		{
			//TemplateActivity.mThis.reportGaEvent("Exception", "convertToDegree() error", "stringDMS:" + stringDMS, 0L);
			return 0;
		}
	}
	

	//------------------------------------------------------------------------------------------
	//	ex) 0730 ==> 07:30 AM
	//	ex) 1930 ==> 07:30 PM
	//------------------------------------------------------------------------------------------
	public static String convertFromHourOfDay2(String strTime)
	{
		if (strTime.length() != 4)
			return "";
		
		String strDisplayTime;
		int hour = Integer.parseInt(strTime.substring(0, 2));
		int min = Integer.parseInt(strTime.substring(2, 4));
		
		if		(hour == 12)	strDisplayTime = String.format("%d:%02d PM", hour, min);
		else if (hour > 12)		strDisplayTime = String.format("%d:%02d PM", hour - 12, min);
		else					strDisplayTime = String.format("%d:%02d AM", hour, min);
		
		return strDisplayTime;
	}
	
	//------------------------------------------------------------------------------------------
	//	ex) 07:30 AM ==> 0730
	//	ex) 07:30 PM ==> 1930
	//------------------------------------------------------------------------------------------
	public static String convertToHourOfDay(String strTime)
	{
		if (StringUtil.isNull(strTime))
			return "";
		
		String[] strSplit = strTime.trim().split(" ");
		if (strSplit.length < 2)
			return "";

		boolean bPM;
		if (strSplit[1].toLowerCase().equals("pm"))
			bPM = true;
		else if (strSplit[1].toLowerCase().equals("am"))
			bPM = false;
		else
			return "";
		
		strSplit = strSplit[0].split(":");
		if (strSplit.length < 2)
			return "";
		
		int hour = Integer.parseInt(strSplit[0]);
		int min = Integer.parseInt(strSplit[1]);
		if (hour < 12 && bPM)
			hour += 12;
		
		return String.format("%02d%02d", hour, min);
	}

	public static Date convertStringToDate(String date)
	{
		try {
			SimpleDateFormat format = new SimpleDateFormat(Key.DATE_FORMAT);
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 숫자가 아닌 character 제거
	 * @param str
	 * @return
	 */
	public static String removeNonDigits(final String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		return str.replaceAll("[^0-9]+", "");
	}

	/**
	 * Html.fromHtml 를 사용할 html tag string을 만든다.
	 *
	 * @param context
	 * @param message
	 * @param colorResId
	 * @return
	 */
	public static String makeHtmlFont(Context context, String message, int colorResId) {
		String rgb = Integer.toHexString(context.getResources().getColor(colorResId));
		if (rgb.length() > 6) {
			rgb = rgb.substring(rgb.length()-6);
		}
		String font = "<font color=\"#"+rgb+"\">";
		font += message;
		font += "</font>";
		return font;
	}

	public static String toCapWords(String target) {
		String dest = target;
		StringBuffer buffer = new StringBuffer();
		if (StringUtil.isNotNull(target)) {
			String[] words = target.split(" ");
			if (words != null) {
				for (int i = 0; i<words.length; i++) {
					String word = words[i];
					if (i > 0) {
						buffer.append(" ");
					}
					if (word.length()>0) {
						buffer.append(word.substring(0, 1).toUpperCase()+word.substring(1));
					}
				}
				dest = buffer.toString();
			}
		}
		return dest;
	}

	public static String toCapWords(int targetResId) {
		return toCapWords(MyApp.get().getString(targetResId));
	}

	public static boolean isValidateEmailPattern(String email) {
		if(isNull(email))
			return false;

		return Pattern.compile(Key.PATTERN_MAIL_REGEXP).matcher(email).matches();
	}
}