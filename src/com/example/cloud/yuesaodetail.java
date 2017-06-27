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
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class yuesaodetail extends Activity {
	private int btn_yuesao_order_cancle_cle = 0;
	private int btn_yuesao_order_confirm_cicle = 0;
	private int btn_yuesao_order_confirm_cle = 0;
	private TextView text_yuesao_order_typenamescreen;
	private TextView text_yuesao_order_otherneedscreen;
	private TextView text_yuesao_order_addressscreen;
	private TextView text_yuesao_order_contactscreen;
	private TextView text_yuesao_order_phonenumberscreen;

	private TextView text_yuesao_order_zhujiascreen;
	private TextView text_yuesao_order_deliverdatescreen;
	private TextView text_yuesao_order_pricescreen;
	private TextView text_yuesao_order_petscreen;
	private String typename = "error";
	private String otherneed = "error";
	private String address = "error";
	private String contact = "error";
	private String phonenumber = "error";

	private String zhujia = "error";
	private String deliverdate = "error";
	private String price = "error";
	private String pet = "error";
	private String orderid = "error";
	private String userid = "error";
	private Button btn_yuesao_order_cancle;
	private Button btn_yuesao_order_confirm;
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
				// address.this.statusTextView.setText("�ļ��������");
				break;
			case 2:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				goback();
				// address.this.statusTextView.setText("�ļ��������");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuesaodetail);
		btn_yuesao_order_cancle = (Button) findViewById(R.id.btn_yuesao_order_cancle);
		btn_yuesao_order_confirm = (Button) findViewById(R.id.btn_yuesao_order_confirm);
		text_yuesao_order_typenamescreen = (TextView) findViewById(R.id.text_yuesao_order_typenamescreen);
		text_yuesao_order_otherneedscreen = (TextView) findViewById(R.id.text_yuesao_order_otherneedscreen);
		text_yuesao_order_addressscreen = (TextView) findViewById(R.id.text_yuesao_order_addressscreen);
		text_yuesao_order_contactscreen = (TextView) findViewById(R.id.text_yuesao_order_contactscreen);
		text_yuesao_order_phonenumberscreen = (TextView) findViewById(R.id.text_yuesao_order_phonenumberscreen);
		text_yuesao_order_zhujiascreen = (TextView) findViewById(R.id.text_yuesao_order_zhujiascreen);
		text_yuesao_order_deliverdatescreen = (TextView) findViewById(R.id.text_yuesao_order_deliverdatescreen);
		text_yuesao_order_pricescreen = (TextView) findViewById(R.id.text_yuesao_order_pricescreen);
		text_yuesao_order_petscreen = (TextView) findViewById(R.id.text_yuesao_order_petscreen);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		orderid = bundle.getString("orderid");
		userid = bundle.getString("userid");
		Log.d("yuesaodetail", orderid);
		Log.d("yuesaodetail", userid);
		btn_yuesao_order_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						btn_yuesao_order_cancle_cle = 0;
						for (btn_yuesao_order_cancle_cle = 0; btn_yuesao_order_cancle_cle < 1; btn_yuesao_order_cancle_cle = btn_yuesao_order_cancle_cle) {
							try {

								nameValuePairs.add(new BasicNameValuePair("userid", userid));
								nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/deleteyuesaoorder.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								btn_yuesao_order_cancle_cle = 1;
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								btn_yuesao_order_cancle_cle = 1;
							}

						}
						Message msg = new Message();
						msg.what = 2;
						msg.arg1 = 123;
						msg.arg2 = 321;
						uiHandler.sendMessage(msg);
					}
				}).start();

			}

		});
		btn_yuesao_order_confirm.setOnClickListener(new OnClickListener() {

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
				btn_yuesao_order_confirm_cicle = 0;
				btn_yuesao_order_confirm_cle = 0;
				for (btn_yuesao_order_confirm_cle = 0; btn_yuesao_order_confirm_cle < 1; btn_yuesao_order_confirm_cle = btn_yuesao_order_confirm_cle) {
					try {

						nameValuePairs.add(new BasicNameValuePair("userid", userid));
						nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectyuesaoorder.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_yuesao_order_confirm_cicle = 0;
						btn_yuesao_order_confirm_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_yuesao_order_confirm_cicle = 1;
					}
					if (btn_yuesao_order_confirm_cicle == 0) {
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
							btn_yuesao_order_confirm_cle = 1;
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

						String zhujia1;
						String deliverdate1;
						String price1;
						String pet1;
						// String lengthtime1;
						// String totalprice1;
						// String gotime1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							btn_yuesao_order_confirm_cle = 1;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);

								typename1 = json_data.getString("typename");
								otherneed1 = json_data.getString("otherneed");
								address1 = json_data.getString("address");
								contact1 = json_data.getString("contact");
								phonenumber1 = json_data.getString("phonenumber");

								zhujia1 = json_data.getString("zhujia");
								deliverdate1 = json_data.getString("deliverdate");
								price1 = json_data.getString("price");
								pet1 = json_data.getString("pet");
								// gotime1 = json_data.getString("taste");
								typename = typename1;
								otherneed = otherneed1;
								address = address1;
								contact = contact1;
								phonenumber = phonenumber1;
								zhujia = zhujia1;
								deliverdate = deliverdate1;
								price = price1;
								pet = pet1;

							}
						} catch (JSONException e1) {
							// Toast.makeText(getBaseContext(), "No City Found"
							// ,Toast.LENGTH_LONG).show();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
				Message msg = new Message();
				// ������������ͬ�Ĵ������
				msg.what = 1;

				msg.arg1 = 123;
				msg.arg2 = 321;

				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	protected void goback() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		// intent.putExtra("otherneed",
		// text_otherneed.getText().toString());
		// intent.putExtra("staues", "cancle");

		intent.putExtra("staues", false);
		setResult(0, intent);
		finish();
	}

	protected void go() {
		// TODO Auto-generated method stub
		text_yuesao_order_typenamescreen.setText(typename);
		text_yuesao_order_otherneedscreen.setText(otherneed);
		text_yuesao_order_addressscreen.setText(address);
		text_yuesao_order_contactscreen.setText(contact);
		text_yuesao_order_phonenumberscreen.setText(phonenumber);
		text_yuesao_order_zhujiascreen.setText(zhujia);
		text_yuesao_order_deliverdatescreen.setText(deliverdate);
		text_yuesao_order_pricescreen.setText(price);
		text_yuesao_order_petscreen.setText(pet);
		// typename = "error";
		// otherneed = "error";
		// address = "error";
		// = "error";
		// phonenumber = "error";
		// content= "error";
		// square= "error";
		// taste= "error";
		// frequency= "error";
		// datestart= "error";
		// dateend= "error";
		// price= "error";
		// pet= "error";
	}

	protected String delete(String result2) {
		// TODO Auto-generated method stub
		String str = result2;
		String b[] = str.split("");// ��ȡ�ַ���Ϊ����
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
