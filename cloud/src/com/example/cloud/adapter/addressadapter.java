package com.example.cloud.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.cloud.R;
import com.example.cloud.address;
import com.example.cloud.navigationfour;
import com.example.cloud.navigationone;
import com.example.cloud.navigationthree;
import com.example.cloud.navigationtwo;
import com.example.cloud.zhongdiangong;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class addressadapter extends BaseAdapter {
	private List mData;
	private Context mContext;
	private int symble = 1;
	private int getclickposition = 124;
	private String temp;
	InputStream is = null;

	public addressadapter(Context context, List data) {
		this.mContext = context;
		this.mData = data;
	}

	// public addressadapter(Thread thread, List data) {
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public int getclickposition() {
		// TODO Auto-generated method stub
		return getclickposition;
	}
	// public Object instantiateItem(ViewGroup container, final int position) {
	// ImageView iv = new ImageView(mContext);
	// iv.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// switch (position) {
	// case 0:
	// Log.d("SP", "0");
	// Intent intent1 = new Intent();
	// intent1.setClass(mContext, navigationone.class);
	// mContext.startActivity(intent1);
	// break;
	// case 1:
	// Log.d("SP", "1");
	// Intent intent2 = new Intent();
	// intent2.setClass(mContext, navigationtwo.class);
	// mContext.startActivity(intent2);
	// break;
	// case 2:
	// Log.d("SP", "2");
	// Intent intent3 = new Intent();
	// intent3.setClass(mContext, navigationthree.class);
	// mContext.startActivity(intent3);
	// break;
	// case 3:
	// Log.d("SP", "3");
	// Intent intent4 = new Intent();
	// intent4.setClass(mContext, navigationfour.class);
	// mContext.startActivity(intent4);
	// break;
	// default:
	// break;
	// }
	//
	// }
	//
	// });
	//// Drawable bitmapDrawable =
	// mContext.getResources().getDrawable(mPaths.get(position));
	//// iv.setImageDrawable(bitmapDrawable);
	////
	//// ((ViewPager) container).addView(iv, 0);
	// return iv;
	// }
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // TODO Auto-generated method stub

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = View.inflate(mContext, R.layout.itemaddress, null);
		// final Button btn_select = (Button)
		// view.findViewById(R.id.btn_select);
		Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
		Button btn_update = (Button) view.findViewById(R.id.btn_update);

		TextView addresscontext = (TextView) view.findViewById(R.id.addresscontext);
		addresscontext.setText((CharSequence) mData.get(position));
		// btn_select.setOnClickListener(new View.OnClickListener() {
		// int btn_select_i = 1;
		//
		// @Override
		// public void onClick(View v) {
		//
		// // TODO Auto-generated method stub
		//
		// // Toast.makeText(addressadapter.this,
		// //
		// // "点击 " + mData.get(position).get("name"),
		// //
		// // Toast.LENGTH_SHORT).show();
		// String temp = (String) mData.get(position);
		// Log.d("SP", "select");
		// Log.d("testselect", temp);
		// if (btn_select_i == 1 & symble == 1) {
		//
		// getclickposition=position;
		// btn_select.setBackgroundResource(R.drawable.bacyes);
		//
		// symble = -1;
		// btn_select_i = -1;
		//
		// } else if (btn_select_i == 1 & symble == -1) {
		//
		// Log.d("SP", "请勿多选");
		// } else if (btn_select_i == -1 & symble == 1) {
		// Log.d("SP", "不可能");
		//
		// } else if (btn_select_i == -1 & symble == -1) {
		// btn_select.setBackgroundResource(R.drawable.bacno);
		// btn_select_i = 1;
		// symble = 1;
		// }
		// }
		//
		// });
		btn_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				// Toast.makeText(addressadapter.this,
				//
				// "点击 " + mData.get(position).get("name"),
				//
				// Toast.LENGTH_SHORT).show();
				temp = (String) mData.get(position);
				Log.d("SP", "delete");
				Log.d("testdelete", temp);
				
				
			}

		});
		btn_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				// Toast.makeText(addressadapter.this,
				//
				// "点击 " + mData.get(position).get("name"),
				//
				// Toast.LENGTH_SHORT).show();
				// temp = (String) mData.get(position);
				// Log.d("SP", "update");
				// Log.d("testupdate", temp);

			}

		});

		return view;
	}

}
