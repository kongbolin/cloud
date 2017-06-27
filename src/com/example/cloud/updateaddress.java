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
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class updateaddress extends Activity {
	private int updateaddress_cle = 0;
	private String contentupdate;
	private String content;
	private EditText updateaddress_address;
	private Button btn_updateaddress_cancle;
	private Button btn_updateaddress_confirm;
	InputStream is = null;
	private SharedPreferences pre;
	private String username;
	private String password;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// case 1:
			// System.out.println("handleMessage thread id " +
			// Thread.currentThread().getId());
			// System.out.println("msg.arg1:" + msg.arg1);
			// System.out.println("msg.arg2:" + msg.arg2);
			//// go();
			// // address.this.statusTextView.setText("文件下载完成");
			// break;
			case 2:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				goback();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

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
						updateaddress_cle = 0;
						for (updateaddress_cle = 0; updateaddress_cle < 1; updateaddress_cle = updateaddress_cle) {
							try {
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/updateaddress.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								updateaddress_cle = 1;
							} catch (Exception e) {
								updateaddress_cle = 0;
								Log.e("log_tag", "Error in http connection" + e.toString());
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

	protected void goback() {
		// TODO Auto-generated method stub
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

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}
}
