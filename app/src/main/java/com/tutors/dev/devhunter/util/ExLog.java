package com.tutors.dev.devhunter.util;

import android.util.Log;

import com.tutors.dev.devhunter.BuildConfig;


/**
 * Logging helper class.
 *  - w,e 를 제외한 하위 level 은 BuildConfig.DEBUG 값에 따라 제거됨
 *
 * Created by jay on 2015. 11. 4..
 */
public class ExLog {
    private static final String CRASHLYTICS_KEY = "DEV-REPORT";

    public static void marking(String tag, String msg) {
//        Crashlytics.log(Log.ASSERT, tag, msg);
    }

    /**
     * Crashlytics 리포팅
     * @param msg msg 는 64글자 미만이어야 함. (넘는 경우, 파라미터 갯수를 늘려서..). Crashlytics.setString의 key 제한 길이가 64.
     */
    public static void report(String msg) {
        report(msg);
    }

    /**
     * Crashlytics 리포팅
     * @param msgs msg 는 64글자 미만이어야 함. (넘는 경우, 파라미터 갯수를 늘려서..). Crashlytics.setString의 key 제한 길이가 64.
     */
    public static void report(String... msgs) {
        if (msgs != null && msgs.length > 0) {
            int index = 0;
            for (String msg : msgs) {
                Log.v("LOG", "msg:"+msg+", msg.length:"+msg.length());
                if (index++ == 0) {
//                    Crashlytics.setString(CRASHLYTICS_KEY, msg);
                } else {
//                    Crashlytics.log(msg);
                }
                if (BuildConfig.DEBUG)
                    d(CRASHLYTICS_KEY, msg);
            }
//            Crashlytics.logException(new Throwable(msgs[0]));
        }
    }

    /**
     * Send a {VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.VERBOSE, tag, msg);
        }
        return 0;
    }

    /**
     * Send a {VERBOSE} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.VERBOSE, tag, msg);
//            Crashlytics.logException(tr);
        }
        return 0;
    }

    /**
     * Send a {DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.DEBUG, tag, msg);
        }
        return 0;
    }

    /**
     * Send a {DEBUG} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.DEBUG, tag, msg);
            if (tr != null) {
//                for (StackTraceElement ste : tr.getStackTrace())
//                    Crashlytics.log(ste.toString());
            }
        }
        return 0;
    }

    /**
     * Send an {INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.INFO, tag, msg);
        }
        return 0;
    }

    /**
     * Send a {INFO} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
//            Crashlytics.log(Log.INFO, tag, msg);
            if (tr != null) {
//                for (StackTraceElement ste : tr.getStackTrace())
//                    Crashlytics.log(ste.toString());
            }
        }
        return 0;
    }

    /**
     * Send a {WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
//        Crashlytics.log(Log.WARN, tag, msg);
        return 0;
    }

    /**
     * Send a {WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
//        Crashlytics.log(Log.WARN, tag, msg);
        if (tr != null) {
//            for (StackTraceElement ste : tr.getStackTrace())
//                Crashlytics.log(ste.toString());
        }
        return 0;
    }

    /**
     * Send a {WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
//        Crashlytics.log(Log.WARN, tag, tr.getMessage());
        if (tr != null) {
//            for (StackTraceElement ste : tr.getStackTrace())
//                Crashlytics.log(ste.toString());
        }
        return 0;
    }

    /**
     * Send an {ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
//        Crashlytics.log(Log.ERROR, tag, msg);
        return 0;
    }

    /**
     * Send a {ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
//        Crashlytics.log(Log.ERROR, tag, msg);
        if (tr != null) {
//            for (StackTraceElement ste : tr.getStackTrace())
//                Crashlytics.log(ste.toString());
        }
        return 0;
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen.
     * The error will always be logged at level ASSERT with the call stack.
     * Depending on system configuration, a report may be added to the
     * {@link android.os.DropBoxManager} and/or the process may be terminated
     * immediately with an error dialog.
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     */
    public static int wtf(String tag, String msg) {
//        Crashlytics.log(Log.ASSERT, tag, msg);
        return 0;
    }

    /**
     * What a Terrible Failure: Report an exception that should never happen.
     * Similar to {wtf(String, Throwable)}, with a message as well.
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     * @param tr An exception to log.  May be null.
     */
    public static int wtf(String tag, String msg, Throwable tr) {
//        Crashlytics.log(Log.ASSERT, tag, msg);
        if (tr != null) {
//            for (StackTraceElement ste : tr.getStackTrace())
//                Crashlytics.log(ste.toString());
        }
        return Log.wtf(tag, msg, tr);
    }

}
