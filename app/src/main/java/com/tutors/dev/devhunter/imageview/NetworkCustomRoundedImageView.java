package com.tutors.dev.devhunter.imageview;

import android.content.Context;
import android.util.AttributeSet;

public class NetworkCustomRoundedImageView extends NetworkImageView
{
    public NetworkCustomRoundedImageView(Context context) {
        super(context);
    }

    public NetworkCustomRoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkCustomRoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
//
////	@Override
////	protected void onDraw(Canvas canvas) {
////		float radius = 1000.0f; // angle of round corners
////		Path clipPath = new Path();
////		RectF rect = new RectF(0+getPaddingLeft(), 0+getPaddingTop(), this.getWidth()-getPaddingRight(), this.getHeight()-getPaddingBottom());
////		clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
////		canvas.clipPath(clipPath);
////
////		super.onDraw(canvas);
////	}
//
//	@Override
//	public void setImageBitmap(Bitmap bm)
//	{
//		if (bm == null)
//			return;
//
//		CircleDrawable d = new CircleDrawable(bm);
//		setImageDrawable(d);
//	}
//
//	class CircleDrawable extends Drawable
//	{
//		//private final BitmapShader mBitmapShader;
//		//private final Paint mPaint;
//		private final Bitmap mSrcBitmap;
//		private Bitmap mBitmap = null;
//
//		CircleDrawable(Bitmap bitmap)
//		{
//			mSrcBitmap = bitmap;
//		}
//
//		@Override
//		protected void onBoundsChange(Rect bounds) {
//			super.onBoundsChange(bounds);
//		}
//
//		@Override
//		public void draw(Canvas canvas)
//		{
//			Rect rectView = new Rect(getBounds());
///*			Rect rectBitmap = new Rect(0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight());
//
//			if (mBitmap == null)
//				mBitmap = getRoundedRectBitmap(mSrcBitmap, rectBitmap);
//
//			rectBitmap.offset(rectView.centerX() - rectBitmap.centerX(), rectView.centerY() - rectBitmap.centerY());
//
//			if (mBitmap != null)
//				canvas.drawBitmap(mBitmap, null, rectBitmap, null);
//*/
//			if (mBitmap == null)
//				mBitmap = getRoundedRectBitmap(mSrcBitmap, rectView);
//			if (mBitmap != null)
//				canvas.drawBitmap(mBitmap, null, rectView, null);
//		}
//
//	    public Bitmap getRoundedRectBitmap(Bitmap bitmap, Rect rectDest)
//	    {
//	    	Bitmap result = Bitmap.createBitmap(rectDest.width(), rectDest.height(), Bitmap.Config.ARGB_8888);
//	    	Canvas canvas = new Canvas(result);
//
//	    	int color = 0xff424242;
//	    	Paint paint = new Paint();
//
//	    	paint.setAntiAlias(true);
//	    	canvas.drawARGB(0, 0, 0, 0);
//	    	paint.setColor(color);
//	    	canvas.drawCircle(rectDest.width()/2, rectDest.height()/2, rectDest.width()/2, paint);
//
//	    	paint.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
//	    	canvas.drawBitmap(bitmap, null, rectDest, paint);
//
//	    	return result;
//	    }
//
//		@Override
//		public int getOpacity() {
//			return PixelFormat.TRANSLUCENT;
//		}
//
//		@Override
//		public void setAlpha(int alpha) {
//			//mPaint.setAlpha(alpha);
//		}
//
//		@Override
//		public void setColorFilter(ColorFilter cf) {
//			//mPaint.setColorFilter(cf);
//		}
//	}
}