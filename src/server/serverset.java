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
import com.example.cloud.baojie;

import android.R.string;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class serverset extends Activity {
	private LinearLayout layout_server_confirm;
	private Boolean eql = true;
	private TextView text_server_username;
	private EditText edit_server_phone;
	private EditText edit_server_phoneagain;
	private EditText edit_server_company;
	private Button btn_server_client;
	private int btn_server_client_i = 0;
	private Button btn_server_server;
	private int btn_server_server_i = 0;
	private Button btn_server_confirm;
	private int btn_select_serverinfo_cicle = 0;
	private int btn_select_serverinfo_cle = 0;
	private int btn_refresh_serverinfo_cicle = 0;
	private int btn_refresh_serverinfo_cle = 0;
	private int btn_update_serverinfo_cicle = 0;
	private int btn_update_serverinfo_cle = 0;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private String username = "error";
	private String usernameread;
	private String page = "error";
	private String phone = "error";
	private String company = "error";
	private SharedPreferences pre;
	private String serverphone = "";
	private String serverphoneagain = "";
	private String companyinput = "";
	private String pageinput = "";
	private String serverphonerefresh = "";
	private String companyrefresh = "";
	private String pagerefresh = "";
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
				gorefresh();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			case 3:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				goconfirm();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(serverset.this);
			alertDialog.setTitle("修改个人设置");
			alertDialog.setMessage("确定放弃修改吗？");
			alertDialog.setPositiveButton("点错了", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
				}
			});
			alertDialog.setNegativeButton("确定放弃", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					// back_int = 0;
					finish();
				}
			});
			alertDialog.show();
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serverset);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		layout_server_confirm = (LinearLayout) findViewById(R.id.layout_server_confirm);
		text_server_username = (TextView) findViewById(R.id.text_server_username);
		edit_server_phone = (EditText) findViewById(R.id.edit_server_phone);
		edit_server_phoneagain = (EditText) findViewById(R.id.edit_server_phoneagain);
		btn_server_client = (Button) findViewById(R.id.btn_server_client);
		btn_server_server = (Button) findViewById(R.id.btn_server_server);
		edit_server_company = (EditText) findViewById(R.id.edit_server_company);
		btn_server_confirm = (Button) findViewById(R.id.btn_server_confirm);
		btn_server_client.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (btn_server_client_i == 0) {
					btn_server_client.setBackgroundResource(R.drawable.bacyes);
					btn_server_client_i = 1;
					btn_server_server.setBackgroundResource(R.drawable.bacno);
					btn_server_server_i = 0;
				} else {
					btn_server_client.setBackgroundResource(R.drawable.bacno);
					btn_server_client_i = 0;
				}
			}

		});
		btn_server_server.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (btn_server_server_i == 0) {
					btn_server_server.setBackgroundResource(R.drawable.bacyes);
					btn_server_server_i = 1;
					btn_server_client.setBackgroundResource(R.drawable.bacno);
					btn_server_client_i = 0;
				} else {
					btn_server_server.setBackgroundResource(R.drawable.bacno);
					btn_server_server_i = 0;
				}
			}

		});
		btn_server_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout_server_confirm.setVisibility(View.GONE);
				serverphone = "";
				serverphoneagain = "";
				companyinput = "";
				pageinput = "";
				eql = true;

				if (btn_server_client_i == 1) {
					pageinput = "client";
				}
				if (btn_server_server_i == 1) {
					pageinput = "server";
				}
				serverphone = edit_server_phone.getText().toString();
				serverphoneagain = edit_server_phoneagain.getText().toString();
				companyinput = edit_server_company.getText().toString();
				if (serverphone.equals(serverphoneagain)) {
					eql = false;
				}
				if (btn_server_client_i == 0 && btn_server_server_i == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(serverset.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(serverset.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择开启页面");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (serverphone.equals("")) {

					AlertDialog alertDialog = new AlertDialog.Builder(serverset.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(serverset.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请输入客服电话");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (eql) {
					AlertDialog alertDialog = new AlertDialog.Builder(serverset.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(serverset.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请保证两次输入一致");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (companyinput.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(serverset.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(serverset.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请简要输入公司名称");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (true) {
					(new Thread() {
						@Override
						public void run() {
							ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							btn_update_serverinfo_cicle = 0;
							btn_update_serverinfo_cle = 0;
							read();

							for (btn_update_serverinfo_cle = 0; btn_update_serverinfo_cle < 1; btn_update_serverinfo_cle = btn_update_serverinfo_cle) {
								try {
									nameValuePairs.add(new BasicNameValuePair("username", usernameread));
									nameValuePairs.add(new BasicNameValuePair("page", pageinput));
									nameValuePairs.add(new BasicNameValuePair("serverphone", serverphone));
									nameValuePairs.add(new BasicNameValuePair("company", companyinput));
									HttpClient httpclient = new DefaultHttpClient();
									HttpPost httppost = new HttpPost(
											"http://www.sundaytek.com/updateserveruserinfo.php");
									httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
									HttpResponse response = httpclient.execute(httppost);
									HttpEntity entity = response.getEntity();
									is = entity.getContent();
									btn_update_serverinfo_cicle = 0;
									btn_update_serverinfo_cle = 1;
									Log.e("log_tag", "isme ");
								} catch (Exception e) {
									Log.d("log_tag", "Error in http connection" + e.toString());
									btn_update_serverinfo_cicle = 0;
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

			}

		});
		(new Thread() {
			@Override
			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				btn_select_serverinfo_cicle = 0;
				btn_select_serverinfo_cle = 0;
				read();
				for (btn_select_serverinfo_cle = 0; btn_select_serverinfo_cle < 1; btn_select_serverinfo_cle = btn_select_serverinfo_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", usernameread));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectserveruserinfo.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_select_serverinfo_cicle = 0;
						btn_select_serverinfo_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_select_serverinfo_cicle = 1;
					}
					if (btn_select_serverinfo_cicle == 0) {
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
							btn_select_serverinfo_cle = 1;
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
								btn_select_serverinfo_cle = 1;
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
		if (pagerefresh.equals(pageinput) && serverphonerefresh.equals(serverphone)
				&& companyrefresh.equals(companyinput)) {
			Intent intent = new Intent();
			intent.putExtra("staues", true);
			setResult(0, intent);
			finish();
		} else {
			Intent intent = new Intent();
			intent.putExtra("staues", false);
			setResult(0, intent);
			finish();
		}
	}

	protected void gorefresh() {
		// TODO Auto-generated method stub
		(new Thread() {
			@Override
			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				btn_refresh_serverinfo_cicle = 0;
				btn_refresh_serverinfo_cle = 0;
				read();
				for (btn_refresh_serverinfo_cle = 0; btn_refresh_serverinfo_cle < 1; btn_refresh_serverinfo_cle = btn_refresh_serverinfo_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", usernameread));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectserveruserinfo.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_refresh_serverinfo_cicle = 0;
						btn_refresh_serverinfo_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_refresh_serverinfo_cicle = 1;
					}
					if (btn_refresh_serverinfo_cicle == 0) {
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
							btn_refresh_serverinfo_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						String usernamerefresh1;
						String pagerefresh1;
						String phonerefresh1;
						String companyrefresh1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;

							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								btn_refresh_serverinfo_cle = 1;
								usernamerefresh1 = json_data.getString("username");
								pagerefresh1 = json_data.getString("page");
								phonerefresh1 = json_data.getString("serverphone");
								companyrefresh1 = json_data.getString("company");
								pagerefresh = pagerefresh1;
								serverphonerefresh = phonerefresh1;
								companyrefresh = companyrefresh1;
								Message msg = new Message();
								msg.what = 3;
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

	protected void go() {
		// TODO Auto-generated method stub
		text_server_username.setText(username);
		edit_server_phone.setText(phone);
		edit_server_phoneagain.setText(phone);
		edit_server_company.setText(company);
		if (page.equals("client")) {

			btn_server_client.setBackgroundResource(R.drawable.bacyes);
			btn_server_client_i = 1;
		} else {
			btn_server_server.setBackgroundResource(R.drawable.bacyes);
			btn_server_server_i = 1;
		}
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
		Log.d("usernfgsgfme", usernameread);
	}
}
