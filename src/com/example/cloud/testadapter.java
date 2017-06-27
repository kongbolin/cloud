package com.example.cloud;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

public class testadapter extends PagerAdapter {
	private List<Integer> mPaths;

	private Context mContext;

	public testadapter(Context cx) {
		mContext = cx;
	}

	public void change(List<Integer> paths) {
		mPaths = paths;
	}

	public int getCount() {
		return mPaths.size();
	}

	public boolean isViewFromObject(View view, Object obj) {
		return view == (View) obj;
	}

	public Object instantiateItem(ViewGroup container, final int position) {
		ImageView iv = new ImageView(mContext);
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Log.d("SP", "0");
					Intent intent1 = new Intent();
					intent1.setClass(mContext, navigationone.class);
					mContext.startActivity(intent1);
					break;
				case 1:
					Log.d("SP", "1");
					Intent intent2 = new Intent();
					intent2.setClass(mContext, navigationtwo.class);
					mContext.startActivity(intent2);
					break;
				case 2:
					Log.d("SP", "2");
					Intent intent3 = new Intent();
					intent3.setClass(mContext, navigationthree.class);
					mContext.startActivity(intent3);
					break;
				case 3:
					Log.d("SP", "3");
					Intent intent4 = new Intent();
					intent4.setClass(mContext, navigationfour.class);
					mContext.startActivity(intent4);
					break;
				default:
					break;
				}

			}

		});
		// Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),
		// mPaths.get(position));// 载入bitmap
		// iv.setImageBitmap(bm);
		Drawable bitmapDrawable = mContext.getResources().getDrawable(mPaths.get(position));
		iv.setImageDrawable(bitmapDrawable);

		((ViewPager) container).addView(iv, 0);
		return iv;
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
