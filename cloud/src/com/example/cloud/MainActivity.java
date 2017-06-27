package com.example.cloud;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.AvoidXfermode.Mode;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.WriteAbortedException;
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

import com.example.cloud.R;

public class MainActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟三秒
	private SharedPreferences pre;
	private String username;
	private String password;
	private TextView text_useradv;
	private TextView text_usersug;
	private int symbel = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
//	private
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				if (symbel == 0) {
					gologin();
					Log.e("userinfo", "error");
				} else {

					Log.e("userinfo", "right");
					goviewpaper();
				}
				// go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};
	// private Handler uiHandler2 = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// switch (msg.what) {
	// case 3:
	// System.out.println("handleMessage thread id " +
	// Thread.currentThread().getId());
	// System.out.println("msg.arg1:" + msg.arg1);
	// System.out.println("msg.arg2:" + msg.arg2);
	// // (new Thread() {
	// //
	// // @Override
	// //
	// // public void run() {
	// try {
	// Thread.sleep(5000);
	// // goser();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // }
	// // }).start();
	// // go();
	// // address.this.statusTextView.setText("文件下载完成");
	//
	// break;
	// }
	// }
	// };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text_useradv = (TextView) findViewById(R.id.text_useradv);
		text_usersug = (TextView) findViewById(R.id.text_usersug);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
////				Intent mainIntent = new Intent(MainActivity.this, viewpaper.class);
////				MainActivity.this.startActivity(mainIntent);
////				MainActivity.this.finish();
//				Message msg = new Message();
//				// 便我们做出不同的处理操作
//				msg.what = 1;
//
//				// 我们可以通过arg1和arg2给Message传入简单的数据
//				msg.arg1 = 123;
//				msg.arg2 = 321;
//				// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
//				// msg.obj = null;
//				// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
//				// msg.setData(null);
//				// Bundle data = msg.getData();
//
//				// 将该Message发送给对应的Handler
//				uiHandler.sendMessage(msg);
//			}
//
//		}, SPLASH_DISPLAY_LENGHT);

		// Write();

		(new Thread() {

			@Override

			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// http get
				try {
					read();
					// HttpClient httpclient = new DefaultHttpClient();
					// HttpPost httppost = new
					// HttpPost("http://121.127.234.233/insertorderbaojie.php");

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
					// Log.d("log_tag", "ddd");
					result = delete(result);
					// Log.d("log_tag", "vvv");
					Log.i("log_tag", result);
				} catch (Exception e) {
					Log.e("log_tag", "Error converting result " + e.toString());
				}
				// paring data
				String username1;
				String password1;
				try {
					Log.e("log_tag", "inininin");
					jArray = new JSONArray(result);
					Log.e("log_tag", "outoutoutout");
					JSONObject json_data = null;
					symbel = 0;

					Log.e("log_tag", "ismeto呵呵呵呵呵eeeeeeeeeo ");
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
					// Toast.makeText(getBaseContext(), "No City Found",
					// Toast.LENGTH_LONG).show();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Message msg = new Message();
				// 便我们做出不同的处理操作
				msg.what = 1;
				Log.e("log_tag", "outoutoutou热热特特t");
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

		// new Handler().postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		// Intent mainIntent = new Intent(MainActivity.this, login.class);
		// MainActivity.this.startActivity(mainIntent);
		// MainActivity.this.finish();
		// }
		//
		// Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time
		// // Zone资料。
		// // t.setToNow(); // 取得系统时间。
		//// int year = t.year;
		//// int month = t.month;
		//// int date1 = t.monthDay;
		//// int date2 = year * 365 + month * 30 + date1;
		//// int hour = t.hour; // 0-23
		//// int minute = t.minute;
		// int second = t.second;
		// int temp = second + 5;
		// while (temp != second) {
		// Time t1 = new Time(); // or Time t=new Time("GMT+8"); 加上Time
		// // Zone资料。
		// // t.setToNow(); // 取得系统时间。
		//
		// second = t.second;
		// }
		// goser();
		// Message msg = new Message();
		// // 便我们做出不同的处理操作
		// msg.what = 3;
		//
		// // 我们可以通过arg1和arg2给Message传入简单的数据
		// msg.arg1 = 123;
		// msg.arg2 = 321;
		// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
		// // msg.obj = null;
		// // 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
		// // msg.setData(null);
		// // Bundle data = msg.getData();
		//
		// // 将该Message发送给对应的Handler
		// uiHandler2.sendMessage(msg);// }, SPLASH_DISPLAY_LENGHT);
		// for(int i=0;i<1000;i++)
		// {
		// for(int j=0;j<1000;j++)
		// {
		// for(int z=0;z<1000;z++)
		// {
		//
		// }
		// }
		// }
		// text_useradv.setVisibility(View.GONE);
		// text_usersug.setVisibility(View.VISIBLE);

	}

	// private void goser() {
	// // TODO Auto-generated method stub
	// // text_useradv.setVisibility(View.GONE);
	// // text_usersug.setVisibility(View.VISIBLE);
	// }

	protected String delete(String result2) {
		// TODO Auto-generated method stub
		String str = result2;

		// str = str.replace("", "-"); // 每个字符加个-
		String b[] = str.split("");// 截取字符串为数组
		int k = b.length;
		String sss = Integer.toString(k);
		Log.i("log_tag", sss);
		int i = 0;
		int j = 0;
		for (int p = i; p < k; p++) {
			// Log.i("log_tag", b[p]);
			if (b[p].equals("[")) {
				p = k;
			} else {
				b[p] = "";
			}
			j++;
		}
		String sss1 = Integer.toString(j);
		Log.i("log_tag", sss1);
		// while (b[i] != "[") {
		// b[i] = "";
		// i++;
		// j++;
		// }
		String right = "";
		for (int s = j - 1; s < k; s++) {
			right = right + b[s];
		}
		Log.d("log_tag", "right");
		Log.i("log_tag", right);
		return right;

	}

	protected void goviewpaper() {
		// TODO Auto-generated method stub
		Intent intent9 = new Intent();
		intent9.setClass(this, viewpaper.class);
		startActivity(intent9);
		finish();
	}

	protected void gologin() {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent();
		intent1.setClass(this, login.class);
		startActivity(intent1);
		finish();
	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}

	// private void Write() {
	// // TODO Auto-generated method stub
	// Editor edit = pre.edit();
	// edit.putString("username", "wuxinchu");
	// edit.commit();
	// }

}
