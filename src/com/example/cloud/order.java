package com.example.cloud;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import model.ordermodel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.cloud.R;
import com.example.cloud.adapter.addressadapter;
import com.example.cloud.adapter.orderadapter;

public class order extends Activity implements OnClickListener {
	private String ordertype = "baojie";
	private int order_cicle = 0;
	private int order_cle = 0;
	private int olderzhongdiangong_cicle = 0;
	private int olderzhongdiangong_cle = 0;
	private int olderbaojie_cicle = 0;
	private int olderbaojie_cle = 0;
	private int olderbaomu_cicle = 0;
	private int olderbaomu_cle = 0;
	private int olderoldman_cicle = 0;
	private int olderoldman_cle = 0;
	private int olderyuer_cicle = 0;
	private int olderyuer_cle = 0;
	private int olderyuesao_cicle = 0;
	private int olderyuesao_cle = 0;
	private orderadapter mAdapter;
	private Button me;
	private Button order;
	private Button firstpage;
	private Button servers;
	private Button exist;
	private int f = 1;
	private int o = 0;
	private int m = 1;
	private int datalength = 0;
	private int displayheight;
	private ListView order_list;
	private LinearLayout older_label;
	private LinearLayout older_labellist;
	// private Button orderbaojie;
	private Button olderbaojie;
	private Button olderzhongdiangong;
	private Button olderbaomu;
	private Button olderyuer;
	private Button olderyuesao;
	private Button olderoldman;
	private Integer olderbaojie_i = 1;
	private Integer olderzhongdiangonge_i = 0;
	private Integer olderbaomue_i = 0;
	private Integer olderyuere_i = 0;
	private Integer olderyuesaoe_i = 0;
	private Integer olderoldmane_i = 0;
	private Integer positiontemp;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private ordermodel[] orderinfo = new ordermodel[100];
	private String[] userid = new String[100];
	private String[] orderid = new String[100];
	private String[] contact = new String[100];
	private String[] typename = new String[100];
	private Drawable drawable;
	private SharedPreferences pre;
	private String username;
	private String password;
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
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(order.this);
			alertDialog.setTitle("云家政");
			alertDialog.setMessage("确定退出吗？");
			alertDialog.setPositiveButton("点错了", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
				}
			});
			alertDialog.setNegativeButton("确定退出", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
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
		setContentView(R.layout.older);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		ordertype = bundle.getString("ordertype");
		// userid = bundle.getString("userid");
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		olderbaojie = (Button) findViewById(R.id.olderbaojie);
		olderzhongdiangong = (Button) findViewById(R.id.olderzhongdiangong);
		olderbaomu = (Button) findViewById(R.id.olderbaomu);
		olderyuer = (Button) findViewById(R.id.olderyuer);
		olderyuesao = (Button) findViewById(R.id.olderyuesao);
		olderoldman = (Button) findViewById(R.id.olderoldman);
		// drawable = olderbaojie.getBackground();?
		olderbaojie.setBackgroundColor(Color.GRAY);
		olderzhongdiangong.setBackgroundColor(Color.GRAY);
		olderbaomu.setBackgroundColor(Color.GRAY);
		olderyuer.setBackgroundColor(Color.GRAY);
		olderyuesao.setBackgroundColor(Color.GRAY);
		olderoldman.setBackgroundColor(Color.GRAY);
		olderbaojie.setBackgroundColor(Color.GREEN);
		older_labellist = (LinearLayout) findViewById(R.id.older_labellist);
		order_list = (ListView) findViewById(R.id.order_list);
		displayheight = getWindow().getWindowManager().getDefaultDisplay().getHeight();
		RelativeLayout.LayoutParams layoutParams1 = (android.widget.RelativeLayout.LayoutParams) older_labellist
				.getLayoutParams();
		layoutParams1.height = displayheight - 150;
		older_label = (LinearLayout) findViewById(R.id.older_label);
		// RelativeLayout.LayoutParams layoutParams =
		// (android.widget.RelativeLayout.LayoutParams) older_label
		// .getLayoutParams();
		// layoutParams.topMargin = displayheight - 140;
		me = (Button) findViewById(R.id.me);
		servers = (Button) findViewById(R.id.servers);
		order = (Button) findViewById(R.id.older);
		exist = (Button) findViewById(R.id.finish);
		firstpage = (Button) findViewById(R.id.firstpage);
		me.setOnClickListener(this);
		servers.setOnClickListener(this);
		exist.setOnClickListener(this);
		order.setOnClickListener(this);
		firstpage.setOnClickListener(this);
		(new Thread() {

			@Override

			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// http get
				order_cicle = 0;
				order_cle = 0;
				for (order_cle = 0; order_cle < 1; order_cle = order_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", username));
						nameValuePairs.add(new BasicNameValuePair("client", "true"));

						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = null;
						if (ordertype.equals("baojie")) {
							olderbaojie_i = 1;
							olderzhongdiangonge_i = 0;
							olderbaomue_i = 0;
							olderyuere_i = 0;
							olderyuesaoe_i = 0;
							olderoldmane_i = 0;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GRAY);
							olderbaomu.setBackgroundColor(Color.GRAY);
							olderyuer.setBackgroundColor(Color.GRAY);
							olderyuesao.setBackgroundColor(Color.GRAY);
							olderoldman.setBackgroundColor(Color.GRAY);
							olderbaojie.setBackgroundColor(Color.GREEN);
							httppost = new HttpPost("http://www.sundaytek.com/selectbaojielist.php");
						} else if (ordertype.equals("zhongdiangong")) {
							olderbaojie_i = 0;
							olderzhongdiangonge_i = 1;
							olderbaomue_i = 0;
							olderyuere_i = 0;
							olderyuesaoe_i = 0;
							olderoldmane_i = 0;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GREEN);
							olderbaomu.setBackgroundColor(Color.GRAY);
							olderyuer.setBackgroundColor(Color.GRAY);
							olderyuesao.setBackgroundColor(Color.GRAY);
							olderoldman.setBackgroundColor(Color.GRAY);
							olderbaojie.setBackgroundColor(Color.GRAY);
							httppost = new HttpPost("http://www.sundaytek.com/selectzhongdiangonglist.php");
						} else if (ordertype.equals("baomu")) {
							olderbaojie_i = 0;
							olderzhongdiangonge_i = 0;
							olderbaomue_i = 1;
							olderyuere_i = 0;
							olderyuesaoe_i = 0;
							olderoldmane_i = 0;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GRAY);
							olderbaomu.setBackgroundColor(Color.GREEN);
							olderyuer.setBackgroundColor(Color.GRAY);
							olderyuesao.setBackgroundColor(Color.GRAY);
							olderoldman.setBackgroundColor(Color.GRAY);
							olderbaojie.setBackgroundColor(Color.GRAY);
							httppost = new HttpPost("http://www.sundaytek.com/selectbaomulist.php");
						} else if (ordertype.equals("yuer")) {
							olderbaojie_i = 0;
							olderzhongdiangonge_i = 0;
							olderbaomue_i = 0;
							olderyuere_i = 1;
							olderyuesaoe_i = 0;
							olderoldmane_i = 0;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GRAY);
							olderbaomu.setBackgroundColor(Color.GRAY);
							olderyuer.setBackgroundColor(Color.GREEN);
							olderyuesao.setBackgroundColor(Color.GRAY);
							olderoldman.setBackgroundColor(Color.GRAY);
							olderbaojie.setBackgroundColor(Color.GRAY);
							httppost = new HttpPost("http://www.sundaytek.com/selectyuerlist.php");
						} else if (ordertype.equals("yuesao")) {
							olderbaojie_i = 0;
							olderzhongdiangonge_i = 0;
							olderbaomue_i = 0;
							olderyuere_i = 0;
							olderyuesaoe_i = 1;
							olderoldmane_i = 0;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GRAY);
							olderbaomu.setBackgroundColor(Color.GRAY);
							olderyuer.setBackgroundColor(Color.GRAY);
							olderyuesao.setBackgroundColor(Color.GREEN);
							olderoldman.setBackgroundColor(Color.GRAY);
							olderbaojie.setBackgroundColor(Color.GRAY);
							httppost = new HttpPost("http://www.sundaytek.com/selectyuesaolist.php");
						} else if (ordertype.equals("oldman")) {
							olderbaojie_i = 0;
							olderzhongdiangonge_i = 0;
							olderbaomue_i = 0;
							olderyuere_i = 0;
							olderyuesaoe_i = 0;
							olderoldmane_i = 1;
							olderbaojie.setBackgroundColor(Color.GRAY);
							olderzhongdiangong.setBackgroundColor(Color.GRAY);
							olderbaomu.setBackgroundColor(Color.GRAY);
							olderyuer.setBackgroundColor(Color.GRAY);
							olderyuesao.setBackgroundColor(Color.GRAY);
							olderoldman.setBackgroundColor(Color.GREEN);
							olderbaojie.setBackgroundColor(Color.GRAY);
							httppost = new HttpPost("http://www.sundaytek.com/selectoldmanlist.php");
						}
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						order_cicle = 0;
						order_cle = 0;
						// HttpClient httpclient = new DefaultHttpClient();
						// HttpGet httpget = new
						// HttpGet("http://www.sundaytek.com/selectbaojielist.php");
						// HttpResponse response = httpclient.execute(httpget);
						// HttpEntity entity = response.getEntity();
						// is = entity.getContent();
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						order_cicle = 1;
					}
					if (order_cicle == 0) {
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
							order_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						// paring data

						String orderid1;
						String userid1;
						String typename1;
						String contact1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							order_cle = 1;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);

								orderid1 = json_data.getString("orderid");
								userid1 = json_data.getString("userid");
								typename1 = json_data.getString("typename");
								contact1 = json_data.getString("contact");
								orderid[i] = orderid1;
								userid[i] = userid1;
								typename[i] = typename1;
								contact[i] = contact1;
								Log.d("111111", "wwwwww");
								Log.d("log_tag", contact[i]);
								// tv.append(ct_name + " \n");
								datalength++;
								Log.e("log_tag", "ismethree");

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
				// 便我们做出不同的处理操作
				msg.what = 1;

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
		olderzhongdiangong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GREEN);
				olderbaomu.setBackgroundColor(Color.GRAY);
				olderyuer.setBackgroundColor(Color.GRAY);
				olderyuesao.setBackgroundColor(Color.GRAY);
				olderoldman.setBackgroundColor(Color.GRAY);
				olderbaojie.setBackgroundColor(Color.GRAY);

				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						olderzhongdiangong_cicle = 0;
						olderzhongdiangong_cle = 0;
						for (olderzhongdiangong_cle = 0; olderzhongdiangong_cle < 1; olderzhongdiangong_cle = olderzhongdiangong_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost(
										"http://www.sundaytek.com/selectzhongdiangonglist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderzhongdiangong_cicle = 0;
								olderzhongdiangong_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectzhongdiangonglist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderzhongdiangong_cicle = 1;
							}
							if (olderzhongdiangong_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderzhongdiangong_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									datalength = 0;
									olderbaojie_i = 0;
									olderzhongdiangonge_i = 1;
									olderbaomue_i = 0;
									olderyuere_i = 0;
									olderyuesaoe_i = 0;
									olderoldmane_i = 0;
									olderzhongdiangong_cle = 1;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

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

			}

		});
		olderbaojie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GRAY);
				olderbaomu.setBackgroundColor(Color.GRAY);
				olderyuer.setBackgroundColor(Color.GRAY);
				olderyuesao.setBackgroundColor(Color.GRAY);
				olderoldman.setBackgroundColor(Color.GRAY);
				olderbaojie.setBackgroundColor(Color.GREEN);
				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						olderbaojie_cicle = 0;
						olderbaojie_cle = 0;
						for (olderbaojie_cle = 0; olderbaojie_cle < 1; olderbaojie_cle = olderbaojie_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectbaojielist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderbaojie_cicle = 0;
								olderbaojie_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectbaojielist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderbaojie_cicle = 1;
							}
							if (olderbaojie_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderbaojie_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									datalength = 0;
									olderbaojie_cle = 1;
									olderbaojie_i = 1;
									olderzhongdiangonge_i = 0;
									olderbaomue_i = 0;
									olderyuere_i = 0;
									olderyuesaoe_i = 0;
									olderoldmane_i = 0;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

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

			}

		});
		olderbaomu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GRAY);
				olderbaomu.setBackgroundColor(Color.GREEN);
				olderyuer.setBackgroundColor(Color.GRAY);
				olderyuesao.setBackgroundColor(Color.GRAY);
				olderoldman.setBackgroundColor(Color.GRAY);
				olderbaojie.setBackgroundColor(Color.GRAY);
				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						olderbaomu_cicle = 0;
						olderbaomu_cle = 0;
						for (olderbaomu_cle = 0; olderbaomu_cle < 1; olderbaomu_cle = olderbaomu_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectbaomulist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderbaomu_cicle = 0;
								olderbaomu_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectbaomulist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderbaomu_cicle = 1;
							}
							if (olderbaomu_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderbaomu_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									datalength = 0;
									olderbaomu_cle = 1;
									olderbaojie_i = 0;
									olderzhongdiangonge_i = 0;
									olderbaomue_i = 1;
									olderyuere_i = 0;
									olderyuesaoe_i = 0;
									olderoldmane_i = 0;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

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

			}

		});
		olderoldman.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GRAY);
				olderbaomu.setBackgroundColor(Color.GRAY);
				olderyuer.setBackgroundColor(Color.GRAY);
				olderyuesao.setBackgroundColor(Color.GRAY);
				olderoldman.setBackgroundColor(Color.GREEN);
				olderbaojie.setBackgroundColor(Color.GRAY);
				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						datalength = 0;
						olderoldman_cicle = 0;
						olderoldman_cle = 0;
						for (olderoldman_cle = 0; olderoldman_cle < 1; olderoldman_cle = olderoldman_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectoldmanlist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderoldman_cicle = 0;
								olderoldman_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectoldmanlist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderoldman_cicle = 1;
							}
							if (olderoldman_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderoldman_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									olderoldman_cle = 1;
									datalength = 0;
									olderbaojie_i = 0;
									olderzhongdiangonge_i = 0;
									olderbaomue_i = 0;
									olderyuere_i = 0;
									olderyuesaoe_i = 0;
									olderoldmane_i = 1;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

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

			}

		});
		olderyuer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GRAY);
				olderbaomu.setBackgroundColor(Color.GRAY);
				olderyuer.setBackgroundColor(Color.GREEN);
				olderyuesao.setBackgroundColor(Color.GRAY);
				olderoldman.setBackgroundColor(Color.GRAY);
				olderbaojie.setBackgroundColor(Color.GRAY);
				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						datalength = 0;
						olderyuer_cicle = 0;
						olderyuer_cle = 0;
						for (olderyuer_cle = 0; olderyuer_cle < 1; olderyuer_cle = olderyuer_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectyuerlist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								// is =
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectyuerlist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderyuer_cicle = 0;
								olderyuer_cle = 0;
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderyuer_cicle = 1;
							}
							if (olderyuer_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderyuer_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									olderyuer_cle = 1;
									datalength = 0;
									olderbaojie_i = 0;
									olderzhongdiangonge_i = 0;
									olderbaomue_i = 0;
									olderyuere_i = 1;
									olderyuesaoe_i = 0;
									olderoldmane_i = 0;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

										// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
										// msg.obj = null;
										// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
										// msg.setData(null);
										// Bundle data = msg.getData();

										// 将该Message发送给对应的Handler

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

						// 我们可以通过arg1和arg2给Message传入简单的数据
						msg.arg1 = 123;
						msg.arg2 = 321;
						uiHandler.sendMessage(msg);
					}
				}).start();

			}

		});

		olderyuesao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datalength = 0;
				olderbaojie.setBackgroundColor(Color.GRAY);
				olderzhongdiangong.setBackgroundColor(Color.GRAY);
				olderbaomu.setBackgroundColor(Color.GRAY);
				olderyuer.setBackgroundColor(Color.GRAY);
				olderyuesao.setBackgroundColor(Color.GREEN);
				olderoldman.setBackgroundColor(Color.GRAY);
				olderbaojie.setBackgroundColor(Color.GRAY);

				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						olderyuesao_cicle = 0;
						olderyuesao_cle = 0;
						for (olderyuesao_cle = 0; olderyuesao_cle < 1; olderyuesao_cle = olderyuesao_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("client", "true"));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectyuesaolist.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								olderyuesao_cicle = 0;
								olderyuesao_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/selectyuesaolist.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								olderyuesao_cicle = 1;
							}
							if (olderyuesao_cicle == 0) {
								// convert response to string
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),
											8);
									sb = new StringBuilder();
									sb.append(reader.readLine() + "\n");

									String line = "0";
									while ((line = reader.readLine()) != null) {
										sb.append(line + "\n");
									}
									is.close();
									result = sb.toString();
									olderyuesao_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data

								String orderid1;
								String userid1;
								String typename1;
								String contact1;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									olderyuesao_cle = 1;
									datalength = 0;
									olderbaojie_i = 0;
									olderzhongdiangonge_i = 0;
									olderbaomue_i = 0;
									olderyuere_i = 0;
									olderyuesaoe_i = 1;
									olderoldmane_i = 0;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);

										orderid1 = json_data.getString("orderid");
										userid1 = json_data.getString("userid");
										typename1 = json_data.getString("typename");
										contact1 = json_data.getString("contact");
										orderid[i] = orderid1;
										userid[i] = userid1;
										typename[i] = typename1;
										contact[i] = contact1;
										Log.d("111111", "wwwwww");
										Log.d("log_tag", contact[i]);
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");

									}
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
						Message msg = new Message();
						// 便我们做出不同的处理操作
						msg.what = 1;

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

			}

		});
		order_list.setOnItemClickListener(new OnItemClickListener() {

			/**
			 * parent 在这里是当前ListView对象 view 项View对象 position 被点击项的位置 id
			 * adapter返回的id
			 */

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// String txt="position:"+position+" id:"+id;
				// getclickposition = position;
				// Log.d("rrr", "ggg");
				// for (int i = 0; i < datalength; i++) {
				// if (getclickposition == i) {
				// Log.d("我来", "我来了");
				// adaptertext_address.setText(string[getclickposition]);
				//
				// }
				// }
				positiontemp = position;
				Log.d("rrr", "ggg");
				for (int j = 0; j < datalength; j++) {
					int i = 0;
					if (positiontemp == j) {
						i = datalength - positiontemp - 1;
						Log.d("我来", orderid[i]);
						Log.d("我来", userid[i]);
						Log.d("我来", typename[i]);
						Log.d("我来", contact[i]);
						if (olderbaojie_i == 1) {
							Intent intentorder = new Intent(order.this, baojiedetail.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
						} else if (olderzhongdiangonge_i == 1) {
							Intent intentorder = new Intent(order.this, zhongdiangongdetail.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
						} else if (olderbaomue_i == 1) {
							Intent intentorder = new Intent(order.this, baomudetai.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
						} else if (olderyuere_i == 1) {
							Intent intentorder = new Intent(order.this, yuerdeta.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
						} else if (olderyuesaoe_i == 1) {
							Intent intentorder = new Intent(order.this, yuesaodetail.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
						} else if (olderoldmane_i == 1) {
							Intent intentorder = new Intent(order.this, oldmandetail.class);
							// resultCode---请求码
							Bundle bundle = new Bundle(); // 创建Bundle对象
							bundle.putString("orderid", orderid[i]); // 装入数据
							bundle.putString("userid", userid[i]); // 装入数据
							intentorder.putExtras(bundle);
							startActivityForResult(intentorder, 3);
							// orderid[i];
						}

						// userid[i];
						// typename[i];
						// contact[i] ;

					}
				}

				// if (getclickposition == 1) {
				// Log.d("我来", "我来fffffffffff了");
				// }
				// if (getclickposition == 2) {
				// Log.d("我来", "我来fffffffffff了");
				// }
				// if (getclickposition == 3) {
				// Log.d("我来", "我来fffffffffff了");
				// }
				// Toast.makeText(FriendListActivity.this,
				// model.getDescription(),
				// 0).show();
			}
		});

	}

	protected void go() {
		// TODO Auto-generated method stub
		Log.e("log_tag", "ismethisgooooo");
		mAdapter = new orderadapter(this, getData());
		order_list.setAdapter(mAdapter);
	}

	private List<ordermodel> getData() {

		List<ordermodel> list = new ArrayList<ordermodel>();
		// FriendModel friend1 = new FriendModel();
		// friend1.setAvatar(R.drawable.image1);
		// friend1.setNickname("凯撒");
		// friend1.setDescription("凯撒的归凯撒，上帝的归上帝");

		Log.e("log_tag", "ismethis");
		for (int i = 0; i < datalength; i++) {
			ordermodel orderinfo = new ordermodel();
			orderinfo.setContact(contact[i]);
			orderinfo.setOrderid(orderid[i]);
			orderinfo.setUserid(userid[i]);
			orderinfo.setTypename(typename[i]);
			// orderinfo[i].setContact("1");
			// orderinfo[i].setOrderid("f");
			// orderinfo[i].setUserid("7");
			// orderinfo[i].setTypename("jjjj");
			list.add(orderinfo);

			Log.e("log_tag", "ismefour");

		}
		return list;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.me:
			if (m == 1) {
				Intent intent6 = new Intent();
				intent6.setClass(this, me.class);
				startActivity(intent6);
				m = 0;
				f = 1;
				o = 1;
				finish();
			}

			break;

		case R.id.older:
			if (o == 1) {
				Intent intent7 = new Intent();
				intent7.setClass(this, order.class);
				startActivity(intent7);
				f = 1;
				m = 1;
				o = 0;
				finish();
			}

			break;
		case R.id.servers:
			AlertDialog alertDialog = new AlertDialog.Builder(order.this).create();
			// alertDialog.setIcon(R.drawable.desert);
			// alertDialog.setTitle("System Information:");
			AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
			settingDialog.setInverseBackgroundForced(true);
			alertDialog.setMessage("10086");
			// 添加取消按钮
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(order.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
				}
			});
			// 添加确定按钮
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "拨打", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Toast.makeText(viewpaper.this,"You clicked the Confirm
					// Button",Toast.LENGTH_LONG).show();
					String phoneNumber = "10086";
					// 3.给这个号码打电话
					Intent intent8 = new Intent();
					intent8.setAction("android.intent.action.CALL");
					intent8.addCategory("android.intent.category.DEFAULT");
					intent8.setData(Uri.parse("tel:" + phoneNumber));
					startActivity(intent8);
				}
			});
			// 添加中立按钮
			// alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"neutral",new
			// DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// Toast.makeText(viewpaper.this,"You clicked the neutral
			// Button",Toast.LENGTH_LONG).show();
			// }
			// });
			alertDialog.show();
			break;
		case R.id.finish:
			// Intent intent10 = new Intent();
			// intent10.setFlags(intent10.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent10);
			finish();
			break;
		case R.id.firstpage:
			if (f == 1) {
				Intent intent9 = new Intent();
				intent9.setClass(this, viewpaper.class);
				startActivity(intent9);
				f = 0;
				m = 1;
				o = 1;
				finish();
			}

			break;

		default:
			break;
		}

	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
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
