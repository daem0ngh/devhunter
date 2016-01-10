package com.tutors.dev.devhunter.data;

import android.app.Activity;
import android.os.Environment;

public class Key
{
	public static final int FULL_LARGE_PAGE_SIZE		= 1000;
	public static final int FULL_PAGE_SIZE				= 500;
	public static final int FULL_SMALL_PAGE_SIZE		= 10;
	public static final int MESSAGE_RELOAD_COMMENT		= 1;

	public static final int COMMENT_COUNT_FOR_EACH_PAGE	= 10;
	//public static final int COMMENT_COUNT_FOR_EACH_PAGE	= 25;
	public static final int COMMENT_COUNT2_FOR_EACH_PAGE= 100;
	public static final int MAX_MEMO_OUTBOX_COUNT		= 30;
	public static final int MAX_IMAGE_RESOLUTION		= 1024 * 768;
	//public static final int MAX_UPLOAD_IMAGE_RESOLUTION	= 1600 * 1200;//2048 * 1536;
	public static final int MAX_UPLOAD_RESULUTION		= 1920;
	public static final int MAX_UPLOAD_IMAGE_RESOLUTION	= 1920 * 1080;//2048 * 1536;

	public static final int MAX_EMAIL_SIZE				= 50;

	public static final int POPUP_IMAGE_TOTAL_SIZE		= 1024 * 1024 * 10;
	public static final int TRANSLATE_MAX_LENGTH		= 3000;

	public static final int THREAD_MAX_COUNT = Runtime.getRuntime().availableProcessors() * 2;

	/**
	 * Patterns
	 */
	public static final String PATTERN_EMAIL_ID = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}";
	public static final String PATTERN_MAIL_REGEXP = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
			+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
			+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
			+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
			+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
			+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
	public static final String PATTERN_ID = "^[_a-zA-Z0-9]+$";
	public static final String PATTERN_ID_DEBUG = "^[_.a-zA-Z0-9]+$";
	public static final String PATTERN_NUMBER = "^[0-9]*$";

	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
