package com.tutors.dev.devhunter.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class NetworkDoubleSizeImageView extends NetworkImageView
{
	public NetworkDoubleSizeImageView(Context context) {
		super(context);
	}

	public NetworkDoubleSizeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NetworkDoubleSizeImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private int getWidth(int widthMeasureSpec)
	{
		Drawable drawable = getDrawable();
		if (drawable == null)
			return MeasureSpec.getSize(widthMeasureSpec);

		int bmpWidth = drawable.getIntrinsicWidth();
		if (bmpWidth < 0)
			return MeasureSpec.getSize(widthMeasureSpec);

		return Math.min(bmpWidth * 2, MeasureSpec.getSize(widthMeasureSpec));
	}

	private int getHeight(int width)
	{
		Drawable drawable = getDrawable();
		if (drawable == null)
			return (int)(width * 0.5);

		int bmpWidth = drawable.getIntrinsicWidth();
		int bmpHeight = drawable.getIntrinsicHeight();
		if (bmpWidth == 0 || bmpHeight == 0)
			return (int)(width * 0.5);

		return bmpHeight * width / bmpWidth;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int width = getWidth(widthMeasureSpec);
		int height = getHeight(width);

    	if (width > 0 && height > 0)
            setMeasuredDimension(width, height);
    	else
    		setMeasuredDimension(200, 200);
	}
}