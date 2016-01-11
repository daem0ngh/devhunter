package com.tutors.dev.devhunter.popup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tutors.dev.devhunter.R;


public class CustomProgressDialog extends Dialog implements OnClickListener
{
    public static int MESSAGE_CANCEL_PROGRESS    =   999;

	//-----------------------------------------------------------------------------
	//	Etc data variables
	//-----------------------------------------------------------------------------
	public Handler mHandler;
    public Handler mActivityhandler;
	public int mResMsg;
	private boolean mShowCancel;
	
	//-----------------------------------------------------------------------------
	//	UI control variables
	//-----------------------------------------------------------------------------
	private ImageButton btnCancel;
	private TextView lblMessage;

	public CustomProgressDialog(Context context, int resMsg, boolean showCancel)
	{
		super(context, R.style.Theme_Dialog_Nude);
		
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		mResMsg = resMsg;
		if (mResMsg == -1)
			mResMsg = R.string.progress_loading;
		mShowCancel = showCancel;

		mHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if (msg.arg1 == -1)
				{
					dismiss();
				}
				else if (msg.arg1 > 0)
				{
					//lblPercent.setText(msg.arg1 + "%");
					//lblProgressA.setText(msg.arg1 >= 100 ? "유튜브로부터 결과를 기다리는 중..." : "");

					//lblProgressA.setVisibility(View.VISIBLE);
					//((LinearLayout.LayoutParams)lblProgressA.getLayoutParams()).weight = msg.arg1;
					//((LinearLayout.LayoutParams)viewProgressB.getLayoutParams()).weight = 100 - msg.arg1;
				}
			}
		};
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_custom_progress);
		//getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		btnCancel = (ImageButton)findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);
        btnCancel.setVisibility(View.GONE);
		btnCancel.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
		lblMessage = (TextView)findViewById(R.id.lblMessage);
		lblMessage.setText(mResMsg);
	}

    public void setShowCancel(boolean isCancel)
    {
        this.mShowCancel = isCancel;
        btnCancel.setVisibility(View.GONE);
        btnCancel.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
    }

    public void setShowText(int resMsg)
    {
        mResMsg = resMsg;
        if (mResMsg == -1)
            mResMsg = R.string.progress_loading;

        if(lblMessage != null)
            lblMessage.setText(mResMsg);
    }

	
	public void onClick(View v) {
		if (v == btnCancel) {
			setCancelActionProgress();
		}
	}

	public void setProgress(int progress)
	{
		Message msg = mHandler.obtainMessage();
		msg.arg1 = progress;
		mHandler.sendMessage(msg);
	}

    public void setCancelActivityHandler(Handler handler)
    {
        mActivityhandler = handler;
    }

    public void setCancelActionProgress() {
		if (mActivityhandler != null) {
			Message msg = mActivityhandler.obtainMessage();
			msg.arg1 = MESSAGE_CANCEL_PROGRESS;
			mActivityhandler.sendMessage(msg);
		}
		cancel();
    }


	public static CustomProgressDialog show(Context context, int resMsg, boolean showCancel)
	{
		CustomProgressDialog dlg = new CustomProgressDialog(context, resMsg, showCancel);
		dlg.show();
		return dlg;
	}
	
	public static CustomProgressDialog show(Context context, int resMsg)
	{
		return show(context, resMsg, true);
	}

	public static CustomProgressDialog show(Context context)
	{
		return show(context, -1);
	}

}