//	public static final String DATE_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String OAUTH_KEY = "Basic RTFjVmR0RlNyOVIzSWhDeFFxa3FyWFJrNldYc0RFMkVHbFg5SlY2RjpYMW9iUUtpZ2tKNXN2WVZyRWtUZjhZeVZJM1lVWk8yczV0c1pZSmJFV3h0WmpaZ1FGQ09Tbk9IUjFpbkpkNDVCclhITDY0Uzl2SjRCSVBDRU5YMENoSmo4UDZlRGZuZVRkek1aSjRJRkJtalp3a05OY0htcm10ZllsQTNyU2tSbg==";
//	public static final String OAUTH_KEY = "Basic eTU0bU4xbHBhWTcyTmlQTEZPQnp5WlNkS2FMV0h4ZUNUV0VoUXp4RzpleENKNVQ5TmlzaGc2NkpEQzh1b1NZN29PM1hTVVVVcjlHRG5penVWaGd3TDJyWkpNVkJHY0hYYTh1UDZ2VmlHbGE2VERGVE8ybDFIMEw3cEdIckFRQ1lpMWsyakEwVTVVT2RmQ2pXeDdSVVJDMk0xZlhhd1ZNRXBIdGJDZExQbQ==";
	public static final String GG_TRANSLATE_KEY = "AIzaSyDHHD5ERYum-p1-fUjQKj8LzAHW4wXqLxQ";

	public static final String DEBUG_TAG = "_DEVHUNTER";
	public static final String PREF_KIDSNOTE = "PREF_KIDSNOTE";
	public static final String TEMP_FILE_PREFIX = "temp_";
	public static final String PREFS_SHOWCASE_INTERNAL = "showcase_internal";
	
	public static final String DEVELOPER_KEY = "AIzaSyDHHD5ERYum-p1-fUjQKj8LzAHW4wXqLxQ";
	
	public static final String URL_PLAY_STORE_ROOT = "market://details?id=";

    public static final String URL_KAKAOLINK_IMAGE = "http://static.kidsnote.com/images/kakao_invitation_img_2.png";
	public static final String URL_KIDSNOTE_DOWNLOAD = "https://kids-s.kakaocdn.net/kidsnote/download/";
	public static final String FILENAME_KIDSNOTE_PREFIX = "Kidsnote_3.0_build_";
	public static final String FILENAME_KIDSNOTE_POSTFIX = "_google.apk";

	public static final int RESULT_CLOSE					= Activity.RESULT_FIRST_USER + 190;
	public static final int RESULT_CLOSE_ALL				= Activity.RESULT_FIRST_USER + 200;
	public static final int RESULT_UPDATE_MENU				= Activity.RESULT_FIRST_USER + 201;
	public static final int RESULT_DATA_CREATED				= Activity.RESULT_FIRST_USER + 300;	// 글이 생성됨
	public static final int RESULT_DATA_MODIFIED			= Activity.RESULT_FIRST_USER + 301;	// 글이 수정됨
	public static final int RESULT_DATA_REMOVED				= Activity.RESULT_FIRST_USER + 302;	// 글이 삭제됨
	public static final int RESULT_DATA_UPDATE				= Activity.RESULT_FIRST_USER + 303;
	public static final int RESULT_ERROR_OCCURRED			= Activity.RESULT_FIRST_USER + 305;
	public static final int RESULT_OUTBOX_CHANGED			= Activity.RESULT_FIRST_USER + 306;
	public static final int RESULT_DATA_AND_OUTBOX_CHANGED	= Activity.RESULT_FIRST_USER + 307;
	public static final int RESULT_TRY_LOGIN				= Activity.RESULT_FIRST_USER + 400;
	public static final int RESULT_REMOVE_INVITE			= Activity.RESULT_FIRST_USER + 500;


	//kidsnote!@maginot#$%project^&*() 32bit;
	public static final byte[] AES_SECRET_KEY= { (byte)0x6b,(byte)0x69,(byte)0x64,(byte)0x73,(byte)0x6e,(byte)0x6f,
												 (byte)0x74,(byte)0x65,(byte)0x21,(byte)0x40,(byte)0x6d,(byte)0x61,
												 (byte)0x67,(byte)0x69,(byte)0x6e,(byte)0x6f,(byte)0x74,(byte)0x23,
												 (byte)0x24,(byte)0x25,(byte)0x70,(byte)0x72,(byte)0x6f,(byte)0x6a,
												 (byte)0x65,(byte)0x63,(byte)0x74,(byte)0x5e,(byte)0x26,(byte)0x2a,
												 (byte)0x28,(byte)0x29};

	public static final byte[] AES_IV= { 	(byte)0x6b,(byte)0x69,(byte)0x64,(byte)0x73,(byte)0x6e,(byte)0x6f,
											(byte)0x74,(byte)0x65,(byte)0x21,(byte)0x40,(byte)0x6d,(byte)0x61,
											(byte)0x67,(byte)0x69,(byte)0x6e,(byte)0x6f};


	//-----------------------------------------------------------------------------
	//	Directories
	//-----------------------------------------------------------------------------
	public static String PATH_KIDSNOTE = Environment.getExternalStorageDirectory() + "/Kidsnote/";
	public static String PATH_KIDSNOTE_CACHE	= PATH_KIDSNOTE + ".cache/";
	public static String PATH_KIDSNOTE_TEMP		= PATH_KIDSNOTE + ".temp/";
	public static String PATH_KIDSNOTE_SIGN		= PATH_KIDSNOTE + ".sign/";
	public static String PATH_KIDSNOTE_PROFILE	= PATH_KIDSNOTE + ".profile/";
	public static String PATH_KIDSNOTE_EDIT		= PATH_KIDSNOTE + ".edit/";
	public static String PATH_KIDSNOTE_UPLOAD	= PATH_KIDSNOTE + ".upload/";
	public static String PATH_UPLOAD			= "Kidsnote/.upload";

	//-----------------------------------------------------------------------------
	//	Boards
	//-----------------------------------------------------------------------------
	public static final String BOARD_TEACHEAR_JOIN			= "teacher_join";	// 교사가입
	public static final String BOARD_TEACHEAR_ACCEPT		= "teacher_accept";	// 교사승인
	public static final String BOARD_TEACHEAR_REJECT		= "teacher_reject";	// 교사거절
	public static final String BOARD_CHILD_JOIN				= "child_join";		// 아이가입
	public static final String BOARD_CHILD_ACCEPT			= "child_accept";	// 아이승인
	public static final String BOARD_CHILD_REJECT			= "child_reject";	// 아이거절
	public static final String BOARD_REPORT					= "report";			// 알림장
	public static final String BOARD_NOTICE					= "notice";			// 공지사항
	public static final String BOARD_ALBUM					= "album";			// 앨범
	public static final String BOARD_MEAL					= "menu";			// 식단표
	public static final String BOARD_CALENDAR				= "calendar";		// 일정표
	public static final String BOARD_INJECTION				= "medication";		// 투약동의서
	public static final String BOARD_RETURN					= "returnhome";		// 귀가동의서
	public static final String BOARD_NEWS					= "news";			// 새소식
	public static final String BOARD_PARTNER				= "partner";		// 파트너

	//-----------------------------------------------------------------------------
	//	Push-spec [target, message, options(id, partner_code, board_name, url)]
	//  https://github.com/kidsnote/backend/wiki/Push-Spec
	//-----------------------------------------------------------------------------
	public static final String TARGET_TEACHEAR_JOIN			= "teacher_join";	// 교사 가입 시
	public static final String TARGET_TEACHEAR_ACCEPT		= "teacher_accept";	// 교사 승인 시
	public static final String TARGET_TEACHEAR_REJECT		= "teacher_reject";	// 교사 거절 시
	public static final String TARGET_CHILD_JOIN			= "child_join";		// 아이 가입 시
	public static final String TARGET_CHILD_ACCEPT			= "child_accept";	// 아이 승인 시
	public static final String TARGET_CHILD_REJECT			= "child_reject";	// 아이 거절 시
	public static final String TARGET_REPORT				= "report";			// 알림장, (댓글) 작성 시		id:알림장 번호
	public static final String TARGET_NOTICE				= "notice";			// 공지사항, (댓글) 작성 시		id:공지사항 번
	public static final String TARGET_ALBUM					= "album";			// 앨범, (댓글) 작성 시			id:앨범 번호
	public static final String TARGET_MEAL					= "menu";			// 식단표 작성 시
	public static final String TARGET_CALENDAR				= "calendar";		// 일정표
	public static final String TARGET_INJECTION				= "medication";		// 투약의뢰서 작성 시			id:투약의뢰서 번호
	public static final String TARGET_RETURN				= "returnhome";		// 귀가동의서 작성 시			id:귀가동의서 번호
	public static final String TARGET_NEWS					= "news";			// 새소식 작성 시				id:새소식 번호
	public static final String TARGET_PARTNER				= "partner";		// 파트너 게시판 글 작성 시		id:파트너 게시판 글 번호, partner_code:파트너 코드, board_name:게시판 이름
	public static final String TARGET_LINK					= "link";			// 링크 연결 푸시 발송 시			url:연결될 주소
	public static final String TARGET_COUPON				= "coupon";			// 링크 연결 푸시 발송 시			url:연결될 주소

	//-----------------------------------------------------------------------------
	//	SID for Trial Mode
	//-----------------------------------------------------------------------------
	public static final String[] TRIAL_ID		= { "kizkiz1", "kizkiz123", "kizkiz12345" };
	public static final String[] TRIAL_ID_JP	= { "kizkiz12", "kizkiz123456", "kizkiz1234" };

	//-----------------------------------------------------------------------------
	//	Toast Icons
	//-----------------------------------------------------------------------------
	public static final int TOAST_POPUP_ICON_NONE	= 0;
	public static final int TOAST_POPUP_ICON_OK		= 1;
	public static final int TOAST_POPUP_ICON_ALERT	= 2;
	public static final int TOAST_POPUP_ICON_ERROR	= 3;

	//-----------------------------------------------------------------------------
	//	READ API mode
	//-----------------------------------------------------------------------------
	public static final int API_READ_AR		= 0x01;
	public static final int API_READ_AF		= 0x02;
	public static final int API_READ_CM		= 0x04;
	public static final int API_READ_ALL	= 0x07;
	
	//-----------------------------------------------------------------------------
	//	Actions for File
	//-----------------------------------------------------------------------------
	public static final int DOWNLOAD_FILE_NONE			= -1;
	public static final int DOWNLOAD_FILE_OPEN			= 0;
	public static final int DOWNLOAD_FILE_WALPAPER		= 1;
	public static final int DOWNLOAD_FILE_SHARE			= 2;
	public static final int DOWNLOAD_FILE_KAKAOTALK		= 3;
	public static final int DOWNLOAD_FILE_KAKAOSTORY	= 4;

	//-----------------------------------------------------------------------------
	//	Column index of tbl_id table
	//-----------------------------------------------------------------------------
	public static final int TABLE_INDEX_ID				= 1;
	public static final int TABLE_INDEX_PWD				= 2;
	public static final int TABLE_INDEX_AUTO_LOGIN		= 3;
	public static final int TABLE_INDEX_LOGIN_COUNT		= 4;
	public static final int TABLE_INDEX_LAST_LOGIN_DATE	= 5;
	public static final int TABLE_INDEX_NICKNAME		= 6;
	public static final int TABLE_INDEX_SELECTED_PB_NO	= 7;


	//-----------------------------------------------------------------------------
	//	Density
	//-----------------------------------------------------------------------------
	public static final float DENSITY_XHDPI				= 2.0f;

}