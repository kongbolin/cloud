package com.example.cloud.adapter;

import java.util.List;

import com.example.cloud.R;

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
		ordermodel orderinfo = mData.get(position);

		temp = orderinfo.getOrderid();
		boolean isNum = temp.matches("[0-9]+");
		if (isNum) {
			a = Integer.parseInt(temp);
			b = a / 31536000;
			a = a - 31536000 * b;
			c = a / 2592000;
			a = a - 2592000 * c;
			d = a / 86400;
			b = b + 2015;
			c = c + 1;
			year = Integer.toString(b);
			month = Integer.toString(c);
			day = Integer.toString(d);
			temp = year + "-" + month + "-" + day;
		} 
		contactorder.setText(orderinfo.getContact());
		useridorder.setText(temp);
		typenameorder.setText(orderinfo.getTypename());
		return view;
	}

}
