package server;

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

import com.example.cloud.R;
import com.example.cloud.our;
import com.example.cloud.viewpaper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class myinfo extends Activity {
	private Button myinfo_older;
	private Button myinfo_me;
	private Button btn_serverset;
	private Button btn_viewpaper;
	private Button btn_sever_me;
	private SharedPreferences pre;
	// private String username;
	private TextView text_server_userinfo;
	private int btn_select_myinfo_cicle = 0;
	private int btn_select_myinfo_cle = 0;
	private int btn_confirm_myinfo_cicle = 0;
	private int btn_confirm_myinfo_cle = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private String usernameread;
	private String username = "error";
	private String page = "error";
	private String phone = "error";
	private String company = "error";
	private String infomation = "";
	private String cpage = "";
	private String usernameconfirm = "error";
	private String pageconfirm = "error";
	private String phoneconfirm = "error";
	private String companyconfirm = "error";
	private String infomationconfirm = "";
	private String cpageconfirm = "";
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
			case 2:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				goconfirm();

				// address.this.statusTextView.setText("文件下载完成");
				break;
			case 3:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				// goconfirm();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		myinfo_older = (Button) findViewById(R.id.myinfo_older);
		myinfo_me = (Button) findViewById(R.id.myinfo_me);
		btn_serverset = (Button) findViewById(R.id.btn_serverset);
		btn_viewpaper = (Button) findViewById(R.id.btn_viewpaper);
		btn_sever_me = (Button) findViewById(R.id.btn_sever_me);
		text_server_userinfo = (TextView) findViewById(R.id.text_server_userinfo);
		// text_server_userinfo.setText(username);
		btn_sever_me.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(myinfo.this, our.class);
				Bundle bundle = new Bundle();
				bundle.putString("something", "ggg");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}

		});
		btn_viewpaper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(myinfo.this, viewpaper.class);
				Bundle bundle = new Bundle();
				bundle.putString("something", "ggg");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}

		});
		myinfo_older.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(myinfo.this, myorder.class);
				Bundle bundle = new Bundle();
				bundle.putString("something", "ggg");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}

		});
		btn_serverset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(myinfo.this, serverset.class);
				Bundle bundle = new Bundle();
				bundle.putString("something", "ggg");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}

		});
		(new Thread() {
			@Override
			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				btn_select_myinfo_cicle = 0;
				btn_select_myinfo_cle = 0;
				read();
				for (btn_select_myinfo_cle = 0; btn_select_myinfo_cle < 1; btn_select_myinfo_cle = btn_select_myinfo_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", usernameread));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectserveruserinfo.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_select_myinfo_cicle = 0;
						btn_select_myinfo_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_select_myinfo_cicle = 1;
					}
					if (btn_select_myinfo_cicle == 0) {
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
							btn_select_myinfo_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						String username1;
						String page1;
						String phone1;
						String company1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;

							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								btn_select_myinfo_cle = 1;
								username1 = json_data.getString("username");
								page1 = json_data.getString("page");
								phone1 = json_data.getString("serverphone");
								company1 = json_data.getString("company");
								username = username1;
								page = page1;
								phone = phone1;
								company = company1;
								Message msg = new Message();
								msg.what = 1;
								msg.arg1 = 123;
								msg.arg2 = 321;
								uiHandler.sendMessage(msg);
							}
						} catch (JSONException e1) {
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	protected void goconfirm() {
		// TODO Auto-generated method stub
		infomationconfirm = "";
		cpageconfirm = "";
		if (pageconfirm.equals("client")) {
			cpageconfirm = "客户端";
		} else {
			cpageconfirm = "服务端";
		}
		infomationconfirm = "账号：" + usernameconfirm + "\n" + " 客服电话：" + phoneconfirm + "\n" + "开启页面：" + cpageconfirm
				+ "\n" + "公司：" + companyconfirm;
		text_server_userinfo.setText(infomationconfirm);
	}

	protected void go() {
		// TODO Auto-generated method stub
		infomation = "";
		cpage = "";
		if (page.equals("client")) {
			cpage = "客户端";
		} else {
			cpage = "服务端";
		}
		infomation = "账号：" + username + "\n" + " 客服电话：" + phone + "\n" + "开启页面：" + cpage + "\n" + "公司：" + company;
		text_server_userinfo.setText(infomation);
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

	private void read() {
		// TODO Auto-generated method stub
		usernameread = pre.getString("username", "");
		Log.d("usernfgsgfme", username);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		switch (requestCode) {
		case 1:
			boolean staues = bundle.getBoolean("staues");
			if (staues) {
				AlertDialog alertDialog = new AlertDialog.Builder(myinfo.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(myinfo.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setMessage("修改成功");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						gooutput();
					}
				});

				alertDialog.show();
			} else {
				AlertDialog alertDialog = new AlertDialog.Builder(myinfo.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(myinfo.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setMessage("修改失败");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						gooutput();
					}
				});

				alertDialog.show();
			}
			break;

		default:
			break;
		}
	}

	protected void gooutput() {
		// TODO Auto-generated method stub
		(new Thread() {
			@Override
			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				btn_confirm_myinfo_cicle = 0;
				btn_confirm_myinfo_cle = 0;
				read();
				for (btn_confirm_myinfo_cle = 0; btn_confirm_myinfo_cle < 1; btn_confirm_myinfo_cle = btn_confirm_myinfo_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", usernameread));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectserveruserinfo.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_confirm_myinfo_cicle = 0;
						btn_confirm_myinfo_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_confirm_myinfo_cicle = 1;
					}
					if (btn_confirm_myinfo_cicle == 0) {
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
							btn_confirm_myinfo_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						String usernameconfirm1;
						String pageconfirm1;
						String phoneconfirm1;
						String companyconfirm1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;

							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								btn_confirm_myinfo_cle = 1;
								usernameconfirm1 = json_data.getString("username");
								pageconfirm1 = json_data.getString("page");
								phoneconfirm1 = json_data.getString("serverphone");
								companyconfirm1 = json_data.getString("company");
								usernameconfirm = usernameconfirm1;
								pageconfirm = pageconfirm1;
								phoneconfirm = phoneconfirm1;
								companyconfirm = companyconfirm1;
								Message msg = new Message();
								msg.what = 2;
								msg.arg1 = 123;
								msg.arg2 = 321;
								uiHandler.sendMessage(msg);
							}
						} catch (JSONException e1) {
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
