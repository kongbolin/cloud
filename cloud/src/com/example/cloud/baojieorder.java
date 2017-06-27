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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import、。 com.baidu.location.r;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class baojieorder extends Activity {
	private TextView text_baojie_order_typenamescreen;
	private TextView text_baojie_order_otherneedscreen;
	private TextView text_baojie_order_addressscreen;
	private TextView text_baojie_order_contactscreen;
	private TextView text_baojie_order_phonenumberscreen;
	private TextView text_baojie_order_lengthtimescreen;
	private TextView text_baojie_order_totalpricescreen;
	private TextView text_baojie_order_gotimescreen;
	private String typename = "error";
	private String otherneed = "error";
	private String address = "error";
	private String contact = "error";
	private String phonenumber = "error";
	private String lengthtime = "error";
	private String totalprice = "error";
	private String gotime = "error";
	private String orderid = "error";
	private String userid = "error";
	private TextView text_baojie_order_detail;
	private Button btn_baojie_order_cancle;
	private Button btn_baojie_order_confirm;
	private int ct_id;
	private String ct_name;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baojieorder);
		btn_baojie_order_cancle = (Button) findViewById(R.id.btn_baojie_order_cancle);
		btn_baojie_order_confirm = (Button) findViewById(R.id.btn_baojie_order_confirm);
		text_baojie_order_typenamescreen = (TextView) findViewById(R.id.text_baojie_order_typenamescreen);
		text_baojie_order_otherneedscreen = (TextView) findViewById(R.id.text_baojie_order_otherneedscreen);
		text_baojie_order_addressscreen = (TextView) findViewById(R.id.text_baojie_order_addressscreen);
		text_baojie_order_contactscreen = (TextView) findViewById(R.id.text_baojie_order_contactscreen);
		text_baojie_order_phonenumberscreen = (TextView) findViewById(R.id.text_baojie_order_phonenumberscreen);
		text_baojie_order_lengthtimescreen = (TextView) findViewById(R.id.text_baojie_order_lengthtimescreen);
		text_baojie_order_totalpricescreen = (TextView) findViewById(R.id.text_baojie_order_totalpricescreen);
		text_baojie_order_gotimescreen = (TextView) findViewById(R.id.text_baojie_order_gotimescreen);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		orderid = bundle.getString("orderid");
		userid = bundle.getString("userid");
		Log.d("baojieorder", orderid);
		Log.d("baojieorder", userid);
		btn_baojie_order_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

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
							nameValuePairs.add(new BasicNameValuePair("userid", userid));
							nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://120.27.45.56/deletebaojieorder.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
							Log.e("log_tag", "isme ");
						} catch (Exception e) {
							Log.d("log_tag", "Error in http connection" + e.toString());
						}
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
						// result = sb.toString();result = delete(result);
						// Log.e("log_tag", "ismetoo ");
						// } catch (Exception e) {
						// Log.e("log_tag", "Error converting result " +
						// e.toString());
						// }
						// // paring data
						// String typename1;
						// String otherneed1;
						// String address1;
						// String contact1;
						// String phonenumber1;
						// String lengthtime1;
						// String totalprice1;
						// String gotime1;
						//// try {
						// jArray = new JSONArray(result);
						// JSONObject json_data = null;
						//
						// for (int i = 0; i < jArray.length(); i++) {
						// json_data = jArray.getJSONObject(i);
						//
						// typename1 = json_data.getString("typename");
						// otherneed1 = json_data.getString("otherneed");
						// address1 = json_data.getString("address");
						// contact1 = json_data.getString("contact");
						// phonenumber1 = json_data.getString("phonenumber");
						// lengthtime1 = json_data.getString("lengthtime");
						// totalprice1 = json_data.getString("totalprice");
						// gotime1 = json_data.getString("gotime");
						// typename = typename1;
						// otherneed = otherneed1;
						// address = address1;
						// contact = contact1;
						// phonenumber = phonenumber1;
						// lengthtime = lengthtime1;
						// totalprice = totalprice1;
						// gotime = gotime1;
						// // string[i] = (String) ct_name;
						// //
						// // datalength++;
						// // Log.e("log_tag", ct_name);
						//// Log.e("log_tag", ct_name);
						// // Message msg = new Message();
						// // 便我们做出不同的处理操作
						// // msg.what = 1;
						// Message msg = new Message();
						// // 便我们做出不同的处理操作
						// msg.what = 1;
						//
						// // 我们可以通过arg1和arg2给Message传入简单的数据
						// msg.arg1 = 123;
						// msg.arg2 = 321;
						// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
						// // msg.obj = null;
						// //
						// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
						// // msg.setData(null);
						// // Bundle data = msg.getData();
						//
						// // 将该Message发送给对应的Handler
						// uiHandler.sendMessage(msg);
						// // 我们可以通过arg1和arg2给Message传入简单的数据
						// // msg.arg1 = 123;
						// // msg.arg2 = 321;
						// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
						// // msg.obj = null;
						// //
						// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
						// // msg.setData(null);
						// // Bundle data = msg.getData();
						//
						// // 将该Message发送给对应的Handler
						// // uiHandler.sendMessage(msg);
						// }
						// } catch (JSONException e1) {
						// // Toast.makeText(getBaseContext(), "No City Found"
						// // ,Toast.LENGTH_LONG).show();
						// } catch (ParseException e1) {
						// e1.printStackTrace();
						// }
					}
				}).start();
				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				// intent.putExtra("staues", "cancle");

				intent.putExtra("staues", false);
				setResult(0, intent);
				finish();

			}

		});
		btn_baojie_order_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("staues", true);
				// intent.putExtra("staues", "confirm");
				setResult(0, intent);
				finish();

			}

		});
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
					// HttpResponse response = httpclient.execute(httppost);
					// HttpEntity entity = response.getEntity();
					nameValuePairs.add(new BasicNameValuePair("userid", userid));
					nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://120.27.45.56/selectbaojieorder.php");
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
				String typename1;
				String otherneed1;
				String address1;
				String contact1;
				String phonenumber1;
				String lengthtime1;
				String totalprice1;
				String gotime1;
				try {
					jArray = new JSONArray(result);
					JSONObject json_data = null;

					for (int i = 0; i < jArray.length(); i++) {
						json_data = jArray.getJSONObject(i);

						typename1 = json_data.getString("typename");
						otherneed1 = json_data.getString("otherneed");
						address1 = json_data.getString("address");
						contact1 = json_data.getString("contact");
						phonenumber1 = json_data.getString("phonenumber");
						lengthtime1 = json_data.getString("lengthtime");
						totalprice1 = json_data.getString("totalprice");
						gotime1 = json_data.getString("gotime");
						typename = typename1;
						otherneed = otherneed1;
						address = address1;
						contact = contact1;
						phonenumber = phonenumber1;
						lengthtime = lengthtime1;
						totalprice = totalprice1;
						gotime = gotime1;
						// string[i] = (String) ct_name;
						//
						// datalength++;
						// Log.e("log_tag", ct_name);
						// Log.e("log_tag", ct_name);
						// Message msg = new Message();
						// 便我们做出不同的处理操作
						// msg.what = 1;
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
			}
		}).start();
	}

	protected void go() {
		// TODO Auto-generated method stub
		// text_baojie_order_detail.setText(typename);
		// typename = json_data.getString("typename");
		// otherneed = json_data.getString("otherneed");
		// address = json_data.getString("address");
		// contact = json_data.getString("contact");
		// phonenumber = json_data.getString("phonenumber");
		// lengthtime = json_data.getString("lengthtime");
		// totalprice = json_data.getString("totalprice");
		// gotime = json_data.getString("gotime");
		text_baojie_order_typenamescreen.setText(typename);
		text_baojie_order_otherneedscreen.setText(otherneed);
		text_baojie_order_addressscreen.setText(address);
		text_baojie_order_contactscreen.setText(contact);
		text_baojie_order_phonenumberscreen.setText(phonenumber);
		text_baojie_order_lengthtimescreen.setText(lengthtime);
		text_baojie_order_totalpricescreen.setText(totalprice);
		text_baojie_order_gotimescreen.setText(gotime);
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
