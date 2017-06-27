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

public class getusername extends Activity {
	private int btn_new_affirm_cle = 0;
	private int go_cicle = 0;
	private int go_cle = 0;
	private EditText edit_newusername;
	private EditText edit_newpassword;
	private Button btn_new_affirm;
	private Button btn_new_cancel;
	private Button btn__affirmgone;
	private String username;
	private String password;
	private int symbel = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private Button btn_new_back;
	private Button btn_new_backerror;
	private SharedPreferences pre;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				// if (symbel == 0) {
				// // newpage();
				// Log.e("userinfo", "error");
				// } else {
				// // Write();
				// Log.e("userinfo", "right");
				// // nextpage();
				// }
				go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};
	private Handler uiHandler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				// if (symbel == 0) {
				// // newpage();
				// Log.e("userinfo", "error");
				// } else {
				// // Write();
				// Log.e("userinfo", "right");
				// // nextpage();
				// }
				// go();
				if (symbel == 0) {
					next();
				} else {
					gonext();
				}

				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getusername);
		btn_new_backerror = (Button) findViewById(R.id.btn_new_backerror);
		btn_new_back = (Button) findViewById(R.id.btn_new_back);
		edit_newusername = (EditText) findViewById(R.id.edit_newusername);
		edit_newpassword = (EditText) findViewById(R.id.edit_newpassword);
		btn_new_affirm = (Button) findViewById(R.id.btn_new_affirm);
		btn_new_cancel = (Button) findViewById(R.id.btn_new_cancel);
		btn__affirmgone = (Button) findViewById(R.id.btn__affirmgone);
		// btn_logingone = (Button) findViewById(R.id.btn_logingone);
		final ArrayList nameValuePairs = new ArrayList();
		btn_new_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}

		});
		btn_new_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}

		});
		btn_new_backerror.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newpage();
			}

		});

		btn_new_affirm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				username = edit_newusername.getText().toString();
				password = edit_newpassword.getText().toString();

				(new Thread() {

					@Override

					public void run() {

						nameValuePairs.add(new BasicNameValuePair("password", password));
						nameValuePairs.add(new BasicNameValuePair("username", username));
						btn_new_affirm_cle = 0;
						for (btn_new_affirm_cle = 0; btn_new_affirm_cle < 1; btn_new_affirm_cle = btn_new_affirm_cle) {
							try {

								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/insertuserinfo.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();

								btn_new_affirm_cle = 1;
								is = entity.getContent();
								// Log.e("log_tag", "对不对" );
							} catch (Exception e) {
								Log.e("log_tag", "Error in http connection" + e.toString());
								btn_new_affirm_cle = 0;
							}

						}
						Message msg = new Message();
						msg.what = 1;
						msg.arg1 = 123;
						msg.arg2 = 321;
						uiHandler.sendMessage(msg);
					}
				}).start();
				btn_new_affirm.setVisibility(View.GONE);
				btn__affirmgone.setVisibility(View.VISIBLE);
				btn_new_cancel.setVisibility(View.VISIBLE);
				btn_new_back.setVisibility(View.GONE);
				btn_new_backerror.setVisibility(View.GONE);
			}
		});

	}

	protected void newpage() {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent();
		intent1.setClass(getusername.this, getusername.class);
		startActivity(intent1);
		finish();
	}

	protected void gonext() {
		// TODO Auto-generated method stub
		btn_new_affirm.setVisibility(View.GONE);
		btn__affirmgone.setVisibility(View.GONE);
		btn_new_cancel.setVisibility(View.GONE);
		btn_new_back.setVisibility(View.VISIBLE);
		btn_new_backerror.setVisibility(View.GONE);
	}

	protected void next() {
		// TODO Auto-generated method stub
		btn_new_affirm.setVisibility(View.GONE);
		btn__affirmgone.setVisibility(View.GONE);
		btn_new_cancel.setVisibility(View.GONE);
		btn_new_back.setVisibility(View.GONE);
		btn_new_backerror.setVisibility(View.VISIBLE);
	}

	protected void go() {
		// TODO Auto-generated method stub
		(new Thread() {

			@Override

			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// http get
				go_cicle = 0;
				go_cle = 0;
				for (go_cle = 0; go_cle < 1; go_cle = go_cle) {
					try {
						username = edit_newusername.getText().toString();
						password = edit_newpassword.getText().toString();
						Log.e("username", username);
						Log.e("password", password);
						nameValuePairs.add(new BasicNameValuePair("username", username));
						nameValuePairs.add(new BasicNameValuePair("password", password));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectuserinfo.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						go_cicle = 0;
						go_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						go_cicle = 1;
					}
					if (go_cicle == 0) {
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
							go_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						// paring data
						String username1;
						String password1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							symbel = 0;
							go_cle = 1;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								symbel++;
								Log.e("log_tag", "ismetuuuuuoo ");
								username1 = json_data.getString("username");
								password1 = json_data.getString("password");
							}
						} catch (JSONException e1) {
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

					}
				}
				Message msg = new Message();
				// 便我们做出不同的处理操作
				msg.what = 2;

				// 我们可以通过arg1和arg2给Message传入简单的数据
				msg.arg1 = 11111;
				msg.arg2 = 222222222;

				uiHandler1.sendMessage(msg);
			}
		}).start();
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
