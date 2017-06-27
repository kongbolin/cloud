package com.example.cloud.adapter;

import java.util.List;

import com.example.cloud.R;
import com.example.cloud.login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import model.ordermodel;

public class orderadapter extends BaseAdapter {
	private List<ordermodel> mData;
	private Context mContext;
	private String temp;
	private int a, b, c, d;
	private String year, month, day, date;

	public orderadapter(Context context, List data) {
		this.mContext = context;
		this.mData = data;
	}

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = View.inflate(mContext, R.layout.itemorder, null);
		TextView contactorder = (TextView) view.findViewById(R.id.contactorder);
		TextView useridorder = (TextView) view.findViewById(R.id.useridorder);
		TextView typenameorder = (TextView) view.findViewById(R.id.typenameorder);
		int totalsize = getCount();
		ordermodel orderinfo = mData.get(totalsize - position - 1);

		temp = orderinfo.getOrderid();
		temp = datechange(temp);
		Log.d("tempppppp", temp);
		// Log.e("log_tag", "Error in http connection");
		// boolean isNum = temp.matches("[0-9]+");
		// if (isNum) {
		// a = Integer.parseInt(temp);
		// b = a / 31536000;
		// a = a - 31536000 * b;
		// c = a / 2592000;
		// a = a - 2592000 * c;
		// d = a / 86400;
		// b = b + 2015;
		// c = c + 1;
		// year = Integer.toString(b);
		// month = Integer.toString(c);
		// day = Integer.toString(d);
		// temp = year + "-" + month + "-" + day;
		// }
		contactorder.setText(orderinfo.getContact());
		useridorder.setText(temp);
		typenameorder.setText(orderinfo.getTypename());
		return view;
	}

	private String datechange(String temp2) {
		// TODO Auto-generated method stub
		String datenumber = temp2;
		// String b[] = { "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x",
		// "x", "x", "x" };
		String b[] = datenumber.split("");
		// for(int i=0;i<14;i++)
		// {
		Log.d("bbbbbbb", b[0]);
		Log.d("bbbbbbb", b[1]);
		// }
		String yearnumber, monthnumber, daynumber, hou, min, sec;
		yearnumber = b[1] + b[2] + b[3] + b[4];
		Log.d("yearnumber", yearnumber);
		monthnumber = b[5] + b[6];
		Log.d("monthnumber", monthnumber);
		daynumber = b[7] + b[8];
		String dateback = yearnumber + "-" + monthnumber + "-" + daynumber;
		return dateback;
	}

}
