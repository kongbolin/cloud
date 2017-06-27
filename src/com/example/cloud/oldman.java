package com.example.cloud;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class oldman extends Activity implements SeekBar.OnSeekBarChangeListener {
	private int choice_sex = 0;
	private int choice_zili = 0;
	private int choice_zhujia = 0;
	private int back_oldman_int = 1;
	private int btn_oldman_ok_delete_cle = 0;
	private int btn_oldman_ok_cle = 0;
	private TextView text_oldman_jiaweijiage;
	private SeekBar seekbar_oldman_xinlijiawei;
	private Button btn_oldman_boy;
	private int btn_oldman_boy_i = 1;
	private Button btn_oldman_buzhujia;
	private int btn_oldman_buzhujia_i = 1;
	private Button btn_oldman_zhujia;
	private int btn_oldman_zhujia_i = 1;
	private Button btn_oldman_girl;
	private int btn_oldman_girl_i = 1;
	private Button btn_oldman_bigdog;
	private int btn_oldman_bigdog_i = 1;
	private Button btn_oldman_smalldog;
	private int btn_oldman_smalldog_i = 1;
	private Button btn_oldman_cat;
	private int btn_oldman_cat_i = 1;
	private TextView btn_oldman_back;
	private TextView text_oldman_serverscon;
	private TextView oldman_otherdetail;
	private Button btn_oldman_self;
	private int btn_oldman_self_i = 1;
	private Button btn_oldman_halfself;
	private int btn_oldman_halfself_i = 1;
	private Button btn_oldman_no;
	private int btn_oldman_no_i = 1;
	private String temp_other = "未填写";
	private String temp = "";
	private TextView text_oldman_address;
	private String address_temp_other = "";
	private Button btn_oldman_ok;
	private String pet = "";
	private String zhujia = "";
	private String sex = "";
	private String zili = "";
	private String orderid;
	private String userid = "1";
	private String typeid = "5";
	private String typename = "看护老人";
	private String text_oldman_contact_text;
	private String text_oldman_phone_text;
	private String text_jiaweijiage_text;
	private TextView text_oldman_contact;
	private TextView text_oldman_phone;
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
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(oldman.this);
			alertDialog.setTitle("看护老人");
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
					back_oldman_int = 0;
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
		setContentView(R.layout.layoutoldman);

		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		userid = username;
		btn_oldman_self = (Button) findViewById(R.id.btn_oldman_self);
		btn_oldman_halfself = (Button) findViewById(R.id.btn_oldman_halfself);
		btn_oldman_no = (Button) findViewById(R.id.btn_oldman_no);
		btn_oldman_bigdog = (Button) findViewById(R.id.btn_oldman_bigdog);
		btn_oldman_smalldog = (Button) findViewById(R.id.btn_oldman_smalldog);
		btn_oldman_cat = (Button) findViewById(R.id.btn_oldman_cat);
		btn_oldman_zhujia = (Button) findViewById(R.id.btn_oldman_zhujia);
		btn_oldman_buzhujia = (Button) findViewById(R.id.btn_oldman_buzhujia);
		btn_oldman_boy = (Button) findViewById(R.id.btn_oldman_boy);
		btn_oldman_girl = (Button) findViewById(R.id.btn_oldman_girl);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		text_oldman_jiaweijiage = (TextView) findViewById(R.id.text_oldman_jiaweijiage);

		seekbar_oldman_xinlijiawei = (SeekBar) findViewById(R.id.seekbar_oldman_xinlijiawei);
		seekbar_oldman_xinlijiawei.setOnSeekBarChangeListener(this);
		text_oldman_serverscon = (TextView) findViewById(R.id.text_oldman_serverscon);
		btn_oldman_back = (TextView) findViewById(R.id.btn_oldman_back);
		oldman_otherdetail = (TextView) findViewById(R.id.oldman_otherdetail);
		text_oldman_address = (TextView) findViewById(R.id.text_oldman_address);
		btn_oldman_ok = (Button) findViewById(R.id.btn_oldman_ok);
		text_oldman_contact = (TextView) findViewById(R.id.text_oldman_order_contactoldman);
		text_oldman_phone = (TextView) findViewById(R.id.text_oldman_order_phoneoldman);
		final ArrayList nameValuePairs = new ArrayList();
		btn_oldman_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				text_oldman_contact_text = text_oldman_contact.getText().toString();
				text_oldman_phone_text = text_oldman_phone.getText().toString();
				text_jiaweijiage_text = (String) text_oldman_jiaweijiage.getText();

				choice_sex = 0;
				choice_zili = 0;
				choice_zhujia = 0;
				pet = "";
				zhujia = "";
				sex = "";
				zili = "";
				if (btn_oldman_boy_i == -1) {
					sex = "男老人";
					choice_sex = 1;
				} else if (btn_oldman_girl_i == -1) {
					sex = "女老人";
					choice_sex = 1;
				} else if (true) {
					sex = "未选择";
					choice_sex = 0;
				}
				if (btn_oldman_self_i == -1) {
					zili = "自理";
					choice_zili = 1;
				} else if (btn_oldman_halfself_i == -1) {
					zili = "半自理";
					choice_zili = 1;
				} else if (btn_oldman_no_i == -1) {
					zili = "不能自理";
					choice_zili = 1;
				} else if (true) {
					zili = "未选择";
					choice_zili = 0;

				}
				if (btn_oldman_buzhujia_i == -1) {
					zhujia = "不住家";
					choice_zhujia = 1;
				} else if (btn_oldman_zhujia_i == -1) {
					zhujia = "住家";
					choice_zhujia = 1;
				} else if (true) {
					zhujia = "无要求";
					choice_zhujia = 0;

				}

				if (btn_oldman_bigdog_i == -1) {
					pet = pet + "大型犬 ";
				}
				if (btn_oldman_smalldog_i == -1) {
					pet = pet + "小型犬 ";
				}
				if (btn_oldman_cat_i == -1) {
					pet = pet + "猫 ";
				}
				if (btn_oldman_bigdog_i == 1 && btn_oldman_smalldog_i == 1 && btn_oldman_cat_i == 1) {
					pet = "无宠物";
				}
				if (choice_sex == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择老人性别");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();

				} else if (choice_zili == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择自理能力");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (text_jiaweijiage_text.equals("请选择价格")) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择心理价位");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (choice_zhujia == 0) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择是否住家");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();
				} else if (address_temp_other.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请选择地址");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();

				} else if (text_oldman_contact_text.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
					settingDialog.setInverseBackgroundForced(true);
					alertDialog.setMessage("请填写联系人");
					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "知道了",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alertDialog.show();

				} else if (text_oldman_phone_text.equals("")) {
					AlertDialog alertDialog = new AlertDialog.Builder(oldman.this).create();
					AlertDialog.Builder settingDialog = new AlertDialog.Builder(oldman.this);
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
					Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time
											// //
					// Zone资料。
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
					// int date2 = year * 365 + month * 30 + date1;
					// orderdate = Integer.toString(date2);
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
					(new Thread() {

						@Override

						public void run() {
							nameValuePairs.add(new BasicNameValuePair("pet", pet));
							nameValuePairs.add(new BasicNameValuePair("price", text_jiaweijiage_text));
							nameValuePairs.add(new BasicNameValuePair("contact", text_oldman_contact_text));
							nameValuePairs.add(new BasicNameValuePair("phonenumber", text_oldman_phone_text));
							nameValuePairs.add(new BasicNameValuePair("typename", typename));
							nameValuePairs.add(new BasicNameValuePair("typeid", typeid));
							nameValuePairs.add(new BasicNameValuePair("userid", userid));
							nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
							nameValuePairs.add(new BasicNameValuePair("otherneed", temp_other));
							nameValuePairs.add(new BasicNameValuePair("address", address_temp_other));
							nameValuePairs.add(new BasicNameValuePair("zili", zili));
							nameValuePairs.add(new BasicNameValuePair("zhujia", zhujia));
							nameValuePairs.add(new BasicNameValuePair("sex", sex));
							nameValuePairs.add(new BasicNameValuePair("client", "true"));
							nameValuePairs.add(new BasicNameValuePair("server", "true"));
							nameValuePairs.add(new BasicNameValuePair("condition", "待处理"));
							Log.d("hhhhhhhhh", pet);

							Log.d("hhhhhhhhh", text_jiaweijiage_text);
							Log.d("hhhhhhhhh", text_oldman_contact_text);
							Log.d("hhhhhhhhh", text_oldman_phone_text);
							Log.d("hhhhhhhhh", typename);
							Log.d("hhhhhhhhh", typeid);
							Log.d("hhhhhhhhh", userid);
							Log.d("hhhhhhhhh", orderid);
							Log.d("hhhhhhhhh", temp_other);
							Log.d("hhhhhhhhh", address_temp_other);
							Log.d("hhhhhhhhh", zili);
							Log.d("hhhhhhhhh", zhujia);
							Log.d("hhhhhhhhh", sex);

							btn_oldman_ok_cle = 0;
							for (btn_oldman_ok_cle = 0; btn_oldman_ok_cle < 1; btn_oldman_ok_cle = btn_oldman_ok_cle) {
								if (back_oldman_int == 1) {
									try {
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost(
												"http://www.sundaytek.com/insertorderoldman.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										// httppost.setEntity(new
										// UrlEncodedFormEntity(nameValuePairs));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();

										btn_oldman_ok_cle = 1;
										is = entity.getContent();

									} catch (Exception e) {
										Log.e("log_tag", "Error in http connection" + e.toString());
										btn_oldman_ok_cle = 0;
									}
								}
							}
							if (back_oldman_int == 1) {
								Message msg = new Message();
								msg.what = 1;
								msg.arg1 = 123;
								msg.arg2 = 321;
								uiHandler.sendMessage(msg);
							} else {
								btn_oldman_ok_delete_cle = 0;
								for (btn_oldman_ok_delete_cle = 0; btn_oldman_ok_delete_cle < 1; btn_oldman_ok_delete_cle = btn_oldman_ok_delete_cle) {
									try {

										nameValuePairs.add(new BasicNameValuePair("userid", userid));
										nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost(
												"http://www.sundaytek.com/deleteoldmanorder.php");
										httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
										HttpResponse response = httpclient.execute(httppost);
										HttpEntity entity = response.getEntity();
										is = entity.getContent();
										btn_oldman_ok_delete_cle = 1;
										Log.e("log_tag", "isme ");
									} catch (Exception e) {
										Log.d("log_tag", "Error in http connection" + e.toString());
										btn_oldman_ok_delete_cle = 1;
									}
								}
							}
						}

					}).start();
					btn_oldman_ok.setVisibility(View.GONE);
				}

			}
		});
		text_oldman_address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(oldman.this, address.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", address_temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 2);

			}

		});
		btn_oldman_self.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_self_i == 1) {

					btn_oldman_self.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_self_i = (-1) * btn_oldman_self_i;

				} else if (btn_oldman_self_i == -1) {

					btn_oldman_self.setBackgroundResource(R.drawable.bacno);

					btn_oldman_self_i = (-1) * btn_oldman_self_i;

				}

			}

		});
		btn_oldman_halfself.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_halfself_i == 1) {

					btn_oldman_halfself.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_halfself_i = (-1) * btn_oldman_halfself_i;

				} else if (btn_oldman_halfself_i == -1) {

					btn_oldman_halfself.setBackgroundResource(R.drawable.bacno);

					btn_oldman_halfself_i = (-1) * btn_oldman_halfself_i;

				}

			}

		});
		btn_oldman_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_no_i == 1) {

					btn_oldman_no.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_no_i = (-1) * btn_oldman_no_i;

				} else if (btn_oldman_no_i == -1) {

					btn_oldman_no.setBackgroundResource(R.drawable.bacno);

					btn_oldman_no_i = (-1) * btn_oldman_no_i;

				}

			}

		});
		oldman_otherdetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(oldman.this, oldmanedit.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				bundle.putString("something", temp); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);

				// resultCode---请求码
				// startActivityForResult(intent, 1);

			}

		});
		text_oldman_serverscon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(oldman.this, oldmanserverscon.class);
				// resultCode---请求码
				startActivity(intent);

			}

		});
		btn_oldman_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(oldman.this);
				alertDialog.setTitle("看护老人");
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
						back_oldman_int = 0;
						finish();
					}
				});
				alertDialog.show();

			}

		});

		btn_oldman_bigdog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_bigdog_i == 1) {

					btn_oldman_bigdog.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_bigdog_i = (-1) * btn_oldman_bigdog_i;

				} else if (btn_oldman_bigdog_i == -1) {

					btn_oldman_bigdog.setBackgroundResource(R.drawable.bacno);

					btn_oldman_bigdog_i = (-1) * btn_oldman_bigdog_i;

				}

			}

		});
		btn_oldman_smalldog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_smalldog_i == 1) {

					btn_oldman_smalldog.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_smalldog_i = (-1) * btn_oldman_smalldog_i;

				} else if (btn_oldman_smalldog_i == -1) {

					btn_oldman_smalldog.setBackgroundResource(R.drawable.bacno);

					btn_oldman_smalldog_i = (-1) * btn_oldman_smalldog_i;

				}

			}

		});
		btn_oldman_cat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_cat_i == 1) {

					btn_oldman_cat.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_cat_i = (-1) * btn_oldman_cat_i;

				} else if (btn_oldman_cat_i == -1) {

					btn_oldman_cat.setBackgroundResource(R.drawable.bacno);

					btn_oldman_cat_i = (-1) * btn_oldman_cat_i;

				}

			}

		});
		btn_oldman_zhujia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_zhujia_i == 1) {

					btn_oldman_zhujia.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_zhujia_i = (-1) * btn_oldman_zhujia_i;

				} else if (btn_oldman_zhujia_i == -1) {

					btn_oldman_zhujia.setBackgroundResource(R.drawable.bacno);

					btn_oldman_zhujia_i = (-1) * btn_oldman_zhujia_i;

				}

			}

		});
		btn_oldman_buzhujia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_buzhujia_i == 1) {

					btn_oldman_buzhujia.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_buzhujia_i = (-1) * btn_oldman_buzhujia_i;

				} else if (btn_oldman_buzhujia_i == -1) {

					btn_oldman_buzhujia.setBackgroundResource(R.drawable.bacno);

					btn_oldman_buzhujia_i = (-1) * btn_oldman_buzhujia_i;

				}

			}

		});
		btn_oldman_boy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_boy_i == 1) {

					btn_oldman_boy.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_boy_i = (-1) * btn_oldman_boy_i;

				} else if (btn_oldman_boy_i == -1) {

					btn_oldman_boy.setBackgroundResource(R.drawable.bacno);

					btn_oldman_boy_i = (-1) * btn_oldman_boy_i;

				}

			}

		});
		btn_oldman_girl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (btn_oldman_girl_i == 1) {

					btn_oldman_girl.setBackgroundResource(R.drawable.bacyes);

					btn_oldman_girl_i = (-1) * btn_oldman_girl_i;

				} else if (btn_oldman_girl_i == -1) {

					btn_oldman_girl.setBackgroundResource(R.drawable.bacno);

					btn_oldman_girl_i = (-1) * btn_oldman_girl_i;

				}

			}

		});
	}

	protected void go() {
		// TODO Auto-generated method stub
		Intent intentorder = new Intent(oldman.this, oldmanorder.class);
		// resultCode---请求码
		Bundle bundle = new Bundle(); // 创建Bundle对象
		bundle.putString("orderid", orderid); // 装入数据
		bundle.putString("userid", userid); // 装入数据
		intentorder.putExtras(bundle);
		startActivityForResult(intentorder, 3);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case R.id.seekbar_oldman_xinlijiawei: {
			// 璁剧疆鈥滀笌绯荤粺榛樿SeekBar瀵瑰簲鐨凾extView鈥濈殑鍊�

			int i = seekBar.getProgress();
			if (i >= 0 & i <= 34) {
				text_oldman_jiaweijiage.setText("3k-4k(每月)");
			}
			if (i > 34 & i <= 67) {
				text_oldman_jiaweijiage.setText("4k-5k(每月)");
			}
			if (i > 67 & i <= 100) {
				text_oldman_jiaweijiage.setText("5k-6k(每月)");
			}
			break;
		}

		default:
			break;
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		String otherneed = bundle.getString("otherneed");
		switch (requestCode) {
		case 1:

			oldman_otherdetail.setText(otherneed);
			temp_other = otherneed;
			temp = otherneed;
			break;
		case 2:
			String address = bundle.getString("address");
			text_oldman_address.setText(address);
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
				btn_oldman_ok.setVisibility(View.VISIBLE);
				Intent intent = new Intent();
				intent.putExtra("staues", true);
				setResult(0, intent);
				finish();
			} else {
				Log.e("log_tag", "false ");
				btn_oldman_ok.setVisibility(View.VISIBLE);
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
}
