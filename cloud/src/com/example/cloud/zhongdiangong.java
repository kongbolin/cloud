package com.example.cloud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.cloud.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
//import cn.sharesdk.example.share.zhongdiangong;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;


public class zhongdiangong extends Activity implements SeekBar.OnSeekBarChangeListener, Callback {
	private LinearLayout layout_baojie;
	private LinearLayout layout_zuofan;

	private TextView otherdetail;
	private TextView text_datestart;
	private TextView text_dateend;
	private TextView text_address;
	private TextView text_jiaweijiage;
	private TextView text_mianjidaxiao;
	private SeekBar seekbar_xinlijiawei;
	private SeekBar seekbar_fangwumianji;
	private TextView btn_back;
	private TextView text_serverscon;
	private TextView text_zhongdiangong_address;
	private Button btn_xian;
	private int btn_xian_i = 1;
	private Button btn_dan;
	private int btn_dan_i = 1;
	private Button btn_la;
	private int btn_la_i = 1;
	private Button btn_qingdan;
	private int btn_qingdan_i = 1;
	private Button btn_mianshi;
	private int btn_mianshi_i = 1;
	private Button btn_mon;
	private int btn_mon_i = 1;
	private Button btn_tue;
	private int btn_tue_i = 1;
	private Button btn_wed;
	private int btn_wed_i = 1;
	private Button btn_thu;
	private int btn_thu_i = 1;
	private Button btn_fri;
	private int btn_fri_i = 1;
	private Button btn_sat;
	private int btn_sat_i = 1;
	private Button btn_sun;
	private int btn_sun_i = 1;
	private Button btn_bigdog;
	private int btn_bigdog_i = 1;
	private Button btn_smalldog;
	private int btn_smalldog_i = 1;
	private Button btn_cat;
	private int btn_cat_i = 1;
	private Button btn_zhongdiangong_ok;
	public static String TEST_IMAGE;
	private String orderid;
	private String userid = "1";
	private String temporderid;
	private String content = " ";
	private String taste = " ";
	private String frequetly = " ";
	private String pet = " ";
	private String text_zhongdiangong_contact_text;
	private String text_zhongdiangong_phone_text;
	private static final String FILE_NAME = "/share_pic.jpg";
	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;
	private String address_temp_other = "";
	private String temp = "";
	private String typeid = "1";
	private String typename = "钟点工";
	private EditText text_zhongdiangong_contact;
	private EditText text_zhongdiangong_phone;
	InputStream is = null;
	private Button btn_baojie;
	private Button btn_zuofan;
	private int btn_baojie_i = 1;
	private int btn_zuofan_i = 1;
	private String text_mianjidaxiao_text;
	private String text_jiaweijiage_text;
	private String tempdatestart;
	private String tempdateend;
	private String username;
	private String password;
	private SharedPreferences pre;
	// text_baojie_contact_text =
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutzhongdiangong);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		userid = username;
		ShareSDK.initSDK(this);
		text_zhongdiangong_contact = (EditText) findViewById(R.id.text_zhongdiangong_contact);
		text_zhongdiangong_phone = (EditText) findViewById(R.id.text_zhongdiangong_phone);
		final TextView et1 = (TextView) findViewById(R.id.text_datestart);
		final TextView et2 = (TextView) findViewById(R.id.text_dateend);
		final Calendar c1 = Calendar.getInstance();
		final Calendar c2 = Calendar.getInstance();
		btn_zhongdiangong_ok = (Button) findViewById(R.id.btn_zhongdiangong_ok);
		btn_baojie = (Button) findViewById(R.id.btn_baojie);
		btn_zuofan = (Button) findViewById(R.id.btn_zuofan);
		btn_xian = (Button) findViewById(R.id.btn_xian);
		btn_dan = (Button) findViewById(R.id.btn_dan);
		btn_la = (Button) findViewById(R.id.btn_la);
		otherdetail = (TextView) findViewById(R.id.otherdetail);
		text_serverscon = (TextView) findViewById(R.id.text_serverscon);
		text_address = (TextView) findViewById(R.id.text_address);
		btn_qingdan = (Button) findViewById(R.id.btn_qingdan);
		btn_mianshi = (Button) findViewById(R.id.btn_mianshi);
		btn_mon = (Button) findViewById(R.id.btn_mon);
		btn_tue = (Button) findViewById(R.id.btn_tue);
		btn_wed = (Button) findViewById(R.id.btn_wed);
		btn_thu = (Button) findViewById(R.id.btn_thu);
		btn_fri = (Button) findViewById(R.id.btn_fri);
		btn_sat = (Button) findViewById(R.id.btn_sat);
		btn_sun = (Button) findViewById(R.id.btn_sun);
		btn_bigdog = (Button) findViewById(R.id.btn_bigdog);
		btn_smalldog = (Button) findViewById(R.id.btn_smalldog);
		btn_cat = (Button) findViewById(R.id.btn_cat);
		btn_back = (TextView) findViewById(R.id.btn_back);
		layout_baojie = (LinearLayout) findViewById(R.id.layout_baojie);
		layout_zuofan = (LinearLayout) findViewById(R.id.layout_zuofan);

		text_zhongdiangong_address = (TextView) findViewById(R.id.text_address);
		text_datestart = (TextView) findViewById(R.id.text_datestart);
		text_dateend = (TextView) findViewById(R.id.text_dateend);
		text_jiaweijiage = (TextView) findViewById(R.id.text_jiaweijiage);
		text_mianjidaxiao = (TextView) findViewById(R.id.text_mianjidaxiao);
		seekbar_xinlijiawei = (SeekBar) findViewById(R.id.seekbar_xinlijiawei);
		seekbar_xinlijiawei.setOnSeekBarChangeListener(this);
		seekbar_fangwumianji = (SeekBar) findViewById(R.id.seekbar_fangwumianji);
		final ArrayList nameValuePairs = new ArrayList();
		btn_zhongdiangong_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// private int btn_baojie_i = 1;
				// private int btn_zuofan_i = 1;
				content = " ";
				taste = " ";
				frequetly = " ";
				pet = " ";
				if (btn_baojie_i == -1) {
					content = content + "保洁  ";
				}
				if (btn_zuofan_i == -1) {
					content = content + "做饭  ";
				}
				if (btn_baojie_i == 1 && btn_zuofan_i == 1) {
					content = "无内容";
				}
				// private int btn_xian_i = 1;
				// private Button btn_dan;
				// private int btn_dan_i = 1;
				// private Button btn_la;
				// private int btn_la_i = 1;
				// private Button btn_qingdan;
				// private int btn_qingdan_i = 1;
				// private Button btn_mianshi;
				// private int btn_mianshi_i = 1;
				if (btn_xian_i == -1) {
					taste = taste + "偏咸 ";
				}
				if (btn_dan_i == -1) {
					taste = taste + "偏淡 ";
				}
				if (btn_la_i == -1) {
					taste = taste + "偏辣 ";
				}
				if (btn_qingdan_i == -1) {
					taste = taste + "清淡";
				}
				if (btn_mianshi_i == -1) {
					taste = taste + "面食";
				}
				if (btn_xian_i == 1 && btn_dan_i == 1 && btn_la_i == 1 && btn_qingdan_i == 1 && btn_mianshi_i == 1) {
					taste = "无要求";
				}
				// private int btn_mon_i = 1;
				// private Button btn_tue;
				// private int btn_tue_i = 1;
				// private Button btn_wed;
				// private int btn_wed_i = 1;
				// private Button btn_thu;
				// private int btn_thu_i = 1;
				// private Button btn_fri;
				// private int btn_fri_i = 1;
				// private Button btn_sat;
				// private int btn_sat_i = 1;
				// private Button btn_sun;
				// private int btn_sun_i = 1;
				if (btn_mon_i == -1) {
					frequetly = frequetly + "周一 ";
				}
				if (btn_tue_i == -1) {
					frequetly = frequetly + "周二";
				}
				if (btn_wed_i == -1) {
					frequetly = frequetly + "周三 ";
				}
				if (btn_thu_i == -1) {
					frequetly = frequetly + "周四 ";
				}
				if (btn_fri_i == -1) {
					frequetly = frequetly + "周五 ";
				}
				if (btn_sat_i == -1) {
					frequetly = frequetly + "周六 ";
				}
				if (btn_sun_i == -1) {
					frequetly = frequetly + "周日 ";
				}
				if (btn_mon_i == 1 && btn_tue_i == 1 && btn_wed_i == 1 && btn_thu_i == 1 && btn_fri_i == 1
						&& btn_sat_i == 1 && btn_sun_i == 1) {
					frequetly = "无要求 ";
				}
				// private int btn_bigdog_i = 1;
				// private Button btn_smalldog;
				// private int btn_smalldog_i = 1;
				// private Button btn_cat;
				// private int btn_cat_i = 1;
				if (btn_bigdog_i == -1) {
					pet = pet + "大型犬 ";
				}
				if (btn_smalldog_i == -1) {
					pet = pet + "小型犬 ";
				}
				if (btn_cat_i == -1) {
					pet = pet + "猫 ";
				}
				if (btn_bigdog_i == 1 && btn_smalldog_i == 1 && btn_cat_i == 1) {
					pet = "无宠物";
				}
				// SimpleDateFormat sDateFormat = new
				// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				// String date = sDateFormat.format(new java.util.Date());
				Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time
										// Zone资料。
				t.setToNow(); // 取得系统时间。
				int year = t.year;
				int month = t.month;
				int date1 = t.monthDay;
				// int date2 = year * 365 + month * 30 + date1;
				// orderdate = Integer.toString(date2);
				int hour = t.hour; // 0-23
				int minute = t.minute;
				int second = t.second;
				int data = (year - 2015) * 365 * 24 * 3600 + month * 30 * 24 * 3600 + date1 * 24 * 3600 + hour * 3600
						+ minute * 60 + second;
				temporderid = Integer.toString(data);
				text_zhongdiangong_contact_text = text_zhongdiangong_contact.getText().toString();
				text_zhongdiangong_phone_text = text_zhongdiangong_phone.getText().toString();
				text_mianjidaxiao_text = (String) text_mianjidaxiao.getText();
				text_jiaweijiage_text = (String) text_jiaweijiage.getText();
				// text_baojie_contact_text =
				// text_baojie_contact.getText().toString();
				// text_baojie_phone_text =
				// text_baojie_phone.getText().toString();
				// // TODO Auto-generated method stub
				// EditText tv = (EditText) findViewById(R.id.editView);
				// ArrayList<NameValuePair> nameValuePairs = new
				// ArrayList<NameValuePair>();
				// EditText tv = (EditText) findViewById(R.id.editView);
				// ArrayList nameValuePairs = new ArrayList();
				// nameValuePairs.add(new BasicNameValuePair("id", "3"));
				// nameValuePairs.add(new BasicNameValuePair("name", "Guo"));
				// nameValuePairs.add(new BasicNameValuePair("school", "xjtu"));

				// if (btn_baojie_ok_i == 1) {
				//
				// btn_baojie_ok.setBackgroundResource(R.drawable.bacyes);
				//
				// btn_baojie_ok_i = (-1) * btn_baojie_ok_i;
				// }
				// if (btn_baojie_ok_i == -1) {
				//
				// btn_baojie_ok.setBackgroundResource(R.drawable.bacno);
				//
				// btn_baojie_ok_i = (-1) * btn_baojie_ok_i;
				// }
				// http get
				(new Thread() {

					@Override

					public void run() {

						// nameValuePairs.add(new
						// BasicNameValuePair("orderdate", orderdate));
						// content = null;
						// private String taste = null;
						// private String frequetly = null;
						// private String pet = null;
						nameValuePairs.add(new BasicNameValuePair("taste", taste));
						nameValuePairs.add(new BasicNameValuePair("frequetly", frequetly));
						nameValuePairs.add(new BasicNameValuePair("pet", pet));
						nameValuePairs.add(new BasicNameValuePair("content", content));
						nameValuePairs.add(new BasicNameValuePair("square", text_mianjidaxiao_text));
						nameValuePairs.add(new BasicNameValuePair("price", text_jiaweijiage_text));
						nameValuePairs.add(new BasicNameValuePair("contact", text_zhongdiangong_contact_text));
						nameValuePairs.add(new BasicNameValuePair("phonenumber", text_zhongdiangong_phone_text));
						nameValuePairs.add(new BasicNameValuePair("typename", typename));
						nameValuePairs.add(new BasicNameValuePair("typeid", typeid));
						nameValuePairs.add(new BasicNameValuePair("userid", userid));
						nameValuePairs.add(new BasicNameValuePair("orderid", temporderid));
						nameValuePairs.add(new BasicNameValuePair("otherneed", temp));
						nameValuePairs.add(new BasicNameValuePair("address", address_temp_other));
						nameValuePairs.add(new BasicNameValuePair("datestart", tempdatestart));
						nameValuePairs.add(new BasicNameValuePair("dateend", tempdateend));
						Log.d("hhhhhhhhh", taste);
						Log.d("hhhhhhhhh", frequetly);
						Log.d("hhhhhhhhh", pet);
						Log.d("hhhhhhhhh", content);
						Log.d("hhhhhhhhh", text_mianjidaxiao_text);
						Log.d("hhhhhhhhh", text_jiaweijiage_text);
						Log.d("hhhhhhhhh", text_zhongdiangong_contact_text);
						Log.d("hhhhhhhhh", text_zhongdiangong_phone_text);
						Log.d("hhhhhhhhh", typename);
						Log.d("hhhhhhhhh", typeid);
						Log.d("hhhhhhhhh", userid);
						Log.d("hhhhhhhhh", temporderid);
						Log.d("hhhhhhhhh", temp);
						Log.d("hhhhhhhhh", address_temp_other);
						Log.d("hhhhhhhhh", tempdatestart);
						Log.d("hhhhhhhhh", tempdateend);
						// nameValuePairs.add(new
						// BasicNameValuePair("orderdate", "1"));
						// nameValuePairs.add(new
						// BasicNameValuePair("totalprice", "50"));
						// nameValuePairs.add(new
						// BasicNameValuePair("lengthoftime", "2"));
						// nameValuePairs.add(new BasicNameValuePair("godate",
						// "2015-45"));
						// nameValuePairs.add(new
						// BasicNameValuePair("otherneed", "otherneed"));

						// nameValuePairs.add(new BasicNameValuePair("contact",
						// "吴新初"));
						// nameValuePairs.add(new
						// BasicNameValuePair("phonenumber", "18710861689"));
						// nameValuePairs.add(new BasicNameValuePair("markone",
						// "6"));
						// nameValuePairs.add(new BasicNameValuePair("marktwo",
						// "20"));
						// nameValuePairs.add(new BasicNameValuePair("test",
						// "吴新初"));
						// nameValuePairs.add(new BasicNameValuePair("testone",
						// "吴新初"));
						// 这里做网络操作
						try {
							// HttpClient httpclient = new DefaultHttpClient();
							// HttpGet httpget = new
							// HttpGet("http://120.27.45.56/test.php");
							// HttpResponse response =
							// httpclient.execute(httpget);
							// HttpEntity entity = response.getEntity();
							// is = entity.getContent();
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://120.27.45.56/insertorderzhongdiangong.php");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
							// httppost.setEntity(new
							// UrlEncodedFormEntity(nameValuePairs));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
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
							is = entity.getContent();
							// Log.e("log_tag", "对不对" );
						} catch (Exception e) {
							Log.e("log_tag", "Error in http connection" + e.toString());
						}

					}

				}).start();
				// Intent intent05 = new Intent();
				// intent05.setClass(baojie.this, yuesao.class);
				// startActivity(intent05);

				// convert response to string
				// try {
				// BufferedReader reader = new BufferedReader(new
				// InputStreamReader(is, "iso-8859-1"), 8);
				// sb = new StringBuilder();
				// sb.append(reader.readLine() + "\n");
				//
				// String line = "0";
				// while ((line = reader.readLine()) != null) {
				// sb.append(line + "\n");
				// }
				// is.close();
				// result = sb.toString();result = delete(result);
				// } catch (Exception e) {
				// Log.e("log_tag", "Error converting result " + e.toString());
				// }
				// // paring data
				// int ct_id;
				// String ct_name;
				// try {
				// jArray = new JSONArray(result);
				// JSONObject json_data = null;
				// for (int i = 0; i < jArray.length(); i++) {
				// json_data = jArray.getJSONObject(i);
				// ct_id = json_data.getInt("id");
				// ct_name = json_data.getString("name");
				// tv.append(ct_name + " \n");
				// }
				// } catch (JSONException e1) {
				// // Toast.makeText(getBaseContext(), "No City Found"
				// // ,Toast.LENGTH_LONG).show();
				// } catch (ParseException e1) {
				// e1.printStackTrace();
				// }
				btn_zhongdiangong_ok.setVisibility(View.GONE);
			}
		});
		text_zhongdiangong_address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(zhongdiangong.this, address.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", address_temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 2);

			}

		});
		// btn_zhongdiangong_ok.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// OnekeyShare oks = new OnekeyShare();
		// oks.setNotification(R.drawable.ic_launcher, "ShareSDK demo");
		// oks.setAddress("12345678901");
		// oks.setTitle(" title");
		// oks.setTitleUrl("http://sharesdk.cn");
		// oks.setText(" text");
		// oks.setImagePath(zhongdiangong.TEST_IMAGE);
		// oks.setImageUrl("http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg");
		// oks.setUrl("http://sharesdk.cn");
		// oks.setComment("comment");
		// oks.setSite("site");
		// oks.setSiteUrl("http://sharesdk.cn");
		// oks.setLatitude(23.122619f);
		// oks.setLongitude(113.372338f);
		// oks.setSilent(false);
		// oks.show(zhongdiangong.this);
		// }
		// });
		//
		// new Thread() {
		// public void run() {
		// initImagePath();
		// }
		// }.start();
		seekbar_fangwumianji.setOnSeekBarChangeListener(this);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();

			}

		});

		otherdetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(baojie.this, baojieedit.class);
				// resultCode---请求码
				Intent intent = new Intent(zhongdiangong.this, edit.class);
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", temp); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);

				// resultCode---请求码
				// startActivityForResult(intent, 1);

			}

		});
		text_serverscon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(zhongdiangong.this, serverscon.class);
				// resultCode---请求码
				startActivity(intent);

			}

		});
		btn_mon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_mon_i == 1) {

					btn_mon.setBackgroundResource(R.drawable.bacyes);

					btn_mon_i = (-1) * btn_mon_i;

				} else if (btn_mon_i == -1) {

					btn_mon.setBackgroundResource(R.drawable.bacno);

					btn_mon_i = (-1) * btn_mon_i;

				}

			}

		});
		btn_tue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_tue_i == 1) {

					btn_tue.setBackgroundResource(R.drawable.bacyes);

					btn_tue_i = (-1) * btn_tue_i;

				} else if (btn_tue_i == -1) {

					btn_tue.setBackgroundResource(R.drawable.bacno);

					btn_tue_i = (-1) * btn_tue_i;

				}

			}

		});

		btn_wed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_wed_i == 1) {

					btn_wed.setBackgroundResource(R.drawable.bacyes);

					btn_wed_i = (-1) * btn_wed_i;

				} else if (btn_wed_i == -1) {

					btn_wed.setBackgroundResource(R.drawable.bacno);

					btn_wed_i = (-1) * btn_wed_i;

				}

			}

		});
		btn_thu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_thu_i == 1) {

					btn_thu.setBackgroundResource(R.drawable.bacyes);

					btn_thu_i = (-1) * btn_thu_i;

				} else if (btn_thu_i == -1) {

					btn_thu.setBackgroundResource(R.drawable.bacno);

					btn_thu_i = (-1) * btn_thu_i;

				}

			}

		});
		btn_xian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_xian_i == 1) {

					btn_xian.setBackgroundResource(R.drawable.bacyes);

					btn_xian_i = (-1) * btn_xian_i;

				} else if (btn_xian_i == -1) {

					btn_xian.setBackgroundResource(R.drawable.bacno);

					btn_xian_i = (-1) * btn_xian_i;

				}

			}

		});

		btn_fri.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_fri_i == 1) {

					btn_fri.setBackgroundResource(R.drawable.bacyes);

					btn_fri_i = (-1) * btn_fri_i;

				} else if (btn_fri_i == -1) {

					btn_fri.setBackgroundResource(R.drawable.bacno);

					btn_fri_i = (-1) * btn_fri_i;

				}

			}

		});
		btn_sat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_sat_i == 1) {

					btn_sat.setBackgroundResource(R.drawable.bacyes);

					btn_sat_i = (-1) * btn_sat_i;

				} else if (btn_sat_i == -1) {

					btn_sat.setBackgroundResource(R.drawable.bacno);

					btn_sat_i = (-1) * btn_sat_i;

				}

			}

		});
		btn_sun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_sun_i == 1) {

					btn_sun.setBackgroundResource(R.drawable.bacyes);

					btn_sun_i = (-1) * btn_sun_i;

				} else if (btn_sun_i == -1) {

					btn_sun.setBackgroundResource(R.drawable.bacno);

					btn_sun_i = (-1) * btn_sun_i;

				}

			}

		});
		btn_bigdog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_bigdog_i == 1) {

					btn_bigdog.setBackgroundResource(R.drawable.bacyes);

					btn_bigdog_i = (-1) * btn_bigdog_i;

				} else if (btn_bigdog_i == -1) {

					btn_bigdog.setBackgroundResource(R.drawable.bacno);

					btn_bigdog_i = (-1) * btn_bigdog_i;

				}

			}

		});
		btn_smalldog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_smalldog_i == 1) {

					btn_smalldog.setBackgroundResource(R.drawable.bacyes);

					btn_smalldog_i = (-1) * btn_smalldog_i;

				} else if (btn_smalldog_i == -1) {

					btn_smalldog.setBackgroundResource(R.drawable.bacno);

					btn_smalldog_i = (-1) * btn_smalldog_i;

				}

			}

		});
		btn_cat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_cat_i == 1) {

					btn_cat.setBackgroundResource(R.drawable.bacyes);

					btn_cat_i = (-1) * btn_cat_i;

				} else if (btn_cat_i == -1) {

					btn_cat.setBackgroundResource(R.drawable.bacno);

					btn_cat_i = (-1) * btn_cat_i;

				}

			}

		});
		btn_xian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_xian_i == 1) {

					btn_xian.setBackgroundResource(R.drawable.bacyes);

					btn_xian_i = (-1) * btn_xian_i;

				} else if (btn_xian_i == -1) {

					btn_xian.setBackgroundResource(R.drawable.bacno);

					btn_xian_i = (-1) * btn_xian_i;

				}

			}

		});
		btn_dan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_dan_i == 1) {

					btn_dan.setBackgroundResource(R.drawable.bacyes);

					btn_dan_i = (-1) * btn_dan_i;

				} else if (btn_dan_i == -1) {

					btn_dan.setBackgroundResource(R.drawable.bacno);

					btn_dan_i = (-1) * btn_dan_i;

				}

			}

		});
		btn_la.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_la_i == 1) {

					btn_la.setBackgroundResource(R.drawable.bacyes);

					btn_la_i = (-1) * btn_la_i;

				} else if (btn_la_i == -1) {

					btn_la.setBackgroundResource(R.drawable.bacno);

					btn_la_i = (-1) * btn_la_i;

				}

			}

		});
		btn_qingdan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_qingdan_i == 1) {

					btn_qingdan.setBackgroundResource(R.drawable.bacyes);

					btn_qingdan_i = (-1) * btn_qingdan_i;

				} else if (btn_qingdan_i == -1) {

					btn_qingdan.setBackgroundResource(R.drawable.bacno);

					btn_qingdan_i = (-1) * btn_qingdan_i;

				}

			}

		});
		btn_mianshi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_mianshi_i == 1) {

					btn_mianshi.setBackgroundResource(R.drawable.bacyes);

					btn_mianshi_i = (-1) * btn_mianshi_i;

				} else if (btn_mianshi_i == -1) {

					btn_mianshi.setBackgroundResource(R.drawable.bacno);

					btn_mianshi_i = (-1) * btn_mianshi_i;

				}

			}

		});
		btn_baojie.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button but = (Button) v;
				switch (but.getId()) {
				case R.id.btn_baojie:
					if (btn_baojie_i == 1) {
						layout_baojie.setVisibility(View.VISIBLE);
						btn_baojie.setBackgroundResource(R.drawable.bacyes);

						btn_baojie_i = (-1) * btn_baojie_i;

					} else if (btn_baojie_i == -1) {
						layout_baojie.setVisibility(View.GONE);
						btn_baojie.setBackgroundResource(R.drawable.bacno);

						btn_baojie_i = (-1) * btn_baojie_i;

					}
					// }
					break;

				default:
					break;
				}

			}
		});

		btn_zuofan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button but = (Button) v;
				switch (but.getId()) {

				case R.id.btn_zuofan:
					if (btn_zuofan_i == 1) {
						layout_zuofan.setVisibility(View.VISIBLE);
						btn_zuofan.setBackgroundResource(R.drawable.bacyes);

						btn_zuofan_i = (-1) * btn_zuofan_i;

					} else if (btn_zuofan_i == -1) {
						layout_zuofan.setVisibility(View.GONE);
						btn_zuofan.setBackgroundResource(R.drawable.bacno);

						btn_zuofan_i = (-1) * btn_zuofan_i;

					}
					// }
					break;

				default:
					break;
				}

			}
		});
		text_datestart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				DatePickerDialog dialog = new DatePickerDialog(zhongdiangong.this,
						new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						c1.set(year, monthOfYear, dayOfMonth);
						et1.setText(DateFormat.format("yyy-MM-dd", c1));
						tempdatestart = (String) DateFormat.format("yyy-MM-dd", c1);
						// nameValuePairs.add(new
						// BasicNameValuePair("datestart", tempdatestart));

					}
				}, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
		text_dateend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				DatePickerDialog dialog = new DatePickerDialog(zhongdiangong.this,
						new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						c2.set(year, monthOfYear, dayOfMonth);
						et2.setText(DateFormat.format("yyy-MM-dd", c2));
						tempdateend = (String) DateFormat.format("yyy-MM-dd", c2);
						// nameValuePairs.add(new BasicNameValuePair("dateend",
						// tempdateend));
					}
				}, c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
	}

	protected void go() {
		// TODO Auto-generated method stub
		Intent intentorder = new Intent(zhongdiangong.this, zhongdiangongorder.class);
		// resultCode---请求码
		Bundle bundle = new Bundle(); // 创建Bundle对象
		bundle.putString("orderid", temporderid); // 装入数据
		bundle.putString("userid", userid); // 装入数据
		intentorder.putExtras(bundle);
		startActivityForResult(intentorder, 3);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		// Log.d(TAG, "seekid:"+seekBar.getId()+", progess"+progress);
		switch (seekBar.getId()) {
		case R.id.seekbar_xinlijiawei: {
			// 璁剧疆鈥滀笌绯荤粺榛樿SeekBar瀵瑰簲鐨凾extView鈥濈殑鍊�

			int i = seekBar.getProgress();
			if (i >= 0 & i <= 50) {
				text_jiaweijiage.setText("25-30元（小时）");

			}
			if (i > 50 & i < 101) {
				text_jiaweijiage.setText("30-35元（小时）");
			}
			break;
		}
		case R.id.seekbar_fangwumianji: {
			int i = seekBar.getProgress();
			if (i == 0) {

				text_mianjidaxiao.setText("50平方以下");
			}
			if (i > 0 & i < 20) {
				text_mianjidaxiao.setText("50-80平方");
			}
			if (i >= 20 & i < 40) {
				text_mianjidaxiao.setText("80-110平方");
			}
			if (i >= 40 & i < 60) {
				text_mianjidaxiao.setText("110-140平方");
			}
			if (i >= 60 & i < 80) {
				text_mianjidaxiao.setText("140-170平方");
			}
			if (i >= 80 & i < 100) {
				text_mianjidaxiao.setText("170-200平方");
			}
			if (i >= 200) {
				text_mianjidaxiao.setText("200平方以上");
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	private void initImagePath() {
		try {
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
					&& Environment.getExternalStorageDirectory().exists()) {
				TEST_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath() + FILE_NAME;
			} else {
				TEST_IMAGE = getApplication().getFilesDir().getAbsolutePath() + FILE_NAME;
			}
			File file = new File(TEST_IMAGE);
			if (!file.exists()) {
				file.createNewFile();
				Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
				FileOutputStream fos = new FileOutputStream(file);
				pic.compress(CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			TEST_IMAGE = null;
		}
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

			otherdetail.setText(otherneed);
			temp = otherneed;
			break;
		case 2:
			String address = bundle.getString("address");
			text_zhongdiangong_address.setText(address);
			// ArrayList nameValuePairs = new ArrayList();
			// temp = address;
			address_temp_other = address;
			// nameValuePairs.add(new BasicNameValuePair("otherneed",
			// otherneed));

			break;
		case 3:
			boolean staues = bundle.getBoolean("staues");
			if (staues) {
				Log.e("log_tag", "true ");
				btn_zhongdiangong_ok.setVisibility(View.VISIBLE);
				finish();
			} else {
				Log.e("log_tag", "false ");
				btn_zhongdiangong_ok.setVisibility(View.VISIBLE);
			}

			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}

	// 设置监听http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
	// 监听是子线程，不能Toast，要用handler处理，不要犯这么二的错误
	public void onCancel(Platform platform, int action) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	public void onComplete(Platform platform, int action, HashMap<String, Object> arg2) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	public void onError(Platform platform, int action, Throwable t) {
		t.printStackTrace();
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MSG_TOAST: {
			String text = String.valueOf(msg.obj);
			Toast.makeText(zhongdiangong.this, text, Toast.LENGTH_SHORT).show();
		}
			break;
		case MSG_ACTION_CCALLBACK: {
			switch (msg.arg1) {
			case 1: { // 成功
				showNotification(2000, getString(R.string.share_completed));
			}
				break;
			case 2: { // 失败
				String expName = msg.obj.getClass().getSimpleName();
				if ("WechatClientNotExistException".equals(expName)
						|| "WechatTimelineNotSupportedException".equals(expName)) {
					showNotification(2000, getString(R.string.wechat_client_inavailable));
				} else if ("GooglePlusClientNotExistException".equals(expName)) {
					showNotification(2000, getString(R.string.google_plus_client_inavailable));
				} else if ("QQClientNotExistException".equals(expName)) {
					showNotification(2000, getString(R.string.qq_client_inavailable));
				} else {
					showNotification(2000, getString(R.string.share_failed));
				}
			}
				break;
			case 3: { // 取消
				showNotification(2000, getString(R.string.share_canceled));
			}
				break;
			}
		}
			break;
		case MSG_CANCEL_NOTIFY: {
			NotificationManager nm = (NotificationManager) msg.obj;
			if (nm != null) {
				nm.cancel(msg.arg1);
			}
		}
			break;
		}
		return false;
	}

	// 在状态栏提示分享操作
	private void showNotification(long cancelTime, String text) {
		try {
			Context app = getApplicationContext();
			NotificationManager nm = (NotificationManager) app.getSystemService(Context.NOTIFICATION_SERVICE);
			final int id = Integer.MAX_VALUE / 13 + 1;
			nm.cancel(id);

			long when = System.currentTimeMillis();
			Notification notification = new Notification(R.drawable.ic_launcher, text, when);
			PendingIntent pi = PendingIntent.getActivity(app, 0, new Intent(), 0);
			notification.setLatestEventInfo(app, "sharesdk test", text, pi);
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			nm.notify(id, notification);

			if (cancelTime > 0) {
				Message msg = new Message();
				msg.what = MSG_CANCEL_NOTIFY;
				msg.obj = nm;
				msg.arg1 = id;
				UIHandler.sendMessageDelayed(msg, cancelTime, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}
}
