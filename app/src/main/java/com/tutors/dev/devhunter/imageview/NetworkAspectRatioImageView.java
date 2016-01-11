package com.tutors.dev.devhunter.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.tutors.dev.devhunter.R;

public class NetworkAspectRatioImageView extends NetworkImageView
{
	private float mRatio = 1f;
	
    public NetworkAspectRatioImageView(Context context) {
        super(context);
    }

    public NetworkAspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
        mRatio = a.getFloat(R.styleable.AspectRatioImageView_ratio, 1f);
        a.recycle();
    }

    public NetworkAspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView, defStyle, 0);
        mRatio = a.getFloat(R.styleable.AspectRatioImageView_ratio, 1f);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, (int)(width * mRatio));
    }
}