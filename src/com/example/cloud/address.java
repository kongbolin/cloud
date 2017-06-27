package com.example.cloud;

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

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.cloud.R;
import com.example.cloud.adapter.addressadapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import cn.sharesdk.google.GooglePlus;

import android.widget.AdapterView.OnItemClickListener;

public class address extends Activity {
	private int address_cicle = 0;
	private int address_cle = 0;
	private int delete_cicle = 0;
	private int btn_address_new_cicle = 0;
	private int btn_address_new_cle = 0;
	private int btn_address_delete_cicle = 0;
	private int btn_address_delete_cle = 0;
	private int case_cicle = 0;
	private int case_cle = 0;
	private ListView lv_list;
	private addressadapter mAdapter;
	private int getclickposition;
	private TextView addadaptertext_address;
	private TextView adaptertext_address;
	private int datalength = 0;
	private Button btn_address_delete;
	private Button btn_address_new;
	private Button btn_address_update;
	private Button btn_address_cancle;
	private Button btn_address_confirm;
	private String contentupdate;
	private String contentdelete;
	private String address_temp_other = "";
	private String[] string = new String[100];
	// private String string="ggg";
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
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
	// private String[]
	// string={"11111","22222","33333","44444","54555","66666","77777","88888","99999","00000","12345"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		// Intent intent = this.getIntent(); //获取已有的intent对象
		// Bundle bundle = intent.getExtras(); //获取intent里面的bundle对象
		// address_temp_other = bundle.getString("something");
		// text_baojie_otherneed.setText(address_temp_other);
		addadaptertext_address = (TextView) findViewById(R.id.addadaptertext_address);
		adaptertext_address = (TextView) findViewById(R.id.adaptertext_address);
		btn_address_new = (Button) findViewById(R.id.btn_address_new);
		btn_address_update = (Button) findViewById(R.id.btn_address_update);
		btn_address_cancle = (Button) findViewById(R.id.btn_address_cancle);
		btn_address_delete = (Button) findViewById(R.id.btn_address_delete);
		btn_address_confirm = (Button) findViewById(R.id.btn_address_confirm);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		address_temp_other = bundle.getString("something");
		adaptertext_address.setText(address_temp_other);
		lv_list = (ListView) findViewById(R.id.lv_list);
		btn_address_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("address", adaptertext_address.getText());
				setResult(0, intent);
				finish();

			}

		});
		btn_address_new.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				datalength = 0;

				(new Thread() {

					@Override

					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						// http get
						for (btn_address_new_cle = 0; btn_address_new_cle < 1; btn_address_new_cle = btn_address_new_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("password", password));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/test.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								btn_address_new_cicle = 0;
								btn_address_new_cle = 0;
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/test.php");
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								btn_address_new_cicle = 0;
							}
							// convert response to string
							if (btn_address_new_cicle == 0) {
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
									btn_address_new_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoo ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data
								int ct_id;
								String ct_name;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									btn_address_new_cle = 1;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);
										ct_id = json_data.getInt("userid");
										ct_name = json_data.getString("address");
										string[i] = (String) ct_name;
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");
										// Message msg = new Message();
										// // 便我们做出不同的处理操作
										// msg.what = 1;
										//
										// // 我们可以通过arg1和arg2给Message传入简单的数据
										// msg.arg1 = 123;
										// msg.arg2 = 321;
										// //
										// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
										// // msg.obj = null;
										// //
										// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
										// // msg.setData(null);
										// // Bundle data = msg.getData();
										//
										// // 将该Message发送给对应的Handler
										// uiHandler.sendMessage(msg);
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
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
					}
				}).start();
				// TODO Auto-generated method stub
				// Message msg = new Message();
				// // 便我们做出不同的处理操作
				// msg.what = 1;
				//
				// // 我们可以通过arg1和arg2给Message传入简单的数据
				// msg.arg1 = 123;
				// msg.arg2 = 321;
				// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
				// // msg.obj = null;
				// // 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
				// msg.setData(null);
				// Bundle data = msg.getData();
				Log.d("log_tag", "ismeseeme ");
				// 将该Message发送给对应的Handler
				// uiHandler.sendMessage(msg);
				// Intent intent16 = new Intent();
				// intent16.setClass(address.this, address.class);
				// startActivity(intent16);
				// finish();

			}

		});
		btn_address_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				contentupdate = (String) adaptertext_address.getText();
				// TODO Auto-generated method stub
				Intent intent = new Intent(address.this, updateaddress.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", contentupdate); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}

		});
		btn_address_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});
		btn_address_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_address_delete_cicle = 0;
				btn_address_delete_cle = 0;
				contentdelete = (String) adaptertext_address.getText();
				Log.d("shhishishi", contentdelete);
				(new Thread() {
					@Override
					public void run() {
						ArrayList nameValuePairs = new ArrayList();
						nameValuePairs.add(new BasicNameValuePair("address", contentdelete));
						nameValuePairs.add(new BasicNameValuePair("username", username));
						for (delete_cicle = 0; delete_cicle < 1; delete_cicle = delete_cicle) {
							try {
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/deleteaddress.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								delete_cicle = 1;
							} catch (Exception e) {
								Log.e("log_tag", "Error in http connection" + e.toString());
								delete_cicle = 0;
							}
						}
						// ArrayList<NameValuePair> nameValuePairs = new
						// ArrayList<NameValuePair>();
						// http get
						datalength = 0;
						for (btn_address_delete_cle = 0; btn_address_delete_cle < 1; btn_address_delete_cle = btn_address_delete_cle) {
							try {
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpPost httppost = new
								// HttpPost("http://www.sundaytek.com/test.php");
								// httppost.setEntity(new
								// UrlEncodedFormEntity(nameValuePairs,
								// "UTF-8"));
								// HttpResponse response =
								// httpclient.execute(httppost);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								// HttpClient httpclient = new
								// DefaultHttpClient();
								// HttpGet httpget = new
								// HttpGet("http://www.sundaytek.com/test.php");
								//
								// HttpResponse response =
								// httpclient.execute(httpget);
								// HttpEntity entity = response.getEntity();
								// is = entity.getContent();
								nameValuePairs.add(new BasicNameValuePair("username", username));
								nameValuePairs.add(new BasicNameValuePair("password", password));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/test.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();
								btn_address_delete_cicle = 0;
								btn_address_delete_cle = 0;
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								btn_address_delete_cicle = 1;
							}
							// convert response to string
							if (btn_address_delete_cicle == 0) {
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
									btn_address_delete_cle = 1;
									result = delete(result);
									Log.e("log_tag", "ismetoogggg ");
								} catch (Exception e) {
									Log.e("log_tag", "Error converting result " + e.toString());
								}
								// paring data
								int ct_id;
								String ct_name;
								try {
									jArray = new JSONArray(result);
									JSONObject json_data = null;
									btn_address_delete_cle = 1;
									for (int i = 0; i < jArray.length(); i++) {
										json_data = jArray.getJSONObject(i);
										ct_id = json_data.getInt("userid");
										ct_name = json_data.getString("address");
										string[i] = (String) ct_name;
										// tv.append(ct_name + " \n");
										datalength++;
										Log.e("log_tag", "ismethree");
										// Message msg = new Message();
										// // 便我们做出不同的处理操作
										// msg.what = 1;
										//
										// // 我们可以通过arg1和arg2给Message传入简单的数据
										// msg.arg1 = 123;
										// msg.arg2 = 321;
										// //
										// 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
										// // msg.obj = null;
										// //
										// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
										// // msg.setData(null);
										// // Bundle data = msg.getData();
										//
										// // 将该Message发送给对应的Handler
										// uiHandler.sendMessage(msg);
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
								} catch (JSONException e1) {
									// Toast.makeText(getBaseContext(), "No City
									// Found"
									// ,Toast.LENGTH_LONG).show();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}
					}
				}).start();
				adaptertext_address.setText("");
			}

		});
		// String[] string = null;

		// Handler uiHandler = new Handler(){
		// @Override
		// public void handleMessage(Message msg) {
		// switch (msg.what){
		// case 1:
		// Go();
		// System.out.println("handleMessage thread id " +
		// Thread.currentThread().getId());
		// System.out.println("msg.arg1:" + msg.arg1);
		// System.out.println("msg.arg2:" + msg.arg2);
		// // MainActivity.this.statusTextView.setText("文件下载完成");
		// break;
		// }
		// }
		// };

		// getclickposition=mAdapter.getclickposition();
		// if(getclickposition==0)
		// {
		// Log.d("我来", "我来了");
		// }
		// btn_topfirst=(Button) findViewById(R.id.btn_topfirst);
		// l// Go();l
		(new Thread() {

			@Override

			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// http get
				address_cicle = 0;
				address_cle = 0;
				for (address_cle = 0; address_cle < 1; address_cle = address_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("username", username));
						nameValuePairs.add(new BasicNameValuePair("password", password));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/test.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						address_cicle = 0;
						address_cle = 0;
						// HttpClient httpclient = new DefaultHttpClient();
						// HttpGet httpget = new
						// HttpGet("http://www.sundaytek.com/test.php");
						// HttpResponse response = httpclient.execute(httpget);
						// HttpEntity entity = response.getEntity();
						// is = entity.getContent();
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						address_cicle = 1;
					}
					// convert response to string
					if (address_cicle == 0) {
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
							address_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						// paring data
						int ct_id;
						String ct_name;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							address_cle = 1;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);
								ct_id = json_data.getInt("userid");
								ct_name = json_data.getString("address");
								string[i] = (String) ct_name;
								// tv.append(ct_name + " \n");
								datalength++;
								Log.e("log_tag", "ismethree");
								// Message msg = new Message();
								// // 便我们做出不同的处理操作
								// msg.what = 1;
								//
								// // 我们可以通过arg1和arg2给Message传入简单的数据
								// msg.arg1 = 123;
								// msg.arg2 = 321;
								// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
								// // msg.obj = null;
								// //
								// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
								// // msg.setData(null);
								// // Bundle data = msg.getData();
								//
								// // 将该Message发送给对应的Handler
								// uiHandler.sendMessage(msg);
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
						} catch (JSONException e1) {
							// Toast.makeText(getBaseContext(), "No City Found"
							// ,Toast.LENGTH_LONG).show();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

					}
				}
			}
		}).start();
		// adaptertext_address.setOnClickListener(new Button.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//// EditText tv = (EditText) findViewById(R.id.editView);
		//
		// }
		// });
		addadaptertext_address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(address.this, addaddress.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", "111"); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
				// Intent intent = new Intent();
				// intent.setClass(address.this, addaddress.class);
				// startActivity(intent);
				//

			}

		});
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			/**
			 * parent 在这里是当前ListView对象 view 项View对象 position 被点击项的位置 id
			 * adapter返回的id
			 */

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// String txt="position:"+position+" id:"+id;
				getclickposition = position;
				Log.d("rrr", "ggg");
				for (int i = 0; i < datalength; i++) {
					if (getclickposition == i) {
						Log.d("我来", "我来了");
						adaptertext_address.setText(string[getclickposition]);

					}
				}
			}
		});
	}

	// private void Go() {
	// // TODO Auto-generated method stub
	// Log.d("我来", "我来go了");
	// if(datalength==0)
	// {
	// Go();
	// }
	// else
	// {
	//// datalength++;
	// mAdapter = new addressadapter(this, getData());
	// lv_list.setAdapter(mAdapter);
	// }
	// }

	protected void go() {
		// TODO Auto-generated method stub
		Log.e("log_tag", "ismethisgooooo");
		mAdapter = new addressadapter(this, getData());
		lv_list.setAdapter(mAdapter);
	}

	private List getData() {

		List list = new ArrayList();

		Log.e("log_tag", "ismethis");

		for (int i = 0; i < datalength; i++) {
			list.add(string[i]);

			Log.e("log_tag", "ismefour");
		}

		return list;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		String otherneed = bundle.getString("otherneed");
		switch (requestCode) {
		case 1:
			datalength = 0;
			case_cicle = 0;
			case_cle = 0;
			(new Thread() {

				@Override

				public void run() {
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					// http get
					for (case_cle = 0; case_cle < 1; case_cle = case_cle) {
						try {
							nameValuePairs.add(new BasicNameValuePair("username", username));
							nameValuePairs.add(new BasicNameValuePair("password", password));
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://www.sundaytek.com/test.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
							case_cicle = 0;
							case_cle = 0;
							// HttpClient httpclient = new DefaultHttpClient();
							// HttpGet httpget = new
							// HttpGet("http://www.sundaytek.com/test.php");
							// HttpResponse response =
							// httpclient.execute(httpget);
							// HttpEntity entity = response.getEntity();
							// is = entity.getContent();
							Log.e("log_tag", "isme ");
						} catch (Exception e) {
							Log.d("log_tag", "Error in http connection" + e.toString());
							case_cicle = 1;
						}
						// convert response to string
						if (case_cicle == 0) {
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
								case_cle = 1;
								result = delete(result);
								Log.e("log_tag", "ismetoo ");
							} catch (Exception e) {
								Log.e("log_tag", "Error converting result " + e.toString());
							}
							// paring data
							int ct_id;
							String ct_name;
							try {
								jArray = new JSONArray(result);
								JSONObject json_data = null;
								case_cle = 1;
								for (int i = 0; i < jArray.length(); i++) {
									json_data = jArray.getJSONObject(i);
									ct_id = json_data.getInt("userid");
									ct_name = json_data.getString("address");
									string[i] = (String) ct_name;
									// tv.append(ct_name + " \n");
									datalength++;
									Log.e("log_tag", "ismethree");
									// Message msg = new Message();
									// // 便我们做出不同的处理操作
									// msg.what = 1;
									//
									// // 我们可以通过arg1和arg2给Message传入简单的数据
									// msg.arg1 = 123;
									// msg.arg2 = 321;
									// // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
									// // msg.obj = null;
									// //
									// 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
									// // msg.setData(null);
									// // Bundle data = msg.getData();
									//
									// // 将该Message发送给对应的Handler
									// uiHandler.sendMessage(msg);
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
							} catch (JSONException e1) {
								// Toast.makeText(getBaseContext(), "No City
								// Found"
								// ,Toast.LENGTH_LONG).show();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}
				}
			}).start();
			// baojie_otherdetail.setText(otherneed);
			// // ArrayList nameValuePairs = new ArrayList();
			// temp = otherneed;
			// temp_other=otherneed;
			// // nameValuePairs.add(new BasicNameValuePair("otherneed",
			// // otherneed));

			break;
		// case 2:
		// String address = bundle.getString("address");
		// text_baojie_address.setText(address);
		// // ArrayList nameValuePairs = new ArrayList();
		// temp = address;
		// address_temp_other=address;
		// // nameValuePairs.add(new BasicNameValuePair("otherneed",
		// // otherneed));
		//
		// break;
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
