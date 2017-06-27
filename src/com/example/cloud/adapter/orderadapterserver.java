package com.example.cloud.adapter;

import java.util.List;

import com.example.cloud.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import model.ordermodelserver;

public class orderadapterserver extends BaseAdapter {
	private List<ordermodelserver> mData;
	private Context mContext;
	private String temp;
	private int a, b, c, d;
	private String year, month, day, date;
	private String getifpay = "";
	private String getifnew = "";
	private String getifdelete = "";

	public orderadapterserver(Context context, List data) {
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
		View view = View.inflate(mContext, R.layout.itemorderserver, null);
		TextView contactorder = (TextView) view.findViewById(R.id.contactorder);
		TextView useridorder = (TextView) view.findViewById(R.id.useridorder);
		TextView typenameorder = (TextView) view.findViewById(R.id.typenameorder);
		TextView ifpay = (TextView) view.findViewById(R.id.ifpay);
		TextView ifnew = (TextView) view.findViewById(R.id.ifnew);
		TextView ifdelete = (TextView) view.findViewById(R.id.ifdelete);
		TextView state = (TextView) view.findViewById(R.id.state);
		TextView orderusername = (TextView) view.findViewById(R.id.orderusername);
		int totalsize = getCount();
		ordermodelserver orderinfo = mData.get(totalsize - position - 1);
		getifpay = orderinfo.getIfpay();
		getifnew = orderinfo.getIfnew();
		getifdelete = orderinfo.getIfdelete();
//		int result = getifpay.compareTo("true");
		if (getifpay.equals("true")) {
			getifpay = "已付款";
		} else {
			getifpay = "未付款";
		}
		if (getifnew.equals("true")) {
			getifnew = "新";
		} else {
			getifnew = "  ";
		}
		if (getifdelete.equals("true")) {
			getifdelete = "正常";
		} else {
			getifdelete = "客户已取消";
		}
		temp = orderinfo.getOrderid();
		temp = datechange(temp);
		Log.d("tempppppp", temp);
		orderusername.setText(orderinfo.getUserid());
		state.setText(orderinfo.getState());
		contactorder.setText(orderinfo.getContact());
		ifnew.setText(getifnew);
		ifpay.setText(getifpay);
		ifdelete.setText(getifdelete);
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
		// hou = b[9] + b[10];
		// min = b[11] + b[12];
		// sec = b[13] + b[14];
		String dateback = yearnumber + "-" + monthnumber + "-" + daynumber;
		// + " " + hou + ":" + min + ":" + sec;
		return dateback;
	}

}
