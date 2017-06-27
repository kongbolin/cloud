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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class updateaddress extends Activity {
	private String contentupdate;
	private String content;
	private EditText updateaddress_address;
	private Button btn_updateaddress_cancle;
	private Button btn_updateaddress_confirm;
	InputStream is = null;
	private SharedPreferences pre;
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateaddress);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		btn_updateaddress_cancle = (Button) findViewById(R.id.btn_updateaddress_cancle);
		btn_updateaddress_confirm = (Button) findViewById(R.id.btn_updateaddress_confirm);
		updateaddress_address = (EditText) findViewById(R.id.updateaddress_address);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		contentupdate = bundle.getString("something");
		updateaddress_address.setText(contentupdate);
		btn_updateaddress_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				(new Thread() {

					@Override

					public void run() {
						content = updateaddress_address.getText().toString();
						ArrayList nameValuePairs = new ArrayList();
						nameValuePairs.add(new BasicNameValuePair("addressupdate", content));
						nameValuePairs.add(new BasicNameValuePair("address", contentupdate));
						nameValuePairs.add(new BasicNameValuePair("username", username));
						// nameValuePairs.add(new
						// BasicNameValuePair("totalprice", "50"));
						// nameValuePairs.add(new
						// BasicNameValuePair("lengthoftime", "2"));
						// nameValuePairs.add(new BasicNameValuePair("godate",
						// "2015-45"));
						// nameValuePairs.add(new
						// BasicNameValuePair("otherneed", "otherneed"));
						// nameValuePairs.add(new
						// BasicNameValuePair("otherneed", temp));
						// nameValuePairs.add(new BasicNameValuePair("address",
						// "西安碑林"));
						// nameValuePairs.add(new BasicNameValuePair("contact",
						// "吴新初"));
						// nameValuePairs.add(new
						// BasicNameValuePair("phonenumber", "18710861689"));
						// nameValuePairs.add(new BasicNameValuePair("markone",
						// "6"));
						// nameValuePairs.add(new BasicNameValuePair("marktwo",
						// "20"));
						// nameValuePairs.add(new BasicNameValuePair("test",
						// "吴新初"));
						// nameValuePairs.add(new BasicNameValuePair("testone",
						// "吴新初"));
						// 这里做网络操作
						try {
							// HttpClient httpclient = new DefaultHttpClient();
							// HttpGet httpget = new
							// HttpGet("http://120.27.45.56/test.php");
							// HttpResponse response =
							// httpclient.execute(httpget);
							// HttpEntity entity = response.getEntity();
							// is = entity.getContent();
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://120.27.45.56/updateaddress.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							// httppost.setEntity(new
							// UrlEncodedFormEntity(nameValuePairs));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						} catch (Exception e) {
							Log.e("log_tag", "Error in http connection" + e.toString());
						}

					}

				}).start();
				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("otherneed", "1");
				setResult(0, intent);

				// Intent intent12 = new Intent();
				// intent12.setClass(updateaddress.this, address.class);
				// startActivity(intent12);
				finish();

			}

		});
		btn_updateaddress_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent13 = new Intent();
				// intent13.setClass(updateaddress.this, address.class);
				// startActivity(intent13);
				finish();

			}

		});
	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}
}
