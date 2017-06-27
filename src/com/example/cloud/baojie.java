package com.example.cloud;

import java.util.ArrayList;
import android.util.Log;
import com.example.cloud.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.text.format.Time;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class baojie extends Activity implements OnDateChangedListener, OnTimeChangedListener {
	private int back_int = 1;
	private int btn_baojie_ok_delete_cle = 0;
	private int content_time = 0;
	private int content_gotime = 0;
	private int btn_baojie_ok_cle = 0;
	private DatePicker dp_date;
	private TimePicker tp_time;
	private TextView text_dotimenumber;
	private int date_year = 2014;
	private int date_month = 8;
	private int date_day = 24;
	private int date_hour = 12;
	private int date_minute = 14;
	private Button btn_dateok;
	private Button btn_two;
	private int btn_two_i = 1;
	private Button btn_three;
	private int btn_three_i = 1;
	private Button btn_four;
	private int btn_four_i = 1;
	private TextView text_totalprice;
	private TextView baojie_otherdetail;
	private TextView btn_baojie_back;
	private TextView text_baojie_serverscon;
	private TextView text_baojie_address;
	private Button btn_baojie_ok;
	private int btn_baojie_ok_i = 1;
	private LinearLayout layout_time;
	private String temp = "未填写";
	private String temp_other = "";
	private String address_temp_other;
	private EditText text_baojie_contact;
	private EditText text_baojie_phone;
	private String text_baojie_contact_text;
	private String text_baojie_phone_text;
	private String type_name = "保洁";
	private String type_id = "1";
	private String orderdate = "error";
	private String orderid = "error";
	private String userid = "1";
	InputStream is = null;
	private String username;
	private String password;
	private SharedPreferences pre;
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
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(baojie.this);
			alertDialog.setTitle("临时保洁");
			alertDialog.setMessage("确定抛弃该订单吗？");
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
					back_int = 0;
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
		setContentView(R.layout.layoutbaojie);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		userid = username;
		Log.d("userid", userid);
		text_baojie_contact = (EditText) findViewById(R.id.text_baojie_contact);
		text_baojie_phone = (EditText) findViewById(R.id.text_baojie_phone);
		dp_date = (DatePicker) findViewById(R.id.dp_date);
		layout_time = (LinearLayout) findViewById(R.id.layout_time);
		text_baojie_address = (TextView) findViewById(R.id.text_baojie_address);
		text_baojie_serverscon = (TextView) findViewById(R.id.text_baojie_serverscon);
		btn_baojie_back = (TextView) findViewById(R.id.btn_baojie_back);
		text_totalprice = (TextView) findViewById(R.id.text_totalprice);
		baojie_otherdetail = (TextView) findViewById(R.id.baojie_otherdetail);
		text_dotimenumber = (TextView) findViewById(R.id.text_dotimenumber);
		tp_time = (TimePicker) findViewById(R.id.tp_time);
		btn_dateok = (Button) findViewById(R.id.btn_dateok);
		btn_two = (Button) findViewById(R.id.btn_two);
		btn_three = (Button) findViewById(R.id.btn_three);
		btn_four = (Button) findViewById(R.id.btn_four);
		btn_baojie_ok = (Button) findViewById(R.id.btn_baojie_ok);
		// 初始化日期，并设置日期被改变后的监听器
		dp_date.init(2014, 8 - 1, 24, this);
		// 设置时间以24小时制显示

		tp_time.setIs24HourView(true);
		// 设置时间被改变后的监听器
		tp_time.setOnTimeChangedListener(this);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		text_baojie_address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(baojie.this, address.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", address_temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 2);

			}

		});

		final ArrayList nameValuePairs = new ArrayList();
		btn_baojie_ok.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				text_baojie_contact_text = text_baojie_contact.getText().toString();
				text_baojie_phone_text = text_baojie_phone.getText().toString();
				if (content_time == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(baojie.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(baojie.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择服务时长");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (content_gotime == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(baojie.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(baojie.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择上门时间");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (address_temp_other == null) {

					AlertDialog alertDialog = new AlertDialog.Builder(baojie.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(baojie.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请填写服务地址");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (text_baojie_contact_text.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(baojie.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(baojie.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请填写联系人");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (text_baojie_phone_text.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(baojie.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(baojie.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请填写联系电话");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();

				} else if (true) {
					Time t = new Time();
					t.setToNow(); // 取得系统时间。
					int year = t.year;
					String yearstr = Integer.toString(year);
					int month = t.month + 1;
					String monthstr = Integer.toString(month);
					if (monthstr.length() == 1) {
						monthstr = "0" + monthstr;
					}
					int date1 = t.monthDay;
					String date1str = Integer.toString(date1);
					if (date1str.length() == 1) {
						date1str = "0" + date1str;
					}
					int hour = t.hour; // 0-23
					String hourstr = Integer.toString(hour);
					if (hourstr.length() == 1) {
						hourstr = "0" + hourstr;
					}
					int minute = t.minute;
					String minutestr = Integer.toString(minute);
					if (minutestr.length() == 1) {
						minutestr = "0" + minutestr;
					}
					int second = t.second;
					String secondstr = Integer.toString(second);
					if (secondstr.length() == 1) {
						secondstr = "0" + secondstr;
					}
					// long data = (year - 2015) * 365 * 24 * 3600 + month *
					// 30 * 24 * 3600 + date1 * 24 * 3600
					// + hour * 3600 + minute * 60 + second;
					orderid = yearstr + monthstr + date1str + hourstr + minutestr + secondstr;
					Log.e("orderid", orderid);

					// // TODO Auto-generated method stub
					// EditText tv = (EditText) findViewById(R.id.editView);
					// ArrayList<NameValuePair> nameValuePairs = new
					// ArrayList<NameValuePair>();
					// EditText tv = (EditText) findViewById(R.id.editView);
					// ArrayList nameValuePairs = new ArrayList();
					// nameValuePairs.add(new BasicNameValuePair("id", "3"));
					// nameValuePairs.add(new BasicNameValuePair("name",
					// "Guo"));
					// nameValuePairs.add(new BasicNameValuePair("school",
					// "xjtu"));

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
							Log.d("useridhjgkghkg", userid);
							nameValuePairs.add(new BasicNameValuePair("orderdate", orderdate));
							nameValuePairs.add(new BasicNameValuePair("contact", text_baojie_contact_text));
							nameValuePairs.add(new BasicNameValuePair("phonenumber", text_baojie_phone_text));
							nameValuePairs.add(new BasicNameValuePair("typename", type_name));
							nameValuePairs.add(new BasicNameValuePair("typeid", type_id));
							nameValuePairs.add(new BasicNameValuePair("userid", username));
							nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
							nameValuePairs.add(new BasicNameValuePair("otherneed", temp));
							nameValuePairs.add(new BasicNameValuePair("address", address_temp_other));
							nameValuePairs.add(new BasicNameValuePair("client", "true"));
							nameValuePairs.add(new BasicNameValuePair("server", "true"));
							nameValuePairs.add(new BasicNameValuePair("condition", "待处理"));
							// nameValuePairs.add(new
							// BasicNameValuePair("orderdate", "1"));
							// nameValuePairs.add(new
							// BasicNameValuePair("totalprice", "50"));
							// nameValuePairs.add(new
							// BasicNameValuePair("lengthoftime", "2"));
							// nameValuePairs.add(new
							// BasicNameValuePair("godate",
							// "2015-45"));
							// nameValuePairs.add(new
							// BasicNameValuePair("otherneed", "otherneed"));

							// nameValuePairs.add(new
							// BasicNameValuePair("contact",
							// "吴新初"));
							// nameValuePairs.add(new
							// BasicNameValuePair("phonenumber",
							// "18710861689"));
							// nameValuePairs.add(new
							// BasicNameValuePair("markone",
							// "6"));
							// nameValuePairs.add(new
							// BasicNameValuePair("marktwo",
							// "20"));
							// nameValuePairs.add(new BasicNameValuePair("test",
							// "吴新初"));
							// nameValuePairs.add(new
							// BasicNameValuePair("testone",
							// "吴新初"));
							// 这里做网络操作
							// btn_baojie_ok_cicle = 0;
							btn_baojie_ok_cle = 0;

							for (btn_baojie_ok_cle = 0; btn_baojie_ok_cle < 1; btn_baojie_ok_cle = btn_baojie_ok_cle) {
								if (back_int == 1) {
									try {
										// HttpClient httpclient = new
										// DefaultHttpClient();
										// HttpGet httpget = new
										// HttpGet("http://www.sundaytek.com/test.php");
										// HttpResponse response =
										// httpclient.execute(httpget);
										// HttpEntity entity =
										// response.getEntity();
										// is = entity.getContent();
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost(
												"http://www.sundaytek.com/insertorderbaojie.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										// httppost.setEntity(new
										// UrlEncodedFormEntity(nameValuePairs));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();
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
										btn_baojie_ok_cle = 1;
										is = entity.getContent();
										// Log.e("log_tag", "对不对" );
									} catch (Exception e) {
										Log.e("log_tag", "Error in http connection" + e.toString());
										btn_baojie_ok_cle = 0;
									}
								}
							}
							if (back_int == 1) {
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
							} else {
								btn_baojie_ok_delete_cle = 0;
								for (btn_baojie_ok_delete_cle = 0; btn_baojie_ok_delete_cle < 1; btn_baojie_ok_delete_cle = btn_baojie_ok_delete_cle) {
									try {
										nameValuePairs.add(new BasicNameValuePair("userid", userid));
										nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost(
												"http://www.sundaytek.com/deletebaojieorder.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();
										btn_baojie_ok_delete_cle = 1;
										is = entity.getContent();
										Log.e("log_tag", "isme ");
									} catch (Exception e) {
										Log.d("log_tag", "Error in http connection" + e.toString());
										btn_baojie_ok_delete_cle = 0;
									}
								}
							}
						}

					}).start();
					btn_baojie_ok.setVisibility(View.GONE);
				}

			}
		});
		text_dotimenumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout_time.setVisibility(View.VISIBLE);

			}

		});
		btn_two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_two_i == 1) {
					content_time = 1;
					btn_two.setBackgroundResource(R.drawable.bacyes);

					btn_two_i = (-1) * btn_two_i;
					text_totalprice.setText("50元");
					btn_three.setBackgroundResource(R.drawable.bacno);
					btn_four.setBackgroundResource(R.drawable.bacno);
					btn_four_i = 1;
					btn_three_i = 1;
					nameValuePairs.add(new BasicNameValuePair("lengthtime", "2"));
					nameValuePairs.add(new BasicNameValuePair("totalprice", "50"));
				}

			}

		});
		btn_three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_three_i == 1) {
					content_time = 1;
					btn_three.setBackgroundResource(R.drawable.bacyes);
					btn_two.setBackgroundResource(R.drawable.bacno);
					btn_four.setBackgroundResource(R.drawable.bacno);
					text_totalprice.setText("75元");
					btn_three_i = (-1) * btn_three_i;
					btn_two_i = 1;
					btn_four_i = 1;
					nameValuePairs.add(new BasicNameValuePair("lengthtime", "3"));
					nameValuePairs.add(new BasicNameValuePair("totalprice", "75"));
				}

			}

		});

		btn_four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_four_i == 1) {
					content_time = 1;
					btn_four.setBackgroundResource(R.drawable.bacyes);
					btn_three.setBackgroundResource(R.drawable.bacno);
					btn_two.setBackgroundResource(R.drawable.bacno);
					text_totalprice.setText("100元");
					btn_four_i = (-1) * btn_four_i;
					btn_three_i = 1;
					btn_two_i = 1;
					nameValuePairs.add(new BasicNameValuePair("lengthtime", "4"));
					nameValuePairs.add(new BasicNameValuePair("totalprice", "100"));
				}

			}

		});
		btn_baojie_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(baojie.this);
				alertDialog.setTitle("临时保洁");
				alertDialog.setMessage("确定抛弃该订单吗？");
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
						back_int = 0;
						finish();
					}
				});
				alertDialog.show();

			}

		});

		baojie_otherdetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(baojie.this, baojieedit.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);

			}

		});
		text_baojie_serverscon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(baojie.this, baojieserverscon.class);
				// resultCode---请求码
				startActivity(intent);

			}

		});
		btn_dateok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String date;
				date = date_year + "-" + date_month + "-" + date_day + "   " + date_hour + ":" + date_minute;
				nameValuePairs.add(new BasicNameValuePair("gotime", date));
				// nameValuePairs.add(new BasicNameValuePair("totalprice",
				// "50"));
				text_dotimenumber.setText(date);
				layout_time.setVisibility(View.GONE);
				content_gotime = 1;
			}

		});
	}

	protected void go() {
		// TODO Auto-generated method stub
		Intent intentorder = new Intent(baojie.this, baojieorder.class);
		// resultCode---请求码
		Bundle bundle = new Bundle(); // 创建Bundle对象
		bundle.putString("orderid", orderid); // 装入数据
		bundle.putString("userid", userid); // 装入数据
		intentorder.putExtras(bundle);
		startActivityForResult(intentorder, 3);
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		date_hour = hourOfDay;
		date_minute = minute;
		// Toast.makeText(this, "时间被改变了 hourOfDay:" + hourOfDay + " minute:" +
		// minute, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub\
		date_year = year;
		date_month = monthOfYear + 1;
		date_day = dayOfMonth;
		// Toast.makeText(this, "日期被改变了 year:" + year + " month:" + monthOfYear
		// + 1 + " day:" + dayOfMonth,
		// Toast.LENGTH_SHORT).show();

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

			baojie_otherdetail.setText(otherneed);
			// ArrayList nameValuePairs = new ArrayList();
			temp = otherneed;
			temp_other = otherneed;
			// nameValuePairs.add(new BasicNameValuePair("otherneed",
			// otherneed));

			break;
		case 2:
			String address = bundle.getString("address");
			text_baojie_address.setText(address);
			// ArrayList nameValuePairs = new ArrayList();
			// temp = address;
			address_temp_other = address;
			// nameValuePairs.add(new BasicNameValuePair("otherneed",
			// otherneed));

			break;
		case 3:
			boolean staues = bundle.getBoolean("staues");
			if (staues) {
				Log.e("log_tagbbbbbb", "true ");
				btn_baojie_ok.setVisibility(View.VISIBLE);
				Intent intent = new Intent();
				intent.putExtra("staues", true);
				setResult(0, intent);
				finish();
			} else {
				Log.e("log_tag", "false ");
				btn_baojie_ok.setVisibility(View.VISIBLE);
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
