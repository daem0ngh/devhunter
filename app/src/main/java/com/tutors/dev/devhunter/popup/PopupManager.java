package com.tutors.dev.devhunter.popup;

import android.app.Activity;
import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tutors.dev.devhunter.MyApp;
import com.tutors.dev.devhunter.R;
import com.tutors.dev.devhunter.data.Key;

/**
 * Created by shs on 2016-01-10.
 */
public class PopupManager {

    private static Toast mToast;
    private static Dialog mDialog;

    public static void showToast(Activity activity, String strMsg)
    {
        showToast(activity, strMsg, Key.TOAST_POPUP_ICON_NONE, -1);
    }

    public static void showToast(Activity activity, int resMsg)
    {
        showToast(activity, MyApp.get().getString(resMsg), Key.TOAST_POPUP_ICON_NONE, -1);
    }

    public static void showToast(Activity activity, int resMsg, int whichIcon)
    {
        showToast(activity, MyApp.get().getString(resMsg), whichIcon, -1);
    }

    public static void showToast(Activity activity, String strMsg, int whichIcon)
    {
        showToast(activity, strMsg, whichIcon, -1);
    }
    public static void showToast(Activity activity, String strMsg, int whichIcon, int length)
    {
        if (activity == null || activity.isFinishing())
            return;

        View layout = activity.getLayoutInflater().inflate(R.layout.layout_toast, null);

        View layoutBg = layout.findViewById(R.id.layoutBg);
        if (whichIcon == Key.TOAST_POPUP_ICON_ERROR)
            layoutBg.setBackgroundResource(R.drawable.toast_bg2);
        layoutBg.getBackground().setAlpha(0xDD);

        ImageView imgIcon = (ImageView)layout.findViewById(R.id.imgIcon);
        switch (whichIcon) {
            case Key.TOAST_POPUP_ICON_ALERT:	imgIcon.setBackgroundResource(R.mipmap.toast_popup_alert);	break;
            case Key.TOAST_POPUP_ICON_ERROR:	imgIcon.setBackgroundResource(R.mipmap.toast_popup_error);	break;
            case Key.TOAST_POPUP_ICON_OK:		imgIcon.setBackgroundResource(R.mipmap.toast_popup_ok);		break;
            default:							imgIcon.setVisibility(View.GONE);
        }

        TextView lblMessage = (TextView)layout.findViewById(R.id.lblMessage);
        lblMessage.setText(strMsg);

        if (mToast == null)
            mToast = new Toast(activity);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(imgIcon.getVisibility() == View.VISIBLE ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        if (length != -1) {
            mToast.setDuration(length);
        }
        mToast.setView(layout);
        mToast.show();
    }

    //=============================================================================
    //
    //	P R O G R E S S
    //
    //=============================================================================
    public static CustomProgressDialog showProgress(Activity activity)
    {
        return showProgress(activity, -1);
    }

    public static CustomProgressDialog showProgress(Activity activity, int resMsg)
    {
        return showProgress(activity, resMsg, true);
    }

    public static CustomProgressDialog showProgress(Activity activity, int resMsg, boolean showCancel)
    {
//		if (mProgress != null)
//			mProgress.dismiss();

        return CustomProgressDialog.show(activity, resMsg, showCancel);
    }

    //FIXME 필요한가? 대신, cancelProgress를 사용??
    public static void closeProgress(CustomProgressDialog mProgress)
    {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

    public static void cancelProgress(CustomProgressDialog mProgress)
    {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.setCancelActionProgress();
        }
    }

    public static boolean isProgressShowing(CustomProgressDialog mProgress)
    {
        return mProgress != null && mProgress.isShowing();
    }


    //=============================================================================
    //
    // S N A C B A R
    //
    //=============================================================================
    public static void showSnackBar(View rootView, String title, String actionMsg)
    {
        showSnackBar(rootView, title, actionMsg, null);
    }

    public static void showSnackBar(View rootView, String title, String actionMsg, View.OnClickListener listner)
    {
        if(rootView != null) {
            Snackbar.make(rootView, title, Snackbar.LENGTH_LONG)
                    .setAction(actionMsg, listner)
                    .show();;
        }
    }




}
