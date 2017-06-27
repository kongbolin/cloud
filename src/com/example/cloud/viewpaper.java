package com.example.cloud;

import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;
//package com.example.cloud;

import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
//import com.example.baidudemo.Location;
import com.example.cloud.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class viewpaper extends Activity implements OnClickListener {
	private ViewPager viewpager;
	private testadapter adapter;
	private LinearLayout viewGroup;
	private ArrayList<View> list;
	private ImageView dot, dots[];
	private int h = 0;
	private SharedPreferences pre;
	private Button btn_nice;
	private Button btn_baojie;
	private Button btn_zhongdiangong;
	private Button btn_baomu;
	private Button btn_yuer;
	private Button btn_oldman;
	private Button btn_yuesao;
	private Button btn_yuesao1;
	private Button btn_yuesao2;
	private Button me;
	private Button order;
	private Button firstpage;
	private Button servers;
	private Button exist;
	private Runnable runnable;
	private int f = 0;
	private int o = 1;
	private int m = 1;
	private int autoChangeTime = 5000;
	private int memory = 0;
	private LinearLayout dotoc;
	private int width1;
	private int width2;
	private int heigth1;
	private int heigth2;
	private String username;
	private String message;
	private TextView text_city;
	TextView textView1;
	private LocationClient mLocClient;
	String str1;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(viewpaper.this);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.d("log_tagvvvvvfewwrvv", "true ");
		if (data == null)
			return;
		Log.d("log_tagvvvvvvrerrrev", "true ");
		Bundle bundle = data.getExtras();
		switch (requestCode) {
		case 1:
			boolean staues = bundle.getBoolean("staues");
			if (staues) {
				Log.d("log_tagvvvvvvv", "true ");
				sendmessage();
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("临时保洁订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "baojie"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		case 2:
			boolean staues2 = bundle.getBoolean("staues");
			if (staues2) {
				Log.d("log_tagvvvvvvv", "true ");
				sendmessage();
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("钟点工订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "zhongdiangong"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		case 3:
			boolean staues3 = bundle.getBoolean("staues");
			if (staues3) {
				Log.d("log_tagvvvvvvv", "true ");
				sendmessage();
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("住家保姆订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "baomu"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		case 4:
			boolean staues4 = bundle.getBoolean("staues");
			if (staues4) {
				Log.d("log_tagvvvvvvv", "true ");
				sendmessage();
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("育儿嫂订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "yuer"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		case 5:
			boolean staues5 = bundle.getBoolean("staues");
			if (staues5) {
				Log.d("log_tagvvvvvvv", "true ");
				sendmessage();
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("看护老人订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "oldman"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		case 6:
			boolean staues6 = bundle.getBoolean("staues");

			if (staues6) {
				Log.d("log_tagvvvvvvv", "true ");
				// btn_baojie_ok.setVisibility(View.VISIBLE);
				// (new Thread() {
				//
				// public void run() {
				//
				// message = "1111";
				// SDKTestSendTemplateSMS.main(message, username);
				// }
				//
				// }).start();
				sendmessage();
				AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
				AlertDialog.Builder settingDialog = new AlertDialog.Builder(viewpaper.this);
				settingDialog.setInverseBackgroundForced(true);
				alertDialog.setTitle("月嫂服务订单");
				alertDialog.setMessage("下单成功，是否去查看？");
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "现在去查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentorder = new Intent(viewpaper.this, order.class);
						Bundle bundleorder = new Bundle(); // 创建Bundle对象
						bundleorder.putString("ordertype", "yuesao"); // 装入数据
						// bundleorder.putString("userid", userid); // 装入数据
						intentorder.putExtras(bundleorder);
						startActivityForResult(intentorder, 7);
						finish();
					}
				});
				alertDialog.show();
			} else {
				Log.d("log_tag", "false ");
			}

			break;
		default:
			break;
		}
	}

	private void sendmessage() {
		// TODO Auto-generated method stub
		(new Thread() {

			public void run() {

				message = "1161";
				SDKTestSendTemplateSMS.main(message, username);
			}

		}).start();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.first);？
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first);
		read();
		text_city = (TextView) findViewById(R.id.text_city);
		text_city.setText("xian");
		// mLocClient = ((Location) getApplication()).mLocationClient;
		// text_city = (TextView) findViewById(R.id.text_city);
		// ((Location) getApplication()).mTv = text_city;
		// setLocationOption();
		// mLocClient.start();
		// mLocClient.requestLocation();
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		width1 = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		heigth1 = (int) (width1 / 2.267);
		FrameLayout.LayoutParams layoutParams1 = (android.widget.FrameLayout.LayoutParams) viewpager.getLayoutParams();
		layoutParams1.height = heigth1;
		viewpager.setLayoutParams(layoutParams1);
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
		FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) viewGroup.getLayoutParams();
		layoutParams.topMargin = heigth1 - 40;// 将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
		viewGroup.setLayoutParams(layoutParams);
		Context ctx = viewpaper.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		memory = sp.getInt("INT_KEY", 0);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		final String[] stringArray = getResources().getStringArray(R.array.stringArray);
		String temp;
		temp = stringArray[0];
		stringArray[0] = stringArray[memory];
		stringArray[memory] = temp;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				stringArray);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				int index = arg0.getSelectedItemPosition(); // 选中的pos
				Context ctx = viewpaper.this;
				SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
				// 存入数据
				Editor editor = sp.edit();
				// editor.putString("STRING_KEY", "string");
				Log.d("SP", stringArray[index]);
				if (index != 0) {
					editor.putInt("INT_KEY", index);
					editor.commit();
				}
				Toast.makeText(getBaseContext(), "SELECT: " + stringArray[index], Toast.LENGTH_SHORT).show();

			}

			//
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		btn_baojie = (Button) findViewById(R.id.btn_baojie);
		btn_zhongdiangong = (Button) findViewById(R.id.btn_zhongdiangong);
		btn_baomu = (Button) findViewById(R.id.btn_baomu);
		btn_yuer = (Button) findViewById(R.id.btn_yuer);
		btn_oldman = (Button) findViewById(R.id.btn_oldman);
		btn_yuesao = (Button) findViewById(R.id.btn_yuesao);
		btn_yuesao1 = (Button) findViewById(R.id.btn_yuesao1);
		btn_yuesao2 = (Button) findViewById(R.id.btn_yuesao2);
		me = (Button) findViewById(R.id.me);
		servers = (Button) findViewById(R.id.servers);
		order = (Button) findViewById(R.id.older);
		exist = (Button) findViewById(R.id.finish);
		firstpage = (Button) findViewById(R.id.firstpage);
		btn_baojie.setOnClickListener(this);
		btn_zhongdiangong.setOnClickListener(this);
		btn_baomu.setOnClickListener(this);
		btn_yuer.setOnClickListener(this);
		btn_oldman.setOnClickListener(this);
		btn_yuesao.setOnClickListener(this);
		btn_yuesao1.setOnClickListener(this);
		btn_yuesao2.setOnClickListener(this);
		me.setOnClickListener(this);
		servers.setOnClickListener(this);
		exist.setOnClickListener(this);
		order.setOnClickListener(this);
		firstpage.setOnClickListener(this);
		initViewPager();
	}

	private void initViewPager() {
		adapter = new testadapter(this);
		adapter.change(getList());

		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(myOnPageChangeListener);
		viewpager.setOnClickListener(this);
		// viewpager.setOnClickListener(onClick);
		initDot();

		runnable = new Runnable() {
			@Override
			public void run() {
				int next = viewpager.getCurrentItem() + 1;
				if (next >= adapter.getCount()) {
					next = 0;
				}
				viewHandler.sendEmptyMessage(next);
			}
		};
		viewHandler.postDelayed(runnable, autoChangeTime);
	}

	private List<Integer> getList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(R.drawable.pictureone);
		list.add(R.drawable.picturetwo);
		list.add(R.drawable.picturethree);
		list.add(R.drawable.picturefour);

		return list;
	}

	// 初始化dot视图
	private void initDot() {
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
		layoutParams.setMargins(4, 3, 4, 3);

		dots = new ImageView[adapter.getCount()];
		for (int i = 0; i < adapter.getCount(); i++) {
			dot = new ImageView(this);

			dot.setLayoutParams(layoutParams);
			dots[i] = dot;
			dots[i].setTag(i);
			dots[i].setOnClickListener(onClick);

			if (i == 0) {
				dots[i].setBackgroundResource(R.drawable.dotc);
			} else {
				dots[i].setBackgroundResource(R.drawable.dotn);
			}

			viewGroup.addView(dots[i]);
		}
	}

	OnPageChangeListener myOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			setCurDot(arg0);
			next(arg0);

			viewHandler.removeCallbacks(runnable);
			viewHandler.postDelayed(runnable, autoChangeTime);
		}

	};

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_baojie:
			Intent intentorder = new Intent(viewpaper.this, baojie.class);
			Bundle bundle = new Bundle(); // 创建Bundle对象
			intentorder.putExtras(bundle);
			startActivityForResult(intentorder, 1);
			break;
		case R.id.btn_zhongdiangong:
			Intent intentzhongdiangong = new Intent(viewpaper.this, zhongdiangong.class);
			Bundle bundlezhongdiangong = new Bundle(); // 创建Bundle对象
			intentzhongdiangong.putExtras(bundlezhongdiangong);
			startActivityForResult(intentzhongdiangong, 2);
			break;
		case R.id.btn_baomu:
			Intent intentbaomu = new Intent(viewpaper.this, baomu.class);
			Bundle bundlebaomu = new Bundle(); // 创建Bundle对象
			intentbaomu.putExtras(bundlebaomu);
			startActivityForResult(intentbaomu, 3);
			break;
		case R.id.btn_yuer:
			Intent intentyuer = new Intent(viewpaper.this, yuer.class);
			Bundle bundleyuer = new Bundle(); // 创建Bundle对象
			intentyuer.putExtras(bundleyuer);
			startActivityForResult(intentyuer, 4);
			break;
		case R.id.btn_oldman:
			Intent intentoldman = new Intent(viewpaper.this, oldman.class);
			Bundle bundleoldman = new Bundle(); // 创建Bundle对象
			intentoldman.putExtras(bundleoldman);
			startActivityForResult(intentoldman, 5);
			break;
		case R.id.btn_yuesao:
			Intent intentyuesao = new Intent(viewpaper.this, yuesao.class);
			Bundle bundleyuesao = new Bundle(); // 创建Bundle对象
			intentyuesao.putExtras(bundleyuesao);
			startActivityForResult(intentyuesao, 6);
			break;
		case R.id.btn_yuesao1:
			Intent intentyuesao1 = new Intent(viewpaper.this, yuesao.class);
			Bundle bundleyuesao1 = new Bundle(); // 创建Bundle对象
			intentyuesao1.putExtras(bundleyuesao1);
			startActivityForResult(intentyuesao1, 6);
			break;
		case R.id.btn_yuesao2:
			Intent intentyuesao2 = new Intent(viewpaper.this, yuesao.class);
			Bundle bundleyuesao2 = new Bundle(); // 创建Bundle对象
			intentyuesao2.putExtras(bundleyuesao2);
			startActivityForResult(intentyuesao2, 6);
			break;
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
				Intent intentolder = new Intent(viewpaper.this, order.class);
				Bundle bundleolder = new Bundle(); // 创建Bundle对象
				bundleolder.putString("ordertype", "baojie"); // 装入数据
				// bundleorder.putString("userid", userid); // 装入数据
				intentolder.putExtras(bundleolder);
				startActivityForResult(intentolder, 7);
				f = 1;
				m = 1;
				o = 0;
				finish();
			}

			break;
		case R.id.servers:

			AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
			// alertDialog.setIcon(R.drawable.desert);
			// alertDialog.setTitle("System Information:");
			AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
			settingDialog.setInverseBackgroundForced(true);
			alertDialog.setMessage("10086");
			// 添加取消按钮
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(viewpaper.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
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
			alertDialog.show();
			break;
		case R.id.finish:
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

	private void next(int position) {
		// TODO Auto-generated method stub
		for (int i = 0; i < dots.length; i++) {
			if (position == i) {

				h = i;
			}
		}
	}

	public Object instantiateItem(ViewGroup container, final int position) {
		// 为每页添加点击监听，初始化音乐并点击时播放，并保证每次点击都可以重新播放
		switch (position) {
		case 0:
			list.get(0).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d("SP12354656", "555");

				}
			});
			break;
		default:
			break;
		}
		((ViewPager) container).addView(list.get(position % list.size()), 0);
		return list.get(position % list.size());
	}

	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			setCurView(position);
		}

	};

	private void setCurView(int position) {
		if (position < 0 || position > adapter.getCount()) {
			return;
		}
		viewpager.setCurrentItem(position);
	}

	private void setCurDot(int position) {
		for (int i = 0; i < dots.length; i++) {
			if (position == i) {
				dots[i].setBackgroundResource(R.drawable.dotc);
			} else {
				dots[i].setBackgroundResource(R.drawable.dotn);
			}
		}
	}

	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			setCurView(msg.what);
		}
	};

	private void read() {
		// TODO Auto-generated method stub
		// username = pre.getString("username", "");
		username = "18710861689";
		// password = pre.getString("password", "");
		// page = pre.getString("page", "");
		// Log.d("username", username);
		// Log.d("password", password);
	}

	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd009ll"); // 设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo(true);

		option.setAddrType("all");

		option.setScanSpan(3000);

		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先

		option.setPoiNumber(10);
		option.disableCache(true);
		mLocClient.setLocOption(option);
//		str1 = Location.str1;
		text_city.setText(str1);
	}
}
