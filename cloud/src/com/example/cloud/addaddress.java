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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addaddress extends Activity {
	private EditText addaddress_addressid;
	private EditText addaddress_userid;
	private EditText addaddress_address;
	private Button btn_addaddress_confirm;
	private Button btn_addaddress_cancle;
	private String addaddress_addresstext;
	private String addaddress_useridtext;
	private String addaddress_addressidtext;
	InputStream is = null;
	private String addressid;
	private SharedPreferences pre;
	private String username;
	private String password;

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
		btn_addaddress_confirm = (Button) findViewById(R.id.btn_addaddress_confirm);
		btn_addaddress_cancle = (Button) findViewById(R.id.btn_addaddress_cancle);
		addaddress_addressid = (EditText) findViewById(R.id.addaddress_addressid);
		addaddress_userid = (EditText) findViewById(R.id.addaddress_userid);
		addaddress_address = (EditText) findViewById(R.id.addaddress_address);
		addaddress_addresstext = addaddress_address.getText().toString();
		addaddress_useridtext = addaddress_userid.getText().toString();
		addaddress_addressidtext = addaddress_addressid.getText().toString();
		// String temp=addaddress_addresstext;
		// int addaddress_addressidtext = Integer.parseInt(content);

		Log.d("value", "addaddress_addresstext");
		final ArrayList nameValuePairs = new ArrayList();
		btn_addaddress_confirm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				addaddress_addresstext = addaddress_address.getText().toString();
				addaddress_useridtext = addaddress_userid.getText().toString();
				addaddress_addressidtext = addaddress_addressid.getText().toString();
				Log.d("value", addaddress_addresstext);
				(new Thread() {

					@Override

					public void run() {

						
						Time t = new Time(); 
					
						t.setToNow();
						int year = t.year;
						int month = t.month;
						int date1 = t.monthDay;
						int date2 = year * 365 + month * 30 + date1;
						// orderdate = Integer.toString(date2);
						int hour = t.hour; // 0-23
						int minute = t.minute;
						int second = t.second;
						int data = (year - 2015) * 365 * 24 * 3600 + month * 30 * 24 * 3600 + date1 * 24 * 3600
								+ hour * 3600 + minute * 60 + second;
						addressid = Integer.toString(data);
						nameValuePairs.add(new BasicNameValuePair("address", addaddress_addresstext));
						nameValuePairs.add(new BasicNameValuePair("addressid", addressid));
						nameValuePairs.add(new BasicNameValuePair("userid", username));
						try {
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://120.27.45.56/insertaddress.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						} catch (Exception e) {
							Log.e("log_tag", "Error in http connection" + e.toString());
						}

					}

				}).start();
				Intent intent = new Intent();
				intent.putExtra("otherneed", "1");
				setResult(0, intent);
				finish();
			}
		});
		btn_addaddress_cancle.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
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
