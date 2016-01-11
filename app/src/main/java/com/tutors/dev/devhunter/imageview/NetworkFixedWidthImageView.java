package com.tutors.dev.devhunter.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class NetworkFixedWidthImageView extends NetworkImageView
{
    public NetworkFixedWidthImageView(Context context) {
        super(context);
    }

    public NetworkFixedWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkFixedWidthImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
    	int width = View.MeasureSpec.getSize(widthMeasureSpec);

        Drawable drawable = getDrawable();
        if (drawable == null) {
            setMeasuredDimension(width, width / 2);
            return;
        }

        int bmpWidth = drawable.getIntrinsicWidth();
        int bmpHeight = drawable.getIntrinsicHeight();
        if (bmpWidth == 0) {
            setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
            return;
        }

        setMeasuredDimension(width, bmpHeight * width / bmpWidth);
    }
}