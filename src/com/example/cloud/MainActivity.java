//package com.example.cloud;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.net.ParseException;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.widget.TextView;
//import server.myorder;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.example.cloud.R;
//
//public class MainActivity extends Activity {
//	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟三秒
//	private SharedPreferences pre;
//	private String username;
//	private String password;
//	private String serverphone;
//	private String page;
//	private TextView text_useradv;
//	private TextView text_usersug;
//	private int symbel = 0;
//	private int clcle = 0;
//	private int cle = 0;
//	JSONArray jArray;
//	String result = null;
//	InputStream is = null;
//	StringBuilder sb = null;
//	// private
//	private Handler uiHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 1:
//				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
//				System.out.println("msg.arg1:" + msg.arg1);
//				System.out.println("msg.arg2:" + msg.arg2);
//				read();
//				if (page.equals("client")) {
//					goviewpaper();
//				} else {
//					if (page.equals("server")) {
//						gomyorder();
//					} else {
//						goviewpaper();
//					}
//				}
//				break;
//			case 2:
//				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
//				System.out.println("msg.arg1:" + msg.arg1);
//				System.out.println("msg.arg2:" + msg.arg2);
//				gologin();
//				break;
//			}
//		}
//	};
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//		text_useradv = (TextView) findViewById(R.id.text_useradv);
//		text_usersug = (TextView) findViewById(R.id.text_usersug);
//		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
//		Write("client");
//		Writepage("client");
//		Writephone("10086");
//		(new Thread() {
//
//			@Override
//
//			public void run() {
//				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//				// http getb[p].equals("[")
//				read();
//				if (username.length() == 11 && password.length() == 6) {
//					Log.d("gggghhhh", "gggghhhh");
//					for (cle = 0; cle < 1; cle = cle) {
//						try {
//							nameValuePairs.add(new BasicNameValuePair("username", username));
//							nameValuePairs.add(new BasicNameValuePair("password", password));
//							HttpClient httpclient = new DefaultHttpClient();
//							Log.d("testtest", "inhttp");
//							HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectserveruserinfo.php");
//							Log.d("testtest", "outhttp");
//							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//							Log.d("testtest", "inhttp");
//							HttpResponse response = httpclient.execute(httppost);
//							Log.d("testtest", "inhttp");
//							HttpEntity entity = response.getEntity();
//							Log.d("testtest", "inhttp");
//							is = entity.getContent();
//							clcle = 0;
//							cle = 0;
//							Log.e("log_tag", "isme ");
//						} catch (Exception e) {
//							Log.d("log_tag", "Error in http connection" + e.toString());
//							clcle = 1;
//						}
//						// convert response to string
//						if (clcle == 0) {
//							try {
//								BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//								sb = new StringBuilder();
//								sb.append(reader.readLine());
//
//								String line = "0";
//								while ((line = reader.readLine()) != null) {
//									sb.append(line);
//								}
//								is.close();
//								String str1 = "qqqeee";
//								Log.d("log_tag", str1);
//								str1 = delete(str1);
//								Log.d("log_tag", str1);
//								result = sb.toString();
//								Log.d("log_tag", result);
//								cle = 1;
//								// Log.d("log_tag", "ddd");
//								result = delete(result);
//								// Log.d("log_tag", "vvv");
//								Log.i("log_tag", result);
//
//							} catch (Exception e) {
//								Log.e("log_tag", "Error converting result " + e.toString());
//							}
//							// paring data
//							String username1;
//							String page1;
//							String serverphone1;
//							try {
//								Log.e("log_tag", "inininin");
//								jArray = new JSONArray(result);
//								Log.e("log_tag", "outoutoutout");
//								JSONObject json_data = null;
//								symbel = 0;
//
//								Log.e("log_tag", "ismeto呵呵呵呵呵eeeeeeeeeo ");
//								cle = 1;
//								for (int i = 0; i < jArray.length(); i++) {
//									json_data = jArray.getJSONObject(i);
//									symbel++;
//									Write("server");
//									Log.d("nicececee", "hfhf");
//									username1 = json_data.getString("username");
//									page1 = json_data.getString("page");
//									serverphone1 = json_data.getString("serverphone");
//									Writepage(page1);
//									Writephone(serverphone1);
//								}
//							} catch (JSONException e1) {
//							} catch (ParseException e1) {
//								e1.printStackTrace();
//							}
//
//						}
//					}
//
//					Message msg = new Message();
//					msg.what = 1;
//					Log.e("log_tag", "outoutoutou热热特特t");
//					msg.arg1 = 123;
//					msg.arg2 = 321;
//					uiHandler.sendMessage(msg);
//				} else {
//					Message msg = new Message();
//					msg.what = 2;
//					Log.e("log_tag", "outoutoutou热热特特t");
//					msg.arg1 = 123;
//					msg.arg2 = 321;
//					uiHandler.sendMessage(msg);
//				}
//
//			}
//		}).start();
//
//	}
//
//	protected String delete(String result2) {
//		// TODO Auto-generated method stub
//		String str = result2;
//
//		// str = str.replace("", "-"); // 每个字符加个-
//		String b[] = str.split("");// 截取字符串为数组
//		int k = b.length;
//		String sss = Integer.toString(k);
//		Log.i("log_tag", sss);
//		int i = 0;
//		int j = 0;
//		for (int p = i; p < k; p++) {
//			// Log.i("log_tag", b[p]);
//			if (b[p].equals("[")) {
//				p = k;
//			} else {
//				b[p] = "";
//			}
//			j++;
//		}
//		String sss1 = Integer.toString(j);
//		Log.i("log_tag", sss1);
//
//		String right = "";
//		for (int s = j - 1; s < k; s++) {
//			right = right + b[s];
//		}
//		Log.d("log_tag", "right");
//		Log.i("log_tag", right);
//		return right;
//
//	}
//
//	protected void goviewpaper() {
//		// TODO Auto-generated method stub
//		Intent intent9 = new Intent();
//		intent9.setClass(this, viewpaper.class);
//		startActivity(intent9);
//		finish();
//	}
//
//	protected void gomyorder() {
//		// TODO Auto-generated method stub
//		Intent intent9 = new Intent();
//		intent9.setClass(this, myorder.class);
//		startActivity(intent9);
//		finish();
//	}
//
//	protected void gologin() {
//		// TODO Auto-generated method stub
//		Intent intent1 = new Intent();
//		intent1.setClass(this, login.class);
//		startActivity(intent1);
//		finish();
//	}
//
//	private void read() {
//		// TODO Auto-generated method stub
//		username = pre.getString("username", "");
//		password = pre.getString("password", "");
//		page = pre.getString("page", "");
//		Log.d("username", username);
//		Log.d("password", password);
//	}
//
//	private void Write(String limit) {
//		// TODO Auto-generated method stub
//		Editor edit = pre.edit();
//		edit.putString("limit", limit);
//		edit.commit();
//	}
//
//	private void Writepage(String page) {
//		// TODO Auto-generated method stub
//		Editor edit = pre.edit();
//		edit.putString("page", page);
//		edit.commit();
//	}
//
//	private void Writephone(String phone) {
//		// TODO Auto-generated method stub
//		Editor edit = pre.edit();
//		edit.putString("phone", phone);
//		edit.commit();
//	}
//}
