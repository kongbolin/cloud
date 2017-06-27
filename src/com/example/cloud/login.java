package com.example.cloud;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ParseException;
import android.opengl.EGLSurface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.google.GooglePlus;

public class login extends Activity {
	private TextView text_getmessage;
	private int btn_login_affirm_cle = 0;
	private int btn_login_affirm_cicle = 0;
	private EditText edit_username;
	private EditText edit_password;
	private Button btn_login_cancel;
	private Button btn_login;
	private Button btn_login_affirm;
	private Button btn_logingone;
	private String usernameone;
	private String usernametwo;
	private String password;
	private String username;
	private String passwordget;
	private String page;
	private int symbel = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private SharedPreferences pre;
	private String args;
	private SDKTestSendTemplateSMS sa;
	private String message = "";
	private int time = 60;
	private Timer timer = new Timer();
	TimerTask task;
	private int clcle = 0;
	private int cle = 0;
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
		text_getmessage = (TextView) findViewById(R.id.text_getmessage);
		edit_username = (EditText) findViewById(R.id.edit_username);
		edit_password = (EditText) findViewById(R.id.edit_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login_cancel = (Button) findViewById(R.id.btn_login_cancel);
		btn_login_affirm = (Button) findViewById(R.id.btn_login_to);
		btn_logingone = (Button) findViewById(R.id.btn_logingone);
		// final ArrayList nameValuePairs = new ArrayList();

		text_getmessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				usernameone = edit_username.getText().toString();
				if (usernameone.length() == 11) {
					message = "";
					// TODO Auto-generated method stub
					for (int i = 0; i < 6; i++) {
						int x = (int) (Math.random() * 10);
						String y;
						y = Integer.toString(x);
						Log.d("rrr", y);
						message = message + y;
						Log.d("y", y);
					}
					(new Thread() {

						public void run() {
							SDKTestSendTemplateSMS.main(message, usernameone);
						}

					}).start();
					Toast.makeText(getApplicationContext(), "我们已发送一条验证短信到您的手机,请注意查收", Toast.LENGTH_SHORT).show();
					text_getmessage.setEnabled(false);
					text_getmessage.setBackgroundResource(R.drawable.daojishi);
					task = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() { // UI thread
								@Override
								public void run() {
									if (time <= 0) {
										// 当倒计时小余=0时记得还原图片，可以点击
										text_getmessage.setEnabled(true);
										text_getmessage.setBackgroundResource(R.drawable.btn_yangzhengma_selector);
										text_getmessage.setTextColor(Color.parseColor("#454545"));
										text_getmessage.setText("获取验证码");
										task.cancel();
									} else {
										text_getmessage.setText(time + "秒后重试");
										text_getmessage.setTextColor(Color.rgb(125, 125, 125));
									}
									time--;
								}
							});
						}
					};

					time = 60;
					timer.schedule(task, 0, 1000);
				} else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(login.this);
					alertDialog.setTitle("温馨提示");
					alertDialog.setMessage("请输入11位手机号");
					alertDialog.setPositiveButton("正确输入", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
						}
					});
					alertDialog.show();
				}
			}

		});
		btn_login_affirm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				usernametwo = edit_username.getText().toString();
				password = edit_password.getText().toString();
				if (usernameone.equals(usernametwo)) {
					if (password.equals(message)) {
						btn_login_affirm.setVisibility(View.GONE);
						btn_logingone.setVisibility(View.VISIBLE);
						nextpage();
						Write();
						Write("client");
						Writepage("client");
						Writephone("10086");
						(new Thread() {

							@Override

							public void run() {
								ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
								// http getb[p].equals("[")

								Log.d("gggghhhh", "gggghhhh");
								for (cle = 0; cle < 1; cle = cle) {
									try {
										nameValuePairs.add(new BasicNameValuePair("username", usernametwo));
										nameValuePairs.add(new BasicNameValuePair("password", passwordget));
										HttpClient httpclient = new DefaultHttpClient();
										Log.d("testtest", "inhttp");
										HttpPost httppost = new HttpPost(
												"http://www.sundaytek.com/selectserveruserinfo.php");
										Log.d("testtest", "outhttp");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										Log.d("testtest", "inhttp");
										HttpResponse response = httpclient.execute(httppost);
										Log.d("testtest", "inhttp");
										HttpEntity entity = response.getEntity();
										Log.d("testtest", "inhttp");
										is = entity.getContent();
										clcle = 0;
										cle = 0;
										Log.e("log_tag", "isme ");
									} catch (Exception e) {
										Log.d("log_tag", "Error in http connection" + e.toString());
										clcle = 1;
									}
									// convert response to string
									if (clcle == 0) {
										try {
											BufferedReader reader = new BufferedReader(
													new InputStreamReader(is, "iso-8859-1"), 8);
											sb = new StringBuilder();
											sb.append(reader.readLine());

											String line = "0";
											while ((line = reader.readLine()) != null) {
												sb.append(line);
											}
											is.close();
											String str1 = "qqqeee";
											Log.d("log_tag", str1);
											str1 = delete(str1);
											Log.d("log_tag", str1);
											result = sb.toString();
											Log.d("log_tag", result);
											cle = 1;
											// Log.d("log_tag", "ddd");
											result = delete(result);
											// Log.d("log_tag", "vvv");
											Log.i("log_tag", result);

										} catch (Exception e) {
											Log.e("log_tag", "Error converting result " + e.toString());
										}
										// paring data
										String username1;
										String page1;
										String serverphone1;
										try {
											Log.e("log_tag", "inininin");
											jArray = new JSONArray(result);
											Log.e("log_tag", "outoutoutout");
											JSONObject json_data = null;
											symbel = 0;

											Log.e("log_tag", "ismeto呵呵呵呵呵eeeeeeeeeo ");
											cle = 1;
											for (int i = 0; i < jArray.length(); i++) {
												json_data = jArray.getJSONObject(i);
												symbel++;
												Write("server");
												Log.d("nicececee", "hfhf");
												username1 = json_data.getString("username");
												page1 = json_data.getString("page");
												serverphone1 = json_data.getString("serverphone");
												Writepage(page1);
												Writephone(serverphone1);
											}
										} catch (JSONException e1) {
										} catch (ParseException e1) {
											e1.printStackTrace();
										}

									}
								}

							}

						}).start();
					} else {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(login.this);
						alertDialog.setTitle("温馨提示");
						alertDialog.setMessage("请正确输入验证码");
						alertDialog.setPositiveButton("正确输入", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
							}
						});
						alertDialog.show();
					}
				} else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(login.this);
					alertDialog.setTitle("温馨提示");
					alertDialog.setMessage("获取验证码后请勿更改手机号");
					alertDialog.setPositiveButton("重新输入", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
						}
					});
					alertDialog.show();
				}

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
		edit.putString("username", usernameone);
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

	private void Write(String limit) {
		// TODO Auto-generated method stub
		Editor edit = pre.edit();
		edit.putString("limit", limit);
		edit.commit();
	}

	private void Writepage(String page) {
		// TODO Auto-generated method stub
		Editor edit = pre.edit();
		edit.putString("page", page);
		edit.commit();
	}

	private void Writephone(String phone) {
		// TODO Auto-generated method stub
		Editor edit = pre.edit();
		edit.putString("phone", phone);
		edit.commit();
	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		page = pre.getString("page", "");
		Log.d("username", username);
		Log.d("password", password);
	}
}
