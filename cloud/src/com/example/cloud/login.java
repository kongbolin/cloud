package com.example.cloud;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.sharesdk.google.GooglePlus;

public class login extends Activity {
	private EditText edit_username;
	private EditText edit_password;
	private Button btn_login_cancel;
	private Button btn_login;
	private Button btn_login_affirm;
	private Button btn_logingone;
	private String username;
	private String password;
	private int symbel = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private SharedPreferences pre;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				if (symbel == 0) {
					newpage();
					Log.e("userinfo", "error");
				} else {
					Write();
					Log.e("userinfo", "right");
					nextpage();
				}
				// go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		edit_username = (EditText) findViewById(R.id.edit_username);
		edit_password = (EditText) findViewById(R.id.edit_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login_cancel = (Button) findViewById(R.id.btn_login_cancel);
		btn_login_affirm = (Button) findViewById(R.id.btn_login_to);
		btn_logingone = (Button) findViewById(R.id.btn_logingone);
		final ArrayList nameValuePairs = new ArrayList();
		btn_login_affirm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// SimpleDateFormat sDateFormat = new
				// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				// String date = sDateFormat.format(new java.util.Date());
				// Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time
				// // Zone资料。
				// t.setToNow(); // 取得系统时间。
				// int year = t.year;
				// int month = t.month;
				// int date1 = t.monthDay;
				// int date2 = year * 365 + month * 30 + date1;
				// orderdate = Integer.toString(date2);
				// int hour = t.hour; // 0-23
				// int minute = t.minute;
				// int second = t.second;
				// int data = (year - 2015) * 365 * 24 * 3600 + month * 30 * 24
				// * 3600 + date1 * 24 * 3600 + hour * 3600
				// + minute * 60 + second;
				// orderid = Integer.toString(data);
				username = edit_username.getText().toString();
				password = edit_password.getText().toString();
				// // TODO Auto-generated method stub
				// EditText tv = (EditText) findViewById(R.id.editView);
				// ArrayList<NameValuePair> nameValuePairs = new
				// ArrayList<NameValuePair>();
				// EditText tv = (EditText) findViewById(R.id.editView);
				// ArrayList nameValuePairs = new ArrayList();
				// nameValuePairs.add(new BasicNameValuePair("id", "3"));
				// nameValuePairs.add(new BasicNameValuePair("name", "Guo"));
				// nameValuePairs.add(new BasicNameValuePair("school", "xjtu"));

				// if (btn_baojie_ok_i == 1) {
				//
				// btn_baojie_ok.setBackgroundResource(R.drawable.bacyes);
				//
				// btn_baojie_ok_i = (-1) * btn_baojie_ok_i;
				// }
				// if (btn_baojie_ok_i == -1) {
				//
				// btn_baojie_ok.setBackgroundResource(R.drawable.bacno);
				//
				// btn_baojie_ok_i = (-1) * btn_baojie_ok_i;
				// }
				// http get
				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						try {
							// HttpClient httpclient = new DefaultHttpClient();
							// HttpPost httppost = new
							// HttpPost("http://120.27.45.56/insertorderbaojie.php");

							// httppost.setEntity(new
							// UrlEncodedFormEntity(nameValuePairs));
							// HttpResponse response =
							// httpclient.execute(httppost);
							// HttpEntity entity = response.getEntity();
							nameValuePairs.add(new BasicNameValuePair("username", username));
							nameValuePairs.add(new BasicNameValuePair("password", password));
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://120.27.45.56/selectuserinfo.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
							Log.e("log_tag", "isme ");
						} catch (Exception e) {
							Log.d("log_tag", "Error in http connection" + e.toString());
						}
						// convert response to string
						try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
							sb = new StringBuilder();
							sb.append(reader.readLine() + "\n");

							String line = "0";
							while ((line = reader.readLine()) != null) {
								sb.append(line + "\n");
							}
							is.close();
							result = sb.toString();
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						// paring data
						String username1;
						String password1;
						// String address1;
						// String contact1;
						// String phonenumber1;
						// String lengthtime1;
						// String totalprice1;
						// String gotime1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							symbel = 0;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								symbel++;
								username1 = json_data.getString("username");
								password1 = json_data.getString("password");
								// address1 = json_data.getString("address");
								// contact1 = json_data.getString("contact");
								// phonenumber1 =
								// json_data.getString("phonenumber");
								// lengthtime1 =
								// json_data.getString("lengthtime");
								// totalprice1 =
								// json_data.getString("totalprice");
								// gotime1 = json_data.getString("gotime");
								// typename = typename1;
								// otherneed = otherneed1;
								// address = address1;
								// contact = contact1;
								// phonenumber = phonenumber1;
								// lengthtime = lengthtime1;
								// totalprice = totalprice1;
								// gotime = gotime1;
								// string[i] = (String) ct_name;
								//
								// datalength++;
								// Log.e("log_tag", ct_name);
								// Log.e("log_tag", ct_name);
								// Message msg = new Message();
								// 便我们做出不同的处理操作
								// msg.what = 1;

								// 我们可以通过arg1和arg2给Message传入简单的数据
								// msg.arg1 = 123;
								// msg.arg2 = 321;
								// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
								// msg.obj = null;
								// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
								// msg.setData(null);
								// Bundle data = msg.getData();

								// 将该Message发送给对应的Handler
								// uiHandler.sendMessage(msg);
							}
						} catch (JSONException e1) {
							// Toast.makeText(getBaseContext(), "No City Found"
							// ,Toast.LENGTH_LONG).show();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

						// 我们可以通过arg1和arg2给Message传入简单的数据
						msg.arg1 = 123;
						msg.arg2 = 321;
						// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
						// msg.obj = null;
						// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
						// msg.setData(null);
						// Bundle data = msg.getData();

						// 将该Message发送给对应的Handler
						uiHandler.sendMessage(msg);
					}
				}).start();
				// Intent intent05 = new Intent();
				// intent05.setClass(baojie.this, yuesao.class);
				// startActivity(intent05);

				// convert response to string
				// try {
				// BufferedReader reader = new BufferedReader(new
				// InputStreamReader(is, "iso-8859-1"), 8);
				// sb = new StringBuilder();
				// sb.append(reader.readLine() + "\n");
				//
				// String line = "0";
				// while ((line = reader.readLine()) != null) {
				// sb.append(line + "\n");
				// }
				// is.close();
				// result = sb.toString();
				// } catch (Exception e) {
				// Log.e("log_tag", "Error converting result " + e.toString());
				// }
				// // paring data
				// int ct_id;
				// String ct_name;
				// try {
				// jArray = new JSONArray(result);
				// JSONObject json_data = null;
				// for (int i = 0; i < jArray.length(); i++) {
				// json_data = jArray.getJSONObject(i);
				// ct_id = json_data.getInt("id");
				// ct_name = json_data.getString("name");
				// tv.append(ct_name + " \n");
				// }
				// } catch (JSONException e1) {
				// // Toast.makeText(getBaseContext(), "No City Found"
				// // ,Toast.LENGTH_LONG).show();
				// } catch (ParseException e1) {
				// e1.printStackTrace();
				// }
				btn_login_affirm.setVisibility(View.GONE);
				btn_logingone.setVisibility(View.VISIBLE);
			}
		});
		btn_login_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}

		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent1 = new Intent();
				intent1.setClass(login.this, getusername.class);
				startActivity(intent1);
			}

		});
	}

	protected void nextpage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, viewpaper.class);
		startActivity(intent);
		finish();
	}

	protected void newpage() {
		// TODO Auto-generated method stub
		Intent intent9 = new Intent();
		intent9.setClass(this, login.class);
		startActivity(intent9);
		finish();
	}

	private void Write() {
		// TODO Auto-generated method stub
		Editor edit = pre.edit();
		edit.putString("username", username);
		edit.putString("password", password);
		edit.commit();
	}

	protected String delete(String result2) {
		// TODO Auto-generated method stub
		String str = result2;
		String b[] = str.split("");// 截取字符串为数组
		int k = b.length;
		String sss = Integer.toString(k);
		Log.i("log_tag", sss);
		int i = 0;
		int j = 0;
		for (int p = i; p < k; p++) {
			if (b[p].equals("[")) {
				p = k;
			} else {
				b[p] = "";
			}
			j++;
		}
		String sss1 = Integer.toString(j);
		Log.i("log_tag", sss1);
		String right = "";
		for (int s = j - 1; s < k; s++) {
			right = right + b[s];
		}
		Log.d("log_tag", "right");
		Log.i("log_tag", right);
		return right;

	}
}
