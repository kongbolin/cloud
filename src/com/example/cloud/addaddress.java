package com.example.cloud;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//import com.baidu.location.r;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addaddress extends Activity {
	private int back_addadress_int = 1;
	private int btn_addaddress_confirm_cle = 0;
	private int insert_cicle = 0;
	private int insert_delete_cicle = 0;
	private EditText addaddress_addressid;
	private EditText addaddress_userid;
	private EditText addaddress_address;
	private Button btn_addaddress_confirm;
	private Button btn_addaddress_confirmemoty;
	private Button btn_addaddress_cancle;
	private String addaddress_addresstext = "";
	InputStream is = null;
	private String addressid;
	private SharedPreferences pre;
	private String username;
	private String password;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				goback();
				// go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(addaddress.this);
			alertDialog.setTitle("添加新地址");
			alertDialog.setMessage("确定抛弃该新地址吗？");
			alertDialog.setPositiveButton("点错了", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
				}
			});
			alertDialog.setNegativeButton("确定退出", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					back_addadress_int = 0;
					finish();
				}
			});
			alertDialog.show();
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addaddress);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		String contentupdate = bundle.getString("something");
		btn_addaddress_confirmemoty = (Button) findViewById(R.id.btn_addaddress_confirmemoty);
		btn_addaddress_confirm = (Button) findViewById(R.id.btn_addaddress_confirm);
		btn_addaddress_cancle = (Button) findViewById(R.id.btn_addaddress_cancle);
		addaddress_addressid = (EditText) findViewById(R.id.addaddress_addressid);
		addaddress_userid = (EditText) findViewById(R.id.addaddress_userid);
		addaddress_address = (EditText) findViewById(R.id.addaddress_address);
		addaddress_addresstext = addaddress_address.getText().toString();
		// String temp=addaddress_addresstext;
		// int addaddress_addressidtext = Integer.parseInt(content);

		Log.d("value", "addaddress_addresstext");
		final ArrayList nameValuePairs = new ArrayList();
		btn_addaddress_confirm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				addaddress_addresstext = addaddress_address.getText().toString();
				Log.d("value", addaddress_addresstext);
				btn_addaddress_confirm.setVisibility(View.GONE);
				btn_addaddress_confirmemoty.setVisibility(View.VISIBLE);
				insert_cicle = 0;
				if (addaddress_addresstext.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(addaddress.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(addaddress.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请填写新地址");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();

				} else if (true) {
					(new Thread() {

						@Override

						public void run() {

							Time t = new Time();

							t.setToNow();
							int year = t.year;
							String yearstr = Integer.toString(year);
							int month = t.month + 1;
							String monthstr = Integer.toString(month);
							if (monthstr.length() == 1) {
								monthstr = "0" + monthstr;
							}
							int date1 = t.monthDay;
							String date1str = Integer.toString(date1);
							if (date1str.length() == 1) {
								date1str = "0" + date1str;
							}
							// int date2 = year * 365 + month * 30 + date1;
							// orderdate = Integer.toString(date2);
							int hour = t.hour; // 0-23
							String hourstr = Integer.toString(hour);
							if (hourstr.length() == 1) {
								hourstr = "0" + hourstr;
							}
							int minute = t.minute;
							String minutestr = Integer.toString(minute);
							if (minutestr.length() == 1) {
								minutestr = "0" + minutestr;
							}
							int second = t.second;
							String secondstr = Integer.toString(second);
							if (secondstr.length() == 1) {
								secondstr = "0" + secondstr;
							}
							// long data = (year - 2015) * 365 * 24 * 3600 +
							// month *
							// 30 * 24 * 3600 + date1 * 24 * 3600
							// + hour * 3600 + minute * 60 + second;
							addressid = yearstr + monthstr + date1str + hourstr + minutestr + secondstr;
							Log.e("riqiqqqqqqqqq", addressid);
							nameValuePairs.add(new BasicNameValuePair("address", addaddress_addresstext));
							nameValuePairs.add(new BasicNameValuePair("addressid", addressid));
							nameValuePairs.add(new BasicNameValuePair("userid", username));
							for (insert_cicle = 0; insert_cicle < 1; insert_cicle = insert_cicle) {
								if (back_addadress_int == 1) {

									try {
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost("http://www.sundaytek.com/insertaddress.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();
										is = entity.getContent();

										insert_cicle = 1;
									} catch (Exception e) {
										Log.e("log_tag", "Error in http connection" + e.toString());
										insert_cicle = 0;
									}
								}
							}
							if (back_addadress_int == 1) {
								Message msg = new Message();
								msg.what = 1;
								Log.e("log_tag", "outoutoutou热热特特t");
								msg.arg1 = 123;
								msg.arg2 = 321;
								uiHandler.sendMessage(msg);
							} else {
								ArrayList nameValuePairs = new ArrayList();
								nameValuePairs.add(new BasicNameValuePair("address", addaddress_addresstext));
								nameValuePairs.add(new BasicNameValuePair("username", username));
								for (insert_delete_cicle = 0; insert_delete_cicle < 1; insert_delete_cicle = insert_delete_cicle) {
									try {
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost("http://www.sundaytek.com/deleteaddress.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();
										is = entity.getContent();
										insert_delete_cicle = 1;
									} catch (Exception e) {
										Log.e("log_tag", "Error in http connection" + e.toString());
										insert_delete_cicle = 0;
									}
								}
							}
						}

					}).start();
				}
			}
		});
		btn_addaddress_cancle.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(addaddress.this);
				alertDialog.setTitle("添加新地址");
				alertDialog.setMessage("确定抛弃该新地址吗？");
				alertDialog.setPositiveButton("点错了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					}
				});
				alertDialog.setNegativeButton("确定退出", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						back_addadress_int = 0;
						finish();
					}
				});
				alertDialog.show();

			}
		});
	}

	protected void goback() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("otherneed", "1");
		setResult(0, intent);
		finish();
	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}
}